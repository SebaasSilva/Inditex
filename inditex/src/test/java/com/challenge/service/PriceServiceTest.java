package com.challenge.service;

import static java.time.LocalDateTime.now;
import static java.util.Optional.empty;
import static java.util.Optional.of;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.challenge.exception.PriceNotFoundException;
import com.challenge.model.request.PriceRequest;
import com.challenge.model.response.PriceDetail;
import com.challenge.repository.PriceRepository;
import java.time.LocalDateTime;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
class PriceServiceTest {

  private static final String EXCEPTION_MESSAGE =
      "Price not found with: PriceRequest(brandId=1, productId=35455, applicationDate=";

  private static final long BRAND_ID = 1L;

  private static final long PRODUCT_ID = 35455L;

  private static final long PRICE_LIST = 4L;

  private static final String PRICE = "38.95 EUR";

  @InjectMocks
  private PriceService instance;

  @Mock
  private PriceRepository priceRepository;

  @BeforeEach
  public void setUp() {
    instance = new PriceService(priceRepository);
  }

  @Test
  @DisplayName("The process was successful and found by the repository")
  public void successfulExecution() throws PriceNotFoundException {

    when(priceRepository.findByPriceDetailsByBrandIdAndProductId(any(), any(), any())).thenReturn(of(buildPriceDetail(now())));

    instance.getPriceDetailsByBrandIdAndProductId(buildPriceRequestMock(now()));

    verify(priceRepository, atLeastOnce()).findByPriceDetailsByBrandIdAndProductId(any(), any(), any());
  }

  @Test
  @DisplayName("The process was successful throwing PriceNotFoundException")
  public void successfulExecutionThrowingPriceNotFoundException() {

    LocalDateTime localDateTime = now();

    when(priceRepository.findByPriceDetailsByBrandIdAndProductId(any(), any(), any())).thenReturn(empty());

    PriceNotFoundException priceNotFoundException = assertThrows(PriceNotFoundException.class, () -> {
      instance.getPriceDetailsByBrandIdAndProductId(buildPriceRequestMock(localDateTime));
    });

    assertEquals(EXCEPTION_MESSAGE + localDateTime.toString() + ")", priceNotFoundException.getMessage());
  }

  private PriceRequest buildPriceRequestMock(LocalDateTime localDateTime) {
    return PriceRequest.builder()
        .applicationDate(localDateTime)
        .brandId(BRAND_ID)
        .productId(PRODUCT_ID)
        .build();
  }

  private PriceDetail buildPriceDetail(LocalDateTime localDateTime) {
    return PriceDetail.builder()
        .price(PRICE)
        .brand_id(BRAND_ID)
        .price_list(PRICE_LIST)
        .end_date(localDateTime)
        .product_id(PRODUCT_ID)
        .start_date(localDateTime)
        .build();
  }

}