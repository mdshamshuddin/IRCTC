package Objects;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

public class Passanger_details extends BasePage {

	public Passanger_details(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(xpath = "//input[@placeholder='Passenger Name']")
	WebElement Passanger_name;
	@FindBy(xpath = "//input[@formcontrolname='passengerAge']")
	WebElement age_txt;
	@FindBy(xpath = "//select[@formcontrolname='passengerGender']")
	WebElement gender_DD;
	@FindBy(xpath = "//select[@formcontrolname='passengerBerthChoice']")
	WebElement preference_DD;
	@FindBy(xpath = "//label[normalize-space()='Consider for Auto Upgradation.']")
	WebElement auto_upgradation;
	@FindBy(xpath = "//p-radiobutton[@id='2']//div[@role='radio']")
	WebElement Pay_through_BHIM;
	@FindBy(xpath = "//button[@class='train_Search btnDefault']")
	WebElement continue_btn1;

	public void passanger_name(String name) {
		Passanger_name.sendKeys(name);
		Passanger_name.sendKeys(Keys.ARROW_DOWN);
	}

	public void age(String age) {
		age_txt.sendKeys(age);
	}

	public void gender_DD(String gender) {
		Select genderDD = new Select(gender_DD);
		genderDD.selectByVisibleText(gender);
	}

	public void preference_DD(String preference) {
		Select preferenceDD = new Select(preference_DD);
		preferenceDD.selectByVisibleText(preference);
	}

	public void auto_Upgradation() {
		auto_upgradation.click();
	}
	public void pay_through() throws Exception {
		Pay_through_BHIM.click();
		Thread.sleep(2000);
	}

	public void continue_btn_1() {
		continue_btn1.click();
	}

}
