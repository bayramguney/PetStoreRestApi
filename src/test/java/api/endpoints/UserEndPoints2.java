package api.endpoints;

import api.payload.*;
import io.restassured.http.*;
import io.restassured.response.*;

import java.util.*;

import static io.restassured.RestAssured.*;

public class UserEndPoints2 {

    //created for getting URLs from properties
    static ResourceBundle getURL(){
        return ResourceBundle.getBundle("routes");
    }

    public static Response createUser(User payload){
        String post_url=getURL().getString("post_url");
        return given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(payload)
                .when()
                .post(post_url);
    }

    public static Response readUser(String username){
        String get_url=getURL().getString("get_url");
        return given()
               .pathParam("username",username)
                .when()
                .get(get_url);
    }

    public static Response updateUser(String username,User payload){
        String update_url=getURL().getString("update_url");
        return given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .pathParam("username",username)
                .body(payload)
                .when()
                .put(update_url);
    }

    public static Response deleteUser(String username){
        String delete_url=getURL().getString("delete_url");
        return given()
                .pathParam("username",username)
                .when()
                .delete(delete_url);
    }
}
