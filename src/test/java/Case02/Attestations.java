package Case02;

import Case01.Model.Design01;
import Case02.Model.Design02;
import Tools.Login;
import io.restassured.http.ContentType;
import io.restassured.http.Cookies;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class Attestations {
    //   Human Resources	Setup	Attestations

    Cookies cookies;

    @BeforeClass
    public void login() {
        cookies = Login.loginCampus();
    }

    String designId;

    String designName;

    @Test
    public void createAttestations() {

        designName = Tools.Random.getRandomName();
        Design02 design02 = new Design02();
        design02.setName(designName);

        designId =
                given()
                        .cookies(cookies)
                        .contentType(ContentType.JSON)
                        .body(design02)
                        .when()
                        .post("school-service/api/attestation")
                        .then()
                        .log().body()
                        .statusCode(201)
                        .extract().jsonPath().getString("id")
        ;


    }

    @Test(dependsOnMethods ="createAttestations")
    public void createAttestationsNegative() {


        Design02 design02 = new Design02();
        design02.setName(designName);


                given()
                        .cookies(cookies)
                        .contentType(ContentType.JSON)
                        .body(design02)
                        .when()
                        .post("school-service/api/attestation")
                        .then()
                        .log().body()
                        .statusCode(400)
                        .body("message", equalTo("The Attestation  with Name \"" + designName + "\" already exists."))
        ;


    }

    @Test(dependsOnMethods = "createAttestations")
    public void updateAttestations() {

        designName = Tools.Random.getRandomName();
        Design01 design = new Design01();
        design.setId(designId);
        design.setName(designName);
        given()
                .cookies(cookies)
                .contentType(ContentType.JSON)
                .body(design)

                .when()
                .put("school-service/api/attestation")

                .then()
                .log().body()
                .statusCode(200)
                .body("name", equalTo(designName))
        ;
    }


    @Test(dependsOnMethods = "updateAttestations")
    public void deleteAttestations() {


        given()
                .cookies(cookies)
                .pathParam("designId", designId)

                .when()
                .delete("school-service/api/attestation/{designId}")

                .then()
                .log().body()
                .statusCode(204)
        ;
    }
    @Test(dependsOnMethods = "updateAttestations")
    public void deleteAttestationsNegative() {


        given()
                .cookies(cookies)
                .pathParam("designId", designId)

                .when()
                .delete("school-service/api/attestation/{designId}")

                .then()
                .log().body()
                .statusCode(400)
        ;
    }

    @Test(dependsOnMethods = "deleteAttestationsNegative")
    public void updateAttestationsNegative() {

        designName = Tools.Random.getRandomName();
        Design01 design = new Design01();
        design.setId(designId);
        design.setName(designName);
        given()
                .cookies(cookies)
                .contentType(ContentType.JSON)
                .body(design)

                .when()
                .put("school-service/api/attestation")

                .then()
                .log().body()
                .statusCode(400)
                .body("message", equalTo("Can't find Attestation"))
        ;
    }


}

