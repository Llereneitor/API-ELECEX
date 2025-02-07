package com.sal.elecex.repository;

import com.sal.elecex.entity.ClienteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClienteRepository extends JpaRepository<ClienteEntity, Integer> {

    @Query(
            "SELECT c FROM ClienteEntity c " +
            "WHERE (:name IS NULL OR LOWER(c.nombre) LIKE LOWER(CONCAT('%', :name, '%'))) " +
            "AND (:phone IS NULL OR c.telefono = :phone)"
    )
    Optional<List<ClienteEntity>> findByNameAndPhone (@Param("name") String name,  @Param("phone") String phone);
}
