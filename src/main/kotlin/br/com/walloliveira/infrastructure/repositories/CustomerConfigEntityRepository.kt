package br.com.walloliveira.infrastructure.repositories

import br.com.walloliveira.domain.customer_config.CustomerConfig
import br.com.walloliveira.domain.customer_config.repositories.CustomerConfigRepository
import br.com.walloliveira.domain.customer_config.repositories.EncryptRepository
import br.com.walloliveira.domain.vos.Api
import br.com.walloliveira.domain.vos.AsymmetricKeyPair
import br.com.walloliveira.domain.vos.Code
import br.com.walloliveira.domain.vos.EncryptValue
import br.com.walloliveira.domain.vos.StringValue
import br.com.walloliveira.infrastructure.entities.customer_config.CustomerConfigEntity
import io.quarkus.hibernate.orm.panache.kotlin.PanacheRepository
import javax.enterprise.context.ApplicationScoped
import javax.inject.Inject
import javax.transaction.Transactional

@ApplicationScoped
class CustomerConfigEntityRepository(@Inject private val encryptRepository: EncryptRepository) :
    CustomerConfigRepository, PanacheRepository<CustomerConfigEntity> {
    @Transactional
    override fun save(customerConfig: CustomerConfig) {
        persist(CustomerConfigEntity.of(customerConfig))
    }

    override fun list(): List<CustomerConfig> {
        return findAll().list().map(::buildCustomerConfig)
    }

    private fun buildCustomerConfig(customerConfigEntity: CustomerConfigEntity): CustomerConfig {
        return CustomerConfig(
            code = Code.of(customerConfigEntity.code),
            customerCode = Code.of(customerConfigEntity.customerCode),
            token = EncryptValue.decrypt(value = StringValue(customerConfigEntity.token), encryptRepository.getKey()),
            clientId = EncryptValue.decrypt(
                value = StringValue(customerConfigEntity.clientId),
                encryptRepository.getKey()
            ),
            api = Api.of(customerConfigEntity.api),
            asymmetricKeyPair = AsymmetricKeyPair.of(
                privateKeyValue = EncryptValue.decrypt(
                    StringValue(customerConfigEntity.privateKey),
                    key = encryptRepository.getKey()
                ),
                publicKeyValue = EncryptValue.decrypt(
                    StringValue(customerConfigEntity.publicKey),
                    key = encryptRepository.getKey()
                ),
                key = encryptRepository.getKey(),
            )
        )
    }

    override fun findByCustomerCode(customerCode: Code): List<CustomerConfig> {
        return find("customerCode", customerCode.valueString).list().map(::buildCustomerConfig)
    }

    override fun findByCustomerCodeAndApi(customerCode: Code, api: Api): CustomerConfig? {
        return find(
            "customerCode = ?1 and api = ?2",
            customerCode.valueString,
            api.description,
        ).firstResult()?.let(::buildCustomerConfig)
    }
}
