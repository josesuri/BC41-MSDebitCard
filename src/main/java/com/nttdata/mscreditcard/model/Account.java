package com.nttdata.mscreditcard.model;

import lombok.Data;

@Data
public class Account {
    private String id;
    private String idClient;
    private String idCard;
    private String typeAccount;
    private String accountNumber;
    private Float balance;
    private Float maintenance;
    private Integer monthlyMovements;
    private Float commission;
}