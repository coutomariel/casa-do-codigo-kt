package br.com.zupacademy.autores

import br.com.zupacademy.autores.client.endereco.EnderecoResponse
import br.com.zupacademy.autores.response.DetalhesAutorResponse
import br.com.zupacademy.domain.entities.Autor
import br.com.zupacademy.domain.entities.Endereco
import br.com.zupacademy.domain.repositories.AutoresRepository
import io.micronaut.http.HttpStatus
import io.micronaut.http.client.HttpClient
import io.micronaut.http.client.annotation.Client
import io.micronaut.test.extensions.junit5.annotation.MicronautTest
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import javax.inject.Inject

@MicronautTest
internal class BuscaAutoresControllerTest {

    @field:Inject
    lateinit var autoresRepository: AutoresRepository

    @field:Inject
    @field:Client("/")
    lateinit var client: HttpClient

    lateinit var autor: Autor

    @BeforeEach
    internal fun setUp() {
        val enderecoResponse = EnderecoResponse("Rua das Laranjeiras", "Rio de Janeiro", "RJ")
        val endereco = Endereco(enderecoResponse, "515")
        autor = Autor("Joao das Couves", "joao.couves@gmail.com", "Guri dos games", endereco)

        autoresRepository.save(autor)
    }

    @AfterEach
    internal fun cleanUp() {
        autoresRepository.deleteAll()
    }

    @Test
    internal fun `deve retornar os detalhes de um autor`() {
        val response = client.toBlocking().exchange("/autores?email=${autor.email}", DetalhesAutorResponse::class.java)

        assertEquals(HttpStatus.OK, response.status)
        assertNotNull(response.body())
        assertEquals(autor.nome, response.body()!!.nome)
    }


}