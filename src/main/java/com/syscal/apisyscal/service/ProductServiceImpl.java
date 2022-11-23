package com.syscal.apisyscal.service;

import com.syscal.apisyscal.exception.BusinessException;
import com.syscal.apisyscal.model.entity.ProductEntity;
import com.syscal.apisyscal.model.request.ProductRequestDTO;
import com.syscal.apisyscal.model.response.ProductResponseDTO;
import com.syscal.apisyscal.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired private ProductRepository productRepository;

    @Override
    public List<ProductResponseDTO> getAll() {
        List<ProductEntity> products = productRepository.findAll();
        List<ProductResponseDTO> productsDTO = new ArrayList<>();
        products.stream().forEach(product -> {
            ProductResponseDTO productDto = new ProductResponseDTO();
            productDto.setId(product.getId());
            productDto.setName(product.getName());
            productDto.setPrice(product.getPrice());
            productDto.setCreatedbyid(product.getCreatedById());
            productDto.setCreatedAt(product.getCreatedAt());
            productDto.setUpdatedAt(product.getUpdatedAt());
            productsDTO.add(productDto);
        });
        return productsDTO;
    }

    @Override
    public ProductEntity getOne(Integer id) {
        Optional<ProductEntity> product = productRepository.findById(id);
        if (!product.isPresent()) {
            throw new BusinessException("Product not Exist","404",HttpStatus.NOT_FOUND);
        }
        return product.get();
    }

    @Override
    public ProductEntity save(ProductRequestDTO body) {
        try {
            ProductEntity newProduct = new ProductEntity();
            newProduct.setName(body.getName());
            newProduct.setPrice(body.getPrice());
            newProduct.setCreatedById(body.getCreatedbyid());
            return productRepository.save(newProduct);
        } catch (Exception ex) {
            throw new BusinessException(ex.getCause().getCause().getMessage(), "500", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ProductEntity update(Integer id, ProductRequestDTO body) {
        ProductEntity product = getOne(id);
        try {
            product.setId(id);
            product.setName(body.getName());
            product.setPrice(body.getPrice());
            product.setCreatedById(body.getCreatedbyid());
            return productRepository.save(product);
        } catch (Exception ex) {
            throw new BusinessException(ex.getCause().getCause().getMessage(), "500", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public void delete(Integer id) {
        ProductEntity product = getOne(id);
        try {
            productRepository.delete(product);
        } catch (Exception ex) {
            throw new BusinessException(ex.getCause().getCause().getMessage(), "500", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
