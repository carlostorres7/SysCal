package com.syscal.apisyscal.service;

import com.syscal.apisyscal.model.entity.ProductEntity;
import com.syscal.apisyscal.model.request.ProductRequestDTO;

import java.util.List;

public interface ProductService {

    public List<ProductEntity> getAllProducts();

    public ProductEntity getProductById(Integer id);

    public ProductEntity saveProduct(ProductRequestDTO data);

    public ProductEntity updateProduct(Integer id, ProductRequestDTO data);

    public boolean deleteProduct(Integer id);

}
