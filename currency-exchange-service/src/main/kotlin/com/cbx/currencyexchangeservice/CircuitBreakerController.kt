package com.cbx.currencyexchangeservice

import io.github.resilience4j.bulkhead.annotation.Bulkhead
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker
import io.github.resilience4j.ratelimiter.annotation.RateLimiter
import io.github.resilience4j.retry.annotation.Retry
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.client.RestTemplate

@RestController
class CircuitBreakerController {
    val logger = LoggerFactory.getLogger(CircuitBreakerController::class.java)

    @GetMapping("/sample-api")
    @Retry(name = "sample-api", fallbackMethod = "hardcodeResponse")
    @Bulkhead(name = "default")
    fun sampleApi(): String? {
//        return "sample api"
        logger.info("sample api request received")
        val result = RestTemplate().getForEntity("http://google11fake.com", String::class.java)
        return result?.body
    }

    @GetMapping("/circuit-api")
    @CircuitBreaker(name = "default", fallbackMethod = "hardcodeResponse")
    @RateLimiter(name = "default") // 10s => 10000 calls to the api
    fun circuitApi(): String? {
//        return "sample api"
        logger.info("sample api request received")
        val result = RestTemplate().getForEntity("http://google11fake.com", String::class.java)
        return result?.body
    }

    fun hardcodeResponse(ex: Exception): String {
        return "hardcodeResponse for test fallback"
    }
}
