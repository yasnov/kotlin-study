package ru.yasnovmi.springdemo.controllers

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono
import ru.yasnovmi.springdemo.webclient.IDogWebClient

@RestController
@RequestMapping("dog")
class DogImageController {

    @Autowired
    lateinit var dogClient: IDogWebClient

    @RequestMapping("image", method = [RequestMethod.GET], produces = [MediaType.TEXT_HTML_VALUE])
    fun getRandomDog(): Mono<String> {
        return dogClient.getDogImage().map {
            "<img src='${it.message}'/>"
        }
    }
}