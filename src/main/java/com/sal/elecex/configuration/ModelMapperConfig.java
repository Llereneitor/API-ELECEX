package com.sal.elecex.configuration;

import com.sal.elecex.entity.ClienteEntity;
import com.sal.elecex.entity.FacturaEntity;
import com.sal.elecex.entity.PagoEntity;
import com.sal.elecex.entity.ProveedorEntity;
import com.sal.elecex.model.clients.ClienteDto;
import com.sal.elecex.model.invoice.CreateInvoiceDto;
import com.sal.elecex.model.invoice.PaymentInvoiceDto;
import com.sal.elecex.model.proveedor.ProveedorDto;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper() {

        ModelMapper modelMapper = new ModelMapper();
         modelMapper.getConfiguration()
                   .setMatchingStrategy(MatchingStrategies.STANDARD)
                   .setSkipNullEnabled(true);

        modelMapper.typeMap(ClienteDto.class, ClienteEntity.class).addMappings(mapper -> {
            mapper.map(ClienteDto::getName, ClienteEntity::setNombre);
            mapper.map(ClienteDto::getAddress, ClienteEntity::setDireccion);
            mapper.map(ClienteDto::getPhone, ClienteEntity::setTelefono);
            mapper.map(ClienteDto::getCreatedAt, ClienteEntity::setFechaCreacion);
        });

        modelMapper.typeMap(ClienteEntity.class, ClienteDto.class).addMappings(mapper -> {
            mapper.map(ClienteEntity::getNombre, ClienteDto::setName);
            mapper.map(ClienteEntity::getTelefono, ClienteDto::setPhone);
            mapper.map(ClienteEntity::getDireccion, ClienteDto::setAddress);
            mapper.map(ClienteEntity::getId, ClienteDto::setIdCliente);
            mapper.map(ClienteEntity::getFechaCreacion, ClienteDto::setCreatedAt);
        });

        modelMapper.typeMap(ProveedorDto.class, ProveedorEntity.class).addMappings(mapper -> {
            mapper.map(ProveedorDto::getName, ProveedorEntity::setNombre);
            mapper.map(ProveedorDto::getAddress, ProveedorEntity::setDireccion);
            mapper.map(ProveedorDto::getPhone, ProveedorEntity::setTelefono);
            mapper.map(ProveedorDto::getCreatedAt, ProveedorEntity::setFechaCreacion);
        });

        modelMapper.typeMap(ProveedorEntity.class, ProveedorDto.class).addMappings(mapper -> {
            mapper.map(ProveedorEntity::getNombre, ProveedorDto::setName);
            mapper.map(ProveedorEntity::getTelefono, ProveedorDto::setPhone);
            mapper.map(ProveedorEntity::getDireccion, ProveedorDto::setAddress);
            mapper.map(ProveedorEntity::getId, ProveedorDto::setIdProveedor);
            mapper.map(ProveedorEntity::getFechaCreacion, ProveedorDto::setCreatedAt);
        });

        modelMapper.typeMap(CreateInvoiceDto.class, FacturaEntity.class).addMappings(mapper -> {
            mapper.map( CreateInvoiceDto::getInvoiceId , FacturaEntity::setFacturaId);
            mapper.map( CreateInvoiceDto::getClientId , FacturaEntity::setClienteId);
            mapper.map( CreateInvoiceDto::getProveedorId , FacturaEntity::setProveedorId);
            mapper.map( CreateInvoiceDto::getInvoiceDate , FacturaEntity::setFechaCreacion);
            mapper.map( CreateInvoiceDto::getTotalAmount , FacturaEntity::setImporteTotal);
            mapper.map( CreateInvoiceDto::getStatus , FacturaEntity::setEstado);
            mapper.map( CreateInvoiceDto::getPaymentType , FacturaEntity::setTipoPago);
            mapper.map( CreateInvoiceDto::getPaymentBetweenPayments , FacturaEntity::setTiempoEntrePlazos);
            mapper.map( CreateInvoiceDto::getTotalTerms , FacturaEntity::setTotalPlazosPago);
        });

        modelMapper.typeMap(PagoEntity.class, PaymentInvoiceDto.class).addMappings(mapper -> {
            mapper.map( PagoEntity::getFacturaId , PaymentInvoiceDto::setInvoiceId);
            mapper.map( PagoEntity::getFechaPago , PaymentInvoiceDto::setPaymentDate);
            mapper.map( PagoEntity::getEstadoPago , PaymentInvoiceDto::setStatus);
            mapper.map( PagoEntity::getMonto , PaymentInvoiceDto::setAmount);
        });

        return modelMapper;
    }
}
