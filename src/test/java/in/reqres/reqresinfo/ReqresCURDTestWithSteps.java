package in.reqres.reqresinfo;


import in.reqres.testbase.TestBase;
import in.reqres.utils.TestUtils;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import java.util.HashMap;

import static org.hamcrest.Matchers.hasValue;


@RunWith(SerenityRunner.class)
public class ReqresCURDTestWithSteps extends TestBase {

    static String name = "Pari" + TestUtils.getRandomValue();
    static String job = "Tester" + TestUtils.getRandomValue();
    static String email = TestUtils.getRandomValue() + "xyz@gmail.com";
    static int userId;

    @Steps
    ReqresSteps userSteps;

    @Title("This will create a new user")
    @Test
    public void test001() {
        userSteps.createUser(name,job,email).log().all().statusCode(201);
    }
    @Title("Verify if the service was added")
    @Test
    public void test002() {

        HashMap<String, Object> value = userSteps.getUserInfoByName(name);
        Assert.assertThat(value, hasValue(name));
        userId = (int) value.get("id");
        System.out.println(userId);
    }

    @Title("Update the user information and verify the updated information")
    @Test
    public void test003() {
        name = "Rima";
        userId = 52;
        userSteps.updateUser(userId, name).statusCode(200).log().all();

    }

    @Title("Delete the service and verify if the service is deleted!")
    @Test
    public void test004() {
        userId = 52;
        userSteps.deleteuser(userId).statusCode(204).log().status();
        userSteps.getuserById(userId).statusCode(404).log().status();

    }

}
