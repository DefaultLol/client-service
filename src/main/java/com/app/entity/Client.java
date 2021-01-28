package com.app.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Data @AllArgsConstructor @NoArgsConstructor @ToString
@Document(collection = "clients")
public class Client {
    @Id
    private String id;
    private String firstName;
    private String lastName;
    private String address;
    private String cin;
    @NotNull(message = "Email should not be null")
    @Email
    private String email;
    @NotNull(message = "Phone should not be null")
    private String tel;
    private Long userID;
    private String accountID;
    private String agentID;
    @Transient
    private Account account;
    @Transient
    private Agent agent;
}
