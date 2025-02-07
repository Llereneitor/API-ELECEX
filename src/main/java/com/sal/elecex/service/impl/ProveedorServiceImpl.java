package com.sal.elecex.service.impl;

import com.sal.elecex.Utils.UtilsService;
import com.sal.elecex.entity.ProveedorEntity;
import com.sal.elecex.model.exceptions.GenericMessageException;
import com.sal.elecex.model.proveedor.ProveedorDto;
import com.sal.elecex.repository.ProveedorRepository;
import com.sal.elecex.service.ProveedorService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ProveedorServiceImpl implements ProveedorService {

    @Autowired
    private ProveedorRepository proveedorRepository;

    @Autowired
    private UtilsService utilsService;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<ProveedorDto> getProveedor(String name, String phone) {

        name = utilsService.returnNullIfIts(name);
        phone = utilsService.returnNullIfIts(phone);

        List<ProveedorEntity> proveedorEntityList = proveedorRepository.findByNameAndPhone(name, phone).orElseThrow( () -> new GenericMessageException("Proveedor no encontrado"));

        return proveedorEntityList.stream().map(proEntity -> modelMapper.map(proEntity, ProveedorDto.class)).toList();

    }

    @Override
    public boolean registrationProveedorService(ProveedorDto proveedorDto) {

        proveedorDto.setCreatedAt(LocalDateTime.now());
        ProveedorEntity proveedorEntity = modelMapper.map(proveedorDto, ProveedorEntity.class);

        proveedorRepository.save(proveedorEntity);

        return Boolean.TRUE;
    }

    @Override
    public boolean deleteProveedor(Integer id) {
        proveedorRepository.deleteById(id);
        return Boolean.TRUE;
    }
}
