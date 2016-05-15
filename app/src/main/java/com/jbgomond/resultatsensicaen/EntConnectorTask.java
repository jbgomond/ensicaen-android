package com.jbgomond.resultatsensicaen;

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
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.net.ssl.HttpsURLConnection;

public class EntConnectorTask extends AsyncTask<String, String, String> {

    @Override
    protected String doInBackground(String... params) {
        try {
            String url = "https://cas.ensicaen.fr/cas/login?service=https://shibboleth.ensicaen.fr/idp/Authn/RemoteUser";
            String userAgent = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2661.94 Safari/537.36";

            // Get lt from the form
            Connection.Response initialResponse = Jsoup.connect(url).userAgent(userAgent).method(Connection.Method.GET).execute();
            Document doc = initialResponse.parse();
            Element ltInput = doc.select("input[name=lt]").first();
            String ltVal = ltInput.attr("value");

            // Get execution from the form
            Element executionInput = doc.select("input[name=execution]").first();
            String executionVal = executionInput.attr("value");

            // Get cookies
            Map<String, String> cookies = initialResponse.cookies();

            Connection.Response res = Jsoup.connect(url)
                    .followRedirects(true)
                    .cookies(initialResponse.cookies())
                    .data("username", params[0])
                    .data("password", params[1])
                    .data("execution", executionVal)
                    .data("_eventId", "submit")
                    .data("lt", ltVal)
                    .data("submit", "SE CONNECTER")
                    .userAgent(userAgent)
                    .header("Content-Type","application/x-www-form-urlencoded;charset=UTF-8")
                    .method(Connection.Method.POST)
                    .execute();

            System.out.println(res.url());
            System.out.println(res.cookies());
            System.out.println(res.parse().toString());

            cookies.putAll(res.cookies());

            /*doc = null;
            try {
                doc = Jsoup.connect("https://ent.normandie-univ.fr/uPortal/f/u17l1s9/normal/render.uP")
                        .cookies(cookies)
                        .get();
                System.out.println(doc);
            } catch (IOException e) {
                e.printStackTrace();
            }*/



            /*URL url = new URL(url);
            HttpsURLConnection urlConnection = (HttpsURLConnection) url.openConnection();

            //Get Response
            InputStream is = urlConnection.getInputStream();
            BufferedReader rd = new BufferedReader(new InputStreamReader(is));
            String line;
            StringBuffer response = new StringBuffer();
            while((line = rd.readLine()) != null) {

                System.out.println(line);
                response.append(line);
                response.append('\r');
            }
            rd.close();

            // Get token CAS*/


            // POST
            /*String postParameters = "username=" + URLEncoder.encode(params[0], "UTF-8") +
                    "&password=" + URLEncoder.encode(params[1], "UTF-8") +
                    "&execution=e1s1" +
                    "&_eventId=submit" +
                    "&lt=" + lt +
                    "&submit=" + URLEncoder.encode("SE CONNECTER", "UTF-8");
            urlConnection.setRequestMethod("POST");
            urlConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            urlConnection.setRequestProperty("Content-Length", "" + Integer.toString(postParameters.getBytes().length));
            urlConnection.setRequestProperty("Content-Language", "fr-FR");

            urlConnection.setUseCaches(false);
            urlConnection.setDoInput(true);
            urlConnection.setDoOutput(true);

            //Send request
            DataOutputStream outputStream = new DataOutputStream(urlConnection.getOutputStream());
            outputStream.writeBytes(postParameters);
            outputStream.flush();
            outputStream.close();

            //Get Response
            InputStream is = urlConnection.getInputStream();
            BufferedReader rd = new BufferedReader(new InputStreamReader(is));
            String line;
            StringBuffer response = new StringBuffer();
            while((line = rd.readLine()) != null) {

                System.out.println(line);
                response.append(line);
                response.append('\r');
            }
            rd.close();*/

            return "";//response.toString();
        } catch(IOException e) {
            System.out.println("IO Error");
        }

        return null;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        System.out.println(s);
    }
}
