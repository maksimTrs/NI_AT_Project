package nisobapi.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

@Data
@Builder
@Jacksonized
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ContactsItem {

    @JsonProperty("passportNumber")
    @JsonDeserialize(using = PassportNumberDeserializer.class)
    private String passportNumber;
    private String percentageofOwnership;
    private String lastName;
    @JsonProperty("mobile")
    @JsonDeserialize(using = MobileNumberDeserializer.class)
    private String mobile;
    private String birthDate;
    private String firstName;

    @JsonProperty("PEPInformation")
    private PEPInformation PEPInformation;
    private String nationality;
    private boolean authorizedSignatory;
    private String passportExpirationDate;
    private String designation;

    @JsonProperty("UAEPASSVerifiedContact")
    private boolean UAEPASSVerifiedContact;

    @JsonProperty("email")
    @JsonDeserialize(using = EmailrDeserializer.class)
    private String email;
    private String visaExpirationDate;
    private String emiratesExpirationDate;
    private String emiratesId;
}