package com.transaction.transaction.controller;

import com.transaction.transaction.model.Transaction;
import com.transaction.transaction.repositoy.TransactionRepository;
import com.transaction.transaction.service.TransactionService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@ExtendWith(SpringExtension.class)
@WebFluxTest(controllers = TransactionController.class)
@Import(TransactionService.class)
public class TransactionControllerTest {
    @MockBean
    TransactionRepository repository;

    @Autowired
    private WebTestClient webClient;

    final private static Map<String, Transaction> transactionMap = new HashMap<>();

    @BeforeAll
    public static void setup(){
        transactionMap.put("test",new Transaction("1","Deposit",100,10, LocalDateTime.now(),"1","1"));
    }

    @Test
    public void testCreateTransaction(){
        Mockito
                .when(repository.save(transactionMap.get("test"))).thenReturn(Mono.just(transactionMap.get("test")));

        webClient.post()
                .uri("/transactions")
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(transactionMap.get("test")))
                .exchange()
                .expectStatus().isOk();
        Mockito.verify(repository,Mockito.times(1)).save(transactionMap.get("test"));

    }


    @Test
    public void testGetTransactionById(){
        Mockito
                .when(repository.findById(transactionMap.get("test").transactionId))
                .thenReturn(Mono.just(transactionMap.get("test")));

        webClient.get()
                .uri("/transactions/{id}",transactionMap.get("test").transactionId)
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBody(Transaction.class)
                .isEqualTo(transactionMap.get("test"));
        Mockito.verify(repository, Mockito.times(1)).findById(transactionMap.get("test").transactionId);
    }

    @Test
    public void testUpdateTransaction(){
        Mockito
                .when(repository.findById(transactionMap.get("test").transactionId))
                .thenReturn(Mono.just(transactionMap.get("test")));

        webClient.put()
                .uri("/transactions/{id}",transactionMap.get("test").transactionId)
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(transactionMap.get("test")))
                .exchange()
                .expectStatus().isOk();
        Mockito.verify(repository,Mockito.times(1)).save(transactionMap.get("test"));
    }

    @Test
    public void testDeleteTransaction(){
        Mockito
                .when(repository.deleteById(transactionMap.get("test").transactionId))
                .thenReturn(Mono.empty());

        webClient.delete()
                .uri("/transactions/{id}",transactionMap.get("test").transactionId)
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBody(Transaction.class)
                .isEqualTo(null);
        Mockito.verify(repository, Mockito.times(1)).deleteById(transactionMap.get("test").transactionId);

    }
}
