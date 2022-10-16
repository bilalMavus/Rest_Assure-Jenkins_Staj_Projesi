package Case09;

import Case09.Model.Design09;
import Tools.Login;
import io.restassured.http.ContentType;
import io.restassured.http.Cookies;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
public class BankAccounts {
    //Setup	Parameters	Bank Accounts
    Cookies cookies;
    @BeforeClass
    public void login() {
        cookies = Login.loginCampus();
    }
    String designId;
    String designName;
    String designIban;
    String designIntegrationCode;
    @Test
    public void createPositions() {

        designName = Tools.Random.getRandomName();
        designIban = Tools.Random.getRandomStringCode(2) + "" + Tools.Random.getRandomIntCode(20);
        designIntegrationCode = Tools.Random.getRandomStringCode(2) + "" + Tools.Random.getRandomIntCode(3);
        Design09 design09 = new Design09();
        design09.setName(designName);
        design09.setIban(designIban);
        design09.setIntegrationCode(designIntegrationCode);
        design09.setCurrency("USD");
        design09.setSchoolId("6343bf893ed01f0dc03a509a");
        designId =
                given()
                        .cookies(cookies)
                        .contentType(ContentType.JSON)
                        .body(design09)
                        .when()
                        .post("school-service/api/bank-accounts")
                        .then()
                        .log().body()
                        .statusCode(201)
                        .extract().jsonPath().getString("id")
        ;
    }
    @Test(dependsOnMethods = "createPositions")
    public void createPositionsNegative() {

        Design09 design09 = new Design09();
        design09.setId(designId);
        design09.setName(designName);
        design09.setIban(designIban);
        design09.setIntegrationCode(designIntegrationCode);
        design09.setCurrency("USD");
        design09.setSchoolId("6343bf893ed01f0dc03a509a");
        given()
                .cookies(cookies)
                .contentType(ContentType.JSON)
                .body(design09)
                .when()
                .post("school-service/api/bank-accounts")
                .then()
                .log().body()
                .statusCode(400)
                .body("message", equalTo("To create a new Bank Account, the field 'ID' must be empty."))
        ;
    }
    @Test(dependsOnMethods = "createPositions")
    public void updatePositions() {

        designName = Tools.Random.getRandomName();
        designIban = Tools.Random.getRandomStringCode(2) + "" + Tools.Random.getRandomIntCode(20);
        designIntegrationCode = Tools.Random.getRandomStringCode(2) + "" + Tools.Random.getRandomIntCode(3);
        Design09 design09 = new Design09();
        design09.setId(designId);
        design09.setName(designName);
        design09.setIban(designIban);
        design09.setIntegrationCode(designIntegrationCode);
        design09.setCurrency("USD");
        design09.setSchoolId("6343bf893ed01f0dc03a509a");
        given()
                .cookies(cookies)
                .contentType(ContentType.JSON)
                .body(design09)
                .when()
                .put("school-service/api/bank-accounts")
                .then()
                .log().body()
                .statusCode(200)
                .body("name", equalTo(designName))
        ;
    }
    @Test(dependsOnMethods = "updatePositions")
    public void deletePositions() {

        given()
                .cookies(cookies)
                .pathParam("designId", designId)
                .when()
                .delete("school-service/api/bank-accounts/{designId}")
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
                .delete("school-service/api/bank-accounts/{designId}")
                .then()
                .log().body()
                .statusCode(400)
        ;
    }
    @Test(dependsOnMethods = "deletePositions")
    public void updatePositionsNegative() {

        designName = Tools.Random.getRandomName();
        designIban = Tools.Random.getRandomStringCode(2) + "" + Tools.Random.getRandomIntCode(20);
        designIntegrationCode = Tools.Random.getRandomStringCode(2) + "" + Tools.Random.getRandomIntCode(3);
        Design09 design09 = new Design09();
        design09.setId(designId);
        design09.setName(designName);
        design09.setIban(designIban);
        design09.setIntegrationCode(designIntegrationCode);
        design09.setCurrency("USD");
        design09.setSchoolId("6343bf893ed01f0dc03a509a");
        given()
                .cookies(cookies)
                .contentType(ContentType.JSON)
                .body(design09)
                .when()
                .put("school-service/api/bank-accounts")
                .then()
                .log().body()
                .statusCode(400)
                .body("message", equalTo("Can't find Bank Account"))
        ;
    }
}
