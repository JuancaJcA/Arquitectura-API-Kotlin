package API.architecture.kotlinAPI.dao.repository

import API.architecture.kotlinAPI.dao.Currency
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
//interface CurrencyRepository: JpaRepository<Currency, Long> {
//    //fun findAll(pageable: Pageable): Page<Currency>
//}

interface CurrencyRepository : CrudRepository<Currency, Long> {
     fun findAll(pageable: Pageable): Page<Currency>
}