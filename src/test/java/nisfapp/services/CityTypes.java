package nisfapp.services;


import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum CityTypes {

    DUBAI("Dubai"),
    ABU_DHABI("Abu Dhabi"),
    AL_AIN("Al Ain"),
    UNM_AL_QUWAIN("Umm Al Quwain");

    private final String displayName;
}
