package com.tradeshift;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class InvalidInputMapper implements ExceptionMapper<Throwable> {
    public Response toResponse(Throwable ex) {
        String message = "Input request is invalid";

        return Response.status(404).
                entity(message).
                type("text/plain").
                build();
    }
}
