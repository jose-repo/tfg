package com.udima.tfg.model;


import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UserData {
    private String username;
    private String password;
    private String name;
    private String token;
    private LocalDateTime timeOut;
}
