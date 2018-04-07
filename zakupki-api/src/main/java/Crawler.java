import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.io.FeedException;
import entities.Lot;
import rss.IRssClient;
import rss.RssClient94FZ;

import java.io.IOException;
import java.util.List;

public class Crawler {

    public static void main(String[] args) {
        RssClient94FZ rssClient = new RssClient94FZ();
        try {
            List<SyndEntry> list = rssClient.processMainRssFeed();
//            System.out.println(list);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (FeedException e) {
            e.printStackTrace();
        }

    }
}
