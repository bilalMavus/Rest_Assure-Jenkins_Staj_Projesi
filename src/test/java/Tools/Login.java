package Tools;

import io.restassured.http.ContentType;
import io.restassured.http.Cookies;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;

public class Login {

    @Test
    public static Cookies loginCampus() {
        baseURI = "https://demo.mersys.io/";

        Map<String, String> credential = new HashMap<>();
        credential.put("username", "richfield.edu");
        credential.put("password", "Richfield2020!");
        credential.put("rememberMe", "true");
        return given()
                .contentType(ContentType.JSON)
                .body(credential)
                .when()
                .post("auth/login")
                .then()
                .statusCode(200)
                .extract().response().getDetailedCookies();
    }


}
