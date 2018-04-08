package rss;

import entities.Lot;
import mappers.AbstractLotMapper;
import mappers.LotMapper44;
import mappers.LotMapper94;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class RssClient94 extends AbstractRssClient {
    private static final String RSS_FEED_FOR_94_FZ = "http://zakupki.gov.ru/tinyurl/32a79c94-d8cb-4568-9652-b136bacddbca";
    private final Logger slf4jLogger = LoggerFactory.getLogger(RssClient94.class);

    @Override
    AbstractLotMapper getLotMapper() {
        return new LotMapper94();
    }

    @Override
    void fillLot(List<Lot> lotList) {

    }

    @Override
    String getRssUrl() {
        return RSS_FEED_FOR_94_FZ;
    }
}