package com.app.twilio;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.NotBlank;

@Data @AllArgsConstructor @NoArgsConstructor @ToString
public class SmsRequest {
    private String phoneNumber;
    private String message;
}
