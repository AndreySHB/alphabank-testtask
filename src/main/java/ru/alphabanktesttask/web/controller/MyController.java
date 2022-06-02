package ru.alphabanktesttask.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.alphabanktesttask.service.MyService;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@EnableCaching
@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public class MyController {
    @Autowired
    MyService service;

    @Value("${giphy.higher}")
    private String higher;

    @Value("${giphy.less}")
    private String less;

    @Value("${giphy.reserve}")
    private String reserve;

    public static final String URL_CODES="/getcodes";

    public static final String URL_GIF="/getrandomgif";

    @GetMapping(URL_CODES)
    public List<String> getCodes() {
        return service.getLatestRates().keySet()
                .stream()
                .sorted(Comparator.naturalOrder())
                .collect(Collectors.toList());
    }

    @GetMapping(URL_GIF)
    public String getRandomGif(@RequestParam String currency) {
        Map<String, Double> latestRates = service.getLatestRates();
        Map<String, Double> previousRates = service.getPreviousRates(LocalDate.now().minusDays(1));
        Boolean isHigher = !(latestRates.containsKey(currency) && previousRates.containsKey(currency)) ?
                null : latestRates.get(currency) > previousRates.get(currency);
        String tag = isHigher == null ? reserve :
                isHigher ? higher : less;
        return service.getRandomGif(tag);
    }
}
