package nisfappui.services;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum PaymentModeTypes {

    MC_777("MC - 777"),
    MC_871("MC - 871"),
    NN("NN - no bank account"),
    FN("FN - for ENBD bank accounts");

    private final String displayName;
}
