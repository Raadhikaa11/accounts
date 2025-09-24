package com.example.accounts.mapper;

import com.example.accounts.dto.CustomerDto;
import com.example.accounts.entity.CustomerEntity;

public class CustomerMapper {
    public static CustomerDto mapToCustomerDto(CustomerEntity customers,CustomerDto customerDto){
        customerDto.setName(customers.getName());
        customerDto.setMobileNumber(customers.getMobileNumber());
        customerDto.setEmail(customers.getEmail());
        return customerDto;
    }
    public static CustomerEntity mapToCustomerEntity(CustomerDto customerDto, CustomerEntity customerEntity) {
        customerEntity.setName(customerDto.getName());
        customerEntity.setEmail(customerDto.getEmail());
        customerEntity.setMobileNumber(customerDto.getMobileNumber());
        return customerEntity;
    }

}
