package ru.yasnovmi.springdemo.utils

import kotlin.reflect.KClass

@Target(AnnotationTarget.CLASS)
annotation class DeprecatedClass(val newImpl: KClass<*>)
