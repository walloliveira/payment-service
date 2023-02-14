package br.com.walloliveira.domains.exceptions

import br.com.walloliveira.domains.customer.config.CustomerConfig

class DuplicatedCustomerConfigurationByApiException(
    customerConfig: CustomerConfig,
) : RuntimeException("The customer config already have an api registered with: ${customerConfig.api.description}")
