package br.com.walloliveira.application.v1.exception_handlers

import br.com.walloliveira.application.v1.responses.ErrorResponse
import javax.ws.rs.core.Response
import javax.ws.rs.ext.ExceptionMapper
import javax.ws.rs.ext.Provider

@Provider
class BadRequestExceptionHandler : ExceptionMapper<IllegalArgumentException> {
    override fun toResponse(exception: IllegalArgumentException?): Response {
        return Response.status(Response.Status.BAD_REQUEST).entity(ErrorResponse(exception?.message)).build()
    }
}
