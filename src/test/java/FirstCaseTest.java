import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pojo.PersonData;

import java.util.List;

import static io.restassured.RestAssured.given;

public class FirstCaseTest {
    private static Logger logger = LogManager.getLogger(FirstCaseTest.class);
    Specifications specifications = new Specifications();

    @Test
    public void httpGetKanyeTest() {
        //успешный тест(200), проверка кода, проверка заголовка, не fluent
        RestAssured.responseSpecification = specifications.getResponseSpec();
        RequestSpecification request = given();
        Response response = request.get("https://api.kanye.rest");
        response.then().statusCode(200).contentType(ContentType.JSON);
        response.prettyPrint();
    }
    @Test
    public void httpGetPartialTest() {
        //успешный тест(206), проверка кода, проверка заголовка, не fluent
        RestAssured.responseSpecification = specifications.getResponseSpec();

        RequestSpecification request = given();
        request.baseUri("https://http.cat/206");
        request.header("range", "bytes=10-100");
        Response response = request.get();
        response.then()
                .statusCode(206)
                .statusLine("HTTP/1.1 206 Partial Content");
        response.prettyPrint();
    }
    @Test
    public void httpGetIncorrectTest() {
        //успешный тест(404), проверка кода
        RestAssured.responseSpecification = specifications.getResponseSpec();

        Response response = given()
                .baseUri("https://fakestoreapi.com/")
                .when().get("random");
        response.then()
                .statusCode(404);
        response.prettyPrint();
    }
    @Test
    public void httpGetWithParamTest() {
        //неуспешный тест(200 , json), проверка кода, проверка заголовка, параметр limit
        RestAssured.responseSpecification = specifications.getResponseSpec();

        Response response = given()
                .baseUri("https://fakestoreapi.com/")
                .queryParam("limit",5)
                .expect()
                //вернет 200 и json
                .contentType(ContentType.XML)
                .statusCode(404)
                //.when().get("products/?limit=5");
                .when().get("products/");
        response.prettyPrint();
    }
    @Test
    public void httpGetPersonTest() {
        //успешный тест(200, Luke Skywalker), проверка тела ответа
        RestAssured.responseSpecification = specifications.getResponseSpec();
        PersonData lukeSkywalker = new PersonData("Luke Skywalker","172");

        PersonData person = given()
                .baseUri("https://swapi.dev/api/")
                .when().get("people/1")
                .then().log().all()
                .extract().body().as(PersonData.class);
        logger.info(person);
        Assertions.assertEquals(person,lukeSkywalker);
    }
    @Test
    public void httpGetListPersonTest() {
        //успешный тест(200, Luke Skywalker), проверка тела ответа(как List)
        RestAssured.responseSpecification = specifications.getResponseSpec();
        PersonData lukeSkywalker = new PersonData("Luke Skywalker","172");

        List<PersonData> person = given()
                .baseUri("https://swapi.dev/api/")
                .when().get("people/")
                .then().log().all()
                .extract().body().jsonPath().getList("results",PersonData.class);
        logger.info(person.get(0));
        Assertions.assertEquals(person.get(0),lukeSkywalker);
    }
}
