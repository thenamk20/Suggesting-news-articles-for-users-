package com.thenam.project2server.utils;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Service;


import java.io.IOException;

@Service
public class ReadHTMLDomUtil {

    public Document handleReadHTML(String url) {
        Document doc = null;
        try {
            doc = (Document) Jsoup.connect(url).get();
        } catch (IOException e) {
            // TODO: handle exception
        }
        return doc;
    }

}
