package nisobapp.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.microsoft.playwright.APIResponse;
import com.microsoft.playwright.options.FormData;
import com.microsoft.playwright.options.RequestOptions;
import nisobapp.pojo.TokenApi;
import org.json.JSONObject;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

import static nisobapp.api.StatusCode.CODE_200;
import static nisobapp.tests.BaseApiTest.loggerAPI;
import static nisobapp.utils.ConfigLoader.getSingletonInstance;
import static nisobapp.utils.MethodAssertionsForAPI.*;
import static nisobapp.utils.TestHelper.TOKEN_URL;
import static nisobapp.utils.TestHelper.TOKEN_URL_ENDPOINT;

public class AccessTokenManager {

    private static String access_token;
    private static Instant expires_time;


    public static String renewToken() {
        try {
            if (Instant.now().isAfter(expires_time) || access_token == null) {
                loggerAPI.info(">>>>>>> Token is updating <<<<<<<");
                access_token = getToken();
            } else {
                loggerAPI.info(">>>>>>>  Token is up to date <<<<<<<");
            }
        } catch (Exception exception) {
            exception.printStackTrace();
            throw new RuntimeException(">>>>>>> Failed to get Token! <<<<<<<");
        }
        return access_token;
    }


    public static String getToken() {
        RequestManager manager = new RequestManager();
        manager.createPlaywright();
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/x-www-form-urlencoded");

        manager.setApiRequestContext(TOKEN_URL, headers);

        ObjectMapper mapper = new ObjectMapper();
        TokenApi tokenData = buildTokenRequest();

        APIResponse response = manager
                .postRequest(TOKEN_URL_ENDPOINT, RequestOptions.create()
                        .setForm(FormData.create()
                                .set("client_id", tokenData.getClient_id())
                                .set("client_secret", tokenData.getClient_secret())
                                .set("grant_type", tokenData.getGrant_type())
                                .set("username", tokenData.getUsername())
                                .set("password", tokenData.getPassword())));

        assertApiResponseStatusCode(response.status(), CODE_200.CODE);

        JSONObject responseObject = new JSONObject(response.text());
        String tokenValue = responseObject.getString("access_token");
        String tokenType = responseObject.getString("token_type");
        long issuedAt = Long.parseLong(responseObject.getString("issued_at"));

        assertApiResponseElementIsNotEmptyOrNull(tokenValue);

        assertApiResponseElementIsNotEqual(issuedAt, 0);

        assertApiResponseElementHasValue(tokenType, "Bearer");

        loggerAPI.debug(">>>>>>>>>>>> tokenValue = " + tokenValue);

        access_token = tokenValue;
        expires_time = Instant.now().plusSeconds(3600);

        loggerAPI.debug(">>>>>>>>>>>> INSTANT millis expires_time = " + expires_time);

        return access_token;
    }


    private static TokenApi buildTokenRequest() {
        return TokenApi.builder()
                .client_id(getSingletonInstance().getClientId())
                .client_secret(getSingletonInstance().getClientSecret())
                .grant_type(getSingletonInstance().getGrantType())
                .username(getSingletonInstance().getUserName())
                .password(getSingletonInstance().getGetUserPass())
                .build();
    }
}
