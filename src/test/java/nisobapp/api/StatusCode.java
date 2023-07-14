package nisobapp.api;


import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum StatusCode {

    CODE_200(200, ""),
    CODE_201(201, ""),
    CODE_400(400, "Missing required field: name"),
    CODE_401(401, "Invalid access token");

    public final int CODE;
    public final String MSG;
}
