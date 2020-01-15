package ru.yasnovmi.springdemo

import org.springframework.boot.SpringApplication
import org.springframework.boot.WebApplicationType
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
class SpringDemoApplication

fun main(args: Array<String>) {
    val app = SpringApplication(SpringDemoApplication::class.java)
    app.webApplicationType = WebApplicationType.REACTIVE
    app.run(*args)
}
