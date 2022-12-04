package com.syscal.apisyscal.service;

import com.syscal.apisyscal.model.entity.ClientEntity;
import com.syscal.apisyscal.model.request.ClientRequestDTO;
import com.syscal.apisyscal.model.response.ClientResponseDTO;

import java.util.List;

public interface ClientService {

    public List<ClientResponseDTO> getAll();

    public ClientEntity getOne(Integer Id);

    public List<ClientResponseDTO> searchClient(String value);

    public ClientEntity save(ClientRequestDTO body);

    public ClientEntity update(Integer Id, ClientRequestDTO body);

    public void delete(Integer Id);

}
