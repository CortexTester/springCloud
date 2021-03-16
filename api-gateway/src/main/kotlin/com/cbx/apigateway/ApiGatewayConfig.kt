package com.cbx.apigateway

import org.springframework.cloud.gateway.route.RouteLocator
import org.springframework.cloud.gateway.route.builder.PredicateSpec
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class ApiGatewayConfig {
    @Bean
    fun gatewayRouter(builder: RouteLocatorBuilder): RouteLocator {
        val redirectRoute = { p: PredicateSpec ->
            p.path("/get")
                .filters { f ->
                    f.addRequestHeader("myHeader", "testerHeader")
                    f.addRequestParameter("myParam", "TestParam")
                }
                .uri("http://httpbin.org:80")
        }
        //spring.cloud.gateway.discovery.locator.enabled is need to turn off

        return builder.routes()
            .route(redirectRoute)
            .route { p: PredicateSpec ->
                p.path("/currency-exchange/**")
                    .uri("lb://currency.exchange.service")
            }
            //spring.cloud.gateway.discovery.locator.enabled is need to turn off
            .route { p: PredicateSpec ->
                p.path("/currency-conversion-feign/**")
                    .uri("lb://currency.conversion.service")
            }
            .route { p: PredicateSpec ->
                p.path("/currency-conversion/**")
                    .uri("lb://currency.conversion.service")
            }
//not working
//            .route { p: PredicateSpec ->
//                p.path("/currency-conversion-new/**")
//                    .filters { f -> f.rewritePath("/currency-conversion-new/(?<segment>.*)", "/currency-conversion-feign/${segment}") }
//                    .uri("lb://currency.conversion.service")
//            }
            .build()
    }
}
