package com.jbgomond.resultatsensicaen;

import android.os.AsyncTask;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

public class EducationalBookletConnectorTask extends AsyncTask<String, String, Document> {

    private ArrayList<ResultsUE> ueResults = new ArrayList<>();
    private ResultsActivity activity;

    public EducationalBookletConnectorTask(ResultsActivity activity) {
        this.activity = activity;
    }

    @Override
    protected Document doInBackground(String... params) {
        try {
            String url = "http://livretpedagogique.ensicaen.fr/pages/afficherSpecialite.php?numSpecialite=" + params[0];

            // Get lt from the form
            Connection.Response res = Jsoup.connect(url).method(Connection.Method.GET).execute();

            return res.parse();
        } catch(IOException e) {
            System.out.println("IO Error");
        }

        return null;
    }

    @Override
    protected void onPostExecute(Document doc) {
        super.onPostExecute(doc);

        Training training = new Training();
        training.setName(doc.select("h2").first().text());
        training.setManager(doc.select("h3").first().text());

        int nSemester = 0;
        for (Element table : doc.select("table[class=tableau_affichage_specialite]")) {
            nSemester++;
            Semester semester = new Semester(nSemester);
            CategoryUE categoryUE = new CategoryUE("");
            ResultsUE ueResult = new ResultsUE();
            for (Element tr : table.select("tr")) {
                Elements tds = tr.select("td");

                if (tds.isEmpty() || !tds.hasText()) { // Header <tr> with only <th>s
                    continue;
                } else if (tds.size() == 1) {
                    categoryUE = new CategoryUE(tds.get(0).text());
                    semester.getCategoriesUE().add(categoryUE);
                    continue;
                }

                if (tds.get(0).hasClass("couleurMajeure1")) {
                    ueResult = new ResultsUE();
                    ueResult.setCode(tds.get(0).select("b").get(0).text());
                    ueResult.setName(tds.get(0).select("b").get(1).text());
                    ueResult.setMinimalAverage(10); // TODO Récupérer la moyenne minimale
                    tds.remove(0); // Delete category td
                }

                if (tr.hasClass("ligne_sous_total")) {
                    ueResult.setEcts(Integer.parseInt(tds.get(4).text()));
                } else {
                    ResultsSubject resultsSubject = new ResultsSubject();
                    resultsSubject.setName(tds.get(0).text());
                    resultsSubject.setCode(tds.get(1).text());
                    resultsSubject.setNbHoursCours(tds.get(2).text());
                    resultsSubject.setNbHoursTD(tds.get(3).text());
                    resultsSubject.setNbHoursTP(tds.get(4).text());
                    resultsSubject.setCoefficientCours(tds.get(5).text());
                    resultsSubject.setCoefficientPartiel(tds.get(6).text());
                    resultsSubject.setCoefficientTP(tds.get(7).text());
                    ueResult.getResultsSubject().add(resultsSubject);
                }
                categoryUE.getResultsUE().add(ueResult);
            }
        }

        //semester.addAll(notifications);
        this.activity.resultsAdapter.notifyDataSetChanged();
    }
}
