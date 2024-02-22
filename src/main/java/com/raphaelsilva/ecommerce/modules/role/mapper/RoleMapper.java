package com.raphaelsilva.ecommerce.modules.role.mapper;

import org.springframework.stereotype.Controller;

import com.raphaelsilva.ecommerce.modules.role.Role;
import com.raphaelsilva.ecommerce.modules.role.dto.request.CreateRoleRequest;

@Controller
public class RoleMapper {
  public Role createRoleRequestToRole(CreateRoleRequest roleRequest){
    return new Role(
      roleRequest.getName()
    );
  }
}
