package com.cbx.limitsservice

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class LimitedController(private val configuration: Configuration) {
    @GetMapping("/limits")
    fun retrieveLimits(): Limits {
        return Limits(configuration.minimum, configuration.maximum)
    }
}

data class Limits(val minimum: Int, val maximum: Int)
