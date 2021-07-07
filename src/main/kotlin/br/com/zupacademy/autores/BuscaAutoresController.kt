package br.com.zupacademy.autores

import br.com.zupacademy.domain.entities.Autor
import br.com.zupacademy.domain.repositories.AutoresRepository
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get

@Controller("/autores")
class BuscaAutoresController(val autoresRepository: AutoresRepository) {

    @Get
    fun buscaAutores(): HttpResponse<List<DetalhesAutorResponse>> {
        val autores: List<Autor> = autoresRepository.findAll()
        val response = autores.map { autor -> DetalhesAutorResponse(autor) }
        return HttpResponse.ok(response)
    }
}