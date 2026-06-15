package com.employee.management.dto;

import com.employee.management.model.Employee;
import com.employee.management.model.OrderItem;
import com.employee.management.model.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
public class OrderResponse {

    private Long id;
    private EmployeeResponse employee;
    private BigDecimal totalAmount;
    private OrderStatus orderStatus;
    private List<OrderItemDTO> items;

    @CreationTimestamp
    private LocalDateTime createdAt;
}
