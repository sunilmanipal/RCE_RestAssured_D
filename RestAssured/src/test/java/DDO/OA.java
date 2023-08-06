package DDO;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class OA {
	public static String resp;
	
@Test(priority=0)
	
	public void AccessToken() {
		
	//OAUT example
	//Application URL: http://coop.apps.symfonycasts.com/api
	//is basically to fetch the accesstoken which is in the format of OAUT
		Response rep = RestAssured.given()
		.formParam("client_id", "DemoRCE")
		.formParam("client_secret", "1bcfe98d44f51a2299d7d652921fc9ca")
		.formParam("grant_type", "client_credentials")
		.post("http://coop.apps.symfonycasts.com/token");
		
		//i am converting my response in to Json Format
		System.out.println(rep.jsonPath().prettify());
		//System.out.println(rep.jsonPath().get("access_token"));
		//i am trying to fetch the accesstoken
	   resp = rep.jsonPath().get("access_token");
	   //if it is xml i will try to get the Xpath //access_Token
		
	}
	
	
	
	
	@Test(priority=1)
	public void info() {
		
	Response rep =	RestAssured
			.given()
			.auth()
			.oauth2(resp)
			.post("http://coop.apps.symfonycasts.com/api/4632/barn-unlock");
		
		System.out.println("Code" +rep.getStatusCode());
		System.out.println("Code" +rep.getBody().asString());
	
	
	}

}
