package com.example.stream.api.service;

import com.example.stream.api.service.entity.Deal;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/deal")
@Log4j2
public class DealController {

    @Autowired
    DealRepo repository;

    @PostMapping
    Flux<Deal> save() {
        Flux<Deal> dealFlux = Flux.just(new Deal(null, "sekhar"), new Deal(null, "panigrahy"));
        Flux<Deal> deal = dealFlux.flatMap(repository::save);
        Flux<Deal> allDeal = repository.findAll();
        log.info("current Data in repository", allDeal);
        repository.deleteAll().thenMany(deal).thenMany(allDeal).subscribe(log::info);
        log.info("current Data in repository after save", allDeal);
        return allDeal;
    }

    @GetMapping(value = "/all")
    Flux get() {
        log.info("current Data in repository", repository.findAll());

        /*return Flux.just(
                new Deal(1, "Doe"),
                new Deal(2, "Doe"));*/

        return repository.findAll();
    }
}
