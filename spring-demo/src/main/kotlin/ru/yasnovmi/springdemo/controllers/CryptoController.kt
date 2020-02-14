package ru.yasnovmi.springdemo.controllers

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import ru.yasnovmi.springdemo.service.Cryptography

@RestController
@RequestMapping("crypto")
class CryptoController {

    @Autowired
    lateinit var crypto: Cryptography

    @RequestMapping("sign", method = [RequestMethod.GET])
    fun signData(
            @RequestParam("data", required = true)
            data: String
    ): String {
        println("adad")
        return crypto.makeSignature(data)
    }
}