package rss;

import com.rometools.rome.feed.synd.SyndContent;
import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.FeedException;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.XmlReader;
import com.rometools.utils.Lists;
import entities.Lot;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class RssClient {
    public static void main(String[] args) throws IOException, FeedException {
        URL feedSource = new URL("http://zakupki.gov.ru/tinyurl/56963ff2-6924-4029-aada-ed51dc2903b0");
        SyndFeedInput input = new SyndFeedInput();
        SyndFeed feed = input.build(new XmlReader(feedSource));


        List<SyndEntry> list = new ArrayList(feed.getEntries());

        List<Lot> lots = list.stream().map(Lot::new).collect(Collectors.toList());

    }


}
