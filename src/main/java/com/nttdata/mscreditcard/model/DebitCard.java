package com.nttdata.mscreditcard.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document("debitcard")
public class DebitCard {

    @Id
    private String id;
    private String idCustomer;
    private String cardNumber;
    private String idProduct;
}