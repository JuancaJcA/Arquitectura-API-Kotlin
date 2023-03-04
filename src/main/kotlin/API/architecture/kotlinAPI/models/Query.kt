package API.architecture.kotlinAPI.models

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import java.math.BigDecimal

@JsonIgnoreProperties(ignoreUnknown = true)
data class Query(val amount: BigDecimal, val from: String, val to: String)