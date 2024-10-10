package Monolithic_code;
import java.io.File;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

import javax.swing.text.Utilities;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.devtools.v125.page.model.Screenshot;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.internal.Utils;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Monolithic_code {

	WebDriver driver;
	Scanner scnr = new Scanner(System.in);
	
	File screenShot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
	File destFile = new File("./filename\\ScreenShot" + System.currentTimeMillis() + ".png");

	By login_btn = By.xpath("//a[@class='search_btn loginText ng-star-inserted']");
	By username = By.xpath("//input[@formcontrolname='userid']");
	By password = By.xpath("//input[@formcontrolname='password']");
	By captcha = By.xpath("//input[@id='captcha']");
	By capIMG = By.xpath("//img[@class='captcha-img']");
	By dikhsa_close = By.id("disha-banner-close");
	By sign_in = By.xpath("//button[@style=\"padding: 10px 14px;\"]");

	@Test(priority = 1)
	public void setUp() {
		WebDriverManager.chromedriver().setup();
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");
		driver = new ChromeDriver(options);
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		driver.get("https://www.irctc.co.in/nget/train-search");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

	}

	 @Test(priority = 2)
	public void login() throws Exception {
		//driver.findElement(dikhsa_close).click();
		//Thread.sleep(2000);
		driver.findElement(login_btn).click();
		driver.findElement(username).sendKeys("mdshamshuddin175");
		Thread.sleep(2000);

		driver.findElement(password).sendKeys("Shams@12345");
		driver.findElement(captcha).click();

		System.out.println("Enter the CAPTCHA text displayed on the screen:");
		String captchaText = scnr.nextLine();
		System.out.println(captchaText);
		Thread.sleep(2000);

		driver.findElement(captcha).sendKeys(captchaText);
		Thread.sleep(2000);
		driver.findElement(sign_in).click();
	}

	By WelcomeNOTE = By.xpath("(//span[@style='font-weight: bold;'])");

	 @Test(priority = 3, dependsOnMethods = "login")
	public void logsuccess() throws Exception {
		String WCMnote = driver.findElement(WelcomeNOTE).getText();
		Assert.assertEquals(WCMnote, "Welcome Md Shamshuddin (mdshamshuddin175)");
		Thread.sleep(2000);
		System.out.println(WCMnote);
//		System.out.println(driver.getTitle());
//		System.out.println(driver.getCurrentUrl());
	}

	By from = By.xpath("//input[@aria-controls='pr_id_1_list']");
	By to = By.xpath("//input[@aria-controls='pr_id_2_list']");
	By calendar = By.xpath(
			"//input[@class='ng-tns-c58-10 ui-inputtext ui-widget ui-state-default ui-corner-all ng-star-inserted']");
	By category = By.id("journeyQuota");
	By dropDown = By.xpath("(//div[@class='form-group'])[2]");
	By cat_DD= By.xpath("//span[@class='ng-tns-c65-78 ui-dropdown-label ui-inputtext ui-corner-all ng-star-inserted']");
	
	By searchbtn = By.xpath("(//button[@class='search_btn train_Search'])[1]");
	@Test(priority = 4)
	public void book_ticket() throws Exception {
		System.out.println("Enter from address");
		String fromadd = scnr.nextLine();

		System.out.println("Enter To below-");
		String toadd = scnr.nextLine();


		WebElement fromInput = driver.findElement(from);
		fromInput.sendKeys(fromadd, Keys.ARROW_DOWN);
		Thread.sleep(2000);
		fromInput.sendKeys(Keys.ENTER);

		WebElement toinput = driver.findElement(to);
		toinput.sendKeys(toadd, Keys.ARROW_DOWN);
		Thread.sleep(2000);
		toinput.sendKeys(Keys.ENTER);
		Thread.sleep(2000);

//		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//		WebElement DD = wait.until(ExpectedConditions.visibilityOfElementLocated(category));

		Thread.sleep(2000);

		LocalDate tomorrow = LocalDate.now().plusDays(10);
		DateTimeFormatter fromatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		String formatteddate = tomorrow.format(fromatter);
		WebElement dateInputField = driver.findElement(calendar);

		dateInputField.sendKeys(Keys.CONTROL + "a", Keys.DELETE);
		dateInputField.sendKeys(formatteddate);
		Thread.sleep(2000);
		FileUtils.copyFile(screenShot, destFile);
		driver.findElement(searchbtn).click();
		
//		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//		WebElement DD = wait.until(ExpectedConditions.elementToBeClickable(cat_DD));

//		WebElement quota = driver.findElement(cat_DD);
//		Select DDown = new Select(DD);
//		Thread.sleep(2000);
//		DDown.selectByVisibleText("GENERAL");
	}



	By train_name = By.xpath("//div[@class='col-sm-5 col-xs-11 train-heading']");
	By coaches = By.xpath("//td[@class='ng-star-inserted']");
	By trainBlocks = By.xpath("//div[@class='form-group no-pad col-xs-12 bull-back border-all']");
	By train_select = By.xpath(
			"(//div[@class='form-group no-pad col-xs-12 bull-back border-all']//strong[contains(text(), ' LUR YPR EXPRESS (16584)')]/ancestor::div[contains(@class,'col-sm-5 col-xs-11 train-heading')]/following::div[contains(@class,'white-back col-xs-12 ng-star-inserted')])[1]//strong[contains(text(),'Sleeper (SL)')]");

	By train_date_selection =By.xpath("(//td[@class='link ng-star-inserted'])[1]");
	By  book_now_btn = By.xpath("//button[@class='btnDefault train_Search ng-star-inserted']");
	
	@Test(priority = 5)
	public void train_selection() throws Exception {

		List<WebElement> trainName = driver.findElements(train_name);
		Thread.sleep(2000);
		for (WebElement trainElement : trainName) {
			String trainnames = trainElement.getText();
			System.out.println("Trains available for the journey : " + trainnames);
			Thread.sleep(500);
		}
		System.out.println("Enter the train name : ");
		String trainname = scnr.nextLine();

//================================================
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		String dynamicXPath = "(//div[@class='form-group no-pad col-xs-12 bull-back border-all']//strong[contains(text(), '"
				+ trainname
				+ "')]/ancestor::div[contains(@class,'col-sm-5 col-xs-11 train-heading')]/following::div[contains(@class,'white-back col-xs-12 ng-star-inserted')])[1]";

		List<WebElement> elements = wait
				.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(dynamicXPath)));

		if (elements.size() > 0) {
			System.out.println("Selected element details: ");
			for (WebElement element : elements) {
				System.out.println(element.getText());
				System.out.println();
				String blocksOfTrain = element.getText();
				System.out.println("Enter the coach you want to select : ");
				String SelectBlock = scnr.nextLine();
				if (blocksOfTrain.contains(SelectBlock)) {
					WebElement blockClick = driver.findElement(By.xpath(
							"(//div[@class='form-group no-pad col-xs-12 bull-back border-all']//strong[contains(text(), '"+trainname+"')]/ancestor::div[contains(@class,'col-sm-5 col-xs-11 train-heading')]/following::div[contains(@class,'white-back col-xs-12 ng-star-inserted')])[1]//strong[contains(text(),'"
									+ SelectBlock + "')]"));
					wait.until(ExpectedConditions.elementToBeClickable(blockClick));
					blockClick.click();
				}
				//(//div[@class='form-group no-pad col-xs-12 bull-back border-all']//strong[contains(text(),'BASAVA EXPRESS (17308)')]/ancestor::div[contains(@class,'col-sm-5 col-xs-11 train-heading')]/following::div[contains(@class,'white-back col-xs-12 ng-star-inserted')])[1]//strong[contains(text(),'Sleeper (SL)')]//ancestor::div
			}
		}
		else {
			System.out.println("No element found for the input: " + trainname);
		}
		driver.findElement(train_date_selection).click();
		
		FileUtils.copyFile(screenShot, destFile);
		
		Thread.sleep(2000);
		driver.findElement(book_now_btn).click();
			
	}
	
	
	By Passanger_name = By.xpath("//input[@placeholder='Passenger Name']");
	By age_txt = By.xpath("//input[@formcontrolname='passengerAge']");
	By gender_DD =By.xpath("//select[@formcontrolname='passengerGender']");
	By preference_DD =By.xpath("//select[@formcontrolname='passengerBerthChoice']");
	By auto_upgradation = By.xpath("//label[normalize-space()='Consider for Auto Upgradation.']");
	By Pay_through_BHIM = By.xpath("//p-radiobutton[@id='2']//div[@role='radio']");
	By continue_btn1 = By.xpath("//button[@class='train_Search btnDefault']");
	By continue_btn2 = By.xpath("//button[@class='btnDefault train_Search']");
	By travel_confirmation =By.xpath("//div[@class='train-Header tbis-height']");
	By Payment_captcha = By.xpath("//input[@id='captcha']");
	By payANDBack_btn = By.xpath("//button[@class='btn btn-primary hidden-xs ng-star-inserted']");
	
	
	@Test(priority = 6)
	public void passangerDetails() throws Exception {
		
		driver.findElement(Passanger_name).sendKeys("Md shamshuddin" );
		Thread.sleep(2000);
		driver.findElement(Passanger_name).sendKeys(Keys.ARROW_DOWN);
		driver.findElement(age_txt).sendKeys("25");
		WebElement DD1 = driver.findElement(gender_DD);
		Select gender = new Select(DD1);
		gender.selectByVisibleText("Male");
		
		WebElement DD2 = driver.findElement(preference_DD);
		Select preference = new Select(DD2);
		preference.selectByVisibleText("Upper");
		
		
		driver.findElement(auto_upgradation).click();
		driver.findElement(Pay_through_BHIM).click();
		Thread.sleep(2000);
		driver.findElement(continue_btn1).click();
		String tktDetails = driver.findElement(travel_confirmation).getText();
		System.out.println(tktDetails);
		System.out.println("Enter captcha displayed on reivew journey :");
		String captcha = scnr.nextLine();
		driver.findElement(Payment_captcha).sendKeys(captcha);
		Thread.sleep(2000);
		driver.findElement(continue_btn2).click();
		FileUtils.copyFile(screenShot, destFile);
		Thread.sleep(2000);
		driver.findElement(payANDBack_btn).click();
		
	}
	@Test(priority = 7)
	public void teardown() {
		driver.close();
	}
}
