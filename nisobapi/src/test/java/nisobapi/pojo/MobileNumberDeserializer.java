package nisobapi.pojo;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static nisobapi.utils.ApiAppDataFaker.MOBILE_POJO;

public class MobileNumberDeserializer extends JsonDeserializer<String> {


    @Override
    public String deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JacksonException {

        String mobile = jsonParser.getText();
        Pattern pattern = Pattern.compile("^<regex3>(.*)<regex3>$");


        if (mobile.contains("<regex3>")) {
            Matcher matcher = pattern.matcher(mobile);
            if (matcher.find()) {
                mobile = MOBILE_POJO;
            }
        }

        return mobile;
    }
}
