package com.syscal.apisyscal.controller;

import com.syscal.apisyscal.exception.BusinessException;
import com.syscal.apisyscal.model.entity.ProductEntity;
import com.syscal.apisyscal.model.request.ProductRequestDTO;
import com.syscal.apisyscal.model.response.ProductResponseDTO;
import com.syscal.apisyscal.model.response.ResponseDTO;
import com.syscal.apisyscal.service.ProductService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/product")
@Slf4j
public class ProductController {

    @Autowired private ProductService productService;

    @GetMapping("")
    public ResponseEntity<?> getAllProducts() {
        log.info("ProductController.getAllProducts");
        List<ProductResponseDTO> products = productService.getAll();
        return ResponseEntity.ok().body(products);
    }

    @GetMapping("/{productId}")
    public ResponseEntity<?> getProduct(@PathVariable Integer productId) {
        log.info("ProductController.getProduct");
        ProductEntity product = productService.getOne(productId);
        return ResponseEntity.ok().body(product);
    }


    @PostMapping("")
    public ResponseEntity<?> saveProduct(@Valid @RequestBody ProductRequestDTO data) {
        log.info("ProductController.saveProduct");
        ProductEntity product = productService.save(data);
        return ResponseEntity.ok().body(product);
    }

    @PutMapping("/{productId}")
    public ResponseEntity<?> updateProduct(@PathVariable Integer productId, @Valid @RequestBody ProductRequestDTO body) {
        log.info("UserController.deleteUser");
        if (productId < 1) {
            throw new BusinessException(String.format("The value %s must greater than 1", productId), "400", HttpStatus.BAD_REQUEST);
        }
        ProductEntity productEntity = productService.update(productId, body);
        return ResponseEntity.ok().body(productEntity);
    }

    @DeleteMapping("/{productId}")
    private ResponseEntity<?> deleteProduct(@PathVariable Integer productId) {
        log.info("UserController.deleteUser");
        if (productId < 1) {
            throw new BusinessException(String.format("The value %s must greater than 1", productId), "400", HttpStatus.BAD_REQUEST);
        }
        productService.delete(productId);
        ResponseDTO response = new ResponseDTO();
        response.setMessage("Successfully deleted Product");
        response.setStatus(HttpStatus.OK);
        Map<String, Object> data = new HashMap<>();
        data.put("message", "The Product "+productId +" was removed");
        response.setData(data);
        return ResponseEntity.ok().body(response);
    }

}
