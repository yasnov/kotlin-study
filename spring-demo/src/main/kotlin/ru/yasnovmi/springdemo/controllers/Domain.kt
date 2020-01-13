package ru.yasnovmi.springdemo.controllers

import org.springframework.stereotype.Repository
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Table

@Table(name = "domain")
data class Domain (
        @Id val id: Long?,
        @Column(name = "name") val firstName: String,
        @Column(name = "count") val lastName: Long)

