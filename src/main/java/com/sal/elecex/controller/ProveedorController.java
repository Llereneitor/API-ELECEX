package com.sal.elecex.controller;

import com.sal.elecex.model.proveedor.ProveedorDto;
import com.sal.elecex.service.ProveedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/elecex_proveedor")
public class ProveedorController {

    @Autowired
    ProveedorService proveedorService;

    @GetMapping("request_proveedor")
    public ResponseEntity<List<ProveedorDto>> GettingProveedor(@RequestParam(required = false) String name, @RequestParam(required = false) String phone) {

        return new ResponseEntity<>(proveedorService.getProveedor(name, phone), HttpStatus.OK);
    }

    @PostMapping("registration_proveedor")
    public ResponseEntity<Boolean> registrationProveedor(@RequestBody ProveedorDto proveedorDto) {

        return new ResponseEntity<>(proveedorService.registrationProveedorService(proveedorDto), HttpStatus.OK);
    }

    @DeleteMapping("delete_proveedor")
    public ResponseEntity<Boolean> deleteProveedor(@RequestParam Integer id) {

        return new ResponseEntity<>(proveedorService.deleteProveedor(id), HttpStatus.OK);
    }
}
