package nisfapp.services;


import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum LegalTypeTypes {

    LLC("LLC"),
    SOLE_PROPRIETOR("Sole Proprietor"),
    PARTNERSHIP("Partnership"),
    FREE_ZONE("Free Zone");

    private final String displayName;
}
