package com.example.accounts.service;

import com.example.accounts.dto.AccountDto;
import com.example.accounts.dto.CustomerDto;
import org.springframework.stereotype.Service;

@Service
public interface AccountService {
    void createCustomer(CustomerDto customerDto);

    CustomerDto fetchAccountDetails(String mobileNumber);

    boolean updateAccount(CustomerDto customerDto);

    boolean deleteAccount(String mobileNumber);
}


