package com.syscal.apisyscal.service;

import com.syscal.apisyscal.exception.BusinessException;
import com.syscal.apisyscal.model.entity.ClientEntity;
import com.syscal.apisyscal.model.entity.ServiceEntity;
import com.syscal.apisyscal.model.request.ClientRequestDTO;
import com.syscal.apisyscal.model.response.ClientResponseDTO;
import com.syscal.apisyscal.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ClientServiceImpl implements ClientService {

    @Autowired
    private ClientRepository clientRepository;

    @Override
    public List<ClientResponseDTO> getAll() {
        List<ClientResponseDTO> clientsDTO = new ArrayList<>();
        List<ClientEntity> clients = clientRepository.findAll();
        clients.stream().forEach( client -> {
            clientsDTO.add(new ClientResponseDTO(client));
        });
        return clientsDTO;
    }

    @Override
    public ClientEntity getOne(Integer Id) {
        Optional<ClientEntity> client = clientRepository.findById(Id);
        if (!client.isPresent()) {
            throw new BusinessException("Client not Exist","404",HttpStatus.NOT_FOUND);
        }
        return client.get();
    }

    @Override
    public List<ClientResponseDTO> searchClient(String value) {
        List<ClientResponseDTO> clientsDTO = new ArrayList<>();
        List<ClientEntity> clients = clientRepository.findAllByPnameOrPlastnameOrCedula(value,value,value);
        clients.stream().forEach( client -> {
            System.out.println(client);
            clientsDTO.add(new ClientResponseDTO(client));
        });
        return clientsDTO;
    }

    @Override
    public ClientEntity save(ClientRequestDTO body) {
        ClientEntity client = convertDtoToEntity(body);
        try {
            return clientRepository.save(client);
        } catch (Exception ex) {
            throw new BusinessException(ex.getCause().getCause().getMessage(), "500", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ClientEntity update(Integer Id, ClientRequestDTO body) {
        ClientEntity client = getOne(Id);
        try {
            ClientEntity updateClient = convertExistToEntity(client,body);
            return clientRepository.save(updateClient);
        } catch (Exception ex) {
            throw new BusinessException(ex.getCause().getCause().getMessage(), "500", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public void delete(Integer Id) {
        ClientEntity client = getOne(Id);
        try {
            clientRepository.delete(client);
        } catch (Exception ex) {
            throw new BusinessException(ex.getCause().getCause().getMessage(), "500", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private ClientEntity convertDtoToEntity(ClientRequestDTO clientDto) {
        ClientEntity client = new ClientEntity();
        client.setPname(clientDto.getPname());
        client.setSname(clientDto.getSname());
        client.setPlastname(clientDto.getPlastname());
        client.setSlastname(clientDto.getSlastname());
        client.setCedula(clientDto.getCedula());
        client.setPhone(clientDto.getPhone());
        client.setEmail(clientDto.getEmail());
        client.setAddress(clientDto.getAddress());
        return client;
    }

    private ClientEntity convertExistToEntity(ClientEntity client, ClientRequestDTO clientDto) {
        client.setPname(clientDto.getPname());
        client.setSname(clientDto.getSname());
        client.setPlastname(clientDto.getPlastname());
        client.setSlastname(clientDto.getSlastname());
        client.setCedula(clientDto.getCedula());
        client.setPhone(clientDto.getPhone());
        client.setEmail(clientDto.getEmail());
        client.setAddress(clientDto.getAddress());
        return client;
    }
}
