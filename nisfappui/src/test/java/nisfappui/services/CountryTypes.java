package nisfappui.services;


import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum CountryTypes {

    UAE("United Arab Emirates"),
    INDIA("India"),
    RUSSIA("Russian Federation"),
    HONG_KONG("Hong Kong"),
    AFGHANISTAN("Afghanistan");

    private final String displayName;
}
