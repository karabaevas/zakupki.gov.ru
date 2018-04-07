package rss;

import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.FeedException;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.XmlReader;
import entities.Lot;
import mappers.LotMapper;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class RssClient94FZ implements IRssClient {
    private static LotMapper lotMapper = new LotMapper();

    private static final String RSS_FEED_FOR_94_FZ = "http://zakupki.gov.ru/tinyurl/e0aad7d6-d53c-4f69-8ed9-66738111ad98";

    List<SyndEntry> getMainRssFeed() throws IOException, FeedException {
        URL feedSource = new URL(RSS_FEED_FOR_94_FZ);
        SyndFeedInput input = new SyndFeedInput();
        SyndFeed feed = input.build(new XmlReader(feedSource));


        List<SyndEntry> list = new ArrayList(feed.getEntries());
        return list;
    }

    List<Lot> syndEntryListToLotList(List<SyndEntry> syndEntryList) {
        List<Lot> resultLotList = new ArrayList<>();

        for (SyndEntry syndEntry : syndEntryList) {
            Lot lot = lotMapper.mapSyndEntryToLot(syndEntry);
            resultLotList.add(lot);
        }

        return resultLotList;
    }

    public List<SyndEntry> processMainRssFeed() throws IOException, FeedException {
        List<SyndEntry> syndEntryList = getMainRssFeed();
        List<Lot> lotList = syndEntryListToLotList(syndEntryList);
        fillLot(lotList);
        return syndEntryList;
    }

    void fillLot(List<Lot> lotList) {

    }

}