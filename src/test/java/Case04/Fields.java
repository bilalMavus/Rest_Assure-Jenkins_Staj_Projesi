package Case04;

import Case04.Model.Design04;
import Tools.Login;
import io.restassured.http.ContentType;
import io.restassured.http.Cookies;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

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
        designCode = Tools.Random.getRandomIntCode(3);
        Design04 design04 = new Design04();
        design04.setName(designName);
        design04.setCode(designCode);
        design04.setSchoolId("6343bf893ed01f0dc03a509a");
        design04.setType("STRING");
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

    @Test(dependsOnMethods = "createFields")
    public void createFieldsNegative() {

        Design04 design04 = new Design04();
        design04.setId(designId);
        design04.setName(designName);
        design04.setCode(designCode);
        design04.setSchoolId("6343bf893ed01f0dc03a509a");
        design04.setType("STRING");
        given()
                .cookies(cookies)
                .contentType(ContentType.JSON)
                .body(design04)
                .when()
                .post("school-service/api/entity-field")
                .then()
                .log().body()
                .statusCode(400)
                .body("message", equalTo("You can't create new EntityField, because it already exists"))
        ;
    }

    @Test(dependsOnMethods = "createFields")
    public void updateFields() {

        designName = Tools.Random.getRandomName();
        designCode = Tools.Random.getRandomIntCode(3);
        Design04 design04 = new Design04();
        design04.setId(designId);
        design04.setName(designName);
        design04.setCode(designCode);
        design04.setSchoolId("6343bf893ed01f0dc03a509a");
        design04.setType("STRING");
        given()
                .cookies(cookies)
                .contentType(ContentType.JSON)
                .body(design04)
                .when()
                .put("school-service/api/entity-field")
                .then()
                .log().body()
                .statusCode(200)
                .body("name", equalTo(designName))
        ;
    }

    @Test(dependsOnMethods = "updateFields")
    public void deleteFields() {

        given()
                .cookies(cookies)
                .pathParam("designId", designId)
                .when()
                .delete("school-service/api/entity-field/{designId}")
                .then()
                .log().body()
                .statusCode(204)
        ;
    }

    @Test(dependsOnMethods = "deleteFields")
    public void deleteFieldsNegative() {

        given()
                .cookies(cookies)
                .pathParam("designId", designId)
                .when()
                .delete("school-service/api/entity-field/{designId}")
                .then()
                .log().body()
                .statusCode(400)
        ;
    }
    @Test(dependsOnMethods = "deleteFields")
    public void updateFieldsNegative() {

        Design04 design04 = new Design04();
        design04.setId(designId);
        design04.setName(designName);
        design04.setCode(designCode);
        design04.setSchoolId("6343bf893ed01f0dc03a509a");
        design04.setType("STRING");
        given()
                .cookies(cookies)
                .contentType(ContentType.JSON)
                .body(design04)
                .when()
                .put("school-service/api/entity-field")
                .then()
                .log().body()
                .statusCode(400)
                .body("message",equalTo("EntityField not found"))
        ;
    }
}
