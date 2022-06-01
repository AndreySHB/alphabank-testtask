package ru.alphabanktesttask.feignclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.alphabanktesttask.model.ExchangeRates;

import java.time.LocalDate;

@FeignClient(name = "exchangerate", url = "${openexchangerates.url}")
public interface ExchangeRateClient {

    @GetMapping("/latest.json?app_id="+"${openexchangerates.app.id}")
    ExchangeRates getLatestRates();

    @GetMapping("/historical/{ldate}.json?app_id="+"${openexchangerates.app.id}")
    ExchangeRates getHistoricalRates(@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate ldate);
}
