package classNotes;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static io.restassured.RestAssured.given;

public class Class4_BasicWayOfGettingRequestBodyAndExtractingResponseUsingJsonPathAndUsingResponseForNextRequestAndExtractingValues {
    /*Pass URI
     * Attach form type Params using formParams
     * Attach Header using header
     * Attach JSON Body from a static method of another class
     * Validate Response Code
     * Validate Header
     * Parse Response Body using JSON
     * Extract Field from Response Body
     * Using Arrays for Calculations and Validations
     * */
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        String[] courseTitles = {"Selenium Webdriver Java","Cypress","Protractor"};
        RestAssured.baseURI = "https://rahulshettyacademy.com";
        String response = 	given()
                .formParams("client_id","692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
                .formParams("client_secret","erZOWM9g3UtwNRj340YYaK_W")
                .formParams("grant_type","client_credentials")
                .formParams("scope","trust")
                .when().log().all()
                .post("/oauthapi/oauth2/resourceOwner/token").asString();

        System.out.println(response);
        JsonPath jsonPath = new JsonPath(response);
        String accessToken = jsonPath.getString("access_token");


        //Using Arrays to get value of a particular Node
        System.out.println(gc.getCourses().getApi().get(1).getCourseTitle());

        //Using Loops to compare values with all Elements
        List<Api> apiCourses=gc.getCourses().getApi();
        for(int i=0;i<apiCourses.size();i++)
        {
            if(apiCourses.get(i).getCourseTitle().equalsIgnoreCase("SoapUI Webservices testing"))
            {
                System.out.println(apiCourses.get(i).getPrice());
            }
        }

    //Using Loops to store names of all elements in an array
        //Get the course names of WebAutomation
        ArrayList<String> a= new ArrayList<String>();

        List<pojo.WebAutomation> w=gc.getCourses().getWebAutomation();

        for(int j=0;j<w.size();j++)
        {
            a.add(w.get(j).getCourseTitle());
        }

        List<String> expectedList=	Arrays.asList(courseTitles);

        Assert.assertTrue(a.equals(expectedList));
    }
}
