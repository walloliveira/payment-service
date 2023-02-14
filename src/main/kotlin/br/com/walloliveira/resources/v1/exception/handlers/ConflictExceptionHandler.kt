package br.com.walloliveira.resources.v1.exception.handlers

import br.com.walloliveira.domains.exceptions.DuplicatedCustomerConfigurationByApiException
import br.com.walloliveira.resources.v1.exception.ErrorResponseData
import javax.ws.rs.core.Response
import javax.ws.rs.ext.ExceptionMapper
import javax.ws.rs.ext.Provider

@Provider
class ConflictExceptionHandler : ExceptionMapper<DuplicatedCustomerConfigurationByApiException> {
    override fun toResponse(exception: DuplicatedCustomerConfigurationByApiException?): Response {
        return Response.status(Response.Status.CONFLICT).entity(ErrorResponseData(exception?.message)).build()
    }
}
