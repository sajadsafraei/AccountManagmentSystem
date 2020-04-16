package com.sajad.controller;

import com.sajad.dto.BankAccountDto;
import com.sajad.dto.TransferDto;
import com.sajad.model.BankAccount;
import com.sajad.service.impl.BankAccountServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/bankAccount")
public class BankAccountControler {

    @Autowired
    private BankAccountServiceService bankAccountServiceService;

    @PostMapping("/createAccount")
    public BankAccount createAccount(@RequestBody BankAccountDto bankAccountDto) {
       return bankAccountServiceService.createAccount(bankAccountDto);
    }

    @GetMapping("/deposit/{accountNumber}/{amount}/{description}")
    public BankAccount deposit(@PathVariable int accountNumber,@PathVariable double amount,@PathVariable String description){
        bankAccountServiceService.deposit(accountNumber,amount,description);
        return bankAccountServiceService.getAccountByAccountNumber(accountNumber);
    }

    @GetMapping("/withdraw/{accountNumber}/{amount}/{description}")
    public BankAccount withdraw(@PathVariable int accountNumber,@PathVariable double amount,@PathVariable String description){
        bankAccountServiceService.withdraw(accountNumber,amount,description);
        return bankAccountServiceService.getAccountByAccountNumber(accountNumber);
    }

    @PostMapping("/transferBetweenAccouts")
    public String transferBetweenAccouts(@RequestBody TransferDto transferDto){
       if (bankAccountServiceService.transferBetweenAccounts(transferDto))
           return "success";
       return "transfer rejected !!!";
    }

}
