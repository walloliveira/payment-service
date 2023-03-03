package br.com.walloliveira.domain.customer_config.exceptions

import br.com.walloliveira.domain.customer_config.CustomerConfig

class DuplicatedCustomerConfigException(
    customerConfig: CustomerConfig,
) : RuntimeException("The customer config already have an api registered with: ${customerConfig.api.description}")
