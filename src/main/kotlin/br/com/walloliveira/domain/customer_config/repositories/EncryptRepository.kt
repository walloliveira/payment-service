package br.com.walloliveira.domain.customer_config.repositories

import java.security.Key
import javax.crypto.Cipher

interface EncryptRepository {

    fun getKey(): Key
    fun getEncryptor(): Cipher
}
