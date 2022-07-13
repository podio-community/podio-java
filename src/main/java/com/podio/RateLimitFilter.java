package com.podio;

import jakarta.ws.rs.client.ClientRequestContext;
import jakarta.ws.rs.client.ClientResponseContext;
import jakarta.ws.rs.client.ClientResponseFilter;

public class RateLimitFilter implements ClientResponseFilter {

    @Override
    public void filter(ClientRequestContext requestContext, ClientResponseContext responseContext) {
        var rateLimitLimit = responseContext.getHeaderString("X-Rate-Limit-Limit");
        var rateLimitRemaining = responseContext.getHeaderString("X-Rate-Limit-Remaining");
        if (rateLimitLimit != null) {
            try {
                RateLimits.setLimit(Integer.parseInt(rateLimitLimit));
            } catch (NumberFormatException nfe) {
                RateLimits.setLimit(null);
            }
        } else {
            RateLimits.setLimit(null);
        }
        if (rateLimitRemaining != null) {
            try {
                RateLimits.setRemaining(Integer.parseInt(rateLimitRemaining));
            } catch (NumberFormatException nfe) {
                RateLimits.setRemaining(null);
            }
        } else {
            RateLimits.setRemaining(null);
        }
    }
}
