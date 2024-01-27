package org.example;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Extractor {
    public List<Chair> getChairs(String url){

        String role, name, email;
        List<Chair> chairs = new ArrayList<>();
        System.out.println("Sample Change");
        return chairs;
    }
    public List<ResearchLab> findResearchLabs(String url) {

        List<ResearchLab> researchLabs = new ArrayList<>();
        //todo
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
