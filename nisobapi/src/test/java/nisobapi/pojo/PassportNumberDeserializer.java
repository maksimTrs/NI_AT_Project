package nisobapi.pojo;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static nisobapi.utils.ApiAppDataFaker.PASSPORT_POJO;

public class PassportNumberDeserializer extends JsonDeserializer<String> {


    @Override
    public String deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JacksonException {

        String passportNumber = jsonParser.getText();
        Pattern pattern = Pattern.compile("^<regex2>(.*)<regex2>$");


        if (passportNumber.contains("<regex2>")) {
            Matcher matcher = pattern.matcher(passportNumber);
            if (matcher.find()) {
                passportNumber = PASSPORT_POJO;
            }
        }

        return passportNumber;
    }
}

