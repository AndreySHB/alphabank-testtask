package ru.alphabanktesttask.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import ru.alphabanktesttask.feignclient.ExchangeRateClient;
import ru.alphabanktesttask.feignclient.GiphyClient;

import java.time.LocalDate;
import java.util.Map;

@Service
public class MyService {
    @Autowired
    ExchangeRateClient exchangeRateClient;

    @Autowired
    GiphyClient giphyClient;

    @Cacheable("yesterdayrates")
    public Map<String, Double> getPreviousRates(LocalDate localDate) {
        return exchangeRateClient.getHistoricalRates(localDate).getRates();
    }

    public Map<String, Double> getLatestRates() {
        return exchangeRateClient.getLatestRates().getRates();
    }

    public String getRandomGif(String tag) {
        return giphyClient.getRandomGif(tag);
    }
}
