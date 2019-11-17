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
    List<String> save() {
        Flux<String> stringFlux = Flux.just("sekhar", "panigrahy");
        List<String> str = Collections.emptyList();
        Flux<Deal> deal = stringFlux.map(it -> new Deal(null, it)).flatMap(repository::save);
        log.info("current Data in repository", repository.findAll());
        repository.deleteAll().thenMany(deal).thenMany(repository.findAll()).subscribe(log::info);
        return str;
    }

    @GetMapping(value = "/all")
    Flux get() {

        /*return Flux.just(
                new Deal(1, "Doe"),
                new Deal(2, "Doe"));*/

        return repository.findAll();
    }
}
