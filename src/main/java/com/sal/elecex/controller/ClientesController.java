package com.sal.elecex.controller;

import com.sal.elecex.model.clients.ClienteDto;
import com.sal.elecex.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/elecex_customer")
public class ClientesController {

    @Autowired
    ClienteService clienteService;

    @GetMapping("request_customer")
    public ResponseEntity<List<ClienteDto>> GettingClient(@RequestParam(required = false) String name, @RequestParam(required = false) String phone) {

        return new ResponseEntity<>(clienteService.getClient(name, phone), HttpStatus.OK);
    }

    @PostMapping("registration_customer")
    public ResponseEntity<Boolean> registrationClient(@RequestBody ClienteDto clienteDto) {

        return new ResponseEntity<>(clienteService.registrationClientService(clienteDto), HttpStatus.OK);
    }

    @DeleteMapping("delete_customer")
    public ResponseEntity<Boolean> deleteCustomer(@RequestParam Integer id) {

        return new ResponseEntity<>(clienteService.deleteClient(id), HttpStatus.OK);
    }
}
