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

public class RssClient223FZ implements IRssClient {

    private static final String RSS_FEED_FOR_223_FZ = "http://zakupki.gov.ru/tinyurl/1525b6f6-6238-413c-83c8-e2df3f7fff79";

    public List<SyndEntry> getMainRssFeed() throws IOException, FeedException {
        URL feedSource = new URL(RSS_FEED_FOR_223_FZ);
        SyndFeedInput input = new SyndFeedInput();
        SyndFeed feed = input.build(new XmlReader(feedSource));


        List<SyndEntry> list = new ArrayList(feed.getEntries());

        return list;
    }
}
