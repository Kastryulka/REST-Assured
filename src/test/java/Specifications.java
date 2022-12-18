import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import pojo.ProductData;

public class Specifications {
    public RequestSpecification getStoreRequestSpec(ProductData product) {
        RequestSpecification requestSpec = new RequestSpecBuilder()
                .setBaseUri("https://fakestoreapi.com/")
                .setAccept(ContentType.JSON)
                .setContentType(ContentType.JSON)
                .setBody(product)
                .log(LogDetail.ALL)
                .build();
        return requestSpec;
    }
    public ResponseSpecification getResponseSpec(){
        ResponseSpecification responseSpec = new ResponseSpecBuilder()
                .log(LogDetail.HEADERS)
                .build();
        return responseSpec;
    }
}
