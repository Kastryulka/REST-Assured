import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.Test;
import pojo.ProductData;

public class SecondCaseTest {
    @Test
    public void httpPostTest(){
        Specifications specifications = new Specifications();
        ProductData product = new ProductData("test product",(float)10);

        RequestSpecification requestSpec= specifications.getStoreRequestSpec(product);
        RestAssured.requestSpecification = requestSpec;
        RestAssured.responseSpecification = specifications.getResponseSpec();

        Response response = RestAssured.post("products/");
        //response.prettyPrint();
        response.then()
                .statusCode(200);
    }
}
