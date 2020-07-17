package com.transaction.transaction.controller;

import com.transaction.transaction.model.Transaction;
import com.transaction.transaction.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    @Autowired
    TransactionService transactionService;

    @GetMapping
    public Flux<Transaction> getAllTransactions(){
        return transactionService.getAll();
    }

    @GetMapping("{id}")
    public Mono<Transaction> getTransactionById(@PathVariable("id") String transactionId){
        return transactionService.getById(transactionId);
    }

    @PostMapping
    public Mono<Transaction> createTransaction(@Validated @RequestBody Transaction transaction){
        return transactionService.save(transaction);
    }

    @PutMapping("{id}")
    public Mono<Transaction> updateTransaction(@PathVariable("id") String transactionId,
                                         @Validated @RequestBody Transaction transaction){
        return transactionService.update(transactionId, transaction);
    }

    @DeleteMapping("{id}")
    public Mono<Transaction> deleteTransaction(@PathVariable("id") String transactionId){
        return transactionService.delete(transactionId);
    }

}
