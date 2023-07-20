package nisfappui.services;


import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum NgOnlinePaymentTypes {

    VISA("Visa"),
    MASTERCARD("MasterCard"),
    JCB("JCB"),
    DINERS("Diners"),
    CUP("CUP");

    private final String displayName;
}
