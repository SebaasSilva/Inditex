package com.challenge.service;

import static java.sql.Timestamp.valueOf;

import com.challenge.exception.PriceNotFoundException;
import com.challenge.model.request.PriceRequest;
import com.challenge.model.response.PriceDetail;
import com.challenge.repository.PriceRepository;
import org.springframework.stereotype.Service;

@Service
public class PriceService implements PriceServiceImpl {

  private static final String PRICE_NOT_FOUND_WITH = "Price not found with: ";

  private final PriceRepository priceRepository;

  public PriceService(PriceRepository priceRepository) {
    this.priceRepository = priceRepository;
  }

  /**
   * @param priceRequest Has all necessary information to get data from the DataSource
   * @return PriceDetail object with the DataSource information.
   * @throws PriceNotFoundException This exception show up when there is no data in the DataSource.
   */
  @Override
  public PriceDetail getPriceDetailsByBrandIdAndProductId(PriceRequest priceRequest)
      throws PriceNotFoundException {
    return priceRepository
        .findByPriceDetailsByBrandIdAndProductId(valueOf(priceRequest.getApplicationDate()), priceRequest.getBrandId(),
            priceRequest.getProductId())
        .orElseThrow(() -> new PriceNotFoundException(PRICE_NOT_FOUND_WITH + priceRequest.toString()) {
        });
  }
}
