package nisobapi.pojo;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.jackson.Jacksonized;

@Setter
@Getter
@Jacksonized
@Builder
//@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class TokenApi {

    @JsonProperty("client_id")
    private String client_id;

    @JsonProperty("client_secret")
    private String client_secret;

    @JsonProperty("grant_type")
    private String grant_type;


    private String username;
    private String password;

}