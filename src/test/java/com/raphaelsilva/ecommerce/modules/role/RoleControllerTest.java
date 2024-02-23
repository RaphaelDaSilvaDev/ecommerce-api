package com.raphaelsilva.ecommerce.modules.role;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.raphaelsilva.ecommerce.modules.role.dto.request.CreateRoleRequest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@WebMvcTest(RoleController.class)
public class RoleControllerTest {

  @MockBean
  private RoleService roleService;

  @Autowired
  private MockMvc mockMvc;

  private UUID id = UUID.fromString("0912df66-ad8e-4923-bc9e-c2e8d82b6dbb");
  private String name = "ADMIN";
  private Role role = new Role(id, name, LocalDateTime.now(), LocalDateTime.now());

  @Test
  @DisplayName("Should be able to create a role")
  void testCreateRole() throws Exception {
    CreateRoleRequest request = new CreateRoleRequest(name);

    Mockito.when(roleService.create(request)).thenReturn(role);
    
    mockMvc.perform(post("/api/role").contentType(MediaType.APPLICATION_JSON).content("{ \"name\": \"Admin\"}")).andDo(print()).andExpect(status().isCreated());
  }

  @Test
  @DisplayName("Should be able to delete a role")
  void testDeleteRole() throws Exception {
    mockMvc.perform(delete("/api/role/{id}", id)).andDo(print()).andExpect(status().isNoContent());
  }

  @Test
  @DisplayName("Should be able to list all roles or a empty list")
  void testGetAll() throws Exception {
    List<Role> roles = List.of(role);

    Mockito.when(roleService.getAll()).thenReturn(roles);

    mockMvc.perform(get("/api/role/all")).andDo(print()).andExpect(status().isOk()).andExpect(content().json("[{\"id\":0912df66-ad8e-4923-bc9e-c2e8d82b6dbb,\"name\":ADMIN,\"created_at\":null,\"updated_at\":null}]"));
  }

  @Test
  @DisplayName("Should be able to get a role by id")
  void testGetRoleByIdRole() throws Exception {
    Mockito.when(roleService.getById(id.toString())).thenReturn(role);

    mockMvc.perform(get("/api/role/{id}", id)).andDo(print()).andExpect(status().isOk());
  }

  @Test
  @DisplayName("Should be able to get a role by name")
  void testGetRoleByName() throws Exception {
    Mockito.when(roleService.getByName(name)).thenReturn(role);

    mockMvc.perform(get("/api/role", name)).andDo(print()).andExpect(status().isOk());
  }

  @Test
  @DisplayName("Should be able to update a role")
  void updateRole() throws Exception {
    String newName = "User";
    CreateRoleRequest request = new CreateRoleRequest(newName);
    role.setName(newName);
    Mockito.when(roleService.update(request, id.toString())).thenReturn(role);

    mockMvc.perform(put("/api/role").contentType(MediaType.APPLICATION_JSON).content("{\"name\": \"User\"}").accept(MediaType.APPLICATION_JSON)).andDo(print()).andExpect(status().isOk()).andExpect(jsonPath("$.name").value(newName));
  }
}
