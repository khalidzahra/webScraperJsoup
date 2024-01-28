package org.example;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Extractor {
    
    public List<Chair> getChairs(String url){
        List<Chair> chairs = new ArrayList<>();

        Document document = getDocument(url);
        if (document == null) return chairs;

        String role, name = "", email = "";

        for (Element tr : document.select("table.table>tbody>tr")) {
            if (tr.children().isEmpty() || !tr.children().first().hasText() || !tr.children().first().text().toLowerCase().contains("chair")) {
                continue;
            }
            role = tr.child(0).text();
            Element facultyDetailsElement = tr.child(1);
            for (Element child : facultyDetailsElement.children()) {
                if (child.tagName().equals("strong")) {
                    name = child.select("a").text();
                } else if (child.tagName().equals("a") && child.attr("abs:href").startsWith("mailto")) {
                    email = child.text();
                }
            }
            chairs.add(new Chair(name, role, email));
        }

        return chairs;
    }

    public List<ResearchLab> findResearchLabs(String url) {
        List<ResearchLab> researchLabs = new ArrayList<>();

        Document document = getDocument(url);
        if (document == null) return researchLabs;

        Element labList = document.select("div.row>div.col-12.col-sm>ul").first();
        labList.select("li>a").forEach(lab -> researchLabs.add(new ResearchLab(lab.text(), lab.attr("abs:href"))));

        return researchLabs;
    }

    /**
     * Uses Jsoup to connect to the given url
     *
     * @param url String parameter containing page URL
     * @return Returns Document if url is valid, otherwise null
     */
    private Document getDocument(String url) {
        try {
            return Jsoup.connect(url).get();
        } catch (IOException e) {
            return null;
        }
    }

}
