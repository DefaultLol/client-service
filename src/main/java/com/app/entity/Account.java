package com.app.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Transient;

import java.util.Date;

@Data @AllArgsConstructor @NoArgsConstructor @ToString
public class Account {
    private String id;
    private String accountNumber;
    private double amount;
    private double credit;
    @Transient
    private String strCreationDate;
    private Date creationDate;
    private String accountType;
}
