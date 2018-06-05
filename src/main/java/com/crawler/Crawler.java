package com.crawler;

import com.model.Lot;
import com.rometools.rome.io.FeedException;
import com.rss.UniversalRssClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class Crawler {
    private final Logger slf4jLogger = LoggerFactory.getLogger(Crawler.class);

    private final UniversalRssClient rssClient = new UniversalRssClient();

    public static Set<Lot> database = new HashSet<>();

    @Scheduled(fixedRate = 60000)
    void showSize() {
        slf4jLogger.info("Database entity = " + database.size());
    }

    @Scheduled(fixedRate = 10000)
    void crawl() {
        try {
            rssClient.processMainRssFeed();
        } catch (IOException | FeedException e) {
            e.printStackTrace();
        }


    }

}