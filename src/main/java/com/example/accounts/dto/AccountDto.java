package com.example.accounts.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class AccountDto {

    @NotEmpty(message = "AccountNumber cannot be a null or empty")
    @Size(min=5,max=30,message = "Account Number must be 10 digits")
    private Long accountNumber;

    @NotEmpty(message = "AccountTYpe cannot be a null or empty")
    private String accountType;

    @NotEmpty(message = "BranchAddress cannot be a null or empty")
    private String branchAddress;
}
