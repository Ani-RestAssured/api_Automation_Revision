package module4;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import utils.Payload;
import io.restassured.path.json.JsonPath;

public class Class2_BasicWayOfGettingRequestBodyAndExtractingResponseUsingJsonPath {

    /*Pass URI
     * Attach Params using queryParam
     * Attach Header using header
     * Attach JSON Body from a static method of another class
     * Validate Response Code
     * Validate Header
     * Parse Response Body using JSON
     * Extract Field from Response Body
     * */
    public static void main (String[] args)
    {
        RestAssured.baseURI = "https://github.com/Anirudho747/api_Automation_Revision.git";

        String response = given().queryParam("key","qaclick123").header("Content-Type","application/json")
                .body(Payload.AddPlace()).
                when().post("/maps/api/place/add/json").
                then().assertThat().
                statusCode(200).
                body("scope",equalTo("APP")).
                header("server","Apache/2.4.52 (Ubuntu)").extract().asString();

        System.out.println("Response "+ response);

        JsonPath js =new JsonPath(response); //for Parsing JSON
        String placeId = js.getString("place_id");
        System.out.println(placeId);
    }
}
