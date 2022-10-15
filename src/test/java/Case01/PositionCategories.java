package Case01;

import Case01.Model.Design01;
import Tools.Login;
import io.restassured.http.ContentType;
import io.restassured.http.Cookies;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class PositionCategories {
    Cookies cookies;

    @BeforeClass
    public void login() {
        cookies = Login.loginCampus();
    }

    String designId;
    String designName;

    @Test
    public void createPositionCategory() {

        designName = Tools.Random.getRandomName();
        Design01 design = new Design01();
        design.setName(designName);
        designId =
                given()
                        .cookies(cookies)
                        .contentType(ContentType.JSON)
                        .body(design)
                        .when()
                        .post("school-service/api/position-category")
                        .then()
                        .log().body()
                        .statusCode(201)
                        .extract().jsonPath().getString("id")
        ;
    }

    @Test(dependsOnMethods = "createPositionCategory")
    public void createPositionCategoryNegative() {

        Design01 design = new Design01();
        design.setName(designName);
        given()
                .cookies(cookies)
                .contentType(ContentType.JSON)
                .body(design)

                .when()
                .post("school-service/api/position-category")

                .then()
                .log().body()
                .statusCode(400)
                .body("message", equalTo("ThePosition Category with Name \"" + designName + "\" already exists."))
        ;
    }


    @Test(dependsOnMethods = "createPositionCategory")
    public void updatePositionCategory() {

        designName = Tools.Random.getRandomName();
        Design01 design = new Design01();
        design.setId(designId);
        design.setName(designName);
        given()
                .cookies(cookies)
                .contentType(ContentType.JSON)
                .body(design)

                .when()
                .put("school-service/api/position-category")

                .then()
                .log().body()
                .statusCode(200)
                .body("name", equalTo(designName))
        ;
    }

    @Test(dependsOnMethods = "updatePositionCategory")
    public void deletePositionCategory() {


        given()
                .cookies(cookies)
                .pathParam("designId", designId)

                .when()
                .delete("school-service/api/position-category/{designId}")

                .then()
                .log().body()
                .statusCode(204)
        ;
    }

    @Test(dependsOnMethods = "deletePositionCategory")
    public void deletePositionCategoryNegaive() {


        given()
                .cookies(cookies)
                .pathParam("designId", designId)
                .log().uri()

                .when()
                .delete("school-service/api/position-category/{designId}")

                .then()
                .log().body()
                .statusCode(400)
        ;
    }

    @Test(dependsOnMethods = "deletePositionCategory")
    public void updatePositionCategoryNegative() {

        designName = Tools.Random.getRandomName();
        Design01 design = new Design01();
        design.setId(designId);
        design.setName(designName);
        given()
                .cookies(cookies)
                .contentType(ContentType.JSON)
                .body(design)

                .when()
                .put("school-service/api/position-category")

                .then()
                .log().body()
                .statusCode(400)
                .body("message", equalTo("Can't find Position Category"))
        ;
    }
}