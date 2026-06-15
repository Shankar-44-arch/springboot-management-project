package com.employee.management.dto;

import com.employee.management.model.Product;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class CartItemRequest {

    private String productId;
    private Integer quantity;

}
