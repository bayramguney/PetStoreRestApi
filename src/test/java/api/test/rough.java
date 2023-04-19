package api.test;

import api.endpoints.*;
import api.utilities.*;
import io.restassured.http.*;
import io.restassured.response.*;
import org.testng.annotations.*;

import java.util.*;

import static io.restassured.RestAssured.given;

public class rough {

    @Test(dataProviderClass = DataProviders.class, dataProvider = "dp")
    public void test(Hashtable<Object,Object> data){
        System.out.println(data.get("Email"));
        System.out.println(data.get("Phone"));

    }
    @Test
    public void att(){

        String payload="{\n" +
                "    \"sid\": \"7hfe1919k0vmbjbbrloz3dz7g3bs3ty2u5kcjh5kb6i\",\n" +
                "    \"state\": \"IL\",\n" +
                "    \"assetID\": \"USATTSARLIL02R\",\n" +
                "    \"country\": \"US\",\n" +
                "    \"socketId\": \"jwgb4m\"\n" +
                "}";

        Response response=given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(payload)
                .when()
                .post("https://expressticketing.acss.att.com/api/qualifyAsset");

        List<String> openticket=response.jsonPath().getList("DATA.troubleTypes.text");
        System.out.println("open ticket is : "+openticket);
    }
}
