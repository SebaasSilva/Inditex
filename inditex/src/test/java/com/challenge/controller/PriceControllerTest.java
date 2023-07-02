package com.challenge.controller;

import static java.time.LocalDateTime.now;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.challenge.exception.PriceNotFoundException;
import com.challenge.model.response.PriceDetail;
import com.challenge.service.PriceService;
import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(PriceController.class)
public class PriceControllerTest {

  @Autowired private MockMvc mockMvc;

  @MockBean
  private PriceService priceService;

  @Test
  void processWhenThereIsDataInTheRequest() throws Exception {
   when(priceService.getPriceDetailsByBrandIdAndProductId(any()))
       .thenReturn(buildPriceDetail(now()));

    mockMvc
        .perform(
            post("/prices/priority-prices")
                .contentType(APPLICATION_JSON_VALUE)
                .content(
                    "{\n" +
                        "    \"brandId\": 1,\n" +
                        "    \"productId\": 35455,\n" +
                        "    \"applicationDate\": \"2020-06-16 21:00:00\"\n" +
                        "}"))
        .andExpect(status().is2xxSuccessful());
  }

  @Test
  void processWhenThereIsNoDataInTheRequest() throws Exception {
    doThrow(PriceNotFoundException.class).when(priceService).getPriceDetailsByBrandIdAndProductId(any());
    mockMvc
        .perform(
            post("/prices/priority-prices")
                .contentType(APPLICATION_JSON_VALUE)
                .content(
                    "{\n" +
                        "    \"brandId\": 1,\n" +
                        "    \"productId\": 35457,\n" +
                        "    \"applicationDate\": \"2020-06-16 21:00:00\"\n" +
                        "}"))
        .andExpect(status().is4xxClientError());
  }

  private PriceDetail buildPriceDetail(LocalDateTime localDateTime){
    return PriceDetail.builder()
        .price("38.95 EUR")
        .brand_id(1L)
        .price_list(4L)
        .end_date(localDateTime)
        .product_id(35455L)
        .start_date(localDateTime)
        .build();
  }

}