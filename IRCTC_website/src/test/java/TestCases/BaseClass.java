package TestCases;

import java.io.File;
import java.time.Duration;
import java.util.Scanner;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import Objects.HomePage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {
	public WebDriver driver;
	
	File screenShot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
	File destFile = new File("./filename\\ScreenShot" + System.currentTimeMillis() + ".png");
	@BeforeClass
	public void Setup() {
		WebDriverManager.chromedriver().setup();
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");
		driver = new ChromeDriver(options);
		driver.manage().window().maximize();
		driver.get("https://www.irctc.co.in/nget/train-search");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
	}
	@AfterClass
	public void TearDown() {
		driver.close();
		driver.quit();
	}

	@BeforeMethod
	public void Login() throws InterruptedException {
		HomePage hp = new HomePage(driver);
		hp.Loginbtn();
		hp.UserName("Username");
		hp.Password("password");
		
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter the CAPTCHA text displayed on the screen:");
		String captchaText =sc.nextLine();
		System.out.println(captchaText);
		Thread.sleep(2000);
		hp.Captcha_txt(captchaText);
		hp.SignIn();
		sc.close();
	}
	@AfterMethod
	public void Logout() {
		HomePage hp = new HomePage(driver);
		hp.Logout();
	}
	
	
}
