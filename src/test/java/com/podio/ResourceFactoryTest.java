package com.podio;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.tomakehurst.wiremock.junit.WireMockRule;
import com.podio.item.ItemAPI;
import com.podio.oauth.OAuthClientCredentials;
import com.podio.oauth.OAuthToken;
import com.podio.oauth.OAuthUsernameCredentials;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jakarta.ws.rs.core.Response;
import java.util.Collections;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;
import static com.google.common.net.HttpHeaders.CONTENT_TYPE;

public class ResourceFactoryTest {

    private static final Logger LOG = LoggerFactory.getLogger(ResourceFactoryTest.class);

    @Rule
    public WireMockRule wireMockRule = new WireMockRule(wireMockConfig().dynamicPort().dynamicHttpsPort());

    private ResourceFactory resourceFactory;
    private APIFactory apiFactory;

    @Before
    public void setUp() {
        resourceFactory = new ResourceFactory(
                "localhost",
                "localhost",
                wireMockRule.port(),
                false,
                false,
                new OAuthClientCredentials("test-client-id", "test-client-secret"),
                new OAuthUsernameCredentials("testuser@test.com", "test-password")
        );
        apiFactory = new APIFactory(resourceFactory);
    }

    @Test
    public void testHappyPath() throws Exception {
        setupDefaultAuthMock();
        stubFor(get(urlEqualTo("/item/42"))
                .willReturn(aResponse()
                        .withHeader(CONTENT_TYPE, "application/json")
                        .withBody("{\"item_id\": 42, \"title\": \"test title\"}")));

        var api = apiFactory.getAPI(ItemAPI.class);

        var item = api.getItem(42);

        Assert.assertNotNull(item);
        Assert.assertEquals(42, item.getId());
        Assert.assertEquals("test title", item.getTitle());
    }

    @Test
    public void test404() throws Exception {
        setupDefaultAuthMock();
        stubFor(get(urlEqualTo("/item/42"))
                .willReturn(notFound()
                        .withStatus(404)
                        .withStatusMessage(Response.Status.NOT_FOUND.getReasonPhrase())
                        .withBody("{\"error\": \"test error\", \"error_description\": \"test description\", \"parameters\": {\"test param key\": \"test param value\"}}")
                ));

        var api = apiFactory.getAPI(ItemAPI.class);

        try {
            api.getItem(42);
            Assert.fail("Did not fetch expected exception for item not found!");
        } catch (Exception e) {
            LOG.info("catched test exception: ", e);
            APIApplicationException expected = new APIApplicationException(Response.Status.NOT_FOUND, "test error", "test description", Collections.singletonMap("test param key", "test param value"));
            Assert.assertEquals(expected.toString(), e.toString());
        }
    }

    private void setupDefaultAuthMock() throws JsonProcessingException {
        var authResponse = new OAuthToken();
        authResponse.setAccessToken("test_access_token");
        authResponse.setRefreshToken("test_refresh_token");
        authResponse.setExpiresIn(10000);

        stubFor(post("/oauth/token")
                .withBasicAuth("test-client-id", "test-client-secret")
                .willReturn(
                        aResponse()
                                .withHeader(CONTENT_TYPE, "application/json")
                                .withBody(new ObjectMapper().writeValueAsString(authResponse))
                )
        );
    }

}
