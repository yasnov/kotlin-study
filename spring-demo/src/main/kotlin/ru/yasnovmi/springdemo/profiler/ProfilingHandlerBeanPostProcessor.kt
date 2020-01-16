package ru.yasnovmi.springdemo.profiler

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.beans.factory.config.BeanPostProcessor
import org.springframework.stereotype.Component
import java.lang.reflect.Proxy
import java.util.*

@Component
class ProfilingHandlerBeanPostProcessor @Autowired constructor(
        @Value("\${common.api.profilier.enable}")
        var enable: Boolean
) : BeanPostProcessor {

    private val items: MutableMap<String, Class<*>> = HashMap()

    override fun postProcessBeforeInitialization(bean: Any, beanName: String): Any? {
        val beanClass: Class<*> = bean::class.java
        if (beanClass.isAnnotationPresent(Profiling::class.java)) {
            items[beanName] = beanClass
        }
        return bean
    }

    override fun postProcessAfterInitialization(bean: Any, beanName: String): Any? {
        val beanClass: Class<*>? = items[beanName]
        if (beanClass != null) {
            return Proxy.newProxyInstance(beanClass.classLoader, beanClass.interfaces) { _, method, args ->
                if (enable) {
                    val before: Long = System.nanoTime()
                    val retval: Any? = if (args == null) {
                        method.invoke(bean)
                    } else {
                        method.invoke(bean, *args)
                    }
                    val after: Long = System.nanoTime()
                    println("${method.name}  - ${after - before}")
                    retval
                } else {
                    method.invoke(bean, args)
                }
            }
        }
        return bean
    }
}
