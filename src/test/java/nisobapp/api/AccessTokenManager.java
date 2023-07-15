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

import static nisobapp.utils.ConfigLoader.getSingletonInstance;
import static org.assertj.core.api.Assertions.assertThat;

public class AccessTokenManager {

    private static final String TOKEN_URL = "https://test.salesforce.com/services/oauth2/token";
    private static String access_token;
    private static Instant expires_time;


    public static String renewToken() {
        try {
            if (Instant.now().isAfter(expires_time) || access_token == null) {
                System.out.println(">>>>>>>>>>>>>>> Token is updating...");
                access_token = getToken();
            } else {
                System.out.println(">>>>>>>>>>>>>>>  Token is up to date");
            }
        } catch (Exception exception) {
            exception.printStackTrace();
            throw new RuntimeException(">>>>>>>>>>>>>>> Failed to get Token!");
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
                .postRequest(TOKEN_URL, RequestOptions.create()
                        .setForm(FormData.create()
                                .set("client_id", tokenData.getClient_id())
                                .set("client_secret", tokenData.getClient_secret())
                                .set("grant_type", tokenData.getGrant_type())
                                .set("username", tokenData.getUsername())
                                .set("password", tokenData.getPassword())));

        assertThat(response.status())
                .isEqualTo(200);

        JSONObject responseObject = new JSONObject(response.text());
        String tokenValue = responseObject.getString("access_token");
        long issuedAt = Long.parseLong(responseObject.getString("issued_at"));
        assertThat(tokenValue)
                .isNotEmpty()
                .isNotNull();

        assertThat(issuedAt)
                .isNotEqualTo(0)
                .isNotNull();

        System.out.println("++++++++++++ tokenValue = " + tokenValue);
        /*        System.out.println("++++++++++++ issuedAt = " + issuedAt);*/
        access_token = tokenValue;

        expires_time = Instant.now().plusSeconds(3600);
        System.out.println("++++++++++++ INSTANT millis expires_time = " + expires_time);

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
