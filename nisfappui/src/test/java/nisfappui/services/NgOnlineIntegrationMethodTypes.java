package nisfappui.services;


import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum NgOnlineIntegrationMethodTypes {

    NHR("Network Hosted- Redirection"),
    MH_API("Merchant Hosted - API"),
    OTHERS("Others");

    private final String displayName;
}
