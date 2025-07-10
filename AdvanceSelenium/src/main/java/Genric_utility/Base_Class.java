package Genric_utility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;

import Pom_Repo.HomePage;
import Pom_Repo.*;


public class Base_Class {
	
	public WebDriver driver;
	public static WebDriver sdriver;
	
	
	@BeforeSuite(groups = {"smoke", "regression"})
	public void BS() {
		System.out.println("Databases connection");
	}
	@BeforeTest(groups = {"smoke", "regression"})
	public void BT() {
		System.out.println("Parell execution");
	}
	
//	@Parameters("BROWSER")
	
//	public void BC(String BROWSER) throws Throwable {
	@BeforeClass
	(groups = {"smoke", "regression"})
	public void BC() throws Throwable {
		
		File_Utility fUtil = new File_Utility();
		String BROWSER = fUtil.getKeyAndValue("Browser");

		//String BROWSER = System.getProperty("Browser");
		
		if(BROWSER.equalsIgnoreCase("chrome")) {
			driver=new ChromeDriver();
		}else if(BROWSER.equalsIgnoreCase("edge")) {
			driver=new EdgeDriver();
			
		}else {
			driver=new FirefoxDriver();
		}
	    Web_Driver_Utility wdu = new Web_Driver_Utility();
	    wdu.maximizeWindow(driver);
	    wdu.waitElmentsToLoad(driver);
		System.out.println("Launching the Browser");
		sdriver=driver;
	}
//	@Parameters({"URL","Username","Password"})
	
	
//	public void BM(String URL, String Username, String Password) throws Throwable {
	
	@BeforeMethod
	(groups = {"smoke", "regression"})
	public void BM() throws Throwable {
		File_Utility fUtil = new File_Utility();
		String BROWSER = fUtil.getKeyAndValue("Browser");
		String URL = fUtil.getKeyAndValue("url");
		String Username = fUtil.getKeyAndValue("username");
	String Password = fUtil.getKeyAndValue("password");
//		String BROWSER = System.getProperty("Browser");
//		String URL = System.getProperty("url");
//		String Username = System.getProperty("username");
//		String Password = System.getProperty("password");
		

	    Web_Driver_Utility wdu = new Web_Driver_Utility();
	    wdu.maximizeWindow(driver);
	    wdu.waitElmentsToLoad(driver);
	    driver.get(URL);
	    
	    	
   LoginPage login = new LoginPage(driver);
	         login.loginToApp(Username, Password);


		System.out.println("Login to Application");
	}
	@AfterMethod(groups = {"smoke", "regression"})
	
	public void AM() {
		  HomePage homePage = new HomePage(driver);
		 // homePage.signoutButton();
		  homePage.logoutButton();
		
		 
		System.out.println("Logout to Application");
	}
	@AfterClass(groups = {"smoke", "regression"})
	public void AC() {
		  driver.quit();
		System.out.println("Close the Browser");
	}
	@AfterTest(groups = {"smoke", "regression"})
	public void AT() {
		System.out.println("Parallel execution close");
	}
	@AfterSuite(groups = {"smoke", "regression"})
	public void AS() {
		System.out.println("DataBase connection close");
	}

}
