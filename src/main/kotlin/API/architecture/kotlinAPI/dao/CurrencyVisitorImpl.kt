package API.architecture.kotlinAPI.dao

import API.architecture.kotlinAPI.dao.repository.CurrencyRepository
import API.architecture.kotlinAPI.dao.repository.CurrencyVisitor
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

//@Service
//class CurrencyVisitorImpl @Autowired constructor(private val currencyRepository: CurrencyRepository) : CurrencyVisitor {
//    companion object {
//        val LOGGER: Logger = LoggerFactory.getLogger(CurrencyVisitorImpl::class.java)
//    }
//
//    override fun visit(currency: CurrencyV) {
//        LOGGER.info("Currency Visitor working")
//        LOGGER.info("Currency: $currency")
//        //JPA
//        val currencySave = Currency()
//        currencySave.currencyFrom = currency.currencyFrom
//        currencySave.currencyTo = currency.currencyTo
//        currencySave.amount = currency.amount
//        currencySave.date = Date()
//        currencySave.result = currency.result
//        currencyRepository.save(currencySave)
//    }
//}

class CurrencyVisitorImpl: CurrencyVisitor {
    companion object {
        val LOGGER: Logger = LoggerFactory.getLogger(CurrencyVisitorImpl::class.java)
        private val currencyRepository: CurrencyRepository? = null
    }
    override fun visit(currency: CurrencyV) {
        LOGGER.info("Saving currency to database: $currency")
        //JPA
        val currencySave = Currency()
        currencySave.currencyFrom = currency.currencyFrom
        currencySave.currencyTo = currency.currencyTo
        currencySave.amount = currency.amount
        currencySave.date = Date()
        currencySave.result = currency.result
        currencyRepository?.save(currencySave)
    }
}