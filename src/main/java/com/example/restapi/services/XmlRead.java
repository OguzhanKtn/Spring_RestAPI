package com.example.restapi.services;

import com.example.restapi.models.Catalog;
import com.example.restapi.models.Currency;
import com.example.restapi.models.Plant;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Parser;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class XmlRead {

    public List<Currency> result(){
        List<Currency> ls = new ArrayList<>();
        try{
            String url = "https://www.tcmb.gov.tr/kurlar/today.xml";
            String stData = Jsoup.connect(url).timeout(15000).ignoreContentType(true).get().toString();
            Document doc = Jsoup.parse(stData, Parser.xmlParser());
            Elements elements = doc.getElementsByTag("Currency");
            for(Element item : elements){
                String currencyName = item.getElementsByTag("CurrencyName").text();
                String forexBuying = item.getElementsByTag("ForexBuying").text();
                String forexSelling = item.getElementsByTag("ForexSelling").text();
                String banknoteBuying = item.getElementsByTag("BanknoteBuying").text();
                String banknoteSelling = item.getElementsByTag("BanknoteSelling").text();
                Currency c = new Currency(currencyName,forexBuying,forexSelling,banknoteBuying,banknoteSelling);
                ls.add(c);
            }
        }catch (Exception ex){
            System.err.println("Result Error : " + ex);
        }
        return ls;
    }

    public List<Catalog> catalog_result() throws IOException {
        List<Catalog> ls = new ArrayList<>();

        String url = "https://www.w3schools.com/xml/cd_catalog.xml";
        String stData = Jsoup.connect(url).timeout(10000).ignoreContentType(true).get().toString();
        Document doc = Jsoup.parse(stData,Parser.xmlParser());
        Elements elements = doc.getElementsByTag("CD");
        for(Element item : elements){
            String title = item.getElementsByTag("title").text();
            String artist = item.getElementsByTag("ARTIST").text();
            String country = item.getElementsByTag("COUNTRY").text();
            String company = item.getElementsByTag("COMPANY").text();
            String price = item.getElementsByTag("PRICE").text();
            String year = item.getElementsByTag("YEAR").text();

            Catalog catalog = new Catalog(title,artist,country,company,price,year);
            ls.add(catalog);

        }
        return ls;
    }

    public List<Plant> plant_result() throws IOException {
        List<Plant> ls = new ArrayList<>();

        String url = "https://www.w3schools.com/xml/plant_catalog.xml";
        String stData = Jsoup.connect(url).timeout(10000).ignoreContentType(true).get().toString();
        Document doc = Jsoup.parse(stData,Parser.xmlParser());
        Elements elements = doc.getElementsByTag("PLANT");

        for(Element item : elements){
            String common = item.getElementsByTag("COMMON").text();
            String botanical = item.getElementsByTag("BOTANICAL").text();
            String zone = item.getElementsByTag("ZONE").text();
            String light = item.getElementsByTag("LIGHT").text();
            String price = item.getElementsByTag("PRICE").text();
            String availability = item.getElementsByTag("COMMON").text();

            Plant plant = new Plant(common,botanical,zone,light,price,availability);
            ls.add(plant);
        }
        return ls;
    }
}
