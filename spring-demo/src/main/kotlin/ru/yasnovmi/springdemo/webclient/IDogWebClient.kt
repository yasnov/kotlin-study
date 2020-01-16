package ru.yasnovmi.springdemo.webclient

import reactor.core.publisher.Mono
import ru.yasnovmi.springdemo.dto.dog.DogDto

interface IDogWebClient {
    fun getDogImage(): Mono<DogDto>
}