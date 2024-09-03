package module4;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import utils.Payload;
import utils.StringToJson;

import static io.restassured.RestAssured.*;

public class Class5_HardcodedWayOfGettingRequestBodyAndConvertingResponseToStringToJSON {

    public static void main(String[] args)
    {
        RestAssured.baseURI="http://216.10.245.166";

        Response response = given().log().all()
                           .header("Content-Type","application/json")
                           .body(Payload.addBook())
                           .when().post("Library/Addbook.php")
                           .then().assertThat()
                           .statusCode(200)
                           .extract().response();

        JsonPath js = StringToJson.convert(response.toString());

        System.out.println(js);
    }
}
