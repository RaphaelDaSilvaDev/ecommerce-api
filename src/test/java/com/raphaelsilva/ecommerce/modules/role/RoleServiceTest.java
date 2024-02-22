package com.raphaelsilva.ecommerce.modules.role;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.raphaelsilva.ecommerce.handler.NotFoundException;
import com.raphaelsilva.ecommerce.modules.role.dto.request.CreateRoleRequest;
import com.raphaelsilva.ecommerce.modules.role.mapper.RoleMapper;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@ExtendWith(SpringExtension.class)
public class RoleServiceTest {

  @Mock
  private RoleRepository roleRepository;

  @Mock
  private RoleMapper roleMapper;

  @InjectMocks
  private RoleService roleService;

  private String roleName = "ADMIN";
  private Role role = new Role(UUID.fromString("9105b4c6-dac6-4c9b-93aa-f93dd206b237"), roleName, LocalDateTime.now(), LocalDateTime.now());

  @Test
  @DisplayName("Should be able to create a role")
  void testCreate() {
    CreateRoleRequest roleRequest = new CreateRoleRequest(roleName);

    when(roleRepository.save(any())).thenReturn(new Role(roleName));

    Role createdRole = roleService.create(roleRequest);

    verify(roleRepository, times(1)).save(any());
    assertThat(createdRole.getName()).isEqualTo(roleName);
  }

  @Test
  @DisplayName("Should be not able to create role with same name")
  void testCreateWithSameName(){
    CreateRoleRequest roleRequest = new CreateRoleRequest(roleName);

    when(roleRepository.save(any())).thenThrow(DataIntegrityViolationException.class);

    assertThatThrownBy(() -> roleService.create(roleRequest)).isInstanceOf(DataIntegrityViolationException.class);
  }

  @Test
  @DisplayName("Should be able to delete a role")
  void testDelete() {
    doNothing().when(roleRepository).deleteById(role.getId());

    roleService.delete(role.getId().toString());

    verify(roleRepository, times(1)).deleteById(any());
  }

  @Test
  @DisplayName("Should be able to get all role or a empty list")
  void testGetAll() {
    when(roleRepository.findAll()).thenReturn(List.of(role));

    List<Role> roles = roleService.getAll();

    verify(roleRepository, times(1)).findAll();
    assertThat(roles.size()).isEqualTo(1);
    assertThat(roles.get(0).getName()).isEqualTo(role.getName());
  }

  @Test
  @DisplayName("Should be able to get a role by id")
  void testGetById() {
    String id = role.getId().toString();
    when(roleRepository.findById(role.getId())).thenReturn(Optional.of(role));

    Role getRole = roleService.getById(id);

    verify(roleRepository, times(1)).findById(any());
    assertThat(getRole.getName()).isEqualTo(role.getName());
  }

  @Test
  @DisplayName("Should be not able to get a role by non existent id")
  void testGetByNonExistentId(){
    String id = role.getId().toString();
    when(roleRepository.findById(role.getId())).thenThrow(NotFoundException.class);

    assertThatThrownBy(() -> roleService.getById(id)).isInstanceOf(NotFoundException.class);
  }
  
  @Test
  @DisplayName("Should be abe to get a role by name")
  void testGetByName() {
    when(roleRepository.findByNameIgnoreCaseContains(role.getName())).thenReturn(Optional.of(role));

    Role getRole = roleService.getByName(roleName);
    
    verify(roleRepository, times(1)).findByNameIgnoreCaseContains(any());
    assertThat(getRole.getName()).isEqualTo(role.getName());
  }

  @Test
  @DisplayName("Should be not able to get a role by non existent name")
  void testGetByNonExistentName(){
    when(roleRepository.findByNameIgnoreCaseContains(role.getName())).thenThrow(NotFoundException.class);

    assertThatThrownBy(() ->  roleService.getByName(roleName)).isInstanceOf(NotFoundException.class);
  }

  @Test
  @DisplayName("Should be abe to update a role")
  void testUpdate() {
      String newName = "USER";
      CreateRoleRequest roleRequest = new CreateRoleRequest(newName);
      Role updatedRole = new Role(UUID.fromString("9105b4c6-dac6-4c9b-93aa-f93dd206b237"), newName, LocalDateTime.now(), LocalDateTime.now());

      when(roleRepository.findById(role.getId())).thenReturn(Optional.of(role));
      when(roleRepository.save(any())).thenReturn(updatedRole);

      Role getRole = roleService.update(roleRequest, role.getId().toString());

      verify(roleRepository, times(1)).save(any());
      assertThat(getRole.getName()).isEqualTo(newName);
    }
}
