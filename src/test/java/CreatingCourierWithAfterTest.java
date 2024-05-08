import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import steps.CourierSteps;

import static constants.RandomData.*;
import static constants.Url.URL;

public class CreatingCourierWithAfterTest {

    CourierSteps courierSteps;

    @Before
    public void setUp() {
        RestAssured.baseURI = URL;
        courierSteps = new CourierSteps();
    }

    @After
    public void deleteCourier() {
        Response responseDelete = courierSteps.deleteCourier(RANDOM_LOGIN, RANDOM_PASS);
        courierSteps.checkAnswerThenValidDeleting(responseDelete);
    }

    @Test
    @DisplayName("Creating new courier")
    @Description("Creating new courier with correct credentials and check the positive creating courier")
    public void creatingCourierPositive() {
        Response responseCreate = courierSteps.createCourier(RANDOM_LOGIN, RANDOM_PASS, RANDOM_NAME);
        courierSteps.checkAnswerValidRegistration(responseCreate);
    }

    @Test
    @DisplayName("Creating a courier with an existing login")
    @Description("Creating a courier with an existing login and password checking the response")
    public void creatingCourierWithExistingLoginConflict() {
        courierSteps.createCourier(RANDOM_LOGIN, RANDOM_PASS, RANDOM_NAME);
        Response responseExisting = courierSteps.createCourier(RANDOM_LOGIN, RANDOM_PASS, RANDOM_NAME);
        courierSteps.checkAnswerReuseRegistrationData(responseExisting);
    }


    @Test
    @DisplayName("Creating a courier without firstName")
    @Description("Creating a courier without firstName and checking the response")
    public void creatingCourierWithoutNamePositive() {
        Response responseWithoutName = courierSteps.createCourier(RANDOM_LOGIN, RANDOM_PASS, "");
        courierSteps.checkAnswerValidRegistration(responseWithoutName);
    }
}


