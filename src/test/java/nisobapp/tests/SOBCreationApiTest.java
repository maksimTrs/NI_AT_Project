package nisobapp.tests;

import com.microsoft.playwright.APIResponse;
import com.microsoft.playwright.options.FormData;
import com.microsoft.playwright.options.RequestOptions;
import org.apache.commons.io.FileUtils;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

public class SOBCreationApiTest extends BaseApiTest {


   // private static Path file = Paths.get("src/test/resources/testData/SOB_Application_API_UAT_Creation.json");


    @Test
    public void createBookingTest()   {

        File file = new File("src/test/resources/testData/SOB_Application_API_UAT_Creation.json");
        String fileContent= null;
        try {
            fileContent = FileUtils.readFileToString(file, "UTF-8");
        } catch (IOException e) {
            // Handle the exception appropriately
            e.printStackTrace();
        }

        APIResponse response = manager.postRequest(BASE_URL1, RequestOptions.create().setData(fileContent));

        System.out.println("***********"  + response.text());
        System.out.println("***********"  + response.statusText());

        assertThat(response.status()).isEqualTo(201);

        JSONObject responseObject = new JSONObject(response.text());
        String applicationNumber = responseObject.getString("applicationNumber");
        System.out.println(applicationNumber);
    }
}
