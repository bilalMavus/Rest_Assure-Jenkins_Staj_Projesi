package Case03;

import Case03.Model.Design03;
import Tools.Login;
import io.restassured.http.ContentType;
import io.restassured.http.Cookies;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class DocumentTypes {
    //Setup	Parameters	Document Types
    Cookies cookies;
    @BeforeClass
    public void login() {
        cookies = Login.loginCampus();
    }
    String designId;
    String designName;
    @Test
    public void createDocumentTypes() {

        designName = Tools.Random.getRandomName();
        Design03 design03 = new Design03();
        design03.setAttachmentStages(new String[]{"CONTRACT"});
        design03.setSchoolId("6343bf893ed01f0dc03a509a");
        design03.setName(designName);
        designId =
                given()
                        .cookies(cookies)
                        .contentType(ContentType.JSON)
                        .body(design03)
                        .when()
                        .post("school-service/api/attachments")
                        .then()
                        .log().body()
                        .statusCode(201)
                        .extract().jsonPath().getString("id")
        ;
    }
    @Test(dependsOnMethods = "createDocumentTypes")
    public void createDocumentTypesNegative() {

        Design03 design03 = new Design03();
        design03.setId(designId);
        design03.setSchoolId("6343bf893ed01f0dc03a509a");
        design03.setAttachmentStages(new String[]{"CONTRACT"});
        design03.setName(designName);
        given()
                .cookies(cookies)
                .contentType(ContentType.JSON)
                .body(design03)
                .when()
                .post("school-service/api/attachments")
                .then()
                .log().body()
                .statusCode(400)
                .body("message", equalTo("Please provide valid data to create 'Attachment Type', your 'Attachment Type' already created"))
        ;
    }
    @Test(dependsOnMethods = "createDocumentTypes")
    public void updateDocumentTypes() {

        designName = Tools.Random.getRandomName();
        Design03 design03 = new Design03();
        design03.setId(designId);
        design03.setName(designName);
        design03.setAttachmentStages(new String[]{"STUDENT_REGISTRATION"});
        given()
                .cookies(cookies)
                .contentType(ContentType.JSON)
                .body(design03)
                .when()
                .body("school-service/api/attachments")
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
                .delete("school-service/api/attachments/{designId}")
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
                .delete("school-service/api/attachments/{designId}")
                .then()
                .log().body()
                .statusCode(400)
        ;
    }
    @Test(dependsOnMethods = "deleteDocumentTypes")
    public void updateDocumentTypesNegative() {

        designName = Tools.Random.getRandomName();
        Design03 design03 = new Design03();
        design03.setId(designId);
        design03.setName(designName);
        given()
                .cookies(cookies)
                .contentType(ContentType.JSON)
                .body(design03)
                .when()
                .put("school-service/api/attestation")
                .then()
                .log().body()
                .statusCode(400)
                .body("message", equalTo("Can't find Attestation"))
        ;
    }
}
