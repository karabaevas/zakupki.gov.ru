import com.rometools.rome.io.FeedException;
import entities.Lot;
import rss.IRssClient;
import rss.RssClient94FZ;

import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        IRssClient rssClient = new RssClient94FZ();
        try {
            List<Lot> list = rssClient.getMainRssFeed();
            System.out.println(list);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (FeedException e) {
            e.printStackTrace();
        }

    }
}
