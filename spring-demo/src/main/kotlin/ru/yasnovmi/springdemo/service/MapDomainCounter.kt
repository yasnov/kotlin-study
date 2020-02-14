package ru.yasnovmi.springdemo.service

import org.springframework.stereotype.Service
import ru.yasnovmi.springdemo.profiler.Profiling
import java.util.*

@Profiling
@Service("mapDomainCounter")
class MapDomainCounter : Counter {
    private val items: MutableMap<String, Int> = HashMap()

    override fun addNew(url: String) {
        items.putIfAbsent(url, 0)
        items[url] = items.getValue(url) + 1
    }

    override fun getTop(): List<Pair<String, Int>> {
        return items.toList().sortedByDescending { it.second }.take(10)
    }
}