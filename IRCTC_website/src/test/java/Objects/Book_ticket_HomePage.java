package Objects;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Book_ticket_HomePage extends BasePage {

	public Book_ticket_HomePage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(xpath = "disha-banner-close")
	WebElement dikhsa_close;
	@FindBy(xpath = "(//span[@style='font-weight: bold;'])")
	WebElement WelcomeNOTE;

	@FindBy(xpath = "//input[@aria-controls='pr_id_1_list']")
	WebElement From_Adrs;
	@FindBy(xpath = "//input[@aria-controls='pr_id_2_list']")
	WebElement To_adrs;
	@FindBy(xpath = "//div[@class='ng-tns-c65-74 ui-dropdown ui-widget ui-state-default ui-corner-all']")
	WebElement category_Quota;
	@FindBy(xpath = "//input[@class='ng-tns-c58-10 ui-inputtext ui-widget ui-state-default ui-corner-all ng-star-inserted']")
	WebElement calendar;
	@FindBy(xpath = "//button[normalize-space()='Search']")
	WebElement Search_btn;

	@FindBy(xpath = "//p-dropdown[@id='journeyClass']")
	WebElement Train_class;

	
	public void Diksha_close() {
		dikhsa_close.click();
	}

	public String WelcomeNote() throws Exception {
		return WelcomeNOTE.getText();
	}

	public WebElement From_address(String from) {
		From_Adrs.sendKeys(from);
		return From_Adrs  ;

	}

	public WebElement To_address(String To) {
		To_adrs.sendKeys(To);
		return To_adrs;
	}

	public void Category_DropDown(String VisibleText) {
		Select dropdown = new Select(category_Quota);
		dropdown.selectByVisibleText(VisibleText);
	}

	public void Calendar() throws Exception {
		LocalDate tomorrow = LocalDate.now().plusDays(10);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		String formatteddate = tomorrow.format(formatter);
		WebElement dateInputField = calendar;

		dateInputField.sendKeys(Keys.CONTROL + "a", Keys.DELETE);
		dateInputField.sendKeys(formatteddate, Keys.ENTER);
		dateInputField.sendKeys(Keys.ENTER);
		Thread.sleep(2000);

		calendar.getText();
		return;
	}
	public void searchBtn() throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.elementToBeClickable(Search_btn));
		// Search_btn.click();
		Thread.sleep(3000);
	}

	public void Class_DropDown(String text) {
		Select dropdown = new Select(Train_class);
		dropdown.selectByVisibleText(text);

	}


}
