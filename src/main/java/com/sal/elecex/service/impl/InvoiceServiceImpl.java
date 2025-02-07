package com.sal.elecex.service.impl;

import com.sal.elecex.Utils.Constants;
import com.sal.elecex.entity.FacturaEntity;
import com.sal.elecex.entity.PagoEntity;
import com.sal.elecex.model.exceptions.GenericMessageException;
import com.sal.elecex.model.invoice.CreateInvoiceDto;
import com.sal.elecex.model.invoice.PaymentInvoiceDto;
import com.sal.elecex.repository.ClienteRepository;
import com.sal.elecex.repository.InvoiceRepository;
import com.sal.elecex.repository.PagoRepository;
import com.sal.elecex.repository.ProveedorRepository;
import com.sal.elecex.service.InvoiceService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;

@Service
public class InvoiceServiceImpl implements InvoiceService {

    @Autowired
    private PagoRepository pagoRepository;

    @Autowired
    private InvoiceRepository invoiceRepository;

    @Autowired
    private ClienteRepository ClienteRepository;

    @Autowired
    private ProveedorRepository ProveedorRepository;

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private ProveedorRepository proveedorRepository;

    @Override
    public Boolean createInvoice(CreateInvoiceDto request) {

        if (isInvoiceCreated(request.getInvoiceId())) {
            throw new GenericMessageException("Factura ya registrada");
        }
        request.setInvoiceDate(LocalDateTime.now());

        List<PagoEntity> pagosList = calculateParcialPayments(request);

        FacturaEntity facturaEntity = modelMapper.map(request, FacturaEntity.class);

        pagoRepository.saveAll(pagosList);
        invoiceRepository.save(facturaEntity);

        return true;
    }

    public Integer paymentInvoice(List<PaymentInvoiceDto> paymentInvoiceDto) {

        List<PagoEntity> savePeList = new ArrayList<>();
        paymentInvoiceDto.forEach( (PaymentInvoiceDto p) -> {

            PagoEntity pe = pagoRepository.paymentInvoice(p.getInvoiceId(), p.getPaymentDate(),
                                                        p.getAmount(), p.getStatus()).orElse(null);
            if (pe != null) {
                pe.setEstadoPago("A");
                savePeList.add(pe);
            } else {
                throw new GenericMessageException("Hay facturas o plazos que no existen.");
            }
        });
        pagoRepository.saveAll(savePeList);

        List<PagoEntity> isAllInvoicesPaid = pagoRepository.findAllByFacturaId(paymentInvoiceDto.get(0).getInvoiceId())
                .orElseThrow(() -> new GenericMessageException("La factura no existe."));


        AtomicBoolean isAllPaymentsPaid = new AtomicBoolean(true);

        isAllInvoicesPaid.forEach((PagoEntity p) -> {
            if("P".equalsIgnoreCase(p.getEstadoPago())) {
                isAllPaymentsPaid.set(false);
            }
        });

        if (isAllPaymentsPaid.get()) {
            FacturaEntity fe = invoiceRepository.findById(paymentInvoiceDto.get(0).getInvoiceId())
                    .orElseThrow(() -> new GenericMessageException("La factura no existe."));
            fe.setEstado("A");
            invoiceRepository.save(fe);
        }

        return savePeList.size();
    }

    @Override
    public List<PaymentInvoiceDto> getPaymentListGraficInvoice(LocalDate startDate, LocalDate endDate) {

        if (null == startDate) {
            startDate = LocalDate.now();
        }
        if (null == endDate) {
            endDate = LocalDate.now().plusMonths(1);
        }

        List<PagoEntity> listPagoEntity = pagoRepository.findAllByEstadoPagoAndFechaPagoBetween(Constants.STATUS_PENDING, startDate, endDate)
                .orElse(new ArrayList<>());

        List<PaymentInvoiceDto> pidList = listPagoEntity.stream().map(p -> modelMapper.map(p, PaymentInvoiceDto.class)).toList();

        pidList.forEach(p -> {
            FacturaEntity facturaEntity = invoiceRepository.findById(p.getInvoiceId()).orElse(null);
            if (null != facturaEntity.getClienteId()) {
                String customerName = Objects.requireNonNull(clienteRepository.findById(facturaEntity.getClienteId()).orElse(null)).getNombre();
                p.setName(customerName);
                p.setIsClient(Boolean.TRUE);
            } else {
                String proveedorName = Objects.requireNonNull(proveedorRepository.findById(facturaEntity.getProveedorId()).orElse(null)).getNombre();
                p.setName(proveedorName);
                p.setIsClient(Boolean.FALSE);
            }
        });

        return pidList;
    }


    private Boolean isInvoiceCreated(Integer invoiceId) {

        FacturaEntity fe = invoiceRepository.findById(invoiceId).orElse(null);
        return fe != null;
    }

    private List<PagoEntity> calculateParcialPayments(CreateInvoiceDto request) {

        List<PagoEntity> pagosList = new ArrayList<>();

        if (!"M".equalsIgnoreCase(request.getPaymentBetweenPayments())) {
            // logica para pagos en tiempo

            BigDecimal totalMonto = request.getTotalAmount().divide(BigDecimal.valueOf(request.getTotalTerms()), 2, RoundingMode.HALF_UP);

            for (int i = 0; i < request.getTotalTerms(); i++) {

                PagoEntity pago = new PagoEntity();
                pago.setFacturaId(request.getInvoiceId());
                pago.setMonto(totalMonto);
                pago.setFechaPago(setMonthPaymentDate(request.getInvoiceDate(), i));
                pago.setEstadoPago(i == 0 ? "A" : "P");

                pagosList.add(pago);
            }


        } else {
            PagoEntity pago = new PagoEntity();
            pago.setFacturaId(request.getInvoiceId());
            pago.setMonto(request.getTotalAmount());
            pago.setFechaPago(LocalDate.now());
            pago.setEstadoPago("A");

            pagosList.add(pago);
        }

        return pagosList;
    }

    private LocalDate setMonthPaymentDate(LocalDateTime date, Integer number) {
        return date.plusMonths(number).toLocalDate();
    }

}
