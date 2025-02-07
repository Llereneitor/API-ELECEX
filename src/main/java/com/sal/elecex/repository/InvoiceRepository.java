package com.sal.elecex.repository;

import com.sal.elecex.entity.FacturaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvoiceRepository extends JpaRepository<FacturaEntity, Integer> {
}
