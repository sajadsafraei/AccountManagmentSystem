package com.sajad.service.impl;

import com.sajad.dao.TransactionDao;
import com.sajad.model.BankAccount;
import com.sajad.model.Transaction;
import com.sajad.service.ITransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionService implements ITransactionService {

    @Autowired
    private TransactionDao transactionDao;

    @Override
    public Transaction createTransaction(Transaction transaction) {
        return transactionDao.save(transaction);
    }

    @Override
    public Transaction getTransaction(Long transactionId) {
        return transactionDao.findById(transactionId).get();
    }
}
