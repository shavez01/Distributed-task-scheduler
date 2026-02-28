package com.scheduler.util;

import java.time.Duration;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class RedisLockUtil {
 private final StringRedisTemplate redis;
 public boolean tryLock(String key) {
  return Boolean.TRUE.equals(redis.opsForValue()
    .setIfAbsent(key,"locked",Duration.ofSeconds(30)));
 }
 public void unlock(String key){ redis.delete(key); }
}
