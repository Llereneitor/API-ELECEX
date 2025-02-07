package com.sal.elecex.repository;

import com.sal.elecex.entity.ClienteEntity;
import com.sal.elecex.entity.ProveedorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProveedorRepository extends JpaRepository<ProveedorEntity, Integer> {

    @Query(
            "SELECT p FROM ProveedorEntity p " +
                    "WHERE (:name IS NULL OR LOWER(p.nombre) LIKE LOWER(CONCAT('%', :name, '%'))) " +
                    "AND (:phone IS NULL OR p.telefono = :phone)"
    )
    Optional<List<ProveedorEntity>> findByNameAndPhone (@Param("name") String name, @Param("phone") String phone);

}
