package nisfapp.services;


import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ApplicationProductTypes {
    POS("New POS"),
    ECOM("Ecom Sandbox"),
    NG1("N-Genius One");

    private final String displayName;
}
