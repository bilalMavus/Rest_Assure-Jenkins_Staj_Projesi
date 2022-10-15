package Case03;

import Case01.Model.Design01;
import Case03.Model.Design03;
import Tools.Login;
import com.sun.deploy.uitoolkit.ToolkitStore;
import io.restassured.http.ContentType;
import io.restassured.http.Cookies;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class DocumentTypes {
    //Setup	Parameters	Document Types
    Cookies cookies;

    @BeforeClass
    public void login() {
        cookies = Login.loginCampus();
    }


    boolean designActive;
    boolean designRequired;
    boolean designUseCamera;
    String designAttachmentStages;
    String designDescription;
    String designId;
    String designName;
    String designSchoolId;
    String designTranslateName;

    @Test
    public void createDocumentTypes() {


        designName = Tools.Random.getRandomName();
        designDescription = Tools.Random.getRandomName();
        Design03 design = new Design03();
        design.setName(designName);
        design.setAttachmentStages("[\"EXAMINATION\"]");
        design.setDescription(designDescription);
        design.setActive(true);
        design.setRequired(true);
        design.setUseCamera(false);
        designId =
                given()
                        .cookies(cookies)
                        .contentType(ContentType.JSON)
                        .body(design)
                        .when()
                        .post("school-service/api/attachments")
                        .then()
                        .log().body()
                        .statusCode(201)
                        .extract().jsonPath().getString("id")
        ;
    }


}
