package me.dri.TesteBackEnd.integration;


import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import me.dri.entities.dto.SellDTO;
import me.dri.entities.dto.SellerDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;


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
    void testSell() {
        RestAssured.baseURI = "http://localhost:8080/api/sell";
        SellerDTO sellerDTO = new SellerDTO("Diego");
        SellDTO sellDTO = new SellDTO(new Date(), 200.0, sellerDTO.sellerName());
        given().when().body(sellDTO).contentType(ContentType.JSON).
                when().post().then().statusCode(201);
    }

    @Test
    void testMultipleSells() {
            RestAssured.baseURI = "http://localhost:8080/api/sell";
            SellerDTO sellerDTO = new SellerDTO("Diego");
            SellDTO sell1 = new SellDTO(new Date(), 200.0, sellerDTO.sellerName());
            SellDTO sell2 = new SellDTO(new Date(), 300.0, sellerDTO.sellerName());
            given().when().body(sell1).contentType(ContentType.JSON).
                    when().post().then().statusCode(201);
            given().when().body(sell2).contentType(ContentType.JSON).
                when().post().then().statusCode(201).body("amountSells", equalTo(3));
    }
}
