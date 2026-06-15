package com.employee.management.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class CartItemResponse {

    private Long id;

    private ProductResponse productResponse;

    private Integer quantity;

    private BigDecimal price;
}
