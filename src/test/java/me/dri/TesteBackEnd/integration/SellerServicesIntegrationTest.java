package me.dri.TesteBackEnd.integration;


import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import me.dri.entities.dto.SellDTO;
import me.dri.entities.dto.SellerDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;


public class SellerServicesIntegrationTest {


    @BeforeEach
    void setup() {
        RestAssured.baseURI = "http://localhost:8080/api/seller";
    }

    @Test
    void testInsertSeller() {
        SellerDTO sellerDTO = new SellerDTO("Diego");
        given().when().body(sellerDTO).contentType(ContentType.JSON).
                when().post().then().statusCode(201);
    }

    @Test
    void testMultipleInsertSellers() {
        for (int i = 0; i < 30; i++) {
            SellerDTO sellerDTO = new SellerDTO("Diego" + i);
            given().when().body(sellerDTO).contentType(ContentType.JSON).
                    when().post().then().statusCode(201);
        }
    }


    @Test
    void testSell() {
        RestAssured.baseURI = "http://localhost:8080/api/sell";
        SellerDTO sellerDTO = new SellerDTO("Diego");
        SellDTO sellDTO = new SellDTO(new Date(), 200.0, sellerDTO.sellerName());
        given().when().body(sellDTO).contentType(ContentType.JSON).
                when().post().then().statusCode(201);
    }

    @Test
    void testMultipleSells() {
        for (int i = 0; i < 30; i++) {

            RestAssured.baseURI = "http://localhost:8080/api/sell";
            SellerDTO sellerDTO = new SellerDTO("Diego" + i);
            SellDTO sellDTO = new SellDTO(new Date(), 200.0, sellerDTO.sellerName());
            given().when().body(sellDTO).contentType(ContentType.JSON).
                    when().post().then().statusCode(201);
        }
    }

    @Test
    void testBestSellersOfWeekend() {
        RestAssured.baseURI = "http://localhost:8080/api/seller";
        given().when().get().then().statusCode(200).
                body("seller", everyItem(notNullValue()))
                .body("average", everyItem(notNullValue()))
                .body("size()", equalTo(10));
    }
}
