package API.architecture.kotlinAPI.dao

import API.architecture.kotlinAPI.dao.repository.CurrencyVisitor
import java.math.BigDecimal
import java.util.*
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
class CurrencyV {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null
    var currencyFrom: String? = null
    var currencyTo: String? = null
    var amount: BigDecimal? = null
    var date: Date? = null
    var result: BigDecimal? = null

    fun accept(visitor: CurrencyVisitor) {
        visitor.visit(this)
    }
}