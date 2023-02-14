package br.com.walloliveira.domains.vos

import br.com.walloliveira.domains.InputCode
import java.util.UUID

class Code(value: String?) : InputCode(value) {
    companion object {
        fun new(): Code {
            return Code(UUID.randomUUID().toString())
        }
    }
}
