package br.com.zupacademy.domain.entities

import java.time.LocalDateTime
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
class Autor(
    var nome: String,
    var email: String,
    var descricao: String,
    var endereco: Endereco
) {
    @Id
    @GeneratedValue
    var id: Long? = null
    val criadoEm: LocalDateTime = LocalDateTime.now()
}
