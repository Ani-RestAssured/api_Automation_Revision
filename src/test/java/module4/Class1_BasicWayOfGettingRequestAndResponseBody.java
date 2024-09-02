package module4;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;
import file.Payload;
import static org.hamcrest.Matchers.*;
public class Class1_BasicWayOfGettingRequestAndResponseBody {

/*Pass URI
* Attach Params using queryParam
* Attach Header using headerr
* Attach Body using body from a static method of another class
* Validate Response Code
* Validate Header
* Validate Body
* */

    public static void main(String[] args)
    {
        //Add Place API
        RestAssured.baseURI = "https://rahulshettyacademy.com";

        String response  = given().log().all().queryParam("key","qaclick123")
                                              .header("Content-Type","application/json")
                                              .body(Payload.AddPlace())
                                              .when().post("/maps/api/place/add/json")
                                              .then().log().all().assertThat()
                                              .statusCode(200)
                                              .body("scope",equalTo("APP"))
                                              .header("server","Apache/2.4.52 (Ubuntu)").extract().asString();


        System.out.println("Response "+ response);

    }
}
