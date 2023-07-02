package com.challenge.controller;

import com.challenge.exception.PriceNotFoundException;
import com.challenge.model.request.PriceRequest;
import com.challenge.model.response.PriceDetail;
import com.challenge.service.PriceServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/prices")
@RequiredArgsConstructor
public class PriceController {

  private final PriceServiceImpl priceServiceImpl;

  /**
   * Expose the necessary data to identify the price by priority.
   *
   * @param priceRequest Is used to get the request data.
   * @return priceDetail PriceDetail Object with all the data in the DataSource.
   * @throws PriceNotFoundException This exception show up when there is no data in the DataSource.
   */
  @PostMapping("/priority-prices")
  public ResponseEntity<PriceDetail> priorityPrices(@RequestBody PriceRequest priceRequest) throws PriceNotFoundException {
    log.info("PricesRequest: {}", priceRequest.toString());
    return new ResponseEntity<>(priceServiceImpl
        .getPriceDetailsByBrandIdAndProductId(priceRequest),
        HttpStatus.OK);

  }

}
