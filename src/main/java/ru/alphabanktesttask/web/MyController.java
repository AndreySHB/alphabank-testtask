package ru.alphabanktesttask.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.alphabanktesttask.util.Constants;

@RestController
public class MyController {
    @GetMapping
    public String get() {
        return Constants.GIPHY_RICH_URL;
    }
}
