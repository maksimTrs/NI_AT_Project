package nisobapi.pojo;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static nisobapi.utils.ApiAppDataFaker.TLN_POJO;
import static nisobapi.utils.ApiAppDataFaker.getRandomTLN;

public class TradeLicenseNumberDeserializer extends JsonDeserializer<String> {


    @Override
    public String deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JacksonException {

        String tradeLicenseNumber = jsonParser.getText();
        Pattern pattern = Pattern.compile("^<regex>(.*)<regex>$");


        if (tradeLicenseNumber.contains("<regex>")) {
            Matcher matcher = pattern.matcher(tradeLicenseNumber);
            if (matcher.find()) {
                tradeLicenseNumber = String.valueOf(TLN_POJO);
            }
        }

        return tradeLicenseNumber;
    }
}
