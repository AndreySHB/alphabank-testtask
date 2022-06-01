package ru.alphabanktesttask.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.alphabanktesttask.model.ExchangeRates;
import ru.alphabanktesttask.web.feign.ExchangeRateClient;
import ru.alphabanktesttask.web.feign.GiphyClient;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public class MyController {
    @Autowired
    ExchangeRateClient exchangeRateClient;

    @Autowired
    GiphyClient giphyClient;

    /*@GetMapping
    public String get() throws IOException {
        URL url = new URL(GIPHY_RICH_URL);
        String content = readContentAsString(url);
        JSONObject jsonObject = new JSONObject(content);
        return jsonObject.getJSONObject("data").get("embed_url").toString();
    }

    private String readContentAsString(URL url) throws IOException {
        String content;
        try (InputStream inputStream = url.openStream()) {
            content = new String(inputStream.readAllBytes());
        }
        return content;
    }*/
    @GetMapping("/getcodes")
    public List<String> getCodes(@RequestParam @Nullable @DateTimeFormat(pattern = "yyyy-MM-dd")
                                             LocalDate localDate) {
        ExchangeRates rates = localDate != null ?
                exchangeRateClient.getHistoricalRates(localDate.toString()) :
                exchangeRateClient.getLatestRates();
        return rates.getRates().keySet()
                .stream()
                .sorted(Comparator.naturalOrder())
                .collect(Collectors.toList());
    }

    @GetMapping("/getrandomgif")
    public String getRandomGif(@RequestParam String tag) {
        return giphyClient.getRandomGif(tag);
    }

}
