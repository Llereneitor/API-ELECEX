package com.sal.elecex.service;

import com.sal.elecex.model.proveedor.ProveedorDto;

import java.util.List;

public interface ProveedorService {

    List<ProveedorDto> getProveedor(String name, String phone);

    boolean registrationProveedorService (ProveedorDto proveedorDto);

    boolean deleteProveedor (Integer id);
}
