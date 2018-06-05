package com.rss;

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
import java.util.List;

public class UniversalRssClient {
    private final Logger slf4jLogger = LoggerFactory.getLogger(UniversalRssClient.class);

    private final UniversalMapper lotMapper = new UniversalMapper();

    private static final String RSS_FEED_FOR_44_FZ = "http://zakupki.gov.ru/tinyurl/3d56c2dd-8c4a-460e-a612-8e00007da80e";
    private static final String RSS_FEED_FOR_94_FZ = "http://zakupki.gov.ru/tinyurl/32a79c94-d8cb-4568-9652-b136bacddbca";
    private static final String RSS_FEED_FOR_223_FZ = "http://zakupki.gov.ru/tinyurl/1525b6f6-6238-413c-83c8-e2df3f7fff79";

    String getRssUrl() {
        return RSS_FEED_FOR_223_FZ;
    }

    public List<Lot> processMainRssFeed() throws IOException, FeedException {
        List<SyndEntry> syndEntryList = getRssEntriesList();
        List<Lot> lotList = mapRSSListToLotList(syndEntryList);
        fillLot(lotList);
        return lotList;
    }

    private List<SyndEntry> getRssEntriesList() throws IOException, FeedException {
        URL feedSource = new URL(getRssUrl());
        SyndFeedInput input = new SyndFeedInput();
        SyndFeed feed = input.build(new XmlReader(feedSource));

        List<SyndEntry> list = new ArrayList(feed.getEntries());
        return list;
    }

    private List<Lot> mapRSSListToLotList(List<SyndEntry> syndEntryList) {
        List<Lot> resultLotList = new ArrayList<>();

        for (SyndEntry syndEntry : syndEntryList) {
            Lot lot = lotMapper.mapSyndEntryToLot(syndEntry);
            resultLotList.add(lot);
        }

        return resultLotList;
    }

    void fillLot(List<Lot> lotList) {

    }

}