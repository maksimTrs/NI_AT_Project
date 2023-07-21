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
public class MerchantsItem {
    private String bankCode;
    private String website;
    private Fees fees;
    private Address address;
    @JsonProperty("IBAN")
    private String IBAN;
    private List<DocumentsItem> documents;
    private Configuration configuration;

    @JsonProperty("merchantEmail")
    @JsonDeserialize(using = EmailrDeserializer.class)
    private String merchantEmail;
    private Services services;
    private String accountNumber;
    private List<TerminalsItem> terminals;
    private String merchantName;


    @JsonProperty("tradeLicenseNumber")
    @JsonDeserialize(using = TradeLicenseNumberDeserializer.class)
    private String tradeLicenseNumber;
    private String tradeName;
    private String tradeLicenseExpirationDate;

    @JsonProperty("MSFRates")
    private MSFRates MSFRates;
    private String taxRegistrationNumber;
    private String merchantType;
}