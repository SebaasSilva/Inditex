package com.challenge.service;

import com.challenge.exception.PriceNotFoundException;
import com.challenge.model.request.PriceRequest;
import com.challenge.model.response.PriceDetail;

public interface PriceServiceImpl {

  PriceDetail getPriceDetailsByBrandIdAndProductId(PriceRequest priceRequest)
      throws PriceNotFoundException;

}
