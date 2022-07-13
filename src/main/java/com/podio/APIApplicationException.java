package com.podio;

import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Response;

import java.util.Map;

public class APIApplicationException extends WebApplicationException {

    private static final long serialVersionUID = -4533177892434958205L;

    private final Response.StatusType status;

    private final String error;

    private final String description;

    private final Map<String, String> parameters;

    public APIApplicationException(Response.StatusType status, String error, String description, Map<String, String> parameters) {
        super(status != null && status.toEnum() != null ? status.toEnum() : Response.Status.EXPECTATION_FAILED);
        this.status = status;
        this.error = error;
        this.description = description;
        this.parameters = parameters;
    }

    @Override
    public String toString() {
        return "APIException [status=" + status + ", error=" + error
                + ", description=" + description + ", parameters=" + parameters
                + "]";
    }

    public Response.StatusType getStatus() {
        return status;
    }

    public String getError() {
        return error;
    }

    public String getDescription() {
        return description;
    }

    public Map<String, String> getParameters() {
        return parameters;
    }
}
