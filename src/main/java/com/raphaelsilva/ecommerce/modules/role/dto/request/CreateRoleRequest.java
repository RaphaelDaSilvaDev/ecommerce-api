package com.raphaelsilva.ecommerce.modules.role.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CreateRoleRequest {
  @NotBlank
  String name;
}
