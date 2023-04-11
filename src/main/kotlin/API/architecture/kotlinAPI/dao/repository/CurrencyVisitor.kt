package API.architecture.kotlinAPI.dao.repository

import API.architecture.kotlinAPI.dao.CurrencyV

interface CurrencyVisitor {
    fun visit(currency: CurrencyV)
}