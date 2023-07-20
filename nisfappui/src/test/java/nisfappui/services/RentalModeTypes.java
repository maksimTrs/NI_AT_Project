package nisfappui.services;


import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum RentalModeTypes {

    PAY_BY_LINK("Pay By Link"),
    BANK_TRANSFER("Bank Transfer"),
    NOT_REQUIRED("Not required");

    private final String displayName;
}
