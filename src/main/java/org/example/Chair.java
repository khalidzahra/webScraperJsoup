package org.example;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Chair {
    String name;
    String role;
    String email;
    public Chair(String name, String role, String email) {
        this.name = name;
        this.role = role;
        this.email = email;
    }

}
