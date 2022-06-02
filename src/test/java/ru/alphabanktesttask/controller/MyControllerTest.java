package ru.alphabanktesttask.controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.alphabanktesttask.service.MyService;

import java.time.LocalDate;
import java.util.HashMap;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.alphabanktesttask.controller.ControllerTestData.*;
import static ru.alphabanktesttask.web.controller.MyController.URL_CODES;
import static ru.alphabanktesttask.web.controller.MyController.URL_GIF;

public class MyControllerTest extends AbstractControllerTest {

    @Value("${giphy.higher}")
    String higher;

    @Value("${giphy.less}")
    String less;

    @Value("${giphy.reserve}")
    String reserve;

    @MockBean
    MyService myService;

    @BeforeEach
    void setGifValues() {
        Mockito.when(myService.getRandomGif(higher))
                .thenReturn(higher);
        Mockito.when(myService.getRandomGif(less))
                .thenReturn(less);
        Mockito.when(myService.getRandomGif(reserve))
                .thenReturn(reserve);
    }

    @Test
    void getCodes() throws Exception {
        loadCodes(higher_rates, less_rates);
        ResultActions actions = perform(MockMvcRequestBuilders.get(URL_CODES))
                .andExpect(status().isOk());
        String contentAsString = getContentAsString(actions);
        Assertions.assertTrue(contentAsString.contains("USD"));
    }

    @Test
    void getGifForHigherRate() throws Exception {
        loadCodes(higher_rates, less_rates);
        ResultActions actions = perform(MockMvcRequestBuilders.get(URL_GIF)
                .param("currency", "USD"))
                .andExpect(status().isOk());
        String contentAsString = getContentAsString(actions);
        Assertions.assertEquals(contentAsString, higher);
    }

    @Test
    void getGifForLessRate() throws Exception {
        loadCodes(less_rates, higher_rates);
        ResultActions actions = perform(MockMvcRequestBuilders.get(URL_GIF)
                .param("currency", "USD"))
                .andExpect(status().isOk());
        String contentAsString = getContentAsString(actions);
        Assertions.assertEquals(contentAsString, less);
    }

    @Test
    void getGifForNotPresentCode() throws Exception {
        loadCodes(higher_rates, empty_rates);
        ResultActions actions = perform(MockMvcRequestBuilders.get(URL_GIF)
                .param("currency", "USD"))
                .andExpect(status().isOk());
        String contentAsString = getContentAsString(actions);
        Assertions.assertEquals(contentAsString, reserve);
    }


    private void loadCodes(HashMap<String, Double> latestRates, HashMap<String, Double> yesterdayRates) {
        Mockito.when(myService.getLatestRates())
                .thenReturn(latestRates);
        Mockito.when(myService.getPreviousRates(LocalDate.now().minusDays(1)))
                .thenReturn(yesterdayRates);
    }
}
