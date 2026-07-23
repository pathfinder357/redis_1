package com.example.redis.repo;

import org.springframework.data.repository.CrudRepository;

public interface ItemRepo extends CrudRepository<Item, String> {
}
