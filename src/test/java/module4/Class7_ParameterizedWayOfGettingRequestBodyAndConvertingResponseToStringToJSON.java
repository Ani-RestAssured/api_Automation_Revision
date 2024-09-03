package module4;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import utils.Payload;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
import org.testng.annotations.DataProvider;
import utils.StringToJson;


public class Class7_ParameterizedWayOfGettingRequestBodyAndConvertingResponseToStringToJSON {

    @Test(dataProvider = "BooksData")
    public void addBook(String bName,String isbn, int aisle,String author)
    {
        RestAssured.baseURI = "http://216.10.245.166";

        Response response =
                given().
                        header("Content-Type","application/json").
                        body(Payload.addBook(bName,isbn,aisle,author)).
                        when().
                        post("Library/Addbook.php").
                        then().assertThat().
                        statusCode(200).
                        extract().response();

        JsonPath js = StringToJson.convert(response.toString());

        //   String id = (js.get("ID"));

        System.out.println(js);
    }

    @DataProvider(name="BooksData")
    public Object[][] getData()
    {
        return new Object[][] {{"book1","is1",1,"author1"},{"book2","is2",2,"author2"},{"book3","is3",3,"author3"},{"book4","is4",4,"author4"}};
    }
}
