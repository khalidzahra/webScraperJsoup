package org.example;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Extractor {
    public List<Chair> getChairs(String url){
        List<Chair> chairs = new ArrayList<>();
        try {
            Document doc = Jsoup.connect(url).get();
            Elements people = doc.select("tr");
            String role, name, email;
            for (Element individual : people) {
                String title = individual.select("td").text();
                boolean chair = title.toLowerCase().contains("chair"); // Check if their affiliation contains the word "Chair" or not.
                if (chair) {
                    name = individual.select("tbody > tr > td > strong").text();
                    role = individual.child(0).text();
                    email = individual.select("tbody > tr > td > a").text();
                    chairs.add(new Chair(name, role, email));
                }
            }
        } catch (IOException e) {
            return null;
        }
        return chairs;
    }
    public List<ResearchLab> findResearchLabs(String url) {
        List<ResearchLab> researchLabs = new ArrayList<>();
        try {
            Document doc = Jsoup.connect(url).get();
            Elements books = doc.select(".col-12.r, .col-sm > ul > li > a");
            String labName;
            String labLink;
            for (Element book : books) {
                labName = book.text();
                labLink = book.attr("abs:href");
                researchLabs.add(new ResearchLab(labName, labLink));
            }
        } catch (IOException e) {
            return null;
        }
        return researchLabs;
    }

}
