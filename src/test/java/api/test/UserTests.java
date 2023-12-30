package api.test;

import api.endpoints.UserEndPoints;
import api.payload.User;
import com.github.javafaker.Faker;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.apache.http.HttpStatus.SC_OK;
import static org.assertj.core.api.Assertions.assertThat;

public class UserTests {

    Faker faker;
    User userPayload;

    public Logger logger; // for logs - use log4j

    // create the user once with faker library before
    // all tests run

    @BeforeClass
    public void setup() {
        faker = new Faker();
        userPayload = new User();

        userPayload.setId(faker.idNumber().hashCode());
        userPayload.setUsername(faker.name().username());
        userPayload.setFirstName(faker.name().firstName());
        userPayload.setLastName(faker.name().lastName());
        userPayload.setEmail(faker.internet().safeEmailAddress());
        userPayload.setPassword(faker.internet().password(5, 10));
        userPayload.setPhone(faker.phoneNumber().cellPhone());

        //logs
        logger = LogManager.getLogger(this.getClass());

        logger.debug("debugging..... create user payload in setup method");

    }

    @Test(priority = 1)
    public void testPostUser() {
        logger.info("********** Creating user  ***************");
        Response response = UserEndPoints.createUser(userPayload);
        response.then().log().all();

        // Assert.assertEquals(response.getStatusCode(), 200);

        assertThat(response.getStatusCode()).isEqualTo(SC_OK);

        logger.info("**********User is created  ***************");

    }

    @Test(priority = 2)
    public void testGetUserByName() {
        logger.info("********** Reading User Info ***************");

        Response response = UserEndPoints.readUser(this.userPayload.getUsername());
        response.then().log().all();
//        Assert.assertEquals(response.getStatusCode(), 200);

        assertThat(response.getStatusCode()).isEqualTo(SC_OK);


        logger.info("**********User info  is displayed ***************");

    }

    @Test(priority = 3)
    public void testUpdateUserByName() {
        logger.info("********** Updating User ***************");

        //update data using payload
        String firstNameToUpdate = faker.name().firstName();
        String lastNameToUpdate = faker.name().lastName();
        String emailToUpdate = faker.internet().safeEmailAddress();

        userPayload.setFirstName(firstNameToUpdate);
        userPayload.setLastName(lastNameToUpdate);
        userPayload.setEmail(emailToUpdate);

        Response response = UserEndPoints.updateUser(this.userPayload.getUsername(), userPayload);
        response.then().log().body();

//        Assert.assertEquals(response.getStatusCode(), 200);

        assertThat(response.getStatusCode()).isEqualTo(SC_OK);


        logger.info("********** User updated ***************");
        //Checking data after update

        //Response responseAfterupdate = UserEndPoints.readUser(this.userPayload.getUsername());
        //Assert.assertEquals(responseAfterupdate.getStatusCode(),200);

        User user = UserEndPoints.getUser(this.userPayload.getUsername());

        // assert the user fields updated successfully
        assertThat(user.getFirstName())
                .as("check first name")
                .isEqualTo(firstNameToUpdate);
        assertThat(user.getLastName())
                .as("check last name")
                .isEqualTo(lastNameToUpdate);
        assertThat(user.getEmail())
                .as("check email")
                .isEqualTo(emailToUpdate);
    }

    @Test(priority = 4)
    public void testDeleteUserByName() {
        logger.info("**********   Deleting User  ***************");

        Response response = UserEndPoints.deleteUser(this.userPayload.getUsername());

        //    Assert.assertEquals(response.getStatusCode(), 200);

        assertThat(response.getStatusCode()).isEqualTo(SC_OK);


        logger.info("********** User deleted ***************");
    }


}
