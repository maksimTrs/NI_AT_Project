package nisfapp.services;


import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum SettlementFrequencyEcomTypes {
    DAILY("Daily"),
    WEEKLY_ONCE("Weekly Once"),
    MONTHLY("Monthly");

    private final String displayName;
}
