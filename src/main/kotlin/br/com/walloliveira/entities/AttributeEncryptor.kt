package br.com.walloliveira.entities

import java.security.InvalidKeyException
import java.security.Key
import java.security.SecureRandom
import java.util.*
import javax.crypto.BadPaddingException
import javax.crypto.Cipher
import javax.crypto.IllegalBlockSizeException
import javax.crypto.spec.SecretKeySpec
import javax.enterprise.context.ApplicationScoped
import javax.inject.Inject
import javax.persistence.AttributeConverter

@ApplicationScoped
class AttributeEncryptor constructor(
    @Inject private val encrypt: EncryptConfig,
) : AttributeConverter<String, String> {
    private val key: Key
    private val cipher: Cipher

    init {
        key = SecretKeySpec(encrypt.key()?.toByteArray(), encrypt.algorithm())
        cipher = Cipher.getInstance(encrypt.algorithm())
    }

    override fun convertToDatabaseColumn(attribute: String): String {
        return try {
            val random = SecureRandom.getInstance("NativePRNG")
            val bytes = ByteArray(32)
            random.nextBytes(bytes)
            cipher.init(Cipher.ENCRYPT_MODE, key)
            Base64.getEncoder().encodeToString(cipher.doFinal(bytes + attribute.toByteArray()))
        } catch (e: InvalidKeyException) {
            throw RuntimeException(e)
        } catch (e: IllegalBlockSizeException) {
            throw RuntimeException(e)
        } catch (e: BadPaddingException) {
            throw RuntimeException(e)
        }
    }

    override fun convertToEntityAttribute(dbData: String): String {
        return try {
            cipher.init(Cipher.DECRYPT_MODE, key)
            val doFinal = cipher.doFinal(Base64.getDecoder().decode(dbData))
            String(doFinal.copyOfRange(32, doFinal.size))
        } catch (e: InvalidKeyException) {
            throw RuntimeException(e)
        } catch (e: IllegalBlockSizeException) {
            throw RuntimeException(e)
        } catch (e: BadPaddingException) {
            throw RuntimeException(e)
        }
    }
}
