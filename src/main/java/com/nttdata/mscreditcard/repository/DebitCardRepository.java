package com.nttdata.mscreditcard.repository;

import com.nttdata.mscreditcard.model.DebitCard;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface DebitCardRepository extends ReactiveMongoRepository<DebitCard, String> {
    public Mono<DebitCard> findByIdCustomer(String idCustomer);
}