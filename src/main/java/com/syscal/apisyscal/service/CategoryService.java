package com.syscal.apisyscal.service;

import com.syscal.apisyscal.model.entity.CategoryEntity;

import java.util.List;

public interface CategoryService {

    public List<CategoryEntity> getAll();

    public CategoryEntity getOne(Integer Id);

}
