package com.sajad.dao;

import com.sajad.model.Transaction;
import org.springframework.data.repository.CrudRepository;

public interface TransactionDao extends CrudRepository<Transaction,Long> {
}
