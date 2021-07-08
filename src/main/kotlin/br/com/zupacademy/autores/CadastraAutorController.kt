package br.com.zupacademy.autores

import br.com.zupacademy.autores.client.endereco.EnderecoClient
import br.com.zupacademy.autores.request.NovoAutorRequest
import br.com.zupacademy.domain.repositories.AutoresRepository
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Post
import io.micronaut.http.uri.UriBuilder
import io.micronaut.validation.Validated
import javax.transaction.Transactional
import javax.validation.Valid

@Validated
@Controller("/autores")
class CadastraAutorController (val autoresRepository: AutoresRepository, val enderecoClient: EnderecoClient){

    @Post
    @Transactional
    fun cadastra(@Body @Valid request: NovoAutorRequest) : HttpResponse<Any>{

        val enderecoResponse = enderecoClient.consulta(request.cep)

        val autor = request.paraAutor(enderecoResponse.body())!!
        val autorSalvo = autoresRepository.save(autor)
        val uri = UriBuilder.of("/autores/{id}").expand(mutableMapOf(Pair("id", autorSalvo.id)))
        return HttpResponse.created(uri)
    }
}