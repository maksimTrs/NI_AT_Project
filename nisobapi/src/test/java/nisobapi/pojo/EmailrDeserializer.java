package nisobapi.pojo;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static nisobapi.utils.ApiAppDataFaker.EMAIL_POJO;
import static nisobapi.utils.ApiAppDataFaker.MOBILE_POJO;

public class EmailrDeserializer  extends JsonDeserializer<String> {


    @Override
    public String deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JacksonException {

        String email = jsonParser.getText();
        Pattern pattern = Pattern.compile("^<regex4>(.*)<regex4>$");


        if (email.contains("<regex4>")) {
            Matcher matcher = pattern.matcher(email);
            if (matcher.find()) {
                email = EMAIL_POJO;
            }
        }

        return email;
    }
}
