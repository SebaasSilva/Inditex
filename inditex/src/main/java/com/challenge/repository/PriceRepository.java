package com.challenge.repository;

import com.challenge.model.response.PriceDetail;
import java.sql.Timestamp;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * JPA repository class for the PriceDetail entity.
 *
 * @see JpaRepository
 */
public interface PriceRepository extends JpaRepository<PriceDetail, Long> {

  /**
   * Find price by applicationDate, brandId and productId and select it by priority order.
   *
   * @param applicationDate Price application date.
   * @param brandId         Brand unique identifier.
   * @param productId       Product unique identifier.
   * @return PriceDetail object with the DataSource information, if there is no information in the DataSource PriceDetail objects is null.
   */
  @Query(value =
      "SELECT p.PRODUCT_ID, p.BRAND_ID, p.PRICE_LIST, p.START_DATE, p.END_DATE, p.PRICE || ' ' || p.CURR as PRICE FROM PRICES p " +
          "WHERE START_DATE <= :applicationDate " +
          "AND END_DATE >= :applicationDate " +
          "AND PRODUCT_ID = :productId " +
          "AND BRAND_ID = :brandId " +
          "ORDER BY PRIORITY DESC LIMIT 1",
      nativeQuery = true)
  Optional<PriceDetail> findByPriceDetailsByBrandIdAndProductId(
      @Param("applicationDate") Timestamp applicationDate,
      @Param("brandId") Long brandId,
      @Param("productId") Long productId);

}
