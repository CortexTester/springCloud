package com.cbx.currencyexchangeservice

import org.slf4j.LoggerFactory
import org.springframework.core.env.Environment
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController
import java.math.BigDecimal

@RestController
class CurrencyExchangeController(
    private val env: Environment,
    private val repository: CurrencyExchangeRepository
) {
    val loggr = LoggerFactory.getLogger(CurrencyExchangeController::class.java)
    @GetMapping("/currency-exchange/from/{from}/to/{to}")
    fun retrieveExchangeValue(@PathVariable from: String, @PathVariable to: String): CurrencyExchange? {
        loggr.info("retrieveExchangeValue called with $from to $to")
        return repository.findByFromAndTo(from, to)

//        return CurrencyExchange(1000L, from, to, BigDecimal.valueOf(50), env.getProperty("local.server.port"))
    }
}

