package DDO;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class APOA {
	
	String AT = "CfDJ8LpTj3qJhNlCsyWchNBJSkjISF4fSrfWS2H5yaI-ch9OM1HwR82ftcek0ltJ7N5Is688GoIghv0_IsKld0lWNGlf82uXCYeZLoN4uez9QbIj4lC_iJLeUgOTr4D86sv0GkUV8krjYQNVfQjM9q_0-WY";
	@Test(priority = 0)
	public void Login_Page() {
		
		Response rep= RestAssured.get("https://www.personalbrandingcouncil.com/home/login");
		System.out.println("Code" +rep.getStatusCode());
	
		
	}
	
	@Test(priority = 1)
	public void Login_Oauth() {
		
	  Response rep1=  RestAssured.given()
			  //Secret ID is not missing //Tried to Fetcht he Token from the Application.
		.formParam("ReturnUrl", "/connect/authorize/callback?client_id=PersonalBrandingWebApp&redirect_uri=https%3A%2F%2Fwww.personalbrandingcouncil.com%2Fsignin-oidc&response_type=code%20id_token&scope=openid%20profile%20email%20offline_access&response_mode=form_post&nonce=638266496445534633.OGEyODE0ODQtZmNlMy00ODNhLTk4NTMtMGUwMDI3MTE3YzIxNDViNDJmMDgtNjRiOC00OTM1LTk4YjktMmE0OWQxMjg0NDg3&state=CfDJ8MAFIzHwJR1IirozD0KixTdYQFOVBKvacZqfRhNxy5dQITc-baWV2v193tzasoaq47tN5y27WBl_NLjQisQz8-pwE1yfBd2MQgnGlr1CbARcwbsSv8GNuC6DjE1BEdVoj0IpLGT1D8ztnO6GQBMxBMsp0yuvZWcaGYQNe2ns527KkQv8Nm2hKRiBWnPWlQjyh1_kOXi_2E_Dlxrk3CoThaBgzE5rwgEK0g4zZ9VyrRFAk9yTsz-jOBcH8CN1QYwhv6xsGzJA2YKpRt9osD5C7K9qY_yPrQTBhHjjHotjFhlKfS7HsKtQMGP0tB3V3gmE4Zh_1FFLgmgutAM0mQHewSI&x-client-SKU=ID_NETSTANDARD2_0&x-client-ver=6.8.0.0")
		.formParam("RememberLogin","false")
		.formParam("__RequestVerificationToken", AT)
		.formParam("Username", "Ifthy")
		.formParam("Password", "Rockon123")
		.post("https://www.oauth.personalbrandingcouncil.com/Account/Login").then().extract().response();
	 
	  System.out.println("Code" +rep1.getStatusCode());
	  System.out.println(rep1.getBody().asString());
	}
	 
	 // @Test
		//public void Sign_in() {
		 
	 // }
	 
	 
	 
		
		
		
	}





