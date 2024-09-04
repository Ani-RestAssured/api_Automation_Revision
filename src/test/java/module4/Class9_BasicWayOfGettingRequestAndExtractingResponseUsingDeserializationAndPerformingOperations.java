package module4;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import pojo.Api;
import pojo.GetCourse;
import static io.restassured.RestAssured.given;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import org.testng.Assert;
public class Class9_BasicWayOfGettingRequestAndExtractingResponseUsingDeserializationAndPerformingOperations {

    public static void main(String[] args)
    {
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

        RestAssured.baseURI = "https://rahulshettyacademy.com";

        GetCourse gc=given()
                .queryParams("access_token", accessToken)
                .when().log().all()
                .get("/oauthapi/getCourseDetails").as(GetCourse.class);

        System.out.println(gc);

        System.out.println(gc.getLinkedIn());
        System.out.println(gc.getInstructor());

        //print Price of All API Courses
        System.out.println(gc.getCourses().getApi().get(0).getCourseTitle());
        System.out.println(gc.getCourses().getApi().get(1).getCourseTitle());

        //Print Price of only Soap API Course

        List<Api> apiCourse = gc.getCourses().getApi();

        for(int i=0;i<apiCourse.size();i++)
        {
            if(apiCourse.get(i).getCourseTitle().equalsIgnoreCase("Rest Assured Automation using Java"))
            {
                System.out.println(apiCourse.get(i).getPrice());
            }
        }

        //Get the course names of WebAutomation
        ArrayList<String> a= new ArrayList<String>();


        List<pojo.WebAutomation> w=gc.getCourses().getWebAutomation();

        for(int j=0;j<w.size();j++)
        {
            a.add(w.get(j).getCourseTitle());
        }

        List<String> expectedList=Arrays.asList(courseTitles);

        Assert.assertTrue(a.equals(expectedList));
    }
}
