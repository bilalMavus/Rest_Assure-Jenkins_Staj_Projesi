package Case08;

import Case08.Model.Design08;
import Tools.Login;
import Tools.School;
import io.restassured.http.ContentType;
import io.restassured.http.Cookies;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

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
    public void createDocumentTypes() {
        designName = Tools.Random.getRandomName();
        designCode = Tools.Random.getRandomStringCode(5);
        Design08 design08 = new Design08();
        design08.setName(designName);
        design08.setCode(designCode);
        //design08.setSchool(new School("6343bf893ed01f0dc03a509a"));
        //design08.setSchool2("6343bf893ed01f0dc03a509a");
        //design08.setSchool3(new String[]{"6343bf893ed01f0dc03a509a"});
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
}
