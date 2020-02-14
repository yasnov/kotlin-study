package ru.yasnovmi.springdemo.utils

import org.springframework.beans.factory.config.BeanDefinition
import org.springframework.beans.factory.config.BeanFactoryPostProcessor
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory
import kotlin.reflect.jvm.jvmName

//@Component
class DeprecationHandlerBeanFactoryPostProcessor : BeanFactoryPostProcessor {

    override fun postProcessBeanFactory(beanFactory: ConfigurableListableBeanFactory) {
        for (name in beanFactory.beanDefinitionNames) {
            val beanDefinition: BeanDefinition = beanFactory.getBeanDefinition(name)
            val beanClassName: String? = beanDefinition.beanClassName
            try {
                val beanClass: Class<*> = Class.forName(beanClassName)
                val annotation: DeprecatedClass = beanClass.getAnnotation(DeprecatedClass::class.java)
                if (annotation != null) {
                    beanDefinition.beanClassName = annotation.newImpl.jvmName
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }

        }
    }
}