package api.test;

import api.endpoints.UserEndPoints;
import api.payload.User;
import api.utilities.DataProviders;
import com.github.javafaker.Faker;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.apache.http.HttpStatus.SC_OK;
import static org.assertj.core.api.Assertions.assertThat;

public class DDTests {


    public Logger logger; // for logs - use log4j

    @BeforeClass
    public void setup() {


        //logs
        logger = LogManager.getLogger(this.getClass());
        logger.debug("logger created for -> DDTests");

    }



    @Test(priority = 1, dataProvider = "Data", dataProviderClass = DataProviders.class)
    public void testPostUser(String userID, String userName, String fname, String lname, String useremail, String pwd, String ph) {
        User userPayload = new User();

        userPayload.setId(Integer.parseInt(userID));
        userPayload.setUsername(userName);
        userPayload.setFirstName(fname);
        userPayload.setLastName(lname);
        userPayload.setEmail(useremail);
        userPayload.setPassword(pwd);
        userPayload.setPhone(ph);

        logger.info("********** Creating user: " + userName + " ***************");

        Response response = UserEndPoints.createUser(userPayload);

        // Assert.assertEquals(response.getStatusCode(), 200);
        assertThat(response.getStatusCode()).isEqualTo(SC_OK);

        logger = LogManager.getLogger(this.getClass());

        logger.info("**********User: "+ userName + " created  ***************");
    }

    @Test(priority = 2, dataProvider = "UserNames", dataProviderClass = DataProviders.class)
    public void testDeleteUserByName(String userName) {
        Response response = UserEndPoints.deleteUser(userName);
        // Assert.assertEquals(response.getStatusCode(), 200);

        assertThat(response.getStatusCode()).isEqualTo(SC_OK);

        logger.info("********** User " + userName + " deleted ***************");


    }


}
