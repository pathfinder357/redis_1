package com.example.redis;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.redis.repo.Item;
import com.example.redis.repo.ItemRepo;

@SpringBootTest
public class RedisRepoTest {
	@Autowired
	private ItemRepo itemRepo;

	@Test
	public void createdTest() {
		// 객체를 만들고
		Item item = Item.builder()
			.name("Test")
			.description("Test")
			.price(10)
			.build();
		// repo.save
		itemRepo.save(item);
	}

	@Test
	public void readOneTest() {
		Item item = itemRepo.findById("").orElseThrow();
		System.out.println(item.getDescription());
	}

	@Test
	public void updateTest() {
		Item item = itemRepo.findById("").orElseThrow();
		item.setDescription("Updated Description");
		item = itemRepo.save(item);
		System.out.println(item.getDescription());
	}

	@Test
	public void deleteTest() {
		itemRepo.deleteById("");
	}

}
