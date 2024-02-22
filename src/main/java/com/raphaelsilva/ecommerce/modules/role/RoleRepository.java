package com.raphaelsilva.ecommerce.modules.role;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, UUID>  {

  Optional<Role> findByNameIgnoreCaseContains(String name);
  
}
