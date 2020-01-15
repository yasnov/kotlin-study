package ru.yasnovmi.springdemo.controllers

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.web.bind.annotation.*
import ru.yasnovmi.springdemo.dto.DomainDto
import ru.yasnovmi.springdemo.service.Counter
import ru.yasnovmi.springdemo.utils.parseUrl

@RestController
@RequestMapping("domain")
class TopDomainController {

    @Autowired
    @Qualifier("dbDomainCounter")
    lateinit var counter: Counter

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