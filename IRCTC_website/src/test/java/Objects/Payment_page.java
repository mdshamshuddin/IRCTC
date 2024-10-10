package Objects;

import java.util.Scanner;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

public class Payment_page extends BasePage {
	Scanner scnr;

	public Payment_page(WebDriver driver) {
		super(driver);
	}


	@FindBy(xpath = "//button[@class='btnDefault train_Search']")
	WebElement continue_btn2;
	@FindBy(xpath = "//div[@class='train-Header tbis-height']")
	WebElement travel_confirmation;
	@FindBy(xpath = "//input[@id='captcha']")
	WebElement Payment_captcha;
	@FindBy(xpath = "//button[@class='btn btn-primary hidden-xs ng-star-inserted']")
	WebElement payANDBack_btn;

	
	public void tkt_details() {
		String tktDetails = travel_confirmation.getText();
		System.out.println(tktDetails);
	}

	public void payment_captcha() throws Exception {
		System.out.println("Enter captcha displayed on reivew journey :");
		String captcha = scnr.nextLine();
		Payment_captcha.sendKeys(captcha);
		Thread.sleep(2000);
	}

	public void continue_btn_2() {
		continue_btn2.click();
	}

	public void PayAndBack_btn() throws InterruptedException {
		Thread.sleep(2000);
		payANDBack_btn.click();
	}
}
