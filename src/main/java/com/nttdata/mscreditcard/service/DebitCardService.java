package com.nttdata.mscreditcard.service;

import com.nttdata.mscreditcard.model.DebitCard;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface DebitCardService {
    public Flux<DebitCard> findAll();
    public Mono<DebitCard> findById(String id);
    public Mono<DebitCard> findByIdCustomer(String idCustomer);
    public Mono<DebitCard> save(DebitCard debitCard);
    public void delete(String id);
}