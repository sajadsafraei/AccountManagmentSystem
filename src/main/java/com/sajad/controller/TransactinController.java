package com.sajad.controller;

import com.sajad.model.Transaction;
import com.sajad.service.impl.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transaction")
public class TransactinController {

    @Autowired
    private TransactionService transactionService;

    @GetMapping("/get/{id}")
    public Transaction get(@PathVariable Long id) {
       return transactionService.getTransaction(id);
    }

}
