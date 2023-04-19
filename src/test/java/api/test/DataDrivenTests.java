package api.test;

import api.endpoints.*;
import api.payload.*;
import api.utilities.*;
import io.restassured.response.*;
import org.testng.*;
import org.testng.annotations.*;

import java.util.*;

public class DataDrivenTests {

    @Test(dataProviderClass = DataProviders.class, dataProvider = "dp", priority = 1)
    public void testPostUsers(Hashtable<String,String> data){

        User userPayload=new User();

        userPayload.setId((Integer.parseInt(data.get("UserID"))));
        userPayload.setUsername(data.get("UserName"));
        userPayload.setFirstName(data.get("FirstName"));
        userPayload.setLastName(data.get("LastName"));
        userPayload.setEmail(data.get("Email"));
        userPayload.setPassword(data.get("Password"));
        userPayload.setPhone(data.get("Phone"));


        Response response = UserEndPoints.createUser(userPayload);
        response.then().log().all();
        Assert.assertEquals(response.getStatusCode(), 200);
    }
    @Test(dataProviderClass = DataProviders.class, dataProvider = "dp1", priority = 2)
    public void testDeleteUserByName(Hashtable<String,String> data){
        Response response = UserEndPoints.deleteUser(data.get("UserName"));
        Assert.assertEquals(response.getStatusCode(), 200);

    }
}
