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
public class Fees {
    @JsonProperty("MISMonthlyFee")
    private Object MISMonthlyFee;
    private Object refundFee;
    private Object chargebackFee;
    private int setupFee;
    private Object perTransactionAuthorizationFee;
    private Object annualFee;
}