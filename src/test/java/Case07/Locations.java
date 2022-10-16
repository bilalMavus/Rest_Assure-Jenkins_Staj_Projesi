package Case07;

import Case07.Model.Design07;
import Tools.School;
import Tools.Login;
import io.restassured.http.ContentType;
import io.restassured.http.Cookies;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
public class Locations {
    //  Setup	School Setups	Locations
    Cookies cookies;
    @BeforeClass
    public void login() {
        cookies = Login.loginCampus();
    }
    String designId;
    String designName;
    String designShortName;
    String designCapacity;
    @Test
    public void createLocations() {

        designName = Tools.Random.getRandomName();
        designShortName = Tools.Random.getRandomName();
        designCapacity = Tools.Random.getRandomIntCode(2);
        Design07 design07 = new Design07();
        design07.setName(designName);
        design07.setShortName(designShortName);
        design07.setCapacity(designCapacity);
        design07.setSchool(new School("6343bf893ed01f0dc03a509a"));
        design07.setType("LABORATORY");
        design07.setActive(true);
        designId =
                given()
                        .cookies(cookies)
                        .contentType(ContentType.JSON)
                        .body(design07)
                        .when()
                        .post("school-service/api/location")
                        .then()
                        .log().body()
                        .statusCode(201)
                        .extract().jsonPath().getString("id")
        ;
    }
    @Test(dependsOnMethods = "createLocations")
    public void createLocationsNegative() {

        Design07 design07 = new Design07();
        design07.setName(designName);
        design07.setShortName(designShortName);
        design07.setCapacity(designCapacity);
        design07.setSchool(new School("6343bf893ed01f0dc03a509a"));
        design07.setType("LABORATORY");
        design07.setActive(true);
        given()
                .cookies(cookies)
                .contentType(ContentType.JSON)
                .body(design07)
                .when()
                .post("school-service/api/location")
                .then()
                .log().body()
                .statusCode(400)
                .body("message", equalTo("The School Location with Name \"" + designName + "\" already exists."))
        ;
    }
    @Test(dependsOnMethods = "createLocations")
    public void updateLocations() {

        designName = Tools.Random.getRandomName();
        designShortName = Tools.Random.getRandomName();
        designCapacity = Tools.Random.getRandomIntCode(2);
        Design07 design07 = new Design07();
        design07.setId(designId);
        design07.setName(designName);
        design07.setShortName(designShortName);
        design07.setCapacity(designCapacity);
        design07.setSchool(new School("6343bf893ed01f0dc03a509a"));
        design07.setType("OTHER");
        design07.setActive(true);
                given()
                        .cookies(cookies)
                        .contentType(ContentType.JSON)
                        .body(design07)
                        .when()
                        .put("school-service/api/location")
                        .then()
                        .log().body()
                        .statusCode(200)
                        .body("name", equalTo(designName))
        ;
    }
    @Test(dependsOnMethods = "updateLocations")
    public void deleteLocations() {

        given()
                .cookies(cookies)
                .pathParam("designId",designId)
                .when()
                .delete("school-service/api/location/{designId}")
                .then()
                .log().body()
                .statusCode(200)
        ;
    }
    @Test(dependsOnMethods = "deleteLocations")
    public void deleteLocationsNegative() {

        given()
                .cookies(cookies)
                .pathParam("designId",designId)
                .when()
                .delete("school-service/api/location/{designId}")
                .then()
                .log().body()
                .statusCode(400)
        ;
    }
    @Test(dependsOnMethods = "deleteLocations")
    public void updateLocationsNegative() {

        designName = Tools.Random.getRandomName();
        designShortName = Tools.Random.getRandomName();
        designCapacity = Tools.Random.getRandomIntCode(2);
        Design07 design07 = new Design07();
        design07.setId(designId);
        design07.setName(designName);
        design07.setShortName(designShortName);
        design07.setCapacity(designCapacity);
        design07.setSchool(new School("6343bf893ed01f0dc03a509a"));
        design07.setType("OTHER");
        design07.setActive(true);
        given()
                .cookies(cookies)
                .contentType(ContentType.JSON)
                .body(design07)
                .when()
                .put("school-service/api/location")
                .then()
                .log().body()
                .statusCode(400)
                .body("message", equalTo("SchoolLocation.ERROR.SCHOOL_LOCATION_NOT_FOUND"))
        ;
    }
}
