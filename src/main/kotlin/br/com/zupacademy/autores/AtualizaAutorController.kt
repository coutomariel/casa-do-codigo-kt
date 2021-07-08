package br.com.zupacademy.autores

import br.com.zupacademy.domain.repositories.AutoresRepository
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.PathVariable
import io.micronaut.http.annotation.Put
import io.micronaut.validation.Validated
import javax.transaction.Transactional

@Validated
@Controller("/autores/{id}")
class AtualizaAutorController(val autoresRepository: AutoresRepository) {

    @Put
    @Transactional
    fun atualiza(@PathVariable id: Long, descricao: String): HttpResponse<Any> {
        if (!autoresRepository.existsById(id)) {
            return HttpResponse.notFound()
        }

        val autor = autoresRepository.findById(id).get()
        autor.descricao = descricao
        autoresRepository.update(autor)
        return HttpResponse.ok("Descrição alterada")

    }
}