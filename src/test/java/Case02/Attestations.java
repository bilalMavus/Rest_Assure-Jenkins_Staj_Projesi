package Case02;

import Tools.Login;
import io.restassured.http.Cookies;
import org.testng.annotations.BeforeClass;

public class Attestations {
    //   Human Resources	Setup	Attestations

    Cookies cookies;

    @BeforeClass
    public void login() {
        cookies = Login.loginCampus();
    }

}

