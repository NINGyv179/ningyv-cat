package com.ningyv.smallcat.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ApplicationTest {
	@Autowired
	private StringRedisTemplate stringRedisTemplate;
	@Autowired
	private RedisTemplate<String, String> redisTemplate;
	
	
	@Test
	public void test1() {
		ValueOperations<String, String> opsForValue = redisTemplate.opsForValue();
		opsForValue.set("yellp", "blue");
	}
	
	@Test
	public void test2() {
		stringRedisTemplate.opsForValue().set("boy", "girl");
	}
}
