package ru.alphabanktesttask.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ControllerTestData {
    public static final HashMap<String, Double> higher_rates = new HashMap<>();
    public static final HashMap<String, Double> less_rates = new HashMap<>();
    public static final HashMap<String, Double> empty_rates = new HashMap<>();
    public static final List<String> codes = new ArrayList<>();
    static {
        higher_rates.put("USD", 2d);
        less_rates.put("USD", 1d);
        codes.add("USD");
    }
}
