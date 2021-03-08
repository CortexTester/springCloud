package com.cbx.limitsservice

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication

@SpringBootApplication
//@ConfigurationPropertiesScan
class LimitsServiceApplication

fun main(args: Array<String>) {
    runApplication<LimitsServiceApplication>(*args)
}
