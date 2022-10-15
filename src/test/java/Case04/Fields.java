package Case04;


import Case04.Model.Design04;
import Tools.Login;
import io.restassured.http.ContentType;
import io.restassured.http.Cookies;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class Fields {
    //Setup	Parameters	Fields

    Cookies cookies;

    @BeforeClass
    public void login() {
        cookies = Login.loginCampus();
    }

    String designId;

    String designName;

    String designCode;

    @Test
    public void createFields() {

        designName = Tools.Random.getRandomName();
        designCode=Tools.Random.getRandomCode();
        Design04 design04 = new Design04();
        design04.setName(designName);
        design04.setCode(designCode);

        designId =
                given()
                        .cookies(cookies)
                        .contentType(ContentType.JSON)
                        .body(design04)
                        .when()
                        .post("school-service/api/entity-field")
                        .then()
                        .log().body()
                        .statusCode(201)
                        .extract().jsonPath().getString("id")
        ;


    }






}
