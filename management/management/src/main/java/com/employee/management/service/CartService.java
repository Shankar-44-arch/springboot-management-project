package com.employee.management.service;

import com.employee.management.dto.CartItemRequest;
import com.employee.management.dto.CartItemResponse;
import com.employee.management.dto.EmployeeResponse;
import com.employee.management.dto.ProductResponse;
import com.employee.management.model.CartItem;
import com.employee.management.model.Employee;
import com.employee.management.model.Product;
import com.employee.management.repository.CartItemRepository;
import com.employee.management.repository.EmployeeRepository;
import com.employee.management.repository.ProductRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class CartService {

    private final ProductRepository productRepository;
    private final EmployeeRepository employeeRepository;
    private final CartItemRepository cartItemRepository;

    public boolean addCartItm(String empId, CartItemRequest cartItemRequest){
        Optional<Product> prodOpt = productRepository.findById(Long.valueOf(cartItemRequest.getProductId()));
        if(prodOpt.isEmpty()){
            return false;
        }
        Product product = prodOpt.get();
        if(product.getStockQuantity() < cartItemRequest.getQuantity()){
            return false;
        }

        Optional<Employee> empOpt = employeeRepository.findById(Long.valueOf(empId));
        if(empOpt.isEmpty()){
            return  false;
        }
        Employee emp = empOpt.get();

        // Getting if the required product is in the cart item or not.
        CartItem existingCartItm = cartItemRepository.findByEmployeeAndProduct(emp, product);
        if(existingCartItm != null){
            // update to the existing cart
            existingCartItm.setQuantity(
                    existingCartItm.getQuantity() + cartItemRequest.getQuantity());
            existingCartItm.setPrice(
                    product.getPrice().multiply(BigDecimal.valueOf(existingCartItm.getQuantity())));
            cartItemRepository.save(existingCartItm);
        } else {
            // create new cart item
            CartItem cartItem = new CartItem();
            cartItem.setEmployee(emp);
            cartItem.setProduct(product);
            cartItem.setQuantity(cartItemRequest.getQuantity());
            cartItem.setPrice(product.getPrice().multiply(BigDecimal.valueOf(cartItemRequest.getQuantity())));
            cartItemRepository.save(cartItem);
        }

        return true;
    }

    public List<CartItem> getAllItm(String empId){
        Optional<Employee> emp = employeeRepository.findById(Long.valueOf(empId));

        return employeeRepository.findById(Long.valueOf(empId))
                .map(cartItemRepository::findByEmployee)
                .orElseGet(List::of);
    }


    @Transactional
    public boolean removeCartItm(String employeeId, Long productId) {
        Optional<Product> prodOpt = productRepository.findById(productId);
        Optional<Employee> empOpt = employeeRepository.findById(Long.valueOf(employeeId));

        if(prodOpt.isPresent() && empOpt.isPresent()){
            cartItemRepository.deleteByEmployeeAndProduct(empOpt.get(), prodOpt.get());
            return true;
        }
        return false;
    }
}
