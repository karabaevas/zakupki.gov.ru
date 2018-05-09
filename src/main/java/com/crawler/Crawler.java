package com.crawler;

import com.model.Lot;
import com.rometools.rome.io.FeedException;
import com.rss.AbstractRssClient;
import com.rss.RssClient94;

import java.io.IOException;
import java.util.List;

public class Crawler {

    public void process() {
        crawl();
    }

    void crawl() {

        AbstractRssClient rssClient = new RssClient94();
//        AbstractRssClient rssClient1 = new RssClient44();
//        AbstractRssClient rssClient2 = new RssClient223();

        try {
            List<Lot> list = rssClient.processMainRssFeed();
//            List<Lot> list1 = rssClient1.processMainRssFeed();
//            List<Lot> list2 = rssClient2.processMainRssFeed();

//            Set<String> set = new HashSet();
//
//            for (Lot lot: list ){set.add(lot.get());}
//            for (Lot lot: list1){set.add(lot.getType());}
//            for (Lot lot: list2){set.add(lot.getType());}

//            for (String s : set){
//                System.out.println(s);
//            }


        } catch (IOException e) {
            e.printStackTrace();
        } catch (FeedException e) {
            e.printStackTrace();
        }


    }

    }