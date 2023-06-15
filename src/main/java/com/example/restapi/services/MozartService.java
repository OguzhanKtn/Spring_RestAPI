package com.example.restapi.services;

import com.example.restapi.models.Item;
import com.example.restapi.models.MusicCategory;
import com.example.restapi.models.MusicCategoryList;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class MozartService {

    public MusicCategoryList result(){

        Map<Integer,String> hmTitle = new HashMap<>();
        List<Item> itemList = new ArrayList<>();

        String baseUrl = "http://yiboyoung.com/music/mozart/mozart_complete/1EARLIERSYMPHONIES/";
        try{
            String url = baseUrl + "EARLYSYMPHONIES.htm";
            Document doc = Jsoup.connect(url).timeout(15000).ignoreContentType(true).get();
            Elements fonts = doc.getElementsByTag("font");
            int countTitle = 0;
            for(Element item : fonts){

              /* Elements elements = item.getElementsByAttributeValue("size","+2");
               for(Element itm : elements){
                   System.out.println(itm);
               }*/

                String title = item.toString();
                if(!title.contains("a href=")){
                    String header = item.text().toString();
                    if(!header.isEmpty()){
                       hmTitle.put(countTitle,header);
                       countTitle++;
                    }
                }else{
                    String subTitle = item.text().toString();
                    if(!subTitle.isEmpty()){
                        String href = baseUrl + item.getAllElements().attr("href");
                        Item item1 = new Item();
                        item1.setBaseCat(countTitle);
                        item1.setTitle(subTitle);
                        item1.setUrl(href);
                        itemList.add(item1);

                    }
                }

            }

        }catch (Exception ex){
            System.err.println("Mozart Error : "+ex);
        }
        MusicCategoryList musicCategoryList = new MusicCategoryList();
        musicCategoryList.setMusicCategories(parseData(hmTitle,itemList));
        return musicCategoryList;
    }

    public List<MusicCategory> parseData(Map<Integer,String> hmTitle, List<Item> items){

        Set<Integer> keys = hmTitle.keySet();
        List<MusicCategory> musicCategories = new ArrayList<>();

        for(Integer key : keys){
            MusicCategory musicCategory = new MusicCategory();
            musicCategory.setBaseTitle(hmTitle.get(key));
            List<Item> itemList = new ArrayList<>();
            for(Item item : items){
               if(item.getBaseCat() -1 == key){
                   itemList.add(item);
               }
            }
            musicCategory.setItems(itemList);
            musicCategories.add(musicCategory);
        }

        return musicCategories;

    }
}
