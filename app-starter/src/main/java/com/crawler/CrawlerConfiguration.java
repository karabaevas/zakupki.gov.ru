package com.crawler;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CrawlerConfiguration {
    @Bean
    public CrawlerListener crawlerListener(){
        return new CrawlerListener();
    }
}
