package com.syscal.apisyscal.model.interfaces;

import com.syscal.apisyscal.model.entity.ProductEntity;

import java.util.List;

public interface ServiceCrud<T> {

    public List<T> getAll();

    public T getOne(Integer id);

    public T save(T body);

    public T update(Integer id, T body);

    public void delete( Integer id);

}
