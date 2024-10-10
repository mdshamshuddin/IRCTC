package Objects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage {

	public HomePage(WebDriver driver) {
		super(driver);
	}

	@FindBy(xpath = "//a[@class='search_btn loginText ng-star-inserted']")
	WebElement LoginBtn;
	@FindBy(xpath = "//input[@formcontrolname='userid']")
	WebElement Username_txt;
	@FindBy(xpath = "//input[@formcontrolname='password']")
	WebElement Password_txt;
	@FindBy(xpath = "//input[@id='captcha']")
	WebElement Captcha_txt;
	@FindBy(xpath = "//button[@style='padding: 10px 14px;']")
	WebElement Sign_in_btn;
	@FindBy(xpath = "//a[@aria-label='Click here Logout from application']")
	WebElement Logout_btn;
	
	

	public void Loginbtn() {
		LoginBtn.click();
	}
	public void UserName(String UName) {
		Username_txt.sendKeys(UName);
	}
	public void Password(String Pswrd) {
		Password_txt.sendKeys(Pswrd);
	}
	public void Captcha_txt(String Captcha) {
		Captcha_txt.sendKeys(Captcha);
	}
	public void SignIn() {
		Sign_in_btn.click();
	}
	public void Logout() {
		Logout_btn.click();
	}
	
}
