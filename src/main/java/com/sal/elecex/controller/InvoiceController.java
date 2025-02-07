package com.sal.elecex.controller;

import com.sal.elecex.model.invoice.CreateInvoiceDto;
import com.sal.elecex.model.invoice.PaymentInvoiceDto;
import com.sal.elecex.model.login.LoginDto;
import com.sal.elecex.service.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/invoice_elecex")
public class InvoiceController {

    @Autowired
    private InvoiceService invoiceService;

    @PostMapping("create_invoice")
    public ResponseEntity<Boolean> createInvoiceController(@RequestBody CreateInvoiceDto createInvoice) {

        return new ResponseEntity<>(invoiceService.createInvoice(createInvoice), HttpStatus.OK);
    }

    @PutMapping("payment_invoice")
    public ResponseEntity<Integer> paymentInvoiceController(@RequestBody List<PaymentInvoiceDto> paymentInvoiceDto) {

        return new ResponseEntity<>(invoiceService.paymentInvoice(paymentInvoiceDto), HttpStatus.OK);
    }

    @GetMapping("grafic_invoice")
    public ResponseEntity<List<PaymentInvoiceDto>> gettingDataGraficInvoice(@RequestParam(required = false) LocalDate startDate,
                                                                             @RequestParam(required = false) LocalDate endDate) {

        return new ResponseEntity<>(invoiceService.getPaymentListGraficInvoice(startDate, endDate), HttpStatus.OK);
    }

}
