package com.employee.management.controller;

import com.employee.management.dto.CartItemRequest;
import com.employee.management.model.CartItem;
import com.employee.management.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/cart")
public class CartController {

    private final CartService cartService;

    @PostMapping
    public ResponseEntity<String> addCart(
            @RequestHeader("X-Employee-ID") String employeeId, // who is requesting for the cart.
            // (cart is related to the employee) just think like "you login so, it need to pass ur id to bring ur cart".
            @RequestBody CartItemRequest cartItemRequest){

        if(!cartService.addCartItm(employeeId, cartItemRequest)){
            return ResponseEntity.badRequest().body("Product is Out of Stock or the Employee is not Found");
        }

        return ResponseEntity.status(HttpStatus.CREATED).body("The Product is added to the Cart");
    }

    @GetMapping
    public ResponseEntity<List<CartItem>> getAllCart(@RequestHeader("X-Employee-ID") String employeeId){
        return ResponseEntity.ok(cartService.getAllItm(employeeId));
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<Void> removeCartItm(
            @RequestHeader("X-Employee-ID") String employeeId,
            @PathVariable Long productId){

        boolean deleted = cartService.removeCartItm(employeeId, productId);

        return deleted? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
