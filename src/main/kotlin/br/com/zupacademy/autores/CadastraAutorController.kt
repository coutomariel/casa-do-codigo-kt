package br.com.zupacademy.autores

import br.com.zupacademy.domain.repositories.AutoresRepository
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Post
import io.micronaut.http.uri.UriBuilder
import io.micronaut.validation.Validated
import javax.validation.Valid

@Validated
@Controller("/autores")
class CadastraAutorController (val autoresRepository: AutoresRepository){

    @Post
    fun cadastra(@Body @Valid request: NovoAutorRequest) : HttpResponse<Any>{
        val autor = request.paraAutor()
        val autorSalvo = autoresRepository.save(autor)
        val uri = UriBuilder.of("/autores/{id}").expand(mutableMapOf(Pair("id", autorSalvo.id)))
        return HttpResponse.created(uri)
    }
}