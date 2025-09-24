package com.example.accounts.service;

import com.example.accounts.constants.AccountsConstants;
import com.example.accounts.dto.AccountDto;
import com.example.accounts.dto.CustomerDto;
import com.example.accounts.entity.AccountEntity;
import com.example.accounts.entity.CustomerEntity;
import com.example.accounts.exception.CustomerAlreadyExistsException;
import com.example.accounts.exception.ResourceNotFoundException;
import com.example.accounts.mapper.AccountsMapper;
import com.example.accounts.mapper.CustomerMapper;
import com.example.accounts.repository.AccountsRepository;
import com.example.accounts.repository.CustomersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;
@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    private AccountsRepository accountsRepository;
    @Autowired
    private CustomersRepository customersRepository;

    @Override
    public void createCustomer(CustomerDto customerDto) {
        CustomerEntity customer= CustomerMapper.mapToCustomerEntity(customerDto,new CustomerEntity());
        Optional<CustomerEntity> existsCustomer = customersRepository.findByMobileNumber(String.valueOf(customerDto.getMobileNumber()));
        if (existsCustomer.isPresent()) {
            throw new CustomerAlreadyExistsException("Customer with mobile number "
                    + customerDto.getMobileNumber() + " already exists");
        }
        CustomerEntity savedCustomer = customersRepository.save(customer);
        accountsRepository.save(createNewAccount(savedCustomer));

    }

    @Override
    public CustomerDto fetchAccountDetails(String mobileNumber) {
        CustomerEntity customer= customersRepository.findByMobileNumber(mobileNumber).orElseThrow(
                ()->new ResourceNotFoundException("Customer","mobileNumber",mobileNumber)
        );

        AccountEntity account=accountsRepository.findByCustomerId(customer.getCustomerId()).orElseThrow(
                ()->new ResourceNotFoundException("Account", "customerId",customer.getCustomerId().toString())
        );
        CustomerDto customerDto= CustomerMapper.mapToCustomerDto(customer,new CustomerDto());
        customerDto.setAccounts(AccountsMapper.mapToAccountsDto(account,new AccountDto()));

        return customerDto;
    }

    @Override
    public boolean updateAccount(CustomerDto customerDto) {
        boolean isUpdated =false;
        AccountDto accountDto=customerDto.getAccounts();
        if (accountDto!=null){
            AccountEntity account=accountsRepository.findById(accountDto.getAccountNumber()).orElseThrow(
                    ()->new ResourceNotFoundException("Account","AccountNumber",accountDto.getAccountNumber().toString())
            );
            AccountsMapper.mapToAccountEntity(accountDto,account);
            account=accountsRepository.save(account);

            Long customerId=account.getCustomerId();
            CustomerEntity customer=customersRepository.findById(customerId).orElseThrow(
                    ()->new ResourceNotFoundException("Customer","CustomerId",customerId.toString())
            );
            CustomerMapper.mapToCustomerEntity(customerDto,customer);
            customersRepository.save(customer);
            isUpdated=true;
        }
        return isUpdated;
    }

    @Override
    public boolean deleteAccount(String mobileNumber) {
        CustomerEntity customer=customersRepository.findByMobileNumber(mobileNumber).orElseThrow(
                ()->new ResourceNotFoundException("Customer","mobileNumber",mobileNumber)
        );
        accountsRepository.deleteByCustomerId(customer.getCustomerId());
        customersRepository.deleteById(customer.getCustomerId());
        return true;
    }


    private AccountEntity createNewAccount(CustomerEntity customer) {
        AccountEntity newAccount = new AccountEntity();
        newAccount.setCustomerId(customer.getCustomerId());
        long randomAccNumber = 1000000000L + new Random().nextInt(900000000);

        newAccount.setAccountNumber(randomAccNumber);
        newAccount.setAccountType(AccountsConstants.SAVINGS);
        newAccount.setBranchAddress(AccountsConstants.ADDRESS);
        return newAccount;
    }
}
