package nisobapi.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

import java.util.List;

@Data
@Builder
@Jacksonized
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Application {
    @Builder.Default
    private String creator = "SOB";
    private String keyMerchant;
    private String rentalMode;
    private String legalType;
    private String businessDescription;
    private String paymentMode;

    @JsonProperty("screeningResult")
    @JsonDeserialize(using = ScreeningResultDeserializer.class)
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

    @JsonProperty("merchantType")
    @Builder.Default
    private String merchantType = "SME";
    private List<ContactsItem> contacts;
}