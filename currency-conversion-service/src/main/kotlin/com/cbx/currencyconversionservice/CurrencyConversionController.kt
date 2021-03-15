package com.cbx.currencyconversionservice

import org.springframework.cloud.openfeign.FeignClient
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.client.RestTemplate
import java.math.BigDecimal

@RestController
class CurrencyConversionController(private val currencyExchangeClient: CurrencyExchangeClient) {
    @GetMapping("/currency-conversion/from/{from}/to/{to}/quantity/{quantity}")
    fun getConversion(
        @PathVariable from: String,
        @PathVariable to: String,
        @PathVariable quantity: BigDecimal
    ): CurrencyConversion? {
        val responseEntity: ResponseEntity<CurrencyConversion> = RestTemplate().getForEntity(
            "http://localhost:8001//currency-exchange/from/$from/to/$to",
            CurrencyConversion::class.java
        )
        val currencyConversion: CurrencyConversion = responseEntity.body ?: return null
        return CurrencyConversion(
            currencyConversion?.id,
            from,
            to,
            quantity,
            currencyConversion.conversionMultiple,
            quantity?.times(currencyConversion.conversionMultiple),
            currencyConversion.env
        )
    }

    @GetMapping("/currency-conversion-feign/from/{from}/to/{to}/quantity/{quantity}")
    fun getConversionFeign(
        @PathVariable from: String,
        @PathVariable to: String,
        @PathVariable quantity: BigDecimal
    ): CurrencyConversion? {
        val currencyConversion: CurrencyConversion = currencyExchangeClient.retrieveExchangeRate(from, to)
        return CurrencyConversion(
            currencyConversion?.id,
            from,
            to,
            quantity,
            currencyConversion.conversionMultiple,
            quantity?.times(currencyConversion.conversionMultiple),
            currencyConversion.env
        )
    }
}

data class CurrencyConversion(
    var id: Long,
    var from: String,
    var to: String,
    var quantity: BigDecimal?,
    var conversionMultiple: BigDecimal,
    var totalCalculatedAmount: BigDecimal?,
    var env: String
)

@FeignClient(name = "currency.exchange.service")
//@FeignClient(url = "localhost:8001", name = "currency.exchange.service")
interface CurrencyExchangeClient {
    @GetMapping("/currency-exchange/from/{from}/to/{to}")
    fun retrieveExchangeRate(
        @PathVariable from: String,
        @PathVariable to: String
    ): CurrencyConversion
}
