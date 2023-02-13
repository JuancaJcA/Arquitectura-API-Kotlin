package API.architecture.kotlinAPI.models

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import java.math.BigDecimal

@JsonIgnoreProperties(ignoreUnknown = true)
data class Exchange(var date: String, var result: BigDecimal, var success: Boolean, var query: Query)