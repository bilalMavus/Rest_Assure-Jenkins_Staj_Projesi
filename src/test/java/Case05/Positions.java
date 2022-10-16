package Case05;

import Case05.Model.Design05;
import Tools.Login;
import io.restassured.http.ContentType;
import io.restassured.http.Cookies;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
public class Positions {
//Human Resources	Setup	Positions
    Cookies cookies;
    @BeforeClass
    public void login() {
        cookies = Login.loginCampus();
    }
    String designId;
    String designName;
    String designShortName;
    @Test
    public void createPositions() {

        designName = Tools.Random.getRandomName();
        designShortName = Tools.Random.getRandomName();
        Design05 design05 = new Design05();
        design05.setName(designName);
        design05.setShortName(designShortName);
        design05.setTenantId("5fe0786230cc4d59295712cf");
        designId =
                given()
                        .cookies(cookies)
                        .contentType(ContentType.JSON)
                        .body(design05)
                        .when()
                        .post("school-service/api/employee-position")
                        .then()
                        .log().body()
                        .statusCode(201)
                        .extract().jsonPath().getString("id")
        ;
    }
    @Test(dependsOnMethods = "createPositions")
    public void createPositionsNegative() {

        Design05 design05 = new Design05();
        design05.setName(designName);
        design05.setShortName(designShortName);
        design05.setTenantId("5fe0786230cc4d59295712cf");
        given()
                .cookies(cookies)
                .contentType(ContentType.JSON)
                .body(design05)
                .when()
                .post("school-service/api/employee-position")
                .then()
                .log().body()
                .statusCode(400)
                .body("message", equalTo("The Position with Name \"" + designName + "\" already exists."))
        ;
    }
    @Test(dependsOnMethods = "createPositions")
    public void updatePositions() {

        designName = Tools.Random.getRandomName();
        designShortName = Tools.Random.getRandomName();
        Design05 design05 = new Design05();
        design05.setId(designId);
        design05.setName(designName);
        design05.setShortName(designShortName);
        design05.setTenantId("5fe0786230cc4d59295712cf");
        given()
                .cookies(cookies)
                .contentType(ContentType.JSON)
                .body(design05)
                .when()
                .put("school-service/api/employee-position")
                .then()
                .log().body()
                .statusCode(200)
                .body("name", equalTo(designName))
        ;
    }
    @Test(dependsOnMethods = "updatePositions")
    public void deletePositions() {

        given()
                .cookies(cookies)
                .pathParam("designId", designId)
                .when()
                .delete("school-service/api/employee-position/{designId}")
                .then()
                .log().body()
                .statusCode(204)
        ;
    }
  @Test(dependsOnMethods = "deletePositions")
    public void deletePositionsNegative() {

        given()
                .cookies(cookies)
                .pathParam("designId", designId)

                .when()
                .delete("school-service/api/employee-position/{designId}")

                .then()
                .log().body()
                .statusCode(204)
        ;
    }
    @Test(dependsOnMethods = "deletePositions")
    public void updatePositionsNegative() {

        designName = Tools.Random.getRandomName();
        designShortName = Tools.Random.getRandomName();
        Design05 design05 = new Design05();
        design05.setId(designId);
        design05.setName(designName);
        design05.setShortName(designShortName);
        design05.setTenantId("5fe0786230cc4d59295712cf");
        given()
                .cookies(cookies)
                .contentType(ContentType.JSON)
                .body(design05)
                .when()
                .put("school-service/api/employee-position")
                .then()
                .log().body()
                .statusCode(400)
                .body("message", equalTo("Can't find Position"))
        ;
    }
}
