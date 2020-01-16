package ru.yasnovmi.springdemo.webclient

import org.springframework.beans.factory.annotation.Value
import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.WebClient.create
import reactor.core.publisher.Mono
import ru.yasnovmi.springdemo.dto.dog.DogDto
import ru.yasnovmi.springdemo.profiler.Profiling
import javax.annotation.PostConstruct

@Component
@Profiling
class DogWebClient : IDogWebClient {

    @Value("\${dog.api.url}")
    lateinit var dogApiUrl: String

    lateinit var client: WebClient

    @PostConstruct
    fun init() {
        client = create(dogApiUrl)
    }

    override fun getDogImage(): Mono<DogDto> {
        return client.get()
                .uri("/breeds/image/random").accept(MediaType.APPLICATION_JSON)
                .exchange()
                .flatMap { response -> response.bodyToMono<DogDto>(DogDto::class.java) }
    }
}