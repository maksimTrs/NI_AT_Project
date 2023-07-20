package nisfappui.services;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum PosTypeAndGatewayTypes {

    POS_TYPE_NG("Ngenius POS"),
    POS_TYPE_SOFT("Soft POS"),
    ECON_TYPE("Ngenius Online");

    private final String displayName;
}
