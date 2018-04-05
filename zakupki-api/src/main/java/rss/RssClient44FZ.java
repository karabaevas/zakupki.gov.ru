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

public class RssClient44FZ implements IRssClient {

    private static final String RSS_FEED_FOR_44_FZ = "http://zakupki.gov.ru/tinyurl/55c93471-7c0f-4e39-b21c-f0eea6043acd";

    @Override
    public List<Lot> getMainRssFeed() throws IOException, FeedException {
        URL feedSource = new URL(RSS_FEED_FOR_44_FZ);
        SyndFeedInput input = new SyndFeedInput();
        SyndFeed feed = input.build(new XmlReader(feedSource));


        List<SyndEntry> syndEntryList = new ArrayList(feed.getEntries());

        List<Lot> lotList = syndEntryList.stream().map(Lot::new).collect(Collectors.toList());
        return lotList;
    }
}
