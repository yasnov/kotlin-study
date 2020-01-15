package ru.yasnovmi.springdemo.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import ru.yasnovmi.springdemo.model.Domain

@Repository
interface DomainRepository : JpaRepository<Domain, Long> {
    @Query("SELECT d FROM Domain d WHERE d.domain = :domain")
    fun getByDomainName(domain: String): Domain?
}