package com.cbx.limitsservice

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding
import org.springframework.stereotype.Component
import java.beans.ConstructorProperties

//@ConstructorBinding
@Component
@ConfigurationProperties("limits-service")
class Configuration(var minimum: Int = 0, var maximum: Int = 100) {
}
