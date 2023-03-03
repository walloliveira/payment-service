package br.com.walloliveira.domain.vos

import br.com.walloliveira.domain.InputCode
import java.util.UUID

class Code(value: String?) : InputCode(value) {
    companion object {
        fun new(): Code {
            return Code(UUID.randomUUID().toString())
        }
    }
}
