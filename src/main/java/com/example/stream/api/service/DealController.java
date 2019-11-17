package com.example.stream.api.service;

import com.example.stream.api.service.entity.Deal;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.reactivestreams.Publisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/deal")
@RequiredArgsConstructor
@Log4j2
public class DealController {

    @Autowired
    private final DealRepo dealRepo;

    @PostMapping
    Publisher<Deal> save(@RequestBody Deal deal) {
        Mono<Deal> dealMono = Mono.just(deal);
        dealMono.flatMap(dealRepo::save);
        return dealRepo.findAll();
    }

    @GetMapping(value = "/all")
    public Flux<Deal> get() {
        dealRepo.findAll().subscribe(log::info);
        return dealRepo.findAll();
    }
}
