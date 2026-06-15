package com.employee.management.repository;

import com.employee.management.model.CartItem;
import com.employee.management.model.Employee;
import com.employee.management.model.Product;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    CartItem findByEmployeeAndProduct(Employee emp, Product product);

    @Transactional
    void deleteByEmployeeAndProduct(Employee emp, Product product);

    List<CartItem> findByEmployee(Employee employee);

}
