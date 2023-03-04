package br.com.walloliveira.infrastructure.entities.customer_config

import br.com.walloliveira.domain.customer_config.CustomerConfig
import br.com.walloliveira.infrastructure.entities.BaseEntity
import javax.persistence.Column
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
    lateinit var token: String

    @Column(name = "str_client_id")
    lateinit var clientId: String

    @Column(name = "enum_api")
    lateinit var api: String

    companion object {
        fun of(customerConfig: CustomerConfig): CustomerConfigEntity {
            val entity = CustomerConfigEntity()
            entity.code = customerConfig.code.valueString
            entity.customerCode = customerConfig.customerCode.valueString
            entity.clientId = customerConfig.clientId.valueString
            entity.token = customerConfig.token.valueString
            entity.api = customerConfig.api.description
            return entity
        }
    }
}
