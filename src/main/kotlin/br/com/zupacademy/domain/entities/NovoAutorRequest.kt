package br.com.zupacademy.domain.entities

import io.micronaut.core.annotation.Introspected
import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.Size

@Introspected
data class NovoAutorRequest(
    @field:NotBlank val nome: String,
    @field:NotEmpty @field:Email val email: String,
    @field:NotBlank @field:Size(max = 2 ) val descricao: String
)