package com.cbx.apigateway

import org.slf4j.LoggerFactory
import org.springframework.cloud.gateway.filter.GatewayFilterChain
import org.springframework.cloud.gateway.filter.GlobalFilter
import org.springframework.stereotype.Component
import org.springframework.web.server.ServerWebExchange
import reactor.core.publisher.Mono

@Component
class LoggingFilter : GlobalFilter{
    val logger = LoggerFactory.getLogger(LoggingFilter::class.java)
    override fun filter(exchange: ServerWebExchange?, chain: GatewayFilterChain?): Mono<Void> {
        logger.info("Path of the request received - > ${exchange?.request?.path}")
        return chain?.filter(exchange)!!
    }
}
