package br.com.zupacademy.domain.repositories

import br.com.zupacademy.domain.entities.Autor
import io.micronaut.data.annotation.Repository
import io.micronaut.data.jpa.repository.JpaRepository
import java.util.*

@Repository
interface AutoresRepository: JpaRepository<Autor, Long>{
    fun findByEmail(email: String): Optional<Autor>
}