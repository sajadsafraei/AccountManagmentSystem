package com.sajad.dao;

import com.sajad.model.BankAccount;
import org.springframework.data.repository.CrudRepository;

public interface BankAccountDao extends CrudRepository<BankAccount,Long> {
    BankAccount findByAccountNumber(int accountNumber);
}
