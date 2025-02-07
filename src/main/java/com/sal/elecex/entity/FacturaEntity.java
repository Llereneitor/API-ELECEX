package com.sal.elecex.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "facturas")
public class FacturaEntity {

    @Id
    private Integer facturaId;
    private Integer clienteId;
    private Integer proveedorId;
    private LocalDateTime fechaCreacion;
    private BigDecimal importeTotal;
    private String estado;
    private String tipoPago;
    private String tiempoEntrePlazos;
    private Integer totalPlazosPago;

}
