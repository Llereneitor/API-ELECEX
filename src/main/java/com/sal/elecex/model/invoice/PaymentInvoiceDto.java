package com.sal.elecex.model.invoice;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class PaymentInvoiceDto {

    private Integer invoiceId;
    private String name;
    private Boolean isClient;
    private LocalDate paymentDate;
    private BigDecimal amount;
    private String status;
}
