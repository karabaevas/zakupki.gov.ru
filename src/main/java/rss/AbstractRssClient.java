package rss;

import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.FeedException;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.XmlReader;
import model.Lot;
import mappers.AbstractLotMapper;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractRssClient {
    private AbstractLotMapper lotMapper = getLotMapper();

    List<SyndEntry> getMainRssFeed() throws IOException, FeedException {
        URL feedSource = new URL(getRssUrl());
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

    public List<Lot> processMainRssFeed() throws IOException, FeedException {
        List<SyndEntry> syndEntryList = getMainRssFeed();
        List<Lot> lotList = syndEntryListToLotList(syndEntryList);
        fillLot(lotList);
        return lotList;
    }

    abstract void fillLot(List<Lot> lotList);

    abstract String getRssUrl();

    abstract AbstractLotMapper getLotMapper();

}