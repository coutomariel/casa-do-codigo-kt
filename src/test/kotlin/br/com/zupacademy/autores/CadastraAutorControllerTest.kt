package br.com.zupacademy.autores

import br.com.zupacademy.autores.client.endereco.EnderecoClient
import br.com.zupacademy.autores.client.endereco.EnderecoResponse
import br.com.zupacademy.autores.request.NovoAutorRequest
import br.com.zupacademy.domain.repositories.AutoresRepository
import io.micronaut.http.HttpRequest
import io.micronaut.http.HttpResponse
import io.micronaut.http.HttpStatus
import io.micronaut.http.client.HttpClient
import io.micronaut.http.client.annotation.Client
import io.micronaut.test.annotation.MockBean
import io.micronaut.test.extensions.junit5.annotation.MicronautTest
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import javax.inject.Inject

@MicronautTest
internal class CadastraAutorControllerTest {

    @field:Inject
    lateinit var enderecoClient: EnderecoClient

    @field:Inject
    lateinit var repository: AutoresRepository

    @field:Inject
    @field:Client("/")
    lateinit var client : HttpClient

    @Test
    fun `deve cadastrar um novo autor`(){
        //cenário
        val novoAutorRequest = NovoAutorRequest("Rafael Ponte", "rafael.ponte@zup.com.br", "Principe dos Oceanos", "92524680", "334")

        val enderecoResponse = EnderecoResponse("Rua das bromélias", "Santos", "SP")
        Mockito.`when`(enderecoClient.consulta(novoAutorRequest.cep)).thenReturn(HttpResponse.ok(enderecoResponse))

        val request = HttpRequest.POST("/autores", novoAutorRequest)

        //ação
        val response = client.toBlocking().exchange(request, Any::class.java)
        //validação

        assertEquals(HttpStatus.CREATED, response.status)
//        assertTrue(response.headers.contains("Location"))
//        assertTrue(response.header("Location")!!.matches("/autores/\\d".toRegex()))

    }

    @MockBean(EnderecoClient::class)
    fun enderecoMock(): EnderecoClient {
        return Mockito.mock(EnderecoClient::class.java)
    }
}
