package ru.yasnovmi.springdemo.service

import java.util.HashMap

class DomainCounterRevers : Counter {
    private val items: MutableMap<String, Int> = HashMap()

    override fun addNew(url: String) {
        items.putIfAbsent(url, 0)
        items[url] = items.getValue(url) + 1
    }

    override fun getTop(): List<Pair<String, Int>> {
        return items.toList().sortedBy { it.second }.take(10)
    }
}