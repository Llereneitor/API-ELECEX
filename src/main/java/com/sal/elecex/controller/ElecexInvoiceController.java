package com.sal.elecex.controller;

import com.sal.elecex.model.login.LoginDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/elecex_invoice")
public class ElecexInvoiceController {

    @GetMapping("grafic_invoice")
    public ResponseEntity<List<LoginDto>> gettingDataGrafictInvoice(String user) {

        return new ResponseEntity<>(null, HttpStatus.OK);
    }
}
