package br.com.zupacademy.autores.response

import br.com.zupacademy.domain.entities.Autor

class DetalhesAutorResponse(autor: Autor) {

    val nome = autor.nome
    val email = autor.email
    val descricao = autor.descricao

}