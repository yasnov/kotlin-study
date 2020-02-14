package ru.yasnovmi.springdemo.service

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import java.nio.file.Files
import java.nio.file.Paths
import java.security.KeyFactory
import java.security.PrivateKey
import java.security.Signature
import java.security.spec.PKCS8EncodedKeySpec
import java.util.*

@Service
class Cryptography {

    @Value("\${crypto.api.key.private}")
    lateinit var privateKeyPath: String

    fun makeSignature(data: String): String {
        val keyBytes: ByteArray = Files.readAllBytes(Paths.get(privateKeyPath))
        val kf: KeyFactory = KeyFactory.getInstance("RSA")

        val keySpecPKCS8 = PKCS8EncodedKeySpec(keyBytes)
        val key: PrivateKey = kf.generatePrivate(keySpecPKCS8)

        val signature: Signature = Signature.getInstance("SHA256withRSA")
        signature.initSign(key)
        val messageBytes = data.toByteArray()
        signature.update(messageBytes)
        val result: ByteArray? = signature.sign()
        return Base64.getEncoder().encodeToString(result)
    }
}