package com.transaction.transaction.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

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
    public double output;
    public double input;
    public String datetime;

    public Transaction(){
        super();
    }
}
