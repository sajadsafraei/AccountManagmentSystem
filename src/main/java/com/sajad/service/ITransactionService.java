package com.sajad.service;

import com.sajad.model.BankAccount;
import com.sajad.model.Transaction;

import java.util.List;

public interface ITransactionService {

    Transaction createTransaction(Transaction transaction);

    Transaction getTransaction(Long transactionId);

}
