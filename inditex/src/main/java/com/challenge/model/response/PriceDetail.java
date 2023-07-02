package com.challenge.model.response;

import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
@Table(name = "prices")
public class PriceDetail {

  private Long product_id;

  private Long brand_id;
  @Id
  private Long price_list;

  private LocalDateTime start_date;

  private LocalDateTime end_date;

  private String price;

}

