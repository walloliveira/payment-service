package br.com.walloliveira.domain.vos

import br.com.walloliveira.domain.base64DecodeToByteArray
import br.com.walloliveira.domain.base64EncodeToString
import br.com.walloliveira.domain.isNull
import java.security.Key
import java.security.KeyFactory
import java.security.KeyPair
import java.security.KeyPairGenerator
import java.security.spec.PKCS8EncodedKeySpec
import java.security.spec.X509EncodedKeySpec

class AsymmetricKeyPair private constructor(
    privateKeyCryptogram: EncryptValue? = null,
    publicKeyCryptogram: EncryptValue? = null,
    key: Key,
) {

    private var publicKey: EncryptValue
    private var privateKey: EncryptValue

    val publicKeyString: String
        get() = this.publicKey.valueString

    val privateKeyString: String
        get() = this.privateKey.valueString

    init {
        val keyPair =
            if (privateKeyCryptogram.isNull() && publicKeyCryptogram.isNull()) {
                generateKeyPair()
            } else {
                keyPairOf(
                    privateKeyCryptogram!!.valueString.base64DecodeToByteArray(),
                    publicKeyCryptogram!!.valueString.base64DecodeToByteArray()
                )
            }
        this.privateKey =
            EncryptValue.encrypt(value = StringValue(keyPair.private.encoded.base64EncodeToString()), key = key)
        this.publicKey =
            EncryptValue.encrypt(value = StringValue(keyPair.public.encoded.base64EncodeToString()), key = key)
    }

    private fun generateKeyPair(): KeyPair {
        val keyPairGenerator = KeyPairGenerator.getInstance("RSA")
        keyPairGenerator.initialize(3072)
        return keyPairGenerator.generateKeyPair()
    }

    private fun keyPairOf(privateKey: ByteArray, publicKey: ByteArray): KeyPair {
        val keyFactory = KeyFactory.getInstance("RSA")
        val publicKeySpec = X509EncodedKeySpec(publicKey)
        val privateKeySpec = PKCS8EncodedKeySpec(privateKey)
        return KeyPair(keyFactory.generatePublic(publicKeySpec), keyFactory.generatePrivate(privateKeySpec))
    }

    companion object {
        fun new(key: Key): AsymmetricKeyPair {
            return AsymmetricKeyPair(key = key)
        }

        fun of(privateKeyValue: EncryptValue, publicKeyValue: EncryptValue, key: Key): AsymmetricKeyPair {
            return AsymmetricKeyPair(privateKeyValue, publicKeyValue, key)
        }
    }
}
