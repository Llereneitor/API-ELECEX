package com.sal.elecex.service.impl;

import com.sal.elecex.Utils.UtilsService;
import com.sal.elecex.entity.ClienteEntity;
import com.sal.elecex.model.clients.ClienteDto;
import com.sal.elecex.model.exceptions.GenericMessageException;
import com.sal.elecex.repository.ClienteRepository;
import com.sal.elecex.service.ClienteService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ClienteServiceImpl implements ClienteService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UtilsService utilsService;

    @Autowired
    private ClienteRepository clienteRepository;

    @Override
    public List<ClienteDto> getClient(String name, String phone) {

        name = utilsService.returnNullIfIts(name);
        phone = utilsService.returnNullIfIts(phone);

        List<ClienteEntity> clientEntityList = clienteRepository.findByNameAndPhone(name, phone).orElseThrow( () -> new GenericMessageException("Cliente no encontrado"));

        return clientEntityList.stream().map(clienteEntity -> modelMapper.map(clienteEntity, ClienteDto.class)).toList();

    }

    @Override
    public boolean registrationClientService(ClienteDto clienteDto) {

        clienteDto.setCreatedAt(LocalDateTime.now());

        ClienteEntity clienteEntity = modelMapper.map(clienteDto, ClienteEntity.class);
        clienteRepository.save(clienteEntity);

        return Boolean.TRUE;
    }

    @Override
    public boolean deleteClient(Integer id) {

        clienteRepository.deleteById(id);
        return Boolean.TRUE;
    }
}
