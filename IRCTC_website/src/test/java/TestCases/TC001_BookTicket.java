package TestCases;

import java.util.Scanner;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import Objects.Payment_page;
import Objects.Book_ticket_HomePage;
import Objects.Passanger_details;
import Objects.train_Selection;

public class TC001_BookTicket extends BaseClass {
	Scanner scnr;

	@Test
	public void book_ticket() throws Exception {

		// pp.Diksha_close();
		Book_ticket_HomePage pph = new Book_ticket_HomePage(driver);
		String WLCMNote = pph.WelcomeNote();
		Assert.assertEquals(WLCMNote, "Welcome Md Shamshuddin (mdshamshuddin175)");
		Thread.sleep(2000);
		System.out.println(WLCMNote);
		System.out.println(driver.getTitle());
		scnr = new Scanner(System.in);
		System.out.println("Enter from address");
		String fromadd = scnr.nextLine();
		WebElement fromInput = pph.From_address(fromadd);
		fromInput.sendKeys(Keys.ARROW_DOWN);
		Thread.sleep(1000);
		fromInput.sendKeys(Keys.ENTER);

		System.out.println("Enter To address");
		String toadd = scnr.nextLine();
		WebElement toinput = pph.To_address(toadd);
		toinput.sendKeys(Keys.ARROW_DOWN);
		Thread.sleep(1000);
		toinput.sendKeys(Keys.ENTER);
		Thread.sleep(2000);
		FileUtils.copyFile(screenShot, destFile);
		// pp.Category_DropDown("GENERAL");
		pph.Calendar();
		// pp.Class_DropDown("All Classes");

		pph.searchBtn();
		Thread.sleep(5000);
		System.out.println(driver.getTitle());
		train_Selection ts = new train_Selection(driver);
		ts.Train_list();
		ts.coach_list();
		ts.train_date_selection();

		ts.book_now();

		Passanger_details ps = new Passanger_details(driver);
		System.out.println(driver.getTitle());
		ps.passanger_name("Md Shamshuddin");
		ps.age("25");
		ps.gender_DD("Male");
		ps.preference_DD("Upper");
		ps.auto_Upgradation();
		ps.pay_through();
		Thread.sleep(5000);
		FileUtils.copyFile(screenShot, destFile);
		Thread.sleep(2000);
		ps.continue_btn_1();

		Payment_page pp = new Payment_page(driver);
		System.out.println(driver.getTitle());
		pp.tkt_details();
		pp.payment_captcha();
		pp.continue_btn_2();
		Thread.sleep(5000);
		FileUtils.copyFile(screenShot, destFile);
		Thread.sleep(2000);
		pp.PayAndBack_btn();
	}
}
