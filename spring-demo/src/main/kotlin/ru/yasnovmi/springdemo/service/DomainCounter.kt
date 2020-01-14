package ru.yasnovmi.springdemo.service

import ru.yasnovmi.springdemo.repository.DomainRepository

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import ru.yasnovmi.springdemo.controllers.Domain

@Service
class DomainCounter : Counter {

    @Autowired
    lateinit var domainRepository: DomainRepository

    override fun addNew(url: String) {
        val savedDomain: Domain? = domainRepository.getByDomainName(url)
        if (savedDomain == null) {
            domainRepository.save(Domain(null, url, 1))
        } else {
            savedDomain.count++
            domainRepository.save(savedDomain)
        }
    }

    override fun getTop(): List<Pair<String, Int>> {
        return domainRepository.findAll()
                .toList()
                .sortedByDescending { it.count }
                .map { Pair(it.domain, it.count.toInt()) }
                .take(10)
    }
}
