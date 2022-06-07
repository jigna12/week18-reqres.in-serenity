package in.reqres.reqresinfo;
import in.reqres.constants.EndPoints;
import in.reqres.model.ReqresPojo;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;
import java.util.HashMap;

public class ReqresSteps {
    @Step ("Creating user with name : {0}, jod : {1},email : {2}")
    public ValidatableResponse createUser(String name, String job, String email){
        ReqresPojo reqresPojo = new ReqresPojo();
        reqresPojo.setName(name);
        reqresPojo.setJob(job);
        reqresPojo.setEmail(email);
        HashMap<String, Object> header1 = new HashMap<>();

        return SerenityRest.given().log().all()
                .headers(header1)
                .body(reqresPojo)
                .when()
                .post(EndPoints.CREATE_USER_BY_ID)
                .then();
    }
    @Step("Getting single User information with name: {0}")
    public HashMap<String, Object> getUserInfoByName(String name) {
        String p1 = "data.findAll{it.name='";
        String p2 = "'}.get(0)";
        return SerenityRest.given().log().all()
                .when()
                .get(EndPoints.GET_ALL_USERS)
                .then()
                .statusCode(200)
                .extract()
                .path(p1 + name + p2);

    }
    @Step("Updating user information with name: {0}")
    public ValidatableResponse updateUser(int userId, String name) {
        ReqresPojo reqresPojo = new ReqresPojo();
        reqresPojo.setName(name);

        return SerenityRest.given().log().all()
                .header("Content-Type", "application/json")
                .pathParam("userID", userId)
                .body(reqresPojo)
                .when()
                .put(EndPoints.UPDATE_USER_BY_ID)
                .then();
    }
    @Step("Deleting user information with userID: {0}")
    public ValidatableResponse deleteuser(int userId) {
        return SerenityRest
                .given()
                .pathParam("userID", userId)
                .when()
                .delete(EndPoints.DELETE_USER_BY_ID)
                .then();
    }
    @Step("Getting user information with userID: {0}")
    public ValidatableResponse getuserById(int userId) {
        return SerenityRest
                .given()
                .pathParam("userID", userId)
                .when()
                .get(EndPoints.GET_SINGLE_USER_BY_ID)
                .then();
    }
}