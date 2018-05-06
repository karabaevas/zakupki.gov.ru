package rss;

import model.Lot;
import mappers.AbstractLotMapper;
import mappers.LotMapper44;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class RssClient44 extends AbstractRssClient {
    private static final String RSS_FEED_FOR_44_FZ = "http://zakupki.gov.ru/tinyurl/3d56c2dd-8c4a-460e-a612-8e00007da80e";
    private final Logger slf4jLogger = LoggerFactory.getLogger(RssClient44.class);

    @Override
    AbstractLotMapper getLotMapper() {
        return new LotMapper44();
    }

    @Override
    void fillLot(List<Lot> lotList) {

    }

    @Override
    String getRssUrl() {
        return RSS_FEED_FOR_44_FZ;
    }

}