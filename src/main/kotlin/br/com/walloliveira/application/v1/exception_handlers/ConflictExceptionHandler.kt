package br.com.walloliveira.application.v1.exception_handlers

import br.com.walloliveira.domain.customer_config.exceptions.DuplicatedCustomerConfigException
import br.com.walloliveira.application.v1.responses.ErrorResponse
import javax.ws.rs.core.Response
import javax.ws.rs.ext.ExceptionMapper
import javax.ws.rs.ext.Provider

@Provider
class ConflictExceptionHandler : ExceptionMapper<DuplicatedCustomerConfigException> {
    override fun toResponse(exception: DuplicatedCustomerConfigException?): Response {
        return Response.status(Response.Status.CONFLICT).entity(ErrorResponse(exception?.message)).build()
    }
}
