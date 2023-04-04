package com.nttdata.mscreditcard.controller;

import com.nttdata.mscreditcard.model.DebitCard;
import com.nttdata.mscreditcard.service.DebitCardService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@RestController
@RequiredArgsConstructor
@RequestMapping("/debit-card")
public class DebitCardController {

    private final DebitCardService debitCardService;

    @GetMapping
    public Flux<DebitCard> getAllDebitCard() {
        return debitCardService.findAll();
    }

    @GetMapping("/{id}")
    public Mono<DebitCard> getDebitCard(@PathVariable("id") String id) {
        return debitCardService.findById(id);
    }

    @PostMapping
    public Mono<DebitCard> saveDebitCard(@RequestBody DebitCard debitCard){
        return debitCardService.save(debitCard);
    }

    @PutMapping
    public Mono<DebitCard> updateDebitCard(@RequestBody DebitCard debitCard){
        return debitCardService.save(debitCard);
    }

    @DeleteMapping("/{id}")
    public void deleteDebitCard(@PathVariable("id") String id) {
        debitCardService.delete(id);
    }

}
