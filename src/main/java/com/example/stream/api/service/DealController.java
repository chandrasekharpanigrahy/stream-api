package com.example.stream.api.service;

import com.example.stream.api.service.entity.Deal;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
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
    Flux<Deal> save(@RequestBody Deal deal) {
        Mono<Deal> dealMono = Mono.just(deal);
        return dealMono.flatMap(dealRepo::save).thenMany(dealRepo.findAll());
    }

    @GetMapping(value = "/all", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Deal> get() {
        dealRepo.findAll().subscribe(log::info);
        return dealRepo.findAll();
    }
}
