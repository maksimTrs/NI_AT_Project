package nisobapp.api;


import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum StatusCode {

    CODE_200(200, "OK"),
    CODE_201(201, "Created"),
    CODE_400(400, "Bad Request"),
    CODE_401(401, "Unauthorized");

    public final int CODE;
    public final String MSG;
}
