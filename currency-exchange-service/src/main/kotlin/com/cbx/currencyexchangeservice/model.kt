package com.cbx.currencyexchangeservice

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.math.BigDecimal
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
data class CurrencyExchange(
    @Id
    @GeneratedValue
    var id: Long,
    @Column(name = "currencyFrom") var from: String,
    @Column(name = "currencyTo") var to: String,
    var conversionMultiple: BigDecimal,
    var env: String?
)

@Repository
interface CurrencyExchangeRepository:JpaRepository<CurrencyExchange, Long>{
    fun findByFromAndTo(from:String, to: String):CurrencyExchange?
}




