package br.com.walloliveira.domain.vos

import java.util.*

class Code(private val value: UUID) {

    val valueString: String
        get() = this.value.toString()

    private fun validate(v: String?) {
        if (v.isNullOrEmpty()) {
            throw IllegalArgumentException("Invalid value")
        }
        try {
            UUID.fromString(v);
        } catch (ex: IllegalArgumentException) {
            throw IllegalArgumentException("Invalid value")
        }
    }

    companion object {
        fun new(): Code {
            return Code(UUID.randomUUID())
        }

        fun of(v: String?): Code {
            if (v.isNullOrEmpty()) {
                throw IllegalArgumentException("Invalid value")
            }
            try {
                return Code(UUID.fromString(v));
            } catch (ex: IllegalArgumentException) {
                throw IllegalArgumentException("Invalid value")
            }
        }
    }
}