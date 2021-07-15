package br.com.zupacademy.autores

import br.com.zupacademy.autores.response.DetalhesAutorResponse
import br.com.zupacademy.domain.entities.Autor
import br.com.zupacademy.domain.repositories.AutoresRepository
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.QueryValue

@Controller("/autores")
class BuscaAutoresController(val autoresRepository: AutoresRepository) {

    @Get
    fun buscaAutores(@QueryValue(defaultValue = "") email: String): HttpResponse<Any> {

        if (email.isBlank()) {
            val autores: List<Autor> = autoresRepository.findAll()
            val response = autores.map { autor -> DetalhesAutorResponse(autor.nome, autor.email, autor.descricao) }
            return HttpResponse.ok(response)
        }

        val possivelAutor = autoresRepository.findByEmail(email)
        if(possivelAutor.isEmpty){
            return HttpResponse.notFound()
        }
        val autor =  possivelAutor.get()
        return HttpResponse.ok(DetalhesAutorResponse(autor.nome, autor.email, autor.descricao))
    }
}