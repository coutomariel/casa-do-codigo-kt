package br.com.zupacademy.autores.request

import br.com.zupacademy.autores.client.endereco.EnderecoResponse
import br.com.zupacademy.domain.entities.Autor
import br.com.zupacademy.domain.entities.Endereco
import io.micronaut.core.annotation.Introspected
import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.Size

@Introspected
data class NovoAutorRequest(
    @field:NotBlank val nome: String,
    @field:NotEmpty @field:Email val email: String,
    @field:NotBlank @field:Size(max = 400 ) val descricao: String,
    @field:NotBlank val cep: String,
    @field:NotBlank val numero: String
) {
    fun paraAutor(enderecoResponse: EnderecoResponse): Autor {
        val endereco: Endereco = Endereco(enderecoResponse, numero)
        return Autor(nome, email, descricao, endereco)
    }
}