package br.com.walloliveira.entities

import io.smallrye.config.ConfigMapping

@ConfigMapping(prefix = "encrypt")
interface EncryptConfig {
    fun key(): String?
    fun algorithm(): String?
}
