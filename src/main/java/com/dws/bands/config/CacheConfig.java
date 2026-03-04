package com.dws.bands.config;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.github.benmanes.caffeine.cache.Caffeine;

@Configuration
public class CacheConfig {
	
	@Value("${spring.cache.ttl}")
	private int ttl;
	
	@Value("${spring.cache.maxSize}")
	private int maxSize;
	
	@Bean
	public CacheManager cacheManager() {
		CaffeineCacheManager cacheManager = new CaffeineCacheManager("bands", "band");
		
		cacheManager.setCaffeine(Caffeine.newBuilder()
				.expireAfterWrite(ttl, TimeUnit.MINUTES)
				.maximumSize(maxSize));
		
		return cacheManager;
	}
	
	@Bean("customKeyGen")
	public KeyGenerator keyGenerator() {
	    return (target, method, params) -> params[0];
	}
}