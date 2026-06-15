package com.employee.management.service;

import com.employee.management.dto.ProductRequest;
import com.employee.management.dto.ProductResponse;
import com.employee.management.model.Product;
import com.employee.management.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public ProductResponse addProduct(ProductRequest prodItm){
        Product product = new Product();
        productRequestToProduct(product, prodItm);
        Product savedProd = productRepository.save(product);
        return mapToProductResponse(savedProd);
    }
    // ProductRequest To Product
    public void productRequestToProduct(Product product, ProductRequest prodItm){
        product.setProdName(prodItm.getProdName());
        product.setCategory(prodItm.getCategory());
        product.setProdDescription(prodItm.getProdDescription());
        product.setPrice(prodItm.getPrice());
        product.setStockQuantity(prodItm.getStockQuantity());
        product.setImgUrl(prodItm.getImgUrl());
        product.setActive(prodItm.getActive());
    }

    public List<ProductResponse> getAllProduct() {
        return productRepository.findAll()
                .stream()
                .map(this::mapToProductResponse)
                .collect(Collectors.toList());
    }

    public ProductResponse getProductById(Long id) {
        Product prodItm = productRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("The product is not Found for the product ID : " + id));

        return mapToProductResponse(prodItm);
    }


    public Optional<ProductResponse> updateProduct(Long id, ProductRequest prodItm) {
//        Product product = productRepository.findById(id)
//                .orElseThrow(() -> new RuntimeException("The product is not found for product ID :" + id));
//        productRequestToProduct(prodItm, product);
//        Product updated = productRepository.save(product);
//        return mapToProductResponse(updated);

                //  OR

        return productRepository.findById(id)
                .map(currentProduct -> {
                    productRequestToProduct(currentProduct, prodItm);
                   Product savedProduct = productRepository.save(currentProduct);
                   return mapToProductResponse(savedProduct);
                });
    }

    public String deleteProduct(Long id) {
//        productRepository.deleteById(id);
        //      NEW

        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("The product is not found for product ID :" + id));
        product.setActive(false);
        productRepository.save(product);
        return "The product is Deactivated";
    }

    public List<ProductResponse> searchProduct(String keyword) {
        return productRepository.searchProducts(keyword)
                .stream()
                .map(this::mapToProductResponse)
                .collect(Collectors.toList());
    }

    // Product to ProductResponse

    private ProductResponse mapToProductResponse(Product product) {
        ProductResponse productResponse = new ProductResponse();
        productResponse.setProdId(product.getProdId());
        productResponse.setProdName(product.getProdName());
        productResponse.setCategory(product.getCategory());
        productResponse.setProdDescription(product.getProdDescription());
        productResponse.setPrice(product.getPrice());
        productResponse.setStockQuantity(product.getStockQuantity());
        productResponse.setImgUrl(product.getImgUrl());
        productResponse.setActive(product.getActive());

        return productResponse;
    }
}
