package ru.yasnovmi.springdemo.utils

import org.springframework.http.HttpStatus
import org.springframework.web.server.ResponseStatusException
import java.net.MalformedURLException
import java.net.URL

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