package br.com.zupacademy.autores

import br.com.zupacademy.domain.repositories.AutoresRepository
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Post
import io.micronaut.validation.Validated
import javax.validation.Valid

@Validated
@Controller("/autores")
class CadastraAutorController (val autoresRepository: AutoresRepository){

    @Post
    fun cadastra(@Body @Valid request: NovoAutorRequest){
        val autor = request.paraAutor()
        println(autor)
        autoresRepository.save(autor)
    }
}