package API.architecture.kotlinAPI.bl

import API.architecture.kotlinAPI.dao.Currency
import API.architecture.kotlinAPI.dao.repository.CurrencyRepository
import API.architecture.kotlinAPI.errorHandling.CustomMessageError
import API.architecture.kotlinAPI.models.Exchange
import com.fasterxml.jackson.module.kotlin.readValue
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import java.io.IOException
import java.math.BigDecimal
import java.util.*

@Service
class ExchangeBL @Autowired constructor(private val currencyRepository: CurrencyRepository) {
    //Logger
    companion object {
        val objectMapper = jacksonObjectMapper()
        val LOGGER: Logger = LoggerFactory.getLogger(ExchangeBL::class.java)
    }

    @Value("\${api.url}")
    lateinit var apiUrl: String

    @Value("\${api.key}")
    lateinit var apiKey: String

    fun getCustomExchange(to: String, from: String, amount: BigDecimal): Exchange {
        //Logger
        LOGGER.info("Custom Exchange BL working")
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
                LOGGER.info("Custom Response: $obj")
                //JPA
                val currency = Currency()
                currency.currencyFrom = from
                currency.currencyTo = to
                currency.amount = amount
                currency.date = Date()
                currency.result = obj.result
                currencyRepository.save(currency)
                return obj
            } catch (e: IOException) {
                throw CustomMessageError(e.toString())
            }
        }
    }
//    fun getCustomExchange(to: String, from: String, amount: BigDecimal): Exchange {
//        //Logger
//        LOGGER.info("Custom Exchange BL working")
//        //Verify amount greater than ZERO
//        if (amount <= BigDecimal.ZERO) {
//            LOGGER.info("Amount value greater or equals to ZERO.")
//            throw CustomMessageError("Amount value greater or equals to ZERO.")
//        }
//        val response = exchangeApi("$apiUrl?to=$to&from=$from&amount=$amount")
//        val responseServiceDto = parseResponse(response)
//        return responseServiceDto
//    }
//
//    fun exchangeApi(endpoint: String): Response {
//        LOGGER.info("Exchange API called")
//        val client = OkHttpClient()
//        val request = Request.Builder()
//            .url(endpoint)
//            .addHeader("apikey", apiKey)
//            .build()
//        try {
//            return client.newCall(request).execute()
//        } catch (e: Exception) {
//            e.printStackTrace()
//            throw CustomMessageError("Error in exchange API")
//        }
//    }
//
//    fun parseResponse(response: Response): Exchange {
//        LOGGER.info("Parsing response.")
//        val body = (response.body)?.string()
//        val jsonData = Objects.requireNonNull(response.body)?.string()
//        LOGGER.info("Response from API => $body")
//        if (response.isSuccessful) {
//            return objectMapper.readValue(jsonData, Exchange::class.java)
//        }
//        throw CustomMessageError(jsonData.toString())
//    }

    fun getDefaultExchange(): String? {
        //Logger
        LOGGER.info("Default Exchange BL working (Amount: 100 - From: USD - To: BOB)")
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
            LOGGER.info("Custom Response: $obj")
            return obj.toString()
        } catch (e: IOException) {
            throw CustomMessageError(e.toString())
        }
    }
}