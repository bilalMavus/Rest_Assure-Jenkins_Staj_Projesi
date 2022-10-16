package Case12;

import Case12.Model.Design12;
import Tools.Login;
import io.restassured.http.ContentType;
import io.restassured.http.Cookies;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class Nationalities {
    // Setup	Parameters	Nationalities
    Cookies cookies;
    @BeforeClass
    public void login() {
        cookies = Login.loginCampus();
    }
    String designId;
    String designName;
    @Test
    public void createDocumentTypes() {

        designName = Tools.Random.getRandomName() + " " + Tools.Random.getRandomName();
        Design12 design12 = new Design12();
        design12.setName(designName);
        designId =
                given()
                        .cookies(cookies)
                        .contentType(ContentType.JSON)
                        .body(design12)
                        .when()
                        .post("school-service/api/nationality")
                        .then()
                        .log().body()
                        .statusCode(201)
                        .extract().jsonPath().getString("id")
        ;
    }
    @Test(dependsOnMethods = "createDocumentTypes")
    public void createDocumentTypesNegative() {

        designName = Tools.Random.getRandomName() + " " + Tools.Random.getRandomName();
        Design12 design12 = new Design12();
        design12.setName(designName);
        design12.setId(designId);
        given()
                .cookies(cookies)
                .contentType(ContentType.JSON)
                .body(design12)
                .when()
                .post("school-service/api/nationality")
                .then()
                .log().body()
                .statusCode(400)
        ;
    }
    @Test(dependsOnMethods = "createDocumentTypes")
    public void updateDocumentTypes() {

        designName = Tools.Random.getRandomName() + " " + Tools.Random.getRandomName();
        Design12 design12 = new Design12();
        design12.setName(designName);
        design12.setId(designId);
        given()
                .cookies(cookies)
                .contentType(ContentType.JSON)
                .body(design12)
                .when()
                .put("school-service/api/nationality")
                .then()
                .log().body()
                .statusCode(200)
                .body("name", equalTo(designName))
        ;
    }
    @Test(dependsOnMethods = "updateDocumentTypes")
    public void deleteDocumentTypes() {

        given()
                .cookies(cookies)
                .pathParam("designId", designId)
                .when()
                .delete("school-service/api/nationality/{designId}")
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
                .delete("school-service/api/nationality/{designId}")
                .then()
                .log().body()
                .statusCode(400)
        ;
    }
    @Test(dependsOnMethods = "deleteDocumentTypes")
    public void updateDocumentTypesNegative() {

        designName = Tools.Random.getRandomName() + " " + Tools.Random.getRandomName();
        Design12 design12 = new Design12();
        design12.setName(designName);
        design12.setId(designId);
        given()
                .cookies(cookies)
                .contentType(ContentType.JSON)
                .body(design12)
                .when()
                .put("school-service/api/nationality")
                .then()
                .log().body()
                .statusCode(400)
                .body("message", equalTo("Can't find Nationality"))
        ;
    }
}
