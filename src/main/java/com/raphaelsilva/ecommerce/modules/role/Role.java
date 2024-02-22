package com.raphaelsilva.ecommerce.modules.role;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity(name = "rules")
@Data
public class Role {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  UUID id;

  @Column(nullable = false, unique = true)
  String name;

  @CreationTimestamp
  LocalDateTime created_at;

  @UpdateTimestamp
  LocalDateTime updated_at;

  public Role() {
  }

  public Role(String name) {
    this.name = name;
  }

  public Role(UUID id, String name, LocalDateTime created_at, LocalDateTime updated_at) {
    this.id = id;
    this.name = name;
    this.created_at = created_at;
    this.updated_at = updated_at;
  }
}
