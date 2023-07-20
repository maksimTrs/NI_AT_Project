package nisfappui.services;


import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum NationalityTypes {

    NATIONALITY_UAE("United Arab Emirates"),
    NATIONALITY_INDIA("India"),
    NATIONALITY_RUSSIA("Russian Federation"),
    NATIONALITY_HONG_KONG("Hong Kong"),
    NATIONALITY_AFGHANISTAN("Afghanistan");

    private final String displayName;
}
