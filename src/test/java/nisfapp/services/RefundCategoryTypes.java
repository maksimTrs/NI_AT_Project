package nisfapp.services;


import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum RefundCategoryTypes {

    C("C"),
    G("G"),
    N("N"),
    Z("Z"),
    R("R");

    private final String displayName;
}
