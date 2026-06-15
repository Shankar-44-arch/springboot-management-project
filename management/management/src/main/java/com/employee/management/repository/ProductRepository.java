package com.employee.management.repository;

import com.employee.management.model.Product;
import io.micrometer.observation.ObservationFilter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    @Query("""
            SELECT p FROM Product p WHERE p.active=true AND 
                       (LOWER(p.prodName) LIKE LOWER(CONCAT('%', :keyword, '%'))) OR 
                                  (LOWER(p.category) LIKE LOWER(CONCAT('%', :keyword, '%')))
           """)
    List<Product> searchProducts(@Param("keyword") String keyword);
}