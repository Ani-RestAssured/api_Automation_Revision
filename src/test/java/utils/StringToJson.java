package utils;

import io.restassured.path.json.JsonPath;

public class StringToJson {

    public static JsonPath convert(String response)
    {
        JsonPath js1 = new JsonPath(response);
        return js1;
    }
}
