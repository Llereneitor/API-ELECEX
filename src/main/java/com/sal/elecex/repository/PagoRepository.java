package com.sal.elecex.repository;

import com.sal.elecex.entity.PagoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface PagoRepository extends JpaRepository<PagoEntity, Integer> {

    @Query(
            "SELECT c from PagoEntity c " +
            "WHERE :invoiceId = c.facturaId " +
            "AND :paymentDate = c.fechaPago " +
            "AND :status = c.estadoPago " +
            "AND :amount = c.monto"
    )
    Optional<PagoEntity> paymentInvoice(Integer invoiceId, LocalDate paymentDate, BigDecimal amount, String status);

    Optional<List<PagoEntity>> findAllByFacturaId(Integer invoiceId);

    Optional<List<PagoEntity>> findAllByEstadoPagoAndFechaPagoBetween(String status, LocalDate startDate, LocalDate endDate);
}
