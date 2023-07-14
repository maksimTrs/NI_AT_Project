package nisobapp.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.microsoft.playwright.APIResponse;
import com.microsoft.playwright.options.FormData;
import com.microsoft.playwright.options.RequestOptions;
import nisobapp.pojo.TokenApi;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import static nisobapp.utils.ConfigLoader.getSingletonInstance;
import static org.assertj.core.api.Assertions.assertThat;

public class AccessTokenManager {

    private static final String TOKEN_URL = "https://test.salesforce.com/services/oauth2/token";


    public static String getToken() {
        RequestManager manager =  new RequestManager();
        manager.createPlaywright();
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/x-www-form-urlencoded");

        manager.setApiRequestContext(TOKEN_URL, headers);

        ObjectMapper mapper = new ObjectMapper();
        TokenApi tokenData = buildTokenRequest();
        String jsonBook;
        try {
            jsonBook = mapper.writeValueAsString(tokenData);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        //System.out.println(jsonBook);

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

        System.out.println("!!!!!!!!!!!!!! " + response.text());
        JSONObject responseObject = new JSONObject(response.text());
        String tokenValue = responseObject.getString("access_token");
        assertThat(tokenValue)
                .isNotEmpty()
                .isNotNull();

        System.out.println("++++++++++++ "   + tokenValue);
        return tokenValue;
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
