package stepDefinitions.API;

import com.github.javafaker.Faker;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import pojos.CountryPojo;

import test_data.Country;
import utilities.Authentication2;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import static base_Url.MedunnaUrl.spec;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.*;
import static utilities.Authentication.generateToken;


    public class Yusuf {
        // 28 API

        List<String> countryNameList;
        Response response;
        Map<String, Object> expectedData;
        Map<String, Object> actualData;
        Faker faker = new Faker();
        String countryPost = faker.country().name() + faker.country().capital();
        String countryPut = faker.country().name() + faker.country().capital();
        Country obj=new Country();

        @Given("Kullanici Get,post,put ve delete islemlerinin yapildigini Api Testi ile dogrular clean code")
        public void kullaniciGetPostPutVeDeleteIslemlerininYapildiginiApiTestiIleDogrularCleanCode() {
            // Get islemi
            String url = "https://medunna.com/api/countries/";
            response = given().headers("Authorization", "Bearer " + generateToken())
                    .when()
                    .get(url);
            // Get Assertion
            assertEquals(200, response.statusCode());

            // Post islemi
            expectedData = obj.expectedDataMap(null, countryPost);
            response = given().contentType(ContentType.JSON).headers("Authorization", "Bearer " + generateToken()).body(expectedData)
                    .when()
                    .post(url);
            // Post Assertion
            /* Map ile */
            actualData = response.as(HashMap.class);
            assertEquals(expectedData.get("name"), actualData.get("name"));
            assertEquals(201, response.statusCode());
            int id= (int) actualData.get("id");
            String country= (String) actualData.get("name");
            /* Pojo ile */
            CountryPojo expectedDataPojo = new CountryPojo(null, countryPost);
            CountryPojo actualDataPojo = response.as(CountryPojo.class);
            assertEquals(expectedDataPojo.getName(), actualDataPojo.getName());

            // Put islemi
            expectedData = obj.expectedDataMap(id, countryPut);
            response = given().contentType(ContentType.JSON).headers("Authorization", "Bearer " + generateToken()).body(expectedData)
                    .when()
                    .put(url);
            // Put Assertion
            String actualDataPut=country;
            String expectedData=countryPut;
            assertNotEquals(expectedData, actualDataPut);
            assertEquals(200, response.statusCode());

            // Delete islemi
            response = given().contentType(ContentType.JSON).headers("Authorization", "Bearer " + generateToken())
                    .when().delete(url+id);
            // Delete Assertion
            assertEquals(204,response.statusCode());
    }
}
