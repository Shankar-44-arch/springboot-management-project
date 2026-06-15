package com.employee.management.controller;

import com.employee.management.dto.ProductRequest;
import com.employee.management.dto.ProductResponse;
import com.employee.management.model.Product;
import com.employee.management.service.ProductService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/product")
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public ResponseEntity<List<ProductResponse>> getProd(){
        return ResponseEntity.ok(productService.getAllProduct());
    }

    @PostMapping
    public ResponseEntity<ProductResponse> addProd(@RequestBody ProductRequest product){
        return ResponseEntity.status(HttpStatus.CREATED).body(productService.addProduct(product));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> getProdById(@PathVariable Long id){
        return ResponseEntity.ok(productService.getProductById(id));

    }

    @PutMapping()
    public ResponseEntity<ProductResponse> updateProd(@PathVariable Long id, @RequestBody ProductRequest product){
//        ProductResponse prodItm = productService.updateProduct(id, product);
//        if(prodItm == null){
//            return ResponseEntity.notFound().build();
//        }
//        return ResponseEntity.ok(prodItm);

                //  OR
        return productService.updateProduct(id, product)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProd(@PathVariable Long id){
        return ResponseEntity.ok(productService.deleteProduct(id));
    }

    @GetMapping("/search")
    public ResponseEntity<List<ProductResponse>> searchProd(@RequestParam String keyword){
        return ResponseEntity.ok(productService.searchProduct(keyword));
    }

}
