package ru.yasnovmi.springdemo.controllers

import java.util.HashMap

interface Counter {
    fun addNew(url: String)
    fun getTop(): List<Pair<String, Int>>
}

fun newDomainCounter(): Counter {
    return DomainCounter()
}

class DomainCounter : Counter {
    private val items: MutableMap<String, Int> = HashMap()

    override fun addNew(url: String) {
        items.putIfAbsent(url, 0)
        items[url] = items.getValue(url) + 1
    }

    override fun getTop(): List<Pair<String, Int>> {
        return items.toList().sortedBy { it.second }.reversed().take(10)
    }
}

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