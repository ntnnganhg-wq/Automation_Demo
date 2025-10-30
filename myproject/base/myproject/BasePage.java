package myproject;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {
	protected WebDriver driver;
	protected WebDriverWait wait;
	
	public BasePage(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	}
	
	public WebElement WaitForVisible(By locator) {
		return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}
	
	public List<WebElement> WaitForListVisible(By locator) {
	    return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
	}
	public WebElement WaitForClickable(By locator) {
		return wait.until(ExpectedConditions.elementToBeClickable(locator));
	}
	
	public void Click (By locator) {
		WaitForClickable(locator).click();
	}
	
	public String GetValueOfElement(By locator) {
		return WaitForVisible(locator).getText();
	}
	
	public void EnterText(By locator, String text) {
		WebElement element = WaitForVisible(locator);
		element.clear();
		element.sendKeys(text);
	}
	
	public void SrollToElement(By locator) {
		WebElement element = driver.findElement(locator);
	    ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
	}
	
	public boolean isElementVisible(By locator) {
		try {
			WaitForVisible(locator);
			return true;
		} catch (TimeoutException e) {
			return false;
		}
	}
}
