package com.example.redis;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJacksonJsonRedisSerializer;
import org.springframework.data.redis.serializer.JacksonJsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

import tools.jackson.databind.ObjectMapper;

@Configuration
@EnableRedisHttpSession
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
		template.setValueSerializer(new JacksonJsonRedisSerializer<>(ItemDto.class));
		return template;
	}

	@Bean
	public RedisSerializer<Object> springSessionDefaultRedisSerializer() {
		return RedisSerializer.java(); // json이라고 취급하고 세션을 저장하겠다는 뜻
	}

}
