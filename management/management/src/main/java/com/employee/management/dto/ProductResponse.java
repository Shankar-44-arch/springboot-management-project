package com.employee.management.dto;

import lombok.Data;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.util.Optional;

@Data
public class ProductResponse {

    private Long prodId;

    private String prodName;

    private String prodDescription;

    private String category;

    private BigDecimal price;

    private Integer stockQuantity;

    private String imgUrl;

    private Boolean active;
}
