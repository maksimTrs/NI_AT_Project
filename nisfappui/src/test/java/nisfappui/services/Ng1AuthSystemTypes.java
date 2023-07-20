package nisfappui.services;


import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Ng1AuthSystemTypes {

    MPGS("MPGS"),
    BASE24("BASE24");

    private final String displayName;
}
