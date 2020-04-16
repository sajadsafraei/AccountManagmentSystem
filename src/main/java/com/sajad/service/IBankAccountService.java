package com.sajad.service;

import com.sajad.dto.BankAccountDto;
import com.sajad.dto.TransferDto;
import com.sajad.model.BankAccount;

public interface IBankAccountService {

    BankAccount createAccount(BankAccountDto bankAccount);

    BankAccount getAccount(Long id);
    BankAccount getAccountByAccountNumber(int accountNumber);

    void deposit(int accountNumber, double amount,String description);
    void withdraw(int accountNumber, double amount,String description);

    Boolean transferBetweenAccounts(TransferDto transferDto);


}
