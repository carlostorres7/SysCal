package com.syscal.apisyscal.service;

import com.syscal.apisyscal.exception.BusinessException;
import com.syscal.apisyscal.model.entity.ProductEntity;
import com.syscal.apisyscal.model.request.ProductRequestDTO;
import com.syscal.apisyscal.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired private ProductRepository productRepository;

    @Override
    public List<ProductEntity> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public ProductEntity getProductById(Integer id) {
        Optional<ProductEntity> product = productRepository.findById(id);
        if (!product.isPresent()) {
            throw new BusinessException("Product not Exist","404",HttpStatus.NOT_FOUND);
        }
        return product.get();
    }

    @Override
    public ProductEntity saveProduct(ProductRequestDTO data) {
        try {
            ProductEntity newProduct = new ProductEntity();
            newProduct.setName(data.getName());
            newProduct.setPrice(data.getPrice());
            newProduct.setCreatedById(data.getCreatedById());
            return productRepository.save(newProduct);
        } catch (Exception ex) {
            throw new BusinessException(ex.getCause().getCause().getMessage(), "500", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ProductEntity updateProduct(Integer id, ProductRequestDTO data) {
        ProductEntity product = getProductById(id);
        try {
            product.setId(id);
            product.setName(data.getName());
            product.setPrice(data.getPrice());
            product.setCreatedById(data.getCreatedById());
            return productRepository.save(product);
        } catch (Exception ex) {
            throw new BusinessException(ex.getCause().getCause().getMessage(), "500", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public boolean deleteProduct(Integer id) {
        ProductEntity product = getProductById(id);
        try {
            productRepository.delete(product);
            return true;
        } catch (Exception ex) {
            throw new BusinessException(ex.getCause().getCause().getMessage(), "500", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
