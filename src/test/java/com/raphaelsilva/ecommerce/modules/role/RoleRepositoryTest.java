package com.raphaelsilva.ecommerce.modules.role;

import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import jakarta.persistence.EntityManager;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ActiveProfiles("test")
public class RoleRepositoryTest {

  @Autowired
  RoleRepository roleRepository;

  @Autowired
  EntityManager entityManager;

  String roleName = "ADMIN";

  @Test
  @DisplayName("Should be able to find a role by full name and lower case")
  void testFindByFullNameIgnoreCaseSuccess() {
    createRole(roleName);
    
    Optional<Role> getRole = roleRepository.findByNameIgnoreCaseContains(roleName.toLowerCase());

    assertThat(getRole.get()).isNotNull();
    assertThat(getRole.get().getName()).isEqualTo(roleName);
  }

  @Test
  @DisplayName("Should be able to find a role by half name")
  void testFindByHalfNameIgnoreCaseSuccess(){
    String roleHalfName = "ADM";
    createRole(roleName);
    
    Optional<Role> getRole = roleRepository.findByNameIgnoreCaseContains(roleHalfName);

    assertThat(getRole.get()).isNotNull();
    assertThat(getRole.get().getName()).isEqualTo(roleName);
  }

  @Test
  @DisplayName("Should not be able to find a non existent role")
  void testFindByNameException(){
    Optional<Role> getRole = roleRepository.findByNameIgnoreCaseContains(roleName);

    assertThat(getRole.isEmpty()).isTrue();
  }

  private Role createRole(String roleName){
    Role role = new Role(roleName);
    entityManager.persist(role);
    return role;
  }
}
