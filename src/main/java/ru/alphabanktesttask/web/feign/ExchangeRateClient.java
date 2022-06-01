package ru.alphabanktesttask.web.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.alphabanktesttask.model.ExchangeRates;

@FeignClient(name = "exchangerate", url = "${openexchangerates.url}")
public interface ExchangeRateClient {
    @GetMapping("/latest.json?app_id="+"${openexchangerates.app.id}")
    ExchangeRates getLatestRates();

    @GetMapping("/historical/{date}.json?app_id="+"${openexchangerates.app.id}")
    ExchangeRates getHistoricalRates(@PathVariable String date);
}
