package br.com.walloliveira.resources.v1.exception.handlers

import br.com.walloliveira.resources.v1.exception.ErrorResponseData
import javax.ws.rs.core.Response
import javax.ws.rs.ext.ExceptionMapper
import javax.ws.rs.ext.Provider

@Provider
class BadRequestExceptionHandler : ExceptionMapper<IllegalArgumentException> {
    override fun toResponse(exception: IllegalArgumentException?): Response {
        return Response.status(Response.Status.BAD_REQUEST).entity(ErrorResponseData(exception?.message)).build()
    }
}
