package com.sal.elecex.model.proveedor;

import jakarta.validation.constraints.Email;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class ProveedorDto {
    private Integer idProveedor;
    private String name;
    private String address;
    @Email(message = "el email no es correcto")
    private String email;
    private String phone;
    private LocalDateTime createdAt;
}
