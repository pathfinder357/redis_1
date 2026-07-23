package com.example.redis.repo;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
// 직렬화 가능하다는 것을 나타내는 인터페이스
// item 클래스가 java의 직렬화를 통해서 직렬화 가능
// 데이터가 오갈때 해석이 편해짐(컨버터입장에서?)
// Entity 대신 RedisHash
@RedisHash("item")
public class Item implements Serializable {
	@Id
	// id를 String으로 쓰면 UUID가 자동으로 배정
	private String id;
	private String name;
	private String description;
	private Integer price;
}
