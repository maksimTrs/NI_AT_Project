package nisobapi.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

@Data
@Builder
@Jacksonized
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MSFRates {
    private String standard;
    private String diners;
    private String premium;
    @JsonProperty("JCB")
    private String JCB;
    private String manual;
    private String debit;
    private String international;

    @JsonProperty("CUP")
    private String CUP;
}