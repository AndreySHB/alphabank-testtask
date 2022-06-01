package ru.alphabanktesttask.feignclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "giphy", url = "${giphy.url}")
public interface GiphyClient {

    @GetMapping("/random?api_key=" + "${giphy.api.key}")
    String getRandomGif(@RequestParam String tag);
}
