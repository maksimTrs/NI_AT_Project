package nisobapi.pojo;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static nisobapi.utils.ApiAppDataFaker.*;

public class ScreeningResultDeserializer  extends JsonDeserializer<String> {


    @Override
    public String deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        String fieldVal = jsonParser.getText();

        // Define the regex patterns
        Pattern regexPattern1 = Pattern.compile("<regex>(.+?)<regex>");
        Pattern regexPattern2 = Pattern.compile("<regex2>(.+?)<regex2>");

        // Replace <regex> pattern
        Matcher matcher1 = regexPattern1.matcher(fieldVal);
        while (matcher1.find()) {
            fieldVal = fieldVal.replace(matcher1.group(), String.valueOf(TLN_POJO));
        }

        // Replace <regex2> pattern
        Matcher matcher2 = regexPattern2.matcher(fieldVal);
        while (matcher2.find()) {
            fieldVal = fieldVal.replace(matcher2.group(), PASSPORT_POJO);
        }

        return fieldVal;
    }
}