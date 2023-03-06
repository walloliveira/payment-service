package br.com.walloliveira.domain.customer_config

import br.com.walloliveira.domain.customer_config.exceptions.DuplicatedKeyPairException
import br.com.walloliveira.domain.vos.Api
import br.com.walloliveira.domain.vos.AsymmetricKeyPair
import br.com.walloliveira.domain.vos.Code
import br.com.walloliveira.domain.vos.EncryptValue


class CustomerConfig constructor(
    val code: Code,
    val customerCode: Code,
    val api: Api,
    val token: EncryptValue,
    val clientId: EncryptValue,
    private val asymmetricKeyPair: AsymmetricKeyPair
) {
    val privateKeyString: String
        get() = this.asymmetricKeyPair.privateKeyString

    val publicKeyString: String
        get() = this.asymmetricKeyPair.publicKeyString
}
