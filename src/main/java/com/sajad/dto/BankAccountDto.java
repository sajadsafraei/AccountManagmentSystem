package com.sajad.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sajad.model.Transaction;
import com.sajad.model.User;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Setter
@Getter
public class BankAccountDto {

    private BigDecimal accountBalance;

    private Long userID;

}
