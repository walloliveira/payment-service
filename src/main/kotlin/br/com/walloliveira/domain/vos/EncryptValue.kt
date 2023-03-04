package br.com.walloliveira.domain.vos

import java.security.InvalidKeyException
import java.security.Key
import java.security.SecureRandom
import java.util.*
import javax.crypto.BadPaddingException
import javax.crypto.Cipher
import javax.crypto.IllegalBlockSizeException


class EncryptValue (
    value: StringValue,
    key: Key,
    decryptMode: Int,
    private val cipher: Cipher,
) {
    companion object {
        private const val SECURE_RANDOM_ALGORITHM = "NativePRNG"
        private const val ENCRYPT_BYTE_SIZE = 32
    }

    val valueString: String
        get() = this.value

    private var value: String

    init {
        cipher.init(decryptMode, key)
        this.value = when (decryptMode) {
            Cipher.ENCRYPT_MODE -> encrypt(value.value)
            Cipher.DECRYPT_MODE -> decrypt(value.value)
            else -> throw IllegalArgumentException("Decrypt mode is invalid")
        }
    }

    private fun decrypt(value: String): String {
        return try {
            val encryptedValue = cipher.doFinal(value.base64DecodeToByteArray())
            val removedPrefix = encryptedValue.copyOfRange(ENCRYPT_BYTE_SIZE, encryptedValue.size)
            val result = removedPrefix.copyOfRange(0, removedPrefix.size - ENCRYPT_BYTE_SIZE)
            String(result)
        } catch (e: InvalidKeyException) {
            throw RuntimeException(e)
        } catch (e: IllegalBlockSizeException) {
            throw RuntimeException(e)
        } catch (e: BadPaddingException) {
            throw RuntimeException(e)
        }
    }

    private fun encrypt(value: String): String {
        return try {
            val random = SecureRandom.getInstance(SECURE_RANDOM_ALGORITHM)
            val bytes = ByteArray(ENCRYPT_BYTE_SIZE)
            random.nextBytes(bytes)
            val prefix = bytes + value.toByteArray()
            random.nextBytes(bytes)
            val result = prefix + bytes
            cipher.doFinal(result).base64EncodeToString()
        } catch (e: InvalidKeyException) {
            throw RuntimeException(e)
        } catch (e: IllegalBlockSizeException) {
            throw RuntimeException(e)
        } catch (e: BadPaddingException) {
            throw RuntimeException(e)
        }
    }
}

private fun String.base64DecodeToByteArray() = Base64.getDecoder().decode(this)

private fun ByteArray.base64EncodeToString() = Base64.getEncoder().encodeToString(this)
