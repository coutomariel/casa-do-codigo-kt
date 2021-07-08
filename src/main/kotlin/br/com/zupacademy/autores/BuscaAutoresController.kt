package br.com.zupacademy.autores

import br.com.zupacademy.domain.entities.Autor
import br.com.zupacademy.domain.repositories.AutoresRepository
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.QueryValue
import javax.swing.Spring

@Controller("/autores")
class BuscaAutoresController(val autoresRepository: AutoresRepository) {

    @Get
    fun buscaAutores(@QueryValue(defaultValue = "") email: String): HttpResponse<Any> {

        if (email.isBlank()) {
            val autores: List<Autor> = autoresRepository.findAll()
            val response = autores.map { autor -> DetalhesAutorResponse(autor) }
            return HttpResponse.ok(response)
        }

        val autorPorEmail = autoresRepository.findByEmail(email)
        if(autorPorEmail.isEmpty){
            return HttpResponse.notFound()
        }

        return HttpResponse.ok(autorPorEmail.get())
    }
}