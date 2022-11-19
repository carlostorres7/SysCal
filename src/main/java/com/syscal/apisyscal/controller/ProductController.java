package com.syscal.apisyscal.controller;

import com.syscal.apisyscal.exception.BusinessException;
import com.syscal.apisyscal.model.entity.ProductEntity;
import com.syscal.apisyscal.model.entity.UserEntity;
import com.syscal.apisyscal.model.request.ProductRequestDTO;
import com.syscal.apisyscal.model.request.UserRequestDTO;
import com.syscal.apisyscal.model.response.ResponseDto;
import com.syscal.apisyscal.model.response.UserControllerResponseDTO;
import com.syscal.apisyscal.service.ProductService;
import com.syscal.apisyscal.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
        List<ProductEntity> products = productService.getAllProducts();
        return ResponseEntity.ok().body(products);
    }

    @GetMapping("/{productId}")
    public ResponseEntity<?> getProduct(@PathVariable Integer productId) {
        log.info("ProductController.getProduct");
        ProductEntity product = productService.getProductById(productId);
        return ResponseEntity.ok().body(product);
    }


    @PostMapping("")
    public ResponseEntity<?> saveProduct(@Valid @RequestBody ProductRequestDTO data) {
        log.info("ProductController.saveProduct");
        ProductEntity product = productService.saveProduct(data);
        return ResponseEntity.ok().body(product);
    }

    @PutMapping("/{productId}")
    public ResponseEntity<?> updateProduct(@PathVariable Integer productId, @Valid @RequestBody ProductRequestDTO body) {
        log.info("UserController.deleteUser");
        if (productId < 1) {
            throw new BusinessException(String.format("The value %s must greater than 1", productId), "400", HttpStatus.BAD_REQUEST);
        }
        ProductEntity productEntity = productService.updateProduct(productId, body);
        return ResponseEntity.ok().body(productEntity);
    }

    @DeleteMapping("/{productId}")
    private ResponseEntity<?> deleteProduct(@PathVariable Integer productId) {
        log.info("UserController.deleteUser");
        if (productId < 1) {
            throw new BusinessException(String.format("The value %s must greater than 1", productId), "400", HttpStatus.BAD_REQUEST);
        }
        productService.deleteProduct(productId);
        ResponseDto response = new ResponseDto();
        response.setMessage("Successfully deleted Product");
        response.setStatus(HttpStatus.OK);
        Map<String, Object> data = new HashMap<>();
        data.put("message", "The Product "+productId +" was removed");
        response.setData(data);
        return ResponseEntity.ok().body(response);
    }


}
