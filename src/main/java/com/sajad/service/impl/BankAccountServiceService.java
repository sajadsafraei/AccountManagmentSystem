package com.sajad.service.impl;

import com.sajad.dao.BankAccountDao;
import com.sajad.dto.BankAccountDto;
import com.sajad.dto.TransferDto;
import com.sajad.model.BankAccount;
import com.sajad.model.Transaction;
import com.sajad.model.User;
import com.sajad.service.IBankAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;

@Service
public class BankAccountServiceService implements IBankAccountService {

    private static int nextAccountNumber = 11223145;

    @Autowired
    private BankAccountDao bankAccountDao;

    @Autowired
    private UserService userService;

    @Autowired
    private TransactionService transactionService;

    @Override
    public BankAccount createAccount(BankAccountDto bankAccountDto) {
        User user = userService.getUserById(bankAccountDto.getUserID());
        BankAccount bankAccount = new BankAccount();
        bankAccount.setUser(user);
        bankAccount.setAccountNumber(accountGen());
        bankAccount.setAccountBalance(bankAccountDto.getAccountBalance());
        bankAccount.setCreatedDate(new Date());

        return bankAccountDao.save(bankAccount);
    }

    @Override
    public BankAccount getAccount(Long id) {
        return bankAccountDao.findById(id).get();
    }

    @Override
    public BankAccount getAccountByAccountNumber(int accountNumber) {
        return bankAccountDao.findByAccountNumber(accountNumber);
    }

    @Transactional
    @Override
    public void deposit(int accountNumber, double amount, String description) {
        BankAccount bankAccount = bankAccountDao.findByAccountNumber(accountNumber);
        bankAccount.setAccountBalance(bankAccount.getAccountBalance().add(BigDecimal.valueOf(amount)));
        bankAccountDao.save(bankAccount);

        doTransaction(bankAccount, description, amount);
    }

    @Transactional
    @Override
    public void withdraw(int accountNumber, double amount, String description) {
        BankAccount bankAccount = bankAccountDao.findByAccountNumber(accountNumber);
        if (bankAccount.getAccountBalance().doubleValue() > amount) {
            bankAccount.setAccountBalance(bankAccount.getAccountBalance().subtract(BigDecimal.valueOf(amount)));
            bankAccountDao.save(bankAccount);
            doTransaction(bankAccount, description, amount);
        } else
            System.out.println("AccountBalance is less than your amount");
    }

    @Transactional
    @Override
    public Boolean transferBetweenAccounts(TransferDto transferDto) {
        BankAccount fromAccount = bankAccountDao.findByAccountNumber(transferDto.getFromAccount());

        if (fromAccount.getAccountBalance().doubleValue() > transferDto.getAmount()) {
            withdraw(transferDto.getFromAccount(), transferDto.getAmount(), transferDto.getDescription());
            deposit(transferDto.getToAccount(), transferDto.getAmount(), transferDto.getDescription());

            return true;
        }
        return false;

    }

    private void doTransaction(BankAccount bankAccount, String description, double amount) {
        Transaction transaction = new Transaction();
        transaction.setAmount(amount);
        transaction.setDescription(description);
        transaction.setDate(new Date());
        transaction.setAvailableBalance(bankAccount.getAccountBalance());
        transaction.setBankAccount(bankAccount);

        transactionService.createTransaction(transaction);
    }

    private int accountGen() {
        return ++nextAccountNumber;
    }
}
