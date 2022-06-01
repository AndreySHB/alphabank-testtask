package ru.alphabanktesttask.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
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

    @GetMapping("/getcodes")
    public List<String> getCodes() {
        return service.getLatestRates().keySet()
                .stream()
                .sorted(Comparator.naturalOrder())
                .collect(Collectors.toList());
    }

//    @Cacheable("gifs")
    @GetMapping("/getrandomgif")
    public String getRandomGif(@RequestParam String currency) {
        boolean isHigher = service.getLatestRates().get(currency) >
                service.getPreviousRates(LocalDate.now().minusDays(1)).get(currency);
        String tag = isHigher ? higher : less;
        return service.getRandomGif(tag);

    }
}
