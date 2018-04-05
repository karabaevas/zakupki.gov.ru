package rss;

import com.rometools.rome.io.FeedException;
import entities.Lot;

import java.io.IOException;
import java.util.List;

public interface IRssClient {

    List<Lot> getMainRssFeed() throws IOException, FeedException;


}
