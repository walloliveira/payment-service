package br.com.walloliveira.domains

import java.util.*

abstract class InputCode(v: String?) {
    var value: UUID
        private set
    val valueString: String
        get() = this.value.toString()

    init {
        validate(v)
        this.value = UUID.fromString(v)
    }

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

    override fun toString(): String {
        return "InputCode(value=$value)"
    }
}