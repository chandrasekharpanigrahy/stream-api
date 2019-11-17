package com.example.stream.api.service;

import com.example.stream.api.service.entity.Deal;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface DealRepo extends ReactiveCrudRepository<Deal, Integer> {
    Flux<Deal> findAll();

}
