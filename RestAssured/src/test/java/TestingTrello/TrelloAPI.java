package TestingTrello;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class TrelloAPI {
	
	// I am Creating my own environment variable 
	
	 public static String BaseURL ="https://api.trello.com";
	 public static String key ="00e8be0fc9896795e3352bf3b09cf364";
	 public static String Token = "ATTA30d01c8aeb9c927997da1265477e9528d86d38e87fa765c167acee83828e1783C33C0ECA";
	 public static String ID;
	 public static String createTable;
	 public static int rowcount;
	 public static XSSFSheet sh;
	 
	 @Test(priority = 0)
	 public void BT() throws IOException
	 {
		 File excel = new File("/Users/administrator/Documents/RCE-Rest Assured/RestAssured/RestAssuredTestData.xlsx");
		 //This below code will help me to read the excel as input file
		 FileInputStream fis = new FileInputStream(excel);
		 //This below line will help me to go to workbook of the excel which i am reading from input stream 
		 XSSFWorkbook wb = new XSSFWorkbook(fis);
		 //This below code will help me to go to the specif sheet
		 XSSFSheet sh = wb.getSheet("Sheet1");
		 //The Below code will tell me how many rows are there
		 rowcount =  sh.getLastRowNum();
		for(int i=0;i<=rowcount;i++)
		{
			 createTable = sh.getRow(i).getCell(0).getStringCellValue();
			 System.out.println(createTable);
			 CreateBoard();
			 getBoard();
			 DeleteBoard();
		}
	 }
	
	
	@Test(priority = 0,enabled = false)
	public void CreateBoard() throws IOException
	
	{
		 //The Below line will get the path of the excel sheet and store it in the variable called excel
		
			 
				RestAssured.baseURI = BaseURL;
				
				//Rest Assured contains 3 methods 
				//given - pre-condition //is a method which can take body, headers,authorization, content-type ,query Parameter
				//when -  Action Method //only one thing in when // HTTTP method name , get, POST, PUT, PATCH, Delete
				//Then -  Post-Condition // status //response 
				
				//given is the intial method which carries the request and fetc the response //when and then will be part 
				//The variable response is use to store the response
				
					 Response response  = 	given()
								.queryParam("key", key)
								.queryParam("token", Token)
								.queryParam("name", createTable)
								.header("Content-Type","application/json")
								
								.when()
								.post("/1/boards/")
								
								
								.then()
								.assertThat().statusCode(200)
								//This below line is use to extract the response
								.extract().response();
							//This below line will Print the response in the strin format in my console
							//System.out.println(response.asString());
							
							//i am stroing the response in a variable and in the string format
							String jsonresponse = response.asString();
							
							//when i use JsonPath i am converting the response in string to response in json
							JsonPath js = new JsonPath(jsonresponse);
							
							ID = js.get("id");
							System.out.println(ID);
							
			
		}
		

		//API 
		//Request and Response 
		//What all goes in Request Authorization, Body, Validation , baseurl , resource,  query parameter(this is a parameter which goes along with URL}
		
		//if you want to pass the URL //because this is my first step//how do i pass the url
	
						//fetch the response 
						//do remember the response is in a simple String format //i have to convert this in to json format
					
			
		
	
	
	
	@Test (priority = 1,enabled = false)
	public void getBoard()
	{
		//what should i do 
		RestAssured.baseURI = BaseURL;
		
		
	Response response =	given()
		.queryParam("key", key)
		.queryParam("token", Token)
		.header("Content-Type","application/json")
		
		.when()
		.get("/1/boards/"+ID)
		
		
		.then()
		.assertThat().statusCode(200)
		.extract().response();
	//This will print me the response
	System.out.println(response.asString());
		
	}

	
	@Test (priority = 2,enabled = false)
	public void DeleteBoard()
	{
		//what should i do 
		RestAssured.baseURI = BaseURL;
		
		
	Response response =	given()
		.queryParam("key", key)
		.queryParam("token", Token)
		.header("Content-Type","application/json")
		
		.when()
		.delete("/1/boards/"+ID)
		
		
		.then()
		.assertThat().statusCode(200)
		.extract().response();
	//This will print me the response
	System.out.println(response.asString());
		
	}

}
