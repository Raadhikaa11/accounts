package com.example.accounts.mapper;

import com.example.accounts.dto.AccountDto;
import com.example.accounts.entity.AccountEntity;

public class AccountsMapper {
    public static AccountDto mapToAccountsDto(AccountEntity accounts , AccountDto accountDto){
        accountDto.setAccountNumber(accounts.getAccountNumber());
        accountDto.setAccountType(accounts.getAccountType());
        accountDto.setBranchAddress(accounts.getBranchAddress());
        return accountDto;
    }

    public static AccountEntity mapToAccountEntity(AccountDto accountDto,AccountEntity accounts){
        accounts.setAccountNumber(accountDto.getAccountNumber());
        accounts.setAccountType(accountDto.getAccountType());
        accounts.setBranchAddress(accountDto.getBranchAddress());
        return accounts;
    }
}
