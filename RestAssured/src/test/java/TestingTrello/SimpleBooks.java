package TestingTrello;



import org.testng.annotations.Test;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

import java.util.Random;


public class SimpleBooks {
	
	//my first step will be to add baseurl
	//when i add static i can call/use this variable in any other class with out creating a object 
	public static String BaseURL = "https://simple-books-api.glitch.me"; 
	public static String token;
	public static String ID;
	
	//Generating Roandome Name and Email
	public static String[] names = {"Sunil1","Nishchith1","Niharika1","Nagaraj1"};
	public static String[] domains = {"example.com","test.com","example.org","test.org"};
	public static Random rand = new Random();
	public static String name = names[rand.nextInt(names.length)];
	public static String email = name.toLowerCase() + "@" + domains[rand.nextInt(domains.length)];
	
	
	@Test(priority = 0,enabled = true)
	public void Authenticate()
	{
		
		RestAssured.baseURI = BaseURL;
		
		String requestbody = "{\n"
				+ "   \"clientName\": \"" + name + "\" ,\n"
				+ "   \"clientEmail\": \"" + email + "\"\n"
				+ "}";
		
	Response res =	given()
		.header("Content-Type","application/json")
		.body(requestbody)
		
		.when()
		.post("/api-clients/")
		
		.then()
		.assertThat().statusCode(201)
		.extract().response();
	
	String jsonresponse = res.asString();
	System.out.println(jsonresponse);
	//i Converted the response in to json Format
	JsonPath js = new JsonPath(jsonresponse);
	//i am trying to fetcht he accesstoken as an object//storing it in a variable called token
	//this variable called token declared  where
	token = js.get("accessToken");
		
	}
	
	
	@Test
	public void createorder()
	{
		RestAssured.baseURI = BaseURL;
		
		String requestbody = "{\n"
				+ "  \"bookId\": 4,\n"
				+ "  \"customerName\": \"sunil\"\n"
				+ "}";
		
		Response res =	given()
				.header("Content-Type","application/json")
				.body(requestbody)
				.header("Authorization", "Bearer "+token)
				
				.when()
				.post("/orders/")
				.then()
				.assertThat().statusCode(201)
				.extract().response();
			
			String jsonresponse = res.asString();
			System.out.println(jsonresponse);
			JsonPath js = new JsonPath(jsonresponse);
			ID = js.get("orderId");
				
				
	}
	
	
}
