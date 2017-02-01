package com.jbgomond.resultatsensicaen;

import android.net.UrlQuerySanitizer;
import android.os.AsyncTask;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.net.ssl.HttpsURLConnection;

public class EntConnectorTask extends AsyncTask<String, String, String> {

    @Override
    protected String doInBackground(String... params) {
        Connection.Response res1;
        Connection.Response res2;
        Connection.Response res3;
        Connection.Response res4;
        Map<String, String> cookies;

        try {
            // AUTH 1
            res1 = Jsoup.connect("http://ent.normandie-univ.fr")
                    .method(Connection.Method.POST)
                    .header("Content-Type", "application/x-www-form-urlencoded; charset=utf-8")
                    .execute();

            cookies = res1.cookies();

            // get action
            Document doc = res1.parse();
            Element loginForm = doc.select("form").first();
            String actionUrl = URLDecoder.decode(loginForm.attr("action"), "UTF-8");

            // Récupération de l'identifiant de cookie
            UrlQuerySanitizer sanitizer = new UrlQuerySanitizer(actionUrl);
            String cookieId = sanitizer.getValue("target"); // get your value

            // AUTH 2
            res2 = Jsoup.connect("http://wayf.normandie-univ.fr/WAYF.php?entityID=" + URLEncoder.encode("https://ent.normandie-univ.fr", "UTF-8") + "&return=" + URLEncoder.encode("https://ent.normandie-univ.fr/Shibboleth.sso/WAYF?SAMLDS=1&target=" + cookieId, "UTF-8"))
                    .method(Connection.Method.POST)
                    .header("Content-Type", "application/x-www-form-urlencoded; charset=utf-8")
                    .data("user_idp", "https://shibboleth.ensicaen.fr/idp/shibboleth")
                    .cookies(cookies)
                    .execute();

            cookies.putAll(res2.cookies());

            // get lt
            Document doc2 = res2.parse();
            Element lt = doc2.select("input[name=lt]").first();
            String ltVal = lt.attr("value");

            String jsessionId = cookies.get("JSESSIONID");

            System.out.println("RES 2 : " + res2);
            System.out.println("jsessionId : " + jsessionId);

            res3 = Jsoup.connect("https://cas.ensicaen.fr/cas/login;jsessionid=" + jsessionId + "?service=https://shibboleth.ensicaen.fr/idp/Authn/RemoteUser")
                    .method(Connection.Method.POST)
                    .header("Content-Type", "application/x-www-form-urlencoded; charset=utf-8")
                    .cookies(cookies)
                    .data("username", "")
                    .data("password", "")
                    .data("lt", ltVal)
                    .data("execution", "e1s1")
                    .data("_eventId", "submit")
                    .execute();

            cookies.putAll(res3.cookies());

            // get lt
            Document doc3 = res3.parse();
            Element SAMLResponse = doc3.select("input[name=SAMLResponse]").first();
            String SAMLResponseVal = SAMLResponse.attr("value");

            res4 = Jsoup.connect("https://ent.normandie-univ.fr/Shibboleth.sso/SAML2/POST")
                    .method(Connection.Method.POST)
                    .header("Content-Type", "application/x-www-form-urlencoded; charset=utf-8")
                    .cookies(cookies)
                    .data("RelayState", cookieId)
                    .data("SAMLResponse", SAMLResponseVal)
                    .execute();

            cookies.putAll(res4.cookies());

            System.out.println("RES 4 : " + res4.parse());

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        System.out.println(s);
    }
}
