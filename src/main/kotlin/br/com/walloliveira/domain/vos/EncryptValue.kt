package br.com.walloliveira.domain.vos

import java.security.InvalidKeyException
import java.security.Key
import java.security.SecureRandom
import java.util.*
import javax.crypto.BadPaddingException
import javax.crypto.Cipher
import javax.crypto.IllegalBlockSizeException
import javax.crypto.spec.IvParameterSpec


class EncryptValue(
    value: StringValue,
    key: Key,
    decryptMode: Int,
) {
    companion object {
        private const val CIPHER_TRANSFORMATION = "AES/CFB8/NoPadding"
        private const val SECURE_RANDOM_ALGORITHM = "NativePRNG"
        private const val IV_SIZE = 16
    }

    val valueString: String
        get() = this.value

    private var value: String

    init {
        this.value = when (decryptMode) {
            Cipher.ENCRYPT_MODE -> encrypt(value.value, key)
            Cipher.DECRYPT_MODE -> decrypt(value.value, key)
            else -> throw IllegalArgumentException("Decrypt mode is invalid")
        }
    }

    private fun decrypt(value: String, key: Key): String {
        val cipher = Cipher.getInstance(CIPHER_TRANSFORMATION)
        return try {
            val cipherText = value.base64DecodeToByteArray()
            val iv = cipherText.copyOfRange(0, IV_SIZE)
            val valueByteArray = cipherText.copyOfRange(IV_SIZE, cipherText.size)
            cipher.init(Cipher.DECRYPT_MODE, key, IvParameterSpec(iv))
            String(cipher.doFinal(valueByteArray))
        } catch (e: InvalidKeyException) {
            throw RuntimeException(e)
        } catch (e: IllegalBlockSizeException) {
            throw RuntimeException(e)
        } catch (e: BadPaddingException) {
            throw RuntimeException(e)
        }
    }

    private fun encrypt(value: String, key: Key): String {
        val cipher = Cipher.getInstance(CIPHER_TRANSFORMATION)
        return try {
            val iv = generateIv()
            cipher.init(Cipher.ENCRYPT_MODE, key, iv)
            val cryptogram = iv.iv + cipher.doFinal(value.toByteArray())
            cryptogram.base64EncodeToString()
        } catch (e: InvalidKeyException) {
            throw RuntimeException(e)
        } catch (e: IllegalBlockSizeException) {
            throw RuntimeException(e)
        } catch (e: BadPaddingException) {
            throw RuntimeException(e)
        }
    }

    private fun generateIv(): IvParameterSpec {
        val bytes = ByteArray(IV_SIZE)
        val secureRandom = SecureRandom.getInstance(SECURE_RANDOM_ALGORITHM)
        secureRandom.nextBytes(bytes)
        return IvParameterSpec(bytes)
    }
}

private fun String.base64DecodeToByteArray() = Base64.getDecoder().decode(this)

private fun ByteArray.base64EncodeToString() = Base64.getEncoder().encodeToString(this)
