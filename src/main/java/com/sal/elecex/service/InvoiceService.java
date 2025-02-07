package com.sal.elecex.service;

import com.sal.elecex.model.invoice.CreateInvoiceDto;
import com.sal.elecex.model.invoice.PaymentInvoiceDto;

import java.time.LocalDate;
import java.util.List;

public interface InvoiceService {

    Boolean createInvoice(CreateInvoiceDto request);

    Integer paymentInvoice(List<PaymentInvoiceDto> paymentInvoiceDto);

    List<PaymentInvoiceDto> getPaymentListGraficInvoice(LocalDate startDate, LocalDate endDate);
}
