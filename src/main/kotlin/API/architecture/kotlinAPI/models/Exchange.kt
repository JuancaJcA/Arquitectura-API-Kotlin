package API.architecture.kotlinAPI.models

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import java.math.BigDecimal

@JsonIgnoreProperties(ignoreUnknown = true)
data class Exchange(val date: String, val result: BigDecimal, val success: Boolean, val query: Query)