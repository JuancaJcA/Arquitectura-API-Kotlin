package API.architecture.kotlinAPI.dao.repository

import API.architecture.kotlinAPI.dao.Currency
import org.springframework.data.repository.CrudRepository

interface CurrencyRepository: CrudRepository<Currency, Long>