package API.architecture.kotlinAPI.dao

import java.math.BigDecimal
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "currency")
data class Currency(
    var currencyFrom: String? = null,
    var currencyTo: String? = null,
    var amount: BigDecimal? = null,
    var result: BigDecimal? = null,
    var date: Date? = null,
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long = 0,
) {
    constructor() : this("", "", BigDecimal.ZERO, BigDecimal.ZERO, Date())
}