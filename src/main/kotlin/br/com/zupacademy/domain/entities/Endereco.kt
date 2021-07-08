package br.com.zupacademy.domain.entities

import br.com.zupacademy.autores.client.endereco.EnderecoResponse
import javax.persistence.Embeddable

@Embeddable
class Endereco(enderecoResponse: EnderecoResponse, val numero: String) {
    val estado= enderecoResponse.uf
    val cidade= enderecoResponse.localidade
    val rua = enderecoResponse.logradouro
}
