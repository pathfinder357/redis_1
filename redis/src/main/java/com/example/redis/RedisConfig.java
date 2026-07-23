package com.example.redis;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;

@Configuration
public class RedisConfig {
	@Bean
	public RedisTemplate<String, ItemDto> itemRedisTemplate(
		RedisConnectionFactory redisConnectionFactory
	) {

		RedisTemplate<String, ItemDto> template = new RedisTemplate<>();
		// 어떤 설정을 가지고 Redis랑 연결할것인지
		// 즉 여기서는 RedisConnectionFactory(yaml파일 정보를 받아서)
		template.setConnectionFactory(redisConnectionFactory);
		// key 문자열 직렬화
		template.setKeySerializer(RedisSerializer.string());
		// val dto 직렬화
		template.setValueSerializer(RedisSerializer.json());
		return template;
	}
}
