package com.raphaelsilva.ecommerce.modules.role;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.raphaelsilva.ecommerce.handler.NotFoundException;
import com.raphaelsilva.ecommerce.modules.role.dto.request.CreateRoleRequest;
import com.raphaelsilva.ecommerce.modules.role.mapper.RoleMapper;

@Service
public class RoleService {

  String notFoundMessage = "Role not found!";

  @Autowired
  RoleRepository roleRepository;

  @Autowired
  RoleMapper roleMapper;

  public List<Role> getAll(){
    return roleRepository.findAll();
  }

  public Role getById(String id){
    UUID roleIdUUid = UUID.fromString(id);
    return roleRepository.findById(roleIdUUid).orElseThrow(() -> {throw new NotFoundException(notFoundMessage);});
  }

  public Role getByName(String name){
    return roleRepository.findByNameIgnoreCaseContains(name).orElseThrow(() -> {throw new NotFoundException(notFoundMessage);});
  }

  public Role create(CreateRoleRequest roleRequest){
    Role role = roleMapper.createRoleRequestToRole(roleRequest);
    return roleRepository.save(role);
  }

  public Role update(CreateRoleRequest roleRequest, String roleId){
    Role role = getById(roleId);
    role.setName(roleRequest.getName());
    return roleRepository.save(role);
  }

  public void delete(String id){
    Role role = getById(id);
    roleRepository.deleteById(role.getId());
  }
}
