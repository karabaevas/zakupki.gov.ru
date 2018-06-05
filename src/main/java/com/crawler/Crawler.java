package com.crawler;

import com.model.Lot;
import com.rometools.rome.io.FeedException;
import com.rss.UniversalRssClient;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class Crawler {
    Set<Lot> database = new HashSet<>();

    @Scheduled(fixedRate = 10000)
    void crawl() {
        UniversalRssClient rssClient = new UniversalRssClient();

        try {
            database.addAll(rssClient.processMainRssFeed());

        } catch (IOException | FeedException e) {
            e.printStackTrace();
        }


    }

    }