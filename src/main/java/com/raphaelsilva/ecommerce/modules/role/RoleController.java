package com.raphaelsilva.ecommerce.modules.role;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.raphaelsilva.ecommerce.modules.role.dto.request.CreateRoleRequest;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/api/role")
public class RoleController {
 
  @Autowired
  RoleService roleService;

  @GetMapping("/all")
  public ResponseEntity<List<Role>> getAll(){
    List<Role> allRoles = roleService.getAll();
    return ResponseEntity.ok().body(allRoles);
  }

  @GetMapping("/{id}")
  public ResponseEntity<Role> getRoleByIdRole(@PathVariable(name = "id") String roleId) {
    Role role = roleService.getById(roleId);
    return ResponseEntity.ok().body(role);
  }

  @GetMapping()
  public ResponseEntity<Role> getRoleByName(@RequestParam(name = "name") String name) {
    Role role = roleService.getByName(name);
    return ResponseEntity.ok().body(role);
  }

  @PostMapping()
  public ResponseEntity<Role> createRole(@RequestBody CreateRoleRequest roleRequest, UriComponentsBuilder uriBuilder) {
    Role createdRole = roleService.create(roleRequest);
    URI uri = uriBuilder.path("/api/"+createdRole.getId()).build().toUri();
    return ResponseEntity.created(uri).body(createdRole);
  }

  @PutMapping("/{id}")
  public ResponseEntity<Role> putMethodName(@PathVariable String id, @RequestBody CreateRoleRequest roleRequest) {
    Role updatedRole = roleService.update(roleRequest, id);
    return ResponseEntity.ok().body(updatedRole);
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(code = HttpStatus.NO_CONTENT)
  public void deleteRole(@PathVariable String id){
    roleService.delete(id);
  }
  
}
