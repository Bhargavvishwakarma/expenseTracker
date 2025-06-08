package com.bhargav.expenses.dto;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@Component
public class LoginDto {
    private Boolean error;
    private String errorMsg;
    private String JwtToken;
}
