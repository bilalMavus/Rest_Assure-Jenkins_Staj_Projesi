package Case10;

import Case10.Model.Design10;
import Tools.Login;
import io.restassured.http.ContentType;
import io.restassured.http.Cookies;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class GradeLevels {
    //Setup	Parameters	Grade Levels
    Cookies cookies;
    @BeforeClass
    public void login() {
        cookies = Login.loginCampus();
    }

    String designId;
    String designName;
    String designShortName;
    String designOrder;

    @Test
    public void createDocumentTypes() {

        designName = Tools.Random.getRandomName();
        designShortName = Tools.Random.getRandomName();
        designOrder = Tools.Random.getRandomIntCode(1);
        Design10 design10 = new Design10();
        design10.setName(designName);
        design10.setShortName(designShortName);
        design10.setOrder(designOrder);
        designId =
                given()
                        .cookies(cookies)
                        .contentType(ContentType.JSON)
                        .body(design10)
                        .when()
                        .post("school-service/api/grade-levels")
                        .then()
                        .log().body()
                        .statusCode(201)
                        .extract().jsonPath().getString("id")
        ;
    }
    @Test(dependsOnMethods = "createDocumentTypes")
    public void createDocumentTypesNegative() {

        designName = Tools.Random.getRandomName();
        designShortName = Tools.Random.getRandomName();
        designOrder = Tools.Random.getRandomIntCode(1);
        Design10 design10 = new Design10();
        design10.setId(designId);
        design10.setName(designName);
        design10.setShortName(designShortName);
        design10.setOrder(designOrder);
        given()
                .cookies(cookies)
                .contentType(ContentType.JSON)
                .body(design10)
                .when()
                .post("school-service/api/grade-levels")
                .then()
                .log().body()
                .statusCode(400)
                .body("message", equalTo("To create a new Grade Level, the field 'ID' must be empty."))
        ;
    }
    @Test(dependsOnMethods = "createDocumentTypes")
    public void updateDocumentTypes() {

        designName = Tools.Random.getRandomName();
        designShortName = Tools.Random.getRandomName();
        designOrder = Tools.Random.getRandomIntCode(1);
        Design10 design10 = new Design10();
        design10.setId(designId);
        design10.setName(designName);
        design10.setShortName(designShortName);
        design10.setOrder(designOrder);
        given()
                .cookies(cookies)
                .contentType(ContentType.JSON)
                .body(design10)
                .when()
                .put("school-service/api/grade-levels")
                .then()
                .log().body()
                .statusCode(200)
                .body("shortName", equalTo(designShortName))
        ;
    }
    @Test(dependsOnMethods = "updateDocumentTypes")
    public void deleteDocumentTypes() {

        given()
                .cookies(cookies)
                .pathParam("designId", designId)
                .when()
                .delete("school-service/api/grade-levels/{designId}")
                .then()
                .log().body()
                .statusCode(200)
        ;
    }
    @Test(dependsOnMethods = "deleteDocumentTypes")
    public void deleteDocumentTypesNegative() {

        given()
                .cookies(cookies)
                .pathParam("designId", designId)
                .when()
                .delete("school-service/api/grade-levels/{designId}")
                .then()
                .log().body()
                .statusCode(400)
        ;
    }
    @Test(dependsOnMethods = "deleteDocumentTypes")
    public void updateDocumentTypesNegative() {

        designName = Tools.Random.getRandomName();
        designShortName = Tools.Random.getRandomName();
        designOrder = Tools.Random.getRandomIntCode(1);
        Design10 design10 = new Design10();
        design10.setId(designId);
        design10.setName(designName);
        design10.setShortName(designShortName);
        design10.setOrder(designOrder);
        given()
                .cookies(cookies)
                .contentType(ContentType.JSON)
                .body(design10)
                .when()
                .put("school-service/api/grade-levels")
                .then()
                .log().body()
                .statusCode(400)
        ;
    }
}
