package com.unicorns.apis.unicorns;

import com.unicorns.apis.BaseAPI;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class BrandsAPI extends BaseAPI {

    private RequestSpecification requestSpecification;
    private String BRANDS_ENDPOINT;
    private Response getAllBrandsResponse;
    private Response getBrandByIdResponse;
    private Response postBrandResponse;


    public BrandsAPI(){
        BRANDS_ENDPOINT = System.getProperty("BRANDS_ENDPOINT");
        BASE_URI = BASE_URI.concat(BRANDS_ENDPOINT);
        requestSpecification = new RequestSpecBuilder()
                .setAccept("*/*")
                .setContentType(ContentType.JSON)
                .setBaseUri(BASE_URI)
                .addHeader("Connection","keep-alive")
                .build();
    }

    // GET ALL Brands Methods
    public void getAllBrandsRequest(){
        getAllBrandsResponse = given().spec(requestSpecification).get();
    }

    public String getAllBrandsResponseStatusCode(){
        return getResponseStatusCode(getGetAllBrandsResponse());
    }

    public int getAllBrandsNumber(){
        return getGetAllBrandsResponse()
                .then().extract().response().jsonPath().getList("$").size();
    }

    public List<Map<String, Object>> getAllBrandsEntries(){
        return getGetAllBrandsResponse()
                .then().extract().response().jsonPath().getList("$");
    }

    public Response getGetAllBrandsResponse() {
        return getAllBrandsResponse;
    }

    //GET Post By ID Methods
    public void getBrandByIdRequest(String id){
        getBrandByIdResponse = given().spec(requestSpecification).get(id);
    }

    public String getBrandByIdStatusCode(){
        return getResponseStatusCode(getGetBrandByIdResponse());
    }

    public String getNameValueInGetBrandByIdResponseBody(){
        return getGetBrandByIdResponse().jsonPath().getString("name");
    }
    public String getIdValueInGetBrandByIdResponseBody(){
        return getGetBrandByIdResponse().jsonPath().getString("_id");
    }

    public String getErrorMessageForInvalidId() {
        return getGetBrandByIdResponse().jsonPath().getString("error");
    }

    public Response getGetBrandByIdResponse() {
        return getBrandByIdResponse;
    }

    //CREATE Brand Methods
    public void createBrand(String name, String description){
        postBrandResponse = given().spec(requestSpecification).body(createBrandBody(name, description)).post();
    }

    public String getCreatePostStatusCode(){
        return getResponseStatusCode(getPostBrandResponse());
    }

    public String getNameValueInCreateBrandResponseBody(){
        return getPostBrandResponse().jsonPath().getString("name");
    }

    public String getDescriptionValueInCreateBrandResponseBody(){
        return getPostBrandResponse().jsonPath().getString("description");
    }

    public Response getPostBrandResponse() {
        return postBrandResponse;
    }

    public String getErrorMessageForDuplicateBrand() {
        return getPostBrandResponse().jsonPath().getString("error");
    }

    private Object createBrandBody(String name, String description){
        JSONObject postBody = new JSONObject();
        postBody.put("name",name);
        postBody.put("description",description);
        return postBody.toJSONString();
    }
}
