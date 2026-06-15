package com.employee.management.service;

import com.employee.management.dto.OrderResponse;
import com.employee.management.repository.EmployeeRepository;
import com.employee.management.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final EmployeeRepository employeeRepository;
    private final ProductRepository productRepository;

    public OrderResponse createOrder(String employeeId) {

    }
}
