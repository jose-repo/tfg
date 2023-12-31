package com.udima.tfg.entitiy;


import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Entity
@Table(name = "USERENTITY")
public class UserEntity {
    @Id
    private String id;
    private String username;
    private String password;
    private String name;
    private String token;
    @Column(name = "CREATEDAT")
    private LocalDateTime createdAt;

    public UserEntity() {

    }
}
