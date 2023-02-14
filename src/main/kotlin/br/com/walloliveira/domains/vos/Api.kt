package br.com.walloliveira.domains.vos

enum class Api(val description: String) {
    PAG_SEGURO("PAG_SEGURO");

    companion object {
        fun of(api: String?): Api {
            if (api.isNullOrEmpty()) {
                throw invalidValue()
            }
            try {
                return Api.valueOf(api)
            } catch (ex: IllegalArgumentException) {
                throw invalidValue()
            }
        }

        private fun invalidValue() = IllegalArgumentException("Value is invalid")
    }
}