package com.syscal.apisyscal.service;

import com.syscal.apisyscal.exception.BusinessException;
import com.syscal.apisyscal.model.entity.CategoryEntity;
import com.syscal.apisyscal.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;


    @Override
    public List<CategoryEntity> getAll() {
        return categoryRepository.findAll();
    }

    @Override
    public CategoryEntity getOne(Integer Id) {
        Optional<CategoryEntity> category = categoryRepository.findById(Id);
        if (!category.isPresent()) {
            throw new BusinessException("Category not Exist","400", HttpStatus.NOT_FOUND);
        }
        return category.get();
    }
}
