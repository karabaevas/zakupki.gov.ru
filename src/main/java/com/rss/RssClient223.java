package com.rss;

import com.mappers.AbstractLotMapper;
import com.mappers.LotMapper223;
import com.model.Lot;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class RssClient223 extends AbstractRssClient {
    private static final String RSS_FEED_FOR_223_FZ = "http://zakupki.gov.ru/tinyurl/1525b6f6-6238-413c-83c8-e2df3f7fff79";
    private final Logger slf4jLogger = LoggerFactory.getLogger(RssClient223.class);

    @Override
    AbstractLotMapper getLotMapper() {
        return new LotMapper223();
    }

    @Override
    void fillLot(List<Lot> lotList) {

    }

    @Override
    String getRssUrl() {
        return RSS_FEED_FOR_223_FZ;
    }
}