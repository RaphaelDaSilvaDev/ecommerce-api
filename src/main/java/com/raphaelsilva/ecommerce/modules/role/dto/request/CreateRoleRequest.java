package com.raphaelsilva.ecommerce.modules.role.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CreateRoleRequest {
  @NotBlank
  String name;

  public CreateRoleRequest() {
  }

  public CreateRoleRequest(@NotBlank String name) {
    this.name = name;
  }

  
}
