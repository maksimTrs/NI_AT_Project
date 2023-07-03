package nisfapp.pages;


import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum NavigationMenuPartitions {

    HOME("Home"),
    APPLICATIONS("Applications"),
    CASES("Cases"),
    CONTACTS("Contacts");


    private final String displayName;
}
