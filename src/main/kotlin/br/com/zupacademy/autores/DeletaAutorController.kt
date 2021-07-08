package br.com.zupacademy.autores

import br.com.zupacademy.domain.repositories.AutoresRepository
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Delete
import io.micronaut.http.annotation.PathVariable

@Controller("/autores/{id}")
class DeletaAutorController(val autoresRepository: AutoresRepository) {

    @Delete
    fun deleta(@PathVariable id: Long): HttpResponse<Any> {
        if (!autoresRepository.existsById(id)) {
            return HttpResponse.notFound()
        }
        autoresRepository.deleteById(id)
        return HttpResponse.ok()

    }
}