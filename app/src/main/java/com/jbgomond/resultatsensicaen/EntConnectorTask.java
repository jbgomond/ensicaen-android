package com.jbgomond.resultatsensicaen;

import android.net.UrlQuerySanitizer;
import android.os.AsyncTask;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

public class EntConnectorTask extends AsyncTask<String, String, String> {

    private final static String ENT_HOST = "https://ent.normandie-univ.fr";
    private Map<String, String> cookies = new HashMap<>();

    @Override
    protected String doInBackground(String... params) {
        try {
            // Connecting to ENT (Shibboleth) via the ENSICAEN CAS
            Document indexEntDoc = loginToEnt();

            // Retrieves the index page from the "My Web Folder" section
            Document myWebFolderDoc = accessMyWebFolderSection(indexEntDoc);

            // Retrieves the page with the list of trainings
            Document trainingsDoc = accessTrainingsPage(myWebFolderDoc);

            // Retrieves the page with the marks
            Document marksDoc = accessMarksPage(trainingsDoc);

            System.out.println(marksDoc);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * Connecting to ENT (Shibboleth) via the ENSICAEN CAS
     *
     * @return document corresponding to the ENT index
     * @throws IOException
     */
    private Document loginToEnt() throws IOException {
        // STEP 1 : Access ENT login index
        Connection.Response entLoginRes = Jsoup.connect(ENT_HOST)
                .method(Connection.Method.GET)
                .execute();

        cookies = entLoginRes.cookies();

        // Get action login
        Document entLogindoc = entLoginRes.parse();
        String actionUrl = URLDecoder.decode(entLogindoc.select("form").first().attr("action"), "UTF-8");

        // Get cookieId value from URL
        UrlQuerySanitizer sanitizer = new UrlQuerySanitizer(actionUrl);
        String cookieId = sanitizer.getValue("target"); // get your value

        // STEP 2 : Prepare login with the ENSICAEN CAS
        Connection.Response preCasLoginRes = Jsoup.connect("http://wayf.normandie-univ.fr/WAYF.php?entityID=" + URLEncoder.encode(ENT_HOST, "UTF-8") + "&return=" + URLEncoder.encode(ENT_HOST + "/Shibboleth.sso/WAYF?SAMLDS=1&target=" + cookieId, "UTF-8"))
                .method(Connection.Method.POST)
                .data("user_idp", "https://shibboleth.ensicaen.fr/idp/shibboleth")
                .cookies(cookies)
                .execute();

        cookies.putAll(preCasLoginRes.cookies());

        // Get lt value from hidden input in form
        Document preCasLoginResDoc = preCasLoginRes.parse();
        String ltVal = preCasLoginResDoc.select("input[name=lt]").first().attr("value");

        // Get JSESSIONID from cookies
        String jsessionId = cookies.get("JSESSIONID");

        // STEP 3 : Login with the ENSICAEN CAS
        Connection.Response loginCasRes = Jsoup.connect("https://cas.ensicaen.fr/cas/login;jsessionid=" + jsessionId + "?service=https://shibboleth.ensicaen.fr/idp/Authn/RemoteUser")
                .method(Connection.Method.POST)
                .cookies(cookies)
                .data("username", "")
                .data("password", "")
                .data("lt", ltVal)
                .data("execution", "e1s1")
                .data("_eventId", "submit")
                .execute();

        cookies.putAll(loginCasRes.cookies());

        // Get SAMLResponse value from hidden input in form
        Document doc3 = loginCasRes.parse();
        String samlResponseVal = doc3.select("input[name=SAMLResponse]").first().attr("value");

        Connection.Response redirectEnt = Jsoup.connect(ENT_HOST + "/Shibboleth.sso/SAML2/POST")
                .method(Connection.Method.POST)
                .cookies(cookies)
                .data("RelayState", cookieId)
                .data("SAMLResponse", samlResponseVal)
                .execute();

        cookies.putAll(redirectEnt.cookies());

        return redirectEnt.parse();
    }

    /**
     * Retrieves the index page from the "My Web Folder" section
     *
     * @param indexEntDoc document corresponding to the ENT index
     * @return document corresponding to the section index
     * @throws IOException
     */
    private Document accessMyWebFolderSection(Document indexEntDoc) throws IOException {
        System.out.println(indexEntDoc);
        Elements totos = indexEntDoc.select("ul[class=up-portal-nav] a");
        String myWebFolderUrl = indexEntDoc.select("ul[class=up-portal-nav] a[href*=esup-mondossierweb2]").first().attr("href");
        Connection.Response res = Jsoup.connect(ENT_HOST + myWebFolderUrl)
                .method(Connection.Method.GET)
                .cookies(cookies)
                .execute();

        cookies.putAll(res.cookies());

        return res.parse();
    }

    /**
     * Retrieves the page with the list of trainings
     *
     * @param myWebFolderDoc document corresponding to the index of the section
     * @return document corresponding to the page listing the trainings
     * @throws IOException
     */
    private Document accessTrainingsPage(Document myWebFolderDoc) throws IOException {
        String action = myWebFolderDoc.select("form[id=formMenu]").first().attr("action");
        String submit = myWebFolderDoc.select("input[name=formMenu_SUBMIT]").first().attr("value");
        String idcl = "formMenu:linknotes1";
        String linkHidden = myWebFolderDoc.select("input[name=formMenu:_link_hidden_]").first().attr("value");
        String viewState = myWebFolderDoc.select("input[name=javax.faces.ViewState]").first().attr("value");

        // Liste des formations suivies
        Connection.Response res = Jsoup.connect(ENT_HOST + action)
                .method(Connection.Method.POST)
                .cookies(cookies)
                .data("formMenu_SUBMIT", submit)
                .data("formMenu:_idcl", idcl)
                .data("formMenu:_link_hidden_", linkHidden)
                .data("javax.faces.ViewState", viewState)
                .execute();

        return res.parse();
    }

    /**
     * Retrieves the page with the marks
     *
     * @param trainingsDoc document corresponding to the page listing the trainings
     * @return document corresponding to the page with the marks
     * @throws IOException
     */
    private Document accessMarksPage(Document trainingsDoc) throws IOException {
        String actionMarks = trainingsDoc.select("form[id=_id145Pluto_436_u1240l1n131_294486_]").first().attr("action");
        Elements inputs = trainingsDoc.select("form[id=_id145Pluto_436_u1240l1n131_294486_] input");
        HashMap<String, String> inputsMap = new HashMap<>();
        for (Element input : inputs) {
            inputsMap.put(input.attr("name"), input.attr("value"));
        }

        // Ligne à récupérer
        inputsMap.put("row", "0");

        // Onclick infos
        String link = trainingsDoc.select("form[id=_id145Pluto_436_u1240l1n131_294486_] table a").get(0).attr("onclick");
        String idclMarks = link.split("'")[3];
        inputsMap.put("_id145Pluto_436_u1240l1n131_294486_:_idcl", idclMarks);

        // Liste des formations suivies
        Connection.Response res = Jsoup.connect("https://ent.normandie-univ.fr" + actionMarks)
                .method(Connection.Method.POST)
                .cookies(cookies)
                .data(inputsMap)
                .execute();

        return res.parse();
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        System.out.println(s);
    }
}
