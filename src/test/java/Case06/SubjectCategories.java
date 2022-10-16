package Case06;

import Case06.Model.Design06;
import Tools.Login;
import io.restassured.http.ContentType;
import io.restassured.http.Cookies;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class SubjectCategories {
    //Education	Setup	Subject Categories
    Cookies cookies;
    @BeforeClass
    public void login() {
        cookies = Login.loginCampus();
    }

    String designId;
    String designName;
    String designCode;

    @Test
    public void createSubjectCategories() {
        designName = Tools.Random.getRandomName();
        designCode = Tools.Random.getRandomIntCode(3);
        Design06 design06 = new Design06();
        design06.setName(designName);
        design06.setCode(designCode);
        designId =
                given()
                        .cookies(cookies)
                        .contentType(ContentType.JSON)
                        .body(design06)
                        .when()
                        .post("school-service/api/subject-categories")
                        .then()
                        .log().body()
                        .statusCode(201)
                        .extract().jsonPath().getString("id")
        ;
    }
    @Test(dependsOnMethods = "createSubjectCategories")
    public void createSubjectCategoriesNegative() {
        Design06 design06 = new Design06();
        design06.setId(designId);
        design06.setName(designName);
        design06.setCode(designCode);
        given()
                .cookies(cookies)
                .contentType(ContentType.JSON)
                .body(design06)
                .when()
                .post("school-service/api/subject-categories")
                .then()
                .log().body()
                .statusCode(400)
                .body("message", equalTo("You can't create new subject category, because it already exists"))
        ;
    }
    @Test(dependsOnMethods = "createSubjectCategories")
    public void updateSubjectCategories() {
        designName = Tools.Random.getRandomName();
        designCode = Tools.Random.getRandomIntCode(3);
        Design06 design06 = new Design06();
        design06.setId(designId);
        design06.setName(designName);
        design06.setCode(designCode);
        given()
                .cookies(cookies)
                .contentType(ContentType.JSON)
                .body(design06)
                .when()
                .put("school-service/api/subject-categories")
                .then()
                .log().body()
                .statusCode(200)
                .body("code",equalTo(designCode))
        ;
    }
    @Test(dependsOnMethods = "updateSubjectCategories")
    public void deleteSubjectCategories() {
        given()
                .cookies(cookies)
                .pathParam("designId",designId)
                .when()
                .delete("school-service/api/subject-categories/{designId}")
                .then()
                .log().body()
                .statusCode(200)
        ;
    }
    @Test(dependsOnMethods = "deleteSubjectCategories")
    public void deleteSubjectCategoriesNegative() {
        given()
                .cookies(cookies)
                .pathParam("designId",designId)
                .when()
                .delete("school-service/api/subject-categories/{designId}")
                .then()
                .log().body()
                .statusCode(400)
        ;
    }
    @Test(dependsOnMethods = "deleteSubjectCategories")
    public void updateSubjectCategoriesNegative() {
        designName = Tools.Random.getRandomName();
        designCode = Tools.Random.getRandomIntCode(3);
        Design06 design06 = new Design06();
        design06.setId(designId);
        design06.setName(designName);
        design06.setCode(designCode);
        given()
                .cookies(cookies)
                .contentType(ContentType.JSON)
                .body(design06)
                .when()
                .put("school-service/api/subject-categories")
                .then()
                .log().body()
                .statusCode(400)
                .body("message",equalTo("Can't find Subject Category"))
        ;
    }
}
