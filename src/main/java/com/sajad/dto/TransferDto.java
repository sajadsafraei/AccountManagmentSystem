package com.sajad.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class TransferDto {
    private int fromAccount;
    private int toAccount;
    private double amount;

    private String description;
}
