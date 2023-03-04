package br.com.walloliveira.domain.customer_config

import br.com.walloliveira.domain.vos.Api
import br.com.walloliveira.domain.vos.Code
import br.com.walloliveira.domain.vos.EncryptValue
import br.com.walloliveira.domain.vos.StringValue
import java.security.Key
import javax.crypto.Cipher

class CustomerConfig private constructor(
    val code: Code,
    val customerCode: Code,
    val api: Api,
    token: StringValue,
    clientId: StringValue,
    key: Key,
    encryptor: Cipher,
    decryptMode: Int,
) {
    val token = EncryptValue(value = token, key = key, cipher = encryptor, decryptMode = decryptMode)
    val clientId = EncryptValue(value = clientId, key = key, cipher = encryptor, decryptMode = decryptMode)

    companion object {
        fun toEncrypt(
            newCustomerConfig: NewCustomerConfig,
            key: Key,
            encryptor: Cipher,
        ): CustomerConfig {
            return CustomerConfig(
                code = Code.new(),
                customerCode = newCustomerConfig.customerCode,
                token = newCustomerConfig.token,
                clientId = newCustomerConfig.clientId,
                api = newCustomerConfig.api,
                key = key,
                encryptor = encryptor,
                decryptMode = Cipher.ENCRYPT_MODE,
            )
        }

        fun fromEncrypted(
            code: Code,
            customerCode: Code,
            token: StringValue,
            clientId: StringValue,
            api: Api,
            key: Key,
            encryptor: Cipher
        ): CustomerConfig {
            return CustomerConfig(
                code = code,
                customerCode = customerCode,
                token = token,
                clientId = clientId,
                api = api,
                key = key,
                encryptor = encryptor,
                decryptMode = Cipher.DECRYPT_MODE,
            )
        }
    }
}
