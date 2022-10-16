package Case08;

import Case08.Model.Design08;
import Tools.Login;
import Tools.School;
import io.restassured.http.ContentType;
import io.restassured.http.Cookies;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class Departments {
    //Setup	School Setups	Departments
    Cookies cookies;

    @BeforeClass
    public void login() {
        cookies = Login.loginCampus();
    }

    String designId;
    String designName;
    String designCode;

    @Test
    public void createDepartments() {

        designName = Tools.Random.getRandomName();
        designCode = Tools.Random.getRandomStringCode(5);
        Design08 design08 = new Design08();
        design08.setName(designName);
        design08.setCode(designCode);
        design08.setConstants(new String[]{});
        design08.setSections(new String[]{});
        design08.setSchool(new School("5fe07e4fb064ca29931236a5"));
        designId =
                given()
                        .cookies(cookies)
                        .contentType(ContentType.JSON)
                        .body(design08)
                        .when()
                        .post("school-service/api/department")
                        .then()
                        .log().body()
                        .statusCode(201)
                        .extract().jsonPath().getString("id")
        ;
    }
    @Test(dependsOnMethods = "createDepartments")
    public void createDepartmentsNegative() {
        designName = Tools.Random.getRandomName();
        designCode = Tools.Random.getRandomStringCode(5);
        Design08 design08 = new Design08();
        design08.setId(designId);
        design08.setName(designName);
        design08.setCode(designCode);
        design08.setConstants(new String[]{});
        design08.setSections(new String[]{});
        design08.setSchool(new School("5fe07e4fb064ca29931236a5"));
        given()
                .cookies(cookies)
                .contentType(ContentType.JSON)
                .body(design08)
                .when()
                .post("school-service/api/department")
                .then()
                .log().body()
                .statusCode(400)
                .body("message", equalTo("Given school department already created. Please, check department info."))

        ;
    }
    @Test(dependsOnMethods = "createDepartments")
    public void updateDepartments() {

        designName = Tools.Random.getRandomName();
        designCode = Tools.Random.getRandomStringCode(5);
        Design08 design08 = new Design08();
        design08.setId(designId);
        design08.setName(designName);
        design08.setCode(designCode);
        design08.setConstants(new String[]{});
        design08.setSections(new String[]{});
        design08.setSchool(new School("5fe07e4fb064ca29931236a5"));
        given()
                .cookies(cookies)
                .contentType(ContentType.JSON)
                .body(design08)
                .when()
                .put("school-service/api/department")
                .then()
                .log().body()
                .statusCode(200)
                .body("code", equalTo(designCode))
        ;
    }
    @Test(dependsOnMethods = "updateDepartments")
    public void deleteDepartments() {

        given()
                .cookies(cookies)
                .pathParam("designId", designId)
                .when()
                .delete("school-service/api/department/{designId}")
                .then()
                .log().body()
                .statusCode(204)
        ;
    }
    @Test(dependsOnMethods = "deleteDepartments")
    public void deleteDepartmentsNegative() {

        given()
                .cookies(cookies)
                .pathParam("designId", designId)
                .when()
                .delete("school-service/api/department/{designId}")
                .then()
                .log().body()
                .statusCode(204)
        ;
    }
    @Test(dependsOnMethods = "deleteDepartments")
    public void updateDepartmentsNegative() {

        designName = Tools.Random.getRandomName();
        designCode = Tools.Random.getRandomStringCode(5);
        Design08 design08 = new Design08();
        design08.setId(designId);
        design08.setName(designName);
        design08.setCode(designCode);
        design08.setConstants(new String[]{});
        design08.setSections(new String[]{});
        design08.setSchool(new School("5fe07e4fb064ca29931236a5"));
        given()
                .cookies(cookies)
                .contentType(ContentType.JSON)
                .body(design08)
                .when()
                .put("school-service/api/department")
                .then()
                .log().body()
                .statusCode(400)
        ;
    }
}
