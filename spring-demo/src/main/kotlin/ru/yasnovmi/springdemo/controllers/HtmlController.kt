package ru.yasnovmi.springdemo.controllers

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.server.ResponseStatusException
import ru.yasnovmi.springdemo.dto.DomainDto
import ru.yasnovmi.springdemo.service.Counter
import java.net.MalformedURLException
import java.net.URL

@RestController
class HtmlController {

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

    @RequestMapping("top", method = [RequestMethod.GET])
    fun getTop(): List<Pair<String, Int>> {
        return counter.getTop()
    }
}

fun parseUrl(url: String): String {
    val u = try {
        URL(url)
    } catch (ex: MalformedURLException) {
        URL("http://$url")
    }

    val split = u.host.split(".")
    if (split.size < 2) {
        throw ResponseStatusException(HttpStatus.BAD_REQUEST, "$url not correct URL")
    }

    return split.takeLast(2).joinToString(".").toLowerCase()
}
