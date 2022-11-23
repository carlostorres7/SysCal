package com.syscal.apisyscal.service;

import com.syscal.apisyscal.model.entity.ProductEntity;
import com.syscal.apisyscal.model.request.ProductRequestDTO;
import com.syscal.apisyscal.model.response.ProductResponseDTO;

import java.util.List;

public interface ProductService {

    public List<ProductResponseDTO> getAll();

    public ProductEntity getOne(Integer id);

    public ProductEntity save(ProductRequestDTO body);

    public ProductEntity update(Integer id, ProductRequestDTO body);

    public void delete( Integer id);

}
