package API.architecture.kotlinAPI.api

import API.architecture.kotlinAPI.bl.ExchangeBL
import API.architecture.kotlinAPI.models.Exchange
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.math.BigDecimal
import java.util.*

@RestController
@RequestMapping(path = ["api/v1"])
//@CrossOrigin(origins = ["http://localhost:5000"], allowedHeaders = ["*"])
class ExchangeAPI {
    //Business Logic
    private var exchangeBL: ExchangeBL? = null

    @Autowired
    fun ExchangeAPI(exchangeBL: ExchangeBL?) {
        this.exchangeBL = exchangeBL
    }

    //Logger
    companion object {
        private val LOGGER: Logger = LoggerFactory.getLogger(ExchangeAPI::class.java)
    }

    @GetMapping(value = ["exchange/{to}/{from}/{amount}"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getExchange(
        @PathVariable to: Optional<String?>, @PathVariable from: Optional<String?>,
        @PathVariable amount: Optional<BigDecimal?>
    ): Exchange? {
        //Logger
        LOGGER.info("Custom Exchange API working")
        //Custom or Default values
        return if (to.isPresent && from.isPresent && amount.isPresent) {
            exchangeBL?.getCustomExchange(to.get(), from.get(), amount.get())
        } else {
            null
        }
    }

    @GetMapping(value = ["exchange"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getDefaultExchange(): String? {
        //Logger
        LOGGER.info("Default Exchange API working")
        //Default values
        return exchangeBL?.getDefaultExchange()
    }


}