package com.example.accounts.repository;

import com.example.accounts.entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface CustomersRepository extends JpaRepository<CustomerEntity,Long> {
    Optional<CustomerEntity> findByMobileNumber(String mobileNumber);
}
