package API.architecture.kotlinAPI.models

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import java.math.BigDecimal

@JsonIgnoreProperties(ignoreUnknown = true)
data class Query(var amount: BigDecimal, var from: String, var to: String)