package com.challenge.repository;

import static java.sql.Timestamp.valueOf;
import static java.time.LocalDateTime.now;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyLong;

import com.challenge.model.response.PriceDetail;
import java.time.LocalDateTime;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;


@DataJpaTest
@ExtendWith(SpringExtension.class)
class PriceRepositoryTest {

  private static final long PRODUCT_ID = 35455L;

  private static final long BRAND_ID = 1L;

  @Autowired
  private PriceRepository priceRepository;

  @Test
  @DisplayName("The data was found by priceDetails, brandId and productId")
  public void findByPriceDetailsByBrandIdAndProductIdWithData() {
    LocalDateTime localDateTime = now();
    PriceDetail priceDetail =
        priceRepository.saveAndFlush(
            buildPriceDetail(localDateTime));
    Optional<PriceDetail> search =
        priceRepository.findByPriceDetailsByBrandIdAndProductId(valueOf(localDateTime), BRAND_ID, PRODUCT_ID);

    assertNotNull(priceDetail);
    assertTrue(search.isPresent());
  }

  @Test
  @DisplayName("The data was not found by priceDetails, brandId and productId")
  public void findByPriceDetailsByBrandIdAndProductIdWithNoData() {

    Optional<PriceDetail> priceDetail =
        priceRepository.findByPriceDetailsByBrandIdAndProductId(valueOf(now()), anyLong(), anyLong());

    assertFalse(priceDetail.isPresent());
  }

  private PriceDetail buildPriceDetail(LocalDateTime localDateTime){
    return PriceDetail.builder()
        .brand_id(BRAND_ID)
        .price_list(4L)
        .end_date(localDateTime)
        .product_id(PRODUCT_ID)
        .start_date(localDateTime)
        .build();
  }


}