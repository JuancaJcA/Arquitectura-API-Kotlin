package API.architecture.kotlinAPI.api

import API.architecture.kotlinAPI.bl.ExchangeBL
import API.architecture.kotlinAPI.models.Query
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.math.BigDecimal
import java.util.*

@RestController
@RequestMapping(path = ["api/v1"])
class ExchangeAPI {
    //Business Logic
    private var exchangeBL: ExchangeBL? = null

    //Logger
    var logger = LoggerFactory.getLogger(ExchangeAPI::class.java)

    @Autowired
    fun ExchangeAPI(exchangeBL: ExchangeBL?) {
        this.exchangeBL = exchangeBL
    }

    @GetMapping(value = ["exchange/{to}/{from}/{amount}"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getExchange(
        @PathVariable to: Optional<String?>, @PathVariable from: Optional<String?>,
        @PathVariable amount: Optional<BigDecimal?>
    ): String? {
        //Logger
        logger.info("Custom Exchange API working")
        //Custom or Default values
        return if (to.isPresent && from.isPresent && amount.isPresent) {
            exchangeBL?.getCustomExchange(to.get(), from.get(), amount.get())
        } else {
            exchangeBL?.getDefaultExchange()
        }
    }

    @GetMapping(value = ["exchange"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getDefaultExchange(): String? {
        //Logger
        logger.info("Default Exchange API working")
        //Default values
        return exchangeBL?.getDefaultExchange()
    }


}