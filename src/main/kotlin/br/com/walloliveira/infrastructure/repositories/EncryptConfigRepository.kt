package br.com.walloliveira.infrastructure.repositories

import br.com.walloliveira.domain.customer_config.repositories.EncryptRepository
import br.com.walloliveira.infrastructure.EncryptConfig
import javax.crypto.Cipher
import javax.crypto.spec.SecretKeySpec
import javax.enterprise.context.ApplicationScoped
import javax.inject.Inject


@ApplicationScoped
class EncryptConfigRepository constructor(
    @Inject private val encryptConfig: EncryptConfig,
) : EncryptRepository {

    private companion object StaticEncryptConfigRepository {
        private const val CIPHER_TRANSFORMATION = "AES"
    }

    private var cipher: Cipher = Cipher.getInstance(CIPHER_TRANSFORMATION)
    private var key: SecretKeySpec = SecretKeySpec(encryptConfig.key()?.toByteArray(), CIPHER_TRANSFORMATION)

    override fun getKey() = key

    override fun getEncryptor() = cipher
}
