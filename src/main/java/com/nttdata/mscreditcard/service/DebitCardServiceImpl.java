package com.nttdata.mscreditcard.service;

import com.nttdata.mscreditcard.model.Account;
import com.nttdata.mscreditcard.model.DebitCard;
import com.nttdata.mscreditcard.proxy.DebitCardProxy;
import com.nttdata.mscreditcard.repository.DebitCardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class DebitCardServiceImpl implements DebitCardService{

    @Autowired
    private DebitCardRepository debitCardRepo;

    private DebitCardProxy debitCardProxy = new DebitCardProxy();

    @Override
    public Flux<DebitCard> findAll() {
        return debitCardRepo.findAll();
    }

    @Override
    public Mono<DebitCard> findById(String id) {
        return debitCardRepo.findById(id);
    }

    @Override
    public Mono<DebitCard> findByIdCustomer(String idCustomer) {
        return debitCardRepo.findByIdCustomer(idCustomer);
    }

    @Override
    public Mono<DebitCard> save(DebitCard debitCard) {
        return clientCheck(debitCard).flatMap(this::productExist)
                .flatMap(debitCardRepo::save);
    }

    @Override
    public void delete(String id) {
        debitCardRepo.deleteById(id).subscribe();
    }

    //DEBITCARD UTIL METHODS
    public Mono<DebitCard> clientCheck(DebitCard debitCard){
        return debitCardProxy.getCustomer(debitCard.getIdCustomer())
                .switchIfEmpty(Mono.error(()->new IllegalArgumentException("Client doesn't exist")))
                .flatMap(resp->findByIdCustomer(resp.getId()).switchIfEmpty(Mono.just(new DebitCard())))
                .flatMap(resp->{
                    return resp.getId()==null ? Mono.just(debitCard)
                            : Mono.error(()->new IllegalArgumentException("Client can have only one debit card"));
                });
    }

    public Mono<DebitCard> productExist(DebitCard debitCard){
        return debitCardProxy.getAccount(debitCard.getIdProduct())
                .switchIfEmpty(Mono.just(new Account()))
                .flatMap(resp -> {
                    return resp.getId()==null ? Mono.error(()->new IllegalArgumentException("Product associated to this debid card doesn't exist"))
                            : Mono.just(debitCard);
                });
    }
}