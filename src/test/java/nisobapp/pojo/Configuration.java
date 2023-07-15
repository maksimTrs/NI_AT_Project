package nisobapp.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

@Data
@Builder
@Jacksonized
@JsonIgnoreProperties(ignoreUnknown = true)
public class Configuration{
	private String transactionsExpectedAnnually;
	private String settlementFirstDay;
	private String settlementSecondDay;
	private Object netSpread;
	private String amountCap;
	private String paymentTypeAccepted;
	private String flexiCutofDays;
	private String refundCategory;
	private String ecommerceVolumeExpectedAnnually;
	private String settlementFrequency;
	@JsonProperty("ECOMAuthSystem")
	private String ECOMAuthSystem;
	@JsonProperty("SoftPOSAuthSystem")
	private String SoftPOSAuthSystem;
}