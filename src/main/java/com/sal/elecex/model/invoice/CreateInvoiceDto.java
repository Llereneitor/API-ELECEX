package com.sal.elecex.model.invoice;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class CreateInvoiceDto {

    private Integer invoiceId;
    private Integer clientId;
    private Integer proveedorId;
    private LocalDateTime invoiceDate;
    private BigDecimal totalAmount;
    private String status;
    private String paymentType;
    private String paymentBetweenPayments;
    private Integer totalTerms;

}
