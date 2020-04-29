package com.podio;

import com.podio.oauth.OAuthClientCredentials;
import com.podio.oauth.OAuthUserCredentials;
import com.podio.serialize.*;
import org.codehaus.jackson.jaxrs.JacksonJsonProvider;
import org.codehaus.jackson.map.DeserializationConfig.Feature;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;
import org.codehaus.jackson.map.deser.CustomDeserializerFactory;
import org.codehaus.jackson.map.deser.StdDeserializerProvider;
import org.codehaus.jackson.map.ser.CustomSerializerFactory;
import org.glassfish.jersey.client.filter.EncodingFilter;
import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.glassfish.jersey.message.GZipEncoder;
import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.client.*;
import javax.ws.rs.core.HttpHeaders;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collections;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

/**
 * This is the main low level entry point to access the Podio API. Construct
 * this and pass it to the APIFactory.
 */
public final class ResourceFactory {

    private static final Logger LOG = LoggerFactory.getLogger(ResourceFactory.class);

    private final WebTarget apiResource;
    private final WebTarget fileResource;

    private final AuthProvider authProvider;

    public ResourceFactory(OAuthClientCredentials clientCredentials,
                           OAuthUserCredentials userCredentials) {
        this("api.podio.com", "files.podio.com", 443, true, false,
                clientCredentials, userCredentials);
    }

    public ResourceFactory(String apiHostname, String fileHostname, int port,
                           boolean ssl, boolean dryRun,
                           OAuthClientCredentials clientCredentials,
                           OAuthUserCredentials userCredentials) {
        this(createDefaultClient(dryRun), apiHostname, fileHostname, port, ssl, clientCredentials, userCredentials);
    }

    public ResourceFactory(Client client, String apiHostname, String fileHostname, int port,
                           boolean ssl,
                           OAuthClientCredentials clientCredentials,
                           OAuthUserCredentials userCredentials) {
        this.apiResource = client.target(getURI(apiHostname, port, ssl));
        this.fileResource = client.target(getURI(fileHostname, port, ssl));

        this.authProvider = new AuthProvider(this, clientCredentials, userCredentials);
    }

    private URI getURI(String hostname, int port, boolean ssl) {
        try {
            return new URI(ssl ? "https" : "http", null, hostname, port, null,
                    null, null);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    private static Client createDefaultClient(boolean dryRun) {
        ClientBuilder clientBuilder = ClientBuilder.newBuilder();
        if (dryRun) {
            clientBuilder.register(DryRunFilter.class);
        }
        return clientBuilder
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(100, TimeUnit.SECONDS)
                .register(MultiPartFeature.class)
                .register(GZipEncoder.class)
                .register(EncodingFilter.class)
                .register(ExceptionFilter.class)
                .register(getJsonProvider())
                .register(RateLimitFilter.class)
                .register((ClientRequestFilter) requestContext -> requestContext.getHeaders().putSingle(HttpHeaders.USER_AGENT, "Podio Java API Client"))
                .register((ClientRequestFilter) requestContext -> LOG.debug("request {} {}", requestContext.getMethod(), requestContext.getUri()))
                .register((ClientResponseFilter) (requestContext, responseContext) -> LOG.debug("response {} {}: {}", requestContext.getMethod(), requestContext.getUri(), responseContext.getStatusInfo()))
                .build();
    }

    private static JacksonJsonProvider getJsonProvider() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.disable(Feature.FAIL_ON_UNKNOWN_PROPERTIES);
        mapper.disable(SerializationConfig.Feature.FAIL_ON_EMPTY_BEANS);
        mapper.setSerializationInclusion(Inclusion.NON_NULL);

        CustomSerializerFactory serializerFactory = new CustomSerializerFactory();
        serializerFactory.addSpecificMapping(DateTime.class, new DateTimeSerializer());
        serializerFactory.addSpecificMapping(LocalDate.class, new LocalDateSerializer());
        serializerFactory.addGenericMapping(TimeZone.class, new TimeZoneSerializer());
        serializerFactory.addSpecificMapping(Locale.class, new LocaleSerializer());
        mapper.setSerializerFactory(serializerFactory);

        CustomDeserializerFactory deserializerFactory = new CustomDeserializerFactory();
        deserializerFactory.addSpecificMapping(DateTime.class, new DateTimeDeserializer());
        deserializerFactory.addSpecificMapping(LocalDate.class, new LocalDateDeserializer());
        deserializerFactory.addSpecificMapping(TimeZone.class, new TimeZoneDeserializer());
        deserializerFactory.addSpecificMapping(Locale.class, new LocaleDeserializer());
        mapper.setDeserializerProvider(new StdDeserializerProvider(deserializerFactory));

        return new CustomJacksonJsonProvider(mapper);
    }

    public Invocation.Builder getFileResource(String path) {
        return getFileResource(path, true);
    }

    public Invocation.Builder getFileResource(String path, boolean secure) {
        WebTarget subResource = fileResource.path(path);
        var requestBuilder = subResource.request();
        if (secure) {
            requestBuilder.header(HttpHeaders.AUTHORIZATION, "OAuth2 " + authProvider.getToken().getAccessToken());
        }

        return requestBuilder;
    }

    public Invocation.Builder getApiResource(String path) {
        return getApiResource(path, true);
    }

    public Invocation.Builder getApiResource(String path, Map<String, String> queryParams) {
        return getApiResource(path, true, queryParams);
    }

    public Invocation.Builder getApiResource(String path, boolean secure) {
        return getApiResource(path, secure, Collections.emptyMap());
    }

    public Invocation.Builder getApiResource(String path, boolean secure, Map<String, String> queryParams) {
        WebTarget subResource = apiResource.path(path);
        for (Map.Entry<String, ?> entry : queryParams.entrySet()) {
            subResource = subResource.queryParam(entry.getKey(), entry.getValue());
        }
        var requestBuilder = subResource.request();
        if (secure) {
            requestBuilder.header(HttpHeaders.AUTHORIZATION, "OAuth2 " + authProvider.getToken().getAccessToken());
        }

        return requestBuilder;
    }
}
