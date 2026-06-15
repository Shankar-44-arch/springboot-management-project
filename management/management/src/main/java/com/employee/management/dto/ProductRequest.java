package com.employee.management.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductRequest {

    private String prodName;

    private String prodDescription;

    private String category;

    private BigDecimal price;

    private Integer stockQuantity;

    private String imgUrl;

    private Boolean active;

}
