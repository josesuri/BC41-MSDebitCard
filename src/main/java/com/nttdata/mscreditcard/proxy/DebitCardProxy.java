package com.nttdata.mscreditcard.proxy;

import com.nttdata.mscreditcard.model.Account;
import com.nttdata.mscreditcard.model.Customer;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

public class DebitCardProxy {

    private final WebClient.Builder webClientBuilder = WebClient.builder();

    //get client by id
    public Mono<Customer> getCustomer(String idCustomer){
        return webClientBuilder.build()
                .get()
                .uri("http://localhost:9020/customer/{idCustomer}", idCustomer)
                .retrieve()
                .bodyToMono(Customer.class);
    }

    public Mono<Account> getAccount(String idAccount){
        return webClientBuilder.build()
                .get()
                .uri("http://localhost:9020/account/{idAccount}", idAccount)
                .retrieve()
                .bodyToMono(Account.class);
    }

}