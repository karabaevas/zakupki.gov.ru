package rss;

import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.FeedException;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.XmlReader;
import entities.Lot;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class RssClient94FZ implements IRssClient {

    private static final String RSS_FEED_FOR_94_FZ = "http://zakupki.gov.ru/tinyurl/79fd08d7-6353-4f01-94e2-d9b94ee76e3";

    @Override
    public List<Lot> getMainRssFeed() throws IOException, FeedException {
        URL feedSource = new URL(RSS_FEED_FOR_94_FZ + "3");
        SyndFeedInput input = new SyndFeedInput();
        SyndFeed feed = input.build(new XmlReader(feedSource));


        List<SyndEntry> list = new ArrayList(feed.getEntries());

        List<Lot> lotList = list.stream().map(Lot::new).collect(Collectors.toList());
        return lotList;
    }
}