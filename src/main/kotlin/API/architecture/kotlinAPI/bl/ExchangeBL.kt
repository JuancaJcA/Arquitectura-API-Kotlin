package API.architecture.kotlinAPI.bl

import API.architecture.kotlinAPI.dao.Currency
import API.architecture.kotlinAPI.dao.repository.CurrencyRepository
import API.architecture.kotlinAPI.errorHandling.CustomMessageError
import API.architecture.kotlinAPI.models.Exchange
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.io.IOException
import java.math.BigDecimal
import java.util.*

@Service
class ExchangeBL @Autowired constructor(private val currencyRepository: CurrencyRepository){
    //Logger
    var logger = LoggerFactory.getLogger(this::class.java)

    fun getCustomExchange(to: String, from: String, amount: BigDecimal): String? {
        //Logger
        logger.info("Custom Exchange BL working")
        //Verify amount greater than ZERO
        if (amount <= BigDecimal.ZERO) {
            throw CustomMessageError("Amount value greater or equals to ZERO.")
        } else {
            //Ok Http
            val client = OkHttpClient().newBuilder().build()
            val request = Request.Builder()
                .url("https://api.apilayer.com/exchangerates_data/convert?to=$to&from=$from&amount=$amount")
                .addHeader("apikey", "gybHqhso62y1RsHXMa24UTLgwPy7l4AS")
                .build()
            val response: Response
            try {
                response = client.newCall(request).execute()
                val jsonData = Objects.requireNonNull(response.body)?.string()
                //Jackson Library
                val objectMapper = jacksonObjectMapper()
                val obj: Exchange = objectMapper.readValue(jsonData, Exchange::class.java)
                //Logger
                logger.info("Custom Response: $obj")
                //JPA
                val currency = Currency()
                currency.currencyFrom = from
                currency.currencyTo = to
                currency.amount = amount
                currency.date = Date()
                currency.result = obj.result
                currencyRepository.save(currency)
                return obj.toString()
            } catch (e: IOException) {
                throw CustomMessageError(e.toString())
            }
        }
    }

    fun getDefaultExchange(): String? {
        //Logger
        logger.info("Default Exchange BL working (Amount: 100 - From: USD - To: BOB)")
        //Ok Http
        val client = OkHttpClient().newBuilder().build()
        val request = Request.Builder()
            .url("https://api.apilayer.com/exchangerates_data/convert?to=USD&from=BOB&amount=100")
            .addHeader("apikey", "gybHqhso62y1RsHXMa24UTLgwPy7l4AS")
            .build()
        val response: Response
        try {
            response = client.newCall(request).execute()
            val jsonData = Objects.requireNonNull(response.body)?.string()
            //Jackson Library
            val objectMapper = jacksonObjectMapper()
            val obj: Exchange = objectMapper.readValue(jsonData, Exchange::class.java)
            //Logger
            logger.info("Custom Response: $obj")
            return obj.toString()
        } catch (e: IOException) {
            throw CustomMessageError(e.toString())
        }
    }
}