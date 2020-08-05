package com.transaction.transaction.controller;

import com.transaction.transaction.model.Transaction;
import com.transaction.transaction.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.time.LocalDateTime;

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

    //find transactions by account
    @GetMapping("/account/{accountId}")
    public Flux<Transaction> findTransactionsByAccount(@PathVariable("accountId") String accountId){
        return transactionService.getByAccountId(accountId);
    }

    //find transactions by date
    @GetMapping("date/{d1}")
    public Flux<Transaction> findTransactionsByDate(@PathVariable("d1") String datetime1){
        LocalDateTime dateTime = LocalDateTime.parse(datetime1+"T00:00:00.000");
        return transactionService.getAll().filter(transaction -> transaction.getDateTime().compareTo(dateTime)==0);
    }

    //find transactions by dates range
    @GetMapping("date/{d1}/{d2}")
    public Flux<Transaction> findTransactionsByDateRange(@PathVariable("d1") String datetime1,
                                                    @PathVariable("d2") String datetime2){
        LocalDateTime startDate = LocalDateTime.parse(datetime1+"T00:00:00.000");
        LocalDateTime finishDate = LocalDateTime.parse(datetime2+"T00:00:00.000");
        return transactionService.getAll()
                .filter(transaction -> transaction.getDateTime().compareTo(startDate)>=0&&
                        transaction.getDateTime().compareTo(finishDate)<=0)
                .flatMap(transaction -> {
                    return transactionService.getById(transaction.getTransactionId());
                });
    }

    @GetMapping("commissions/{d1}/{d2}")
    public Flux plusCommissionsByRangeDate(@PathVariable("d1") String datetime1,
                                                         @PathVariable("d2") String datetime2){
        LocalDateTime startDate = LocalDateTime.parse(datetime1+"T00:00:00.000");
        LocalDateTime finishDate = LocalDateTime.parse(datetime2+"T00:00:00.000");
        return transactionService.getAll()
                .filter(transaction -> transaction.getDateTime().compareTo(startDate)>=0&&
                        transaction.getDateTime().compareTo(finishDate)<=0&&
                        transaction.getCommission()>0)
                .flatMap(transaction -> {
                    return transactionService.getAll()
                            .filter(transaction1 -> transaction1
                                    .getTransactionId()
                                    .compareTo(transaction.getTransactionId())==0)
                            .map(Transaction::getCommission);
                });
    }

}
