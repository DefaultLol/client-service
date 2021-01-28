package com.app.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data @AllArgsConstructor @NoArgsConstructor @ToString
public class Agent {
    private String id;
    private String first_name;
    private String last_name;
    private String phone_number;
    private int patente_number;
    private int identity_number;
    private String email;
    private Agency agency;

}
