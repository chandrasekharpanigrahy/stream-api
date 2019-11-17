package com.example.stream.api.service;

import com.example.stream.api.service.entity.Deal;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface DealRepo extends ReactiveCrudRepository<Deal, Integer> {
}
