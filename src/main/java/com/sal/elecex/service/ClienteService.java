package com.sal.elecex.service;

import com.sal.elecex.entity.ClienteEntity;
import com.sal.elecex.model.clients.ClienteDto;

import java.util.List;

public interface ClienteService {

    List<ClienteDto> getClient(String name, String phone);

    boolean registrationClientService (ClienteDto clienteDto);

    boolean deleteClient (Integer id);
}
