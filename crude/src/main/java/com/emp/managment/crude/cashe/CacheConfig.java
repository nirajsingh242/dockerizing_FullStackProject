package com.emp.managment.crude.cashe;

import com.emp.managment.crude.dto.EmployeeDto;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.jcache.JCacheCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.cache.CacheManager;

import javax.cache.Caching;
import javax.cache.configuration.MutableConfiguration;
import javax.cache.expiry.AccessedExpiryPolicy;
import javax.cache.expiry.Duration;
import javax.cache.spi.CachingProvider;

import java.util.concurrent.TimeUnit;

@Configuration
@EnableCaching
public class CacheConfig {

    @Bean
    public CacheManager cacheManager() {
        CachingProvider provider = Caching.getCachingProvider();
        javax.cache.CacheManager ehCacheManager = provider.getCacheManager();

        // Define a cache configuration with correct types and TTL of 5 minutes
        MutableConfiguration<Long, EmployeeDto> config = new MutableConfiguration<Long, EmployeeDto>()
                .setTypes(Long.class, EmployeeDto.class)
                .setStoreByValue(false) // Prevents serialization overhead
                .setExpiryPolicyFactory(AccessedExpiryPolicy.factoryOf(new Duration(TimeUnit.MINUTES, 5))); // Set TTL

        // Create and register the cache
        ehCacheManager.createCache("employeeCache", config);

        return new JCacheCacheManager(ehCacheManager);
    }
}
