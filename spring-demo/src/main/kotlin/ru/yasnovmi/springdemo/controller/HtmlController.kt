package ru.yasnovmi.springdemo.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException
import reactor.core.publisher.Mono
import ru.yasnovmi.springdemo.dto.DomainDto
import ru.yasnovmi.springdemo.service.Counter
import ru.yasnovmi.springdemo.utils.parseUrl
import ru.yasnovmi.springdemo.webclient.DogWebClient

@RestController
class HtmlController {

    @Autowired
    lateinit var dogClient: DogWebClient

    @Autowired
    @Qualifier("dbDomainCounter")
    lateinit var counter: Counter

    @RequestMapping("hello")
    fun helloWorld(): String {
        return "hello world!"
    }

    @RequestMapping("error")
    fun testError(): Void {
        throw ResponseStatusException(HttpStatus.BAD_REQUEST, "hello error!")
    }

    @RequestMapping("new", method = [RequestMethod.GET])
    fun newParamUrl(
            @RequestParam("url", required = true)
            url: String
    ): String {
        counter.addNew(parseUrl(url))
        return "ok"
    }

    @RequestMapping("new", method = [RequestMethod.POST])
    fun newJsonUrl(
            @RequestBody domain: DomainDto
    ): String {
        counter.addNew(parseUrl(domain.domain))
        return "ok"
    }

    @RequestMapping("dog", method = [RequestMethod.GET], produces = [MediaType.TEXT_HTML_VALUE])
    fun getRandomDog(): Mono<String> {
        return dogClient.getDogImage().map {
            "<img src='${it.message}'/>"
        }
    }

    @RequestMapping("top", method = [RequestMethod.GET])
    fun getTop(): List<Pair<String, Int>> {
        return counter.getTop()
    }
}