package ru.yasnovmi.springdemo.service

interface Counter {
    fun addNew(url: String)
    fun getTop(): List<Pair<String, Int>>
}
