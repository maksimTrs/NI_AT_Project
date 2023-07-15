package nisobapp.pojo;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

@Data
@Builder
@Jacksonized
@JsonIgnoreProperties(ignoreUnknown = true)
public class Application{
	private String creator;
	private String keyMerchant;
	private String rentalMode;
	private String legalType;
	private String businessDescription;
	private String paymentMode;
	private String screeningResult;
	private String expectedCardVolume;
	private String businessNature;
	private List<Object> shareHolders;
	private String yearsInBusiness;
	private String expectedVolumePerYear;
	private String countryOfEstablishment;
	private String businessLine;
	private String dateOfEstablishment;
	private SanctionCountryInformation sanctionCountryInformation;
	private String tenantId;
	private List<MerchantsItem> merchants;
	private String merchantType;
	private List<ContactsItem> contacts;
}