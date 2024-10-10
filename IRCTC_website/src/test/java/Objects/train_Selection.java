package Objects;

import java.time.Duration;
import java.util.List;
import java.util.Scanner;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class train_Selection extends BasePage {

	public train_Selection(WebDriver driver) {
		super(driver);
	}
	
	Scanner scnr;
	
	@FindBy(xpath = "//div[@class='col-sm-5 col-xs-11 train-heading']")
	WebElement train_Names;

	@FindBy(xpath = "(//td[@class='link ng-star-inserted'])[1]")
	WebElement train_date_selection_btn;

	@FindBy(xpath = "//button[@class='btnDefault train_Search ng-star-inserted']")
	WebElement Book_now_btn;
	
	public void Train_list() throws Exception {
		List<WebElement> trainName = driver.findElements(By.xpath("//div[@class='col-sm-5 col-xs-11 train-heading']"));
		for (WebElement trainElement : trainName) {
			String trainnames = trainElement.getText();
			System.out.println("Trains available for the journey : " + trainnames);
			Thread.sleep(500);	}

	}

	public void coach_list() {
		System.out.println("Enter the train name : ");
		scnr = new Scanner(System.in);
		String trainname = scnr.nextLine();
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
							"(//div[@class='form-group no-pad col-xs-12 bull-back border-all']//strong[contains(text(), '"
									+ trainname
									+ "')]/ancestor::div[contains(@class,'col-sm-5 col-xs-11 train-heading')]/following::div[contains(@class,'white-back col-xs-12 ng-star-inserted')])[1]//strong[contains(text(),'"
									+ SelectBlock + "')]"));
					wait.until(ExpectedConditions.elementToBeClickable(blockClick));
					blockClick.click();
				}
			}
		} else {
			System.out.println("No element found for the input: " + trainname);
		}
	}

	public void train_date_selection() {
		train_date_selection_btn.click();
	}

	public void book_now() {
		Book_now_btn.click();
	}


}
