package br.com.walloliveira.entities.customer.config

import br.com.walloliveira.domains.customer.config.CustomerConfig
import br.com.walloliveira.domains.vos.Api
import br.com.walloliveira.domains.vos.ClientId
import br.com.walloliveira.domains.vos.Code
import br.com.walloliveira.domains.vos.Token
import br.com.walloliveira.entities.AttributeEncryptor
import br.com.walloliveira.entities.BaseEntity
import javax.persistence.Column
import javax.persistence.Convert
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.SequenceGenerator
import javax.persistence.Table

@Entity
@Table(name = "customer_config")
class CustomerConfigEntity : BaseEntity() {
    @SequenceGenerator(name = "CustomerConfigSeq", sequenceName = "customer_config_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CustomerConfigSeq")
    @Id
    @Column(name = "int_id")
    var id: Long? = null

    @Column(name = "code_customer")
    lateinit var customerCode: String

    @Column(name = "code_id")
    lateinit var code: String

    @Column(name = "str_token")
    @Convert(converter = AttributeEncryptor::class)
    lateinit var token: String

    @Column(name = "str_client_id")
    @Convert(converter = AttributeEncryptor::class)
    lateinit var clientId: String

    @Column(name = "enum_api")
    lateinit var api: String

    companion object {
        fun of(customerConfig: CustomerConfig): CustomerConfigEntity {
            val entity = CustomerConfigEntity()
            entity.code = customerConfig.code.valueString
            entity.customerCode = customerConfig.customerCode.valueString
            entity.clientId = customerConfig.clientId.value
            entity.token = customerConfig.token.value
            entity.api = customerConfig.api.description
            return entity
        }
    }

    fun toDomain() = CustomerConfig(
        code = Code(this.code),
        customerCode = Code(this.customerCode),
        token = Token(this.token),
        clientId = ClientId(this.clientId),
        api = Api.of(this.api),
    )
}
