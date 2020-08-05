package com.transaction.transaction.service;

import com.transaction.transaction.model.Transaction;
import com.transaction.transaction.repositoy.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    public Flux<Transaction> getAll() {
        return transactionRepository.findAll();
    }

    public Mono<Transaction> getById(String id) {
        return transactionRepository.findById(id);
    }

    public Mono update(String id, Transaction transaction) {
        return transactionRepository.save(transaction);
    }

    public Mono save(Transaction transaction) {
        return transactionRepository.save(transaction);
    }

    public Mono delete(String id) {
        return transactionRepository.deleteById(id);
    }

    public Flux<Transaction> getByAccountId(String accountId) {
        return transactionRepository.findByAccountId(accountId);
    }
}
