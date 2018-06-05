package com.rss;

import com.crawler.Crawler;
import com.mappers.UniversalMapper;
import com.model.Lot;
import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.FeedException;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.XmlReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

public class UniversalRssClient {
    private final Logger slf4jLogger = LoggerFactory.getLogger(UniversalRssClient.class);

    private final UniversalMapper lotMapper = new UniversalMapper();

    private static final String RSS_FEED_FOR_223_94_44FZ = "http://zakupki.gov.ru/tinyurl/61c07b19-a2b7-450d-adda-e7bc1f3efe8c";

    private String getRssUrl() {
        return RSS_FEED_FOR_223_94_44FZ;
    }

    public void processMainRssFeed() throws IOException, FeedException {
        SyndFeedInput input = new SyndFeedInput();
        SyndFeed feed = input.build(new XmlReader(new URL(getRssUrl())));

        ArrayList<SyndEntry> listEntries = new ArrayList<>(feed.getEntries());

        for (SyndEntry syndEntry : listEntries) {
            Lot lot = lotMapper.map(syndEntry);
            Crawler.database.add(lot);
        }
    }

}