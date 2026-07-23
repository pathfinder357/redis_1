package com.example.redis;

import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

@SpringBootTest
public class RedisTemplateTest {

	@Autowired
	private StringRedisTemplate stringRedisTemplate; // 자바에서 redis로 넘어가는 데이터 타입
	// 이 무었이냐를 정의하는 클래스

	@Test
	public void stringOpsTest() {
		// 문자열 조작을 위한 클래스
		// <,> String타입이지만 Generic이기 때문에 .set을 통해 받는 것이 문자열일 뿐임
		// 따라서 SpringBoot는 자바타입을 사용하기 때문에 StringRedisTemp인것이지
		// 실제 Redis는 String으로 받는것이 아닌 key, val 값이 실제와는 다르다
		ValueOperations<String, String> ops = stringRedisTemplate.opsForValue();

		ops.set("simplekey", "simplevalue");
		System.out.println(ops.get("simplekey"));
		// 집합을 조작하기 위한 클래스
		SetOperations<String, String> setOps = stringRedisTemplate.opsForSet();
		setOps.add("hobbies", "games");
		setOps.add("hobbies", "science", "alchol","games");

		System.out.println(setOps.size("hobbies"));

		stringRedisTemplate.expire("hobbies", 10, TimeUnit.SECONDS);
		stringRedisTemplate.delete("simplekey");
	}


	@Autowired
	private RedisTemplate<String, ItemDto> itemRedisTemplate;


	@Test
	public void itemRedisTemplateTest() {
		ValueOperations<String, ItemDto> ops = itemRedisTemplate.opsForValue();
		ops.set("my:keyboard", ItemDto.builder()
			.name("ggg2")
				.price(25555)
				.description("ggg2")
			.build());
		System.out.println(ops.get("my:keyboard"));

		ops.set("my:mouse", ItemDto.builder()
				.name("ggg234")
			.price(25555)
			.description("ggg234")
			.build());
		System.out.println(ops.get("my:mouse"));
	}

	@Test
	public void getValueTest() {
		Long articleId = 1L;
		String veiwKey = "article:" + articleId+":veiw";
		ValueOperations<String, String> ops = stringRedisTemplate.opsForValue();
		Long currentView = ops.increment(veiwKey);
		System.out.println("게시글"+articleId+"의 현재조회수 "+currentView);
	}
}
