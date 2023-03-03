package br.com.walloliveira.domain

abstract class InputString(v: String?) {
    var value: String
        private set

    init {
        validate(v)
        this.value = v!!;
    }

    private fun validate(v: String?) {
        if (v.isNullOrEmpty()) {
            throw IllegalArgumentException("Invalid value")
        }
    }
}
