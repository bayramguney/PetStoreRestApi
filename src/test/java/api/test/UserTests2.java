package api.test;

import api.endpoints.*;
import api.extentlisteners.*;
import api.payload.*;
import com.github.javafaker.*;
import io.restassured.response.*;
import org.apache.logging.log4j.*;
import org.testng.*;
import org.testng.annotations.*;


public class UserTests2 {

    Faker faker;
    User userPayload;
    public Logger logger;

    @BeforeClass
    public void setupData(){
        faker=new Faker();
        userPayload=new User();

        userPayload.setId(faker.idNumber().hashCode());
        userPayload.setUsername(faker.name().username());
        userPayload.setFirstName(faker.name().firstName());
        userPayload.setLastName(faker.name().lastName());
        userPayload.setEmail(faker.internet().safeEmailAddress());
        userPayload.setPassword(faker.internet().password(5,10));
        userPayload.setPhone(faker.phoneNumber().cellPhone());

        //logs
        logger=LogManager.getLogger(this.getClass());

    }

    @Test(priority = 1)
    public void testPostUser(){
        logger.info("******Creating User**********");
        ExtentListeners.testReport.get().info("******Creating User**********");
        Response response=UserEndPoints2.createUser(userPayload);
        response.then().log().all();
        Assert.assertEquals(response.getStatusCode(),200);

        logger.info("**********User Created**********");
        ExtentListeners.testReport.get().info("**********User Created**********");
    }

    @Test(priority = 2)
    public void testGetUserByName(){
        logger.info("*********Reading User Info**********");
        ExtentListeners.testReport.get().info("*********Reeading User Info**********");

        Response response=UserEndPoints2.readUser(this.userPayload.getUsername());
        response.then().log().all();
        Assert.assertEquals(response.getStatusCode(),200);
    }

    @Test(priority = 3)
    public void testUpdateUserByName(){
        //update data using payload
        userPayload.setLastName(faker.name().lastName());
        userPayload.setEmail(faker.internet().safeEmailAddress());
        userPayload.setPassword(faker.internet().password(5,10));

        logger.info("**********Updating User**********");
        ExtentListeners.testReport.get().info("**********Updating User**********");

        Response response=UserEndPoints2.updateUser(this.userPayload.getUsername(),userPayload);
        response.then().log().all();
        response.then().log().body().statusCode(200);
        Assert.assertEquals(response.getStatusCode(),200);

        //Checking data after update
        Response responseAfterUpdate=UserEndPoints2.readUser(this.userPayload.getUsername());
        responseAfterUpdate.then().log().all();
        Assert.assertEquals(responseAfterUpdate.getStatusCode(),200);
    }

    @Test(priority = 4)
    public void testDeleteUserByName(){

        logger.info("**********Deleting User*********");
        ExtentListeners.testReport.get().info("**********Deleting User**********");

        Response response=UserEndPoints2.deleteUser(this.userPayload.getUsername());
        Assert.assertEquals(response.getStatusCode(),200);
    }
}
