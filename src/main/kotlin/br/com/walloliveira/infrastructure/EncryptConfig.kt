package br.com.walloliveira.infrastructure

import io.smallrye.config.ConfigMapping

@ConfigMapping(prefix = "encrypt")
interface EncryptConfig {
    fun key(): String?
    fun algorithm(): String?
}
