package nisobapi.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import nisobapi.pojo.*;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

public class SobAppApiSerialization {

    public static String deserializePostSonJsonAndSerializeToUniqueJson(String filePath) {

        String fileContent = null;
        try {
            File file = new File(filePath);
            fileContent = FileUtils.readFileToString(file, "UTF-8");
            //System.out.println(fileContent);
        } catch (IOException e) {
            e.printStackTrace();
        }


        ObjectMapper mapper = new ObjectMapper();
        SimpleModule module = new SimpleModule();
        module.addDeserializer(String.class, new PassportNumberDeserializer());
        module.addDeserializer(String.class, new MobileNumberDeserializer());
        module.addDeserializer(String.class, new TradeLicenseNumberDeserializer());
        module.addDeserializer(String.class, new ScreeningResultDeserializer());
        module.addDeserializer(String.class, new EmailrDeserializer());
        mapper.registerModule(module);


        SobAppApiMain newApplicationFromBuilder;
        try {
            newApplicationFromBuilder = mapper.readValue(fileContent, SobAppApiMain.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }


        ObjectMapper objectMapper = new ObjectMapper();
        String jsonConvertAppResult;
        try {
            jsonConvertAppResult = objectMapper.writeValueAsString(newApplicationFromBuilder);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        return jsonConvertAppResult;
    }
}
