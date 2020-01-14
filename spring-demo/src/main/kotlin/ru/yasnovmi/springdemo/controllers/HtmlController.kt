package ru.yasnovmi.springdemo.controllers

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.server.ResponseStatusException
import ru.yasnovmi.springdemo.service.Counter
import java.net.MalformedURLException
import java.net.URL

@RestController
class HtmlController {

    @Autowired
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
    fun newURL(
            @RequestParam("url", required = true)
            url: String
    ): String {
        counter.addNew(parseUrl(url))
        return "ok"
    }

    @RequestMapping("top", method = [RequestMethod.GET])
    fun getTop(): String {
        return counter.getTop().toString()
    }
}

fun parseUrl(url: String): String {
    val u = try {
        URL(url)
    } catch (ex: MalformedURLException) {
        URL("http://$url")
    }

    val pattern = "^[-a-zA-Z0-9+&@#/%?=~_|!:,;]+\\.[-a-zA-Z0-9+&@#/%?=~_|!:,.;]+\$".toRegex()
    if (!u.host.matches(pattern)) {
        throw ResponseStatusException(HttpStatus.BAD_REQUEST, "$url not correct URL")
    }
    return u.host.toLowerCase()
}
