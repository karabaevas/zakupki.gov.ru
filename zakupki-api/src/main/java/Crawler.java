import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.io.FeedException;
import entities.Lot;
import rss.AbstractRssClient;
import rss.RssClient223;
import rss.RssClient44;
import rss.RssClient94;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Crawler {

    public static void main(String[] args) {
        AbstractRssClient rssClient = new RssClient94();
        AbstractRssClient rssClient1 = new RssClient44();
        AbstractRssClient rssClient2 = new RssClient223();

        try {
            List<Lot> list = rssClient.processMainRssFeed();
            List<Lot> list1 = rssClient1.processMainRssFeed();
            List<Lot> list2 = rssClient2.processMainRssFeed();

            Set<String> set = new HashSet();

            for (Lot lot: list ){set.add(lot.getType());}
            for (Lot lot: list1){set.add(lot.getType());}
            for (Lot lot: list2){set.add(lot.getType());}

            for (String s : set){
                System.out.println(s);
            }


        } catch (IOException e) {
            e.printStackTrace();
        } catch (FeedException e) {
            e.printStackTrace();
        }

    }
}
