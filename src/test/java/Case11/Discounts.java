package Case11;

import Case11.Model.Design11;
import Tools.Login;
import io.restassured.http.ContentType;
import io.restassured.http.Cookies;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
public class Discounts {
    //Setup	Parameters	Discounts
    Cookies cookies;
    @BeforeClass
    public void login() {
        cookies = Login.loginCampus();
    }
    String designId;
    String designDescription;
    String designCode;
    String designPriority;
    @Test
    public void createPositions() {

        designDescription = Tools.Random.getRandomName() + " " + Tools.Random.getRandomName();
        designCode = Tools.Random.getRandomName();
        designPriority = Tools.Random.getRandomIntCode(2);
        Design11 design11 = new Design11();
        design11.setDescription(designDescription);
        design11.setCode(designCode);
        design11.setPriority(designPriority);
        designId =
                given()
                        .cookies(cookies)
                        .contentType(ContentType.JSON)
                        .body(design11)
                        .when()
                        .post("school-service/api/discounts")
                        .then()
                        .log().body()
                        .statusCode(201)
                        .extract().jsonPath().getString("id")
        ;
    }
    @Test(dependsOnMethods = "createPositions")
    public void createPositionsNegative() {

        Design11 design11 = new Design11();
        design11.setId(designId);
        design11.setDescription(designDescription);
        design11.setCode(designCode);
        design11.setPriority(designPriority);
        given()
                .cookies(cookies)
                .contentType(ContentType.JSON)
                .body(design11)
                .when()
                .post("school-service/api/discounts")
                .then()
                .log().body()
                .statusCode(400)
                .body("message", equalTo("To create a new Discount, the field 'ID' must be empty."))
        ;
    }
    @Test(dependsOnMethods = "createPositions")
    public void updatePositions() {

        designDescription = Tools.Random.getRandomName() + " " + Tools.Random.getRandomName();
        designCode = Tools.Random.getRandomName();
        designPriority = Tools.Random.getRandomIntCode(2);
        Design11 design11 = new Design11();
        design11.setId(designId);
        design11.setDescription(designDescription);
        design11.setCode(designCode);
        design11.setPriority(designPriority);
        given()
                .cookies(cookies)
                .contentType(ContentType.JSON)
                .body(design11)
                .when()
                .put("school-service/api/discounts")
                .then()
                .log().body()
                .statusCode(200)
                .body("description", equalTo(designDescription))
        ;
    }

    @Test(dependsOnMethods = "updatePositions")
    public void deletePositions() {

        given()
                .cookies(cookies)
                .pathParam("designId", designId)
                .when()
                .delete("school-service/api/discounts/{designId}")
                .then()
                .log().body()
                .statusCode(200)
        ;
    }

    @Test(dependsOnMethods = "deletePositions")
    public void deletePositionsNegative() {

        given()
                .cookies(cookies)
                .pathParam("designId", designId)
                .when()
                .delete("school-service/api/discounts/{designId}")
                .then()
                .log().body()
                .statusCode(400)
        ;
    }

    @Test(dependsOnMethods = "deletePositions")
    public void updatePositionsNegative() {

        designDescription = Tools.Random.getRandomName() + " " + Tools.Random.getRandomName();
        designCode = Tools.Random.getRandomName();
        designPriority = Tools.Random.getRandomIntCode(2);
        Design11 design11 = new Design11();
        design11.setId(designId);
        design11.setDescription(designDescription);
        design11.setCode(designCode);
        design11.setPriority(designPriority);
        given()
                .cookies(cookies)
                .contentType(ContentType.JSON)
                .body(design11)
                .when()
                .put("school-service/api/discounts")
                .then()
                .log().body()
                .statusCode(400)
                .body("message", equalTo("Discount.Error.DISCOUNT_NOT_FOUND"))
        ;
    }
}
