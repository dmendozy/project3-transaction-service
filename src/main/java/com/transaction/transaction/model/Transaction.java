package com.transaction.transaction.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Data
@Getter
@Setter
@AllArgsConstructor
@Builder
@Document(collection = "transactions")
public class Transaction {

    @Id
    public String transactionId;
    public String transactionName;
    public double amount;
    public double commission;
    public LocalDate datetime;
    public String accountId;
    public String creditId;

    public Transaction(){
        super();
    }
    public Transaction(double commission){
        this.commission=commission;
    }
    public Transaction(String transactionName,double amount){
        this.transactionName=transactionName;
        this.amount=amount;
    }
}
