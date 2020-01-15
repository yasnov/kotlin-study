package ru.yasnovmi.springdemo.model

import javax.persistence.*

@Entity
@Table(name = "domain", uniqueConstraints = [UniqueConstraint(columnNames = arrayOf("domain"))])
data class Domain(
        @Id
        @GeneratedValue
        val id: Long?,
        @Column(name = "domain")
        val domain: String,
        @Column(name = "count")
        var count: Long
)

