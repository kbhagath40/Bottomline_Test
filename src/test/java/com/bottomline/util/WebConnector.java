package com.bottomline.util;

import java.io.FileInputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WebConnector {
	
	WebDriver driver;
	Properties OR = null;
	FileInputStream ip = null;
	static WebConnector w;
	
	public WebConnector() {
		if (OR == null) {
			try {
				OR = new Properties();
				ip = new FileInputStream(
						System.getProperty("user.dir") + "/src/test/java/com/bottomline/config/OR.properties");
				OR.load(ip);

			} catch (Throwable t) {
				System.err.println("Error in loading files");
			}
		}
	}

	public void OpenBrowser(String browser) {
		if (browser.equalsIgnoreCase("Mozilla")) {
			System.setProperty("webdriver.gecko.driver", "/Users/Koraboina/Downloads/geckodriver");// Please download geckodriver and change this path of geckodriver in your local system
																										// Please download geckodriver v0.14.0 version from https://github.com/mozilla/geckodriver/releases
																								
			driver = new FirefoxDriver();
		} else if (browser.equalsIgnoreCase("IE")) {
			System.setProperty("webdriver.ie.driver", "path of the ie driver in your local system");// As iam using mac, idon't have access to download ie driver, please download ie driver from https://www.seleniumhq.org
																									
			driver = new InternetExplorerDriver();
		} else if (browser.equalsIgnoreCase("Chrome")) {
			System.setProperty("webdriver.chrome.driver", "/Users/Koraboina/Downloads/chromedriver");// Please download chromedriver and change this path of chromedriver in your local system
																										// Please download chromedriver 2.26 version from https://chromedriver.storage.googleapis.com/index.html?path=2.26/
			driver = new ChromeDriver();
		}
		// driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	public void navigate(String url) {
		driver.get(OR.getProperty(url));

	}

	public void type(String property, String text) {
		driver.findElement(By.xpath(OR.getProperty(property))).sendKeys(text);
	}

	public void explicit_Wait(String locator) {
		WebDriverWait wait = new WebDriverWait(driver, 5);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(OR.getProperty(locator))));
	}

	public void click(String locator) {

		driver.findElement(By.xpath(OR.getProperty(locator))).click();
	}

	public void clearText(String identifier) {
		driver.findElement(By.xpath(OR.getProperty(identifier))).clear();
	}

	public void selectValue(String identifier, String text) {
		Select dropdown = new Select(driver.findElement(By.xpath(OR.getProperty(identifier))));
		dropdown.selectByVisibleText(text);
	}

	public void clickOnLink(String linkText) {
		driver.findElement(By.linkText(OR.getProperty(linkText)));
	}

	public void acceptAlert() {
		Alert alt = driver.switchTo().alert();
		alt.accept();
	}

	public void refresh() {
		driver.navigate().refresh();
	}

	public void verify(String prop, String expectedMessage) throws InterruptedException {
		String actualResult = driver.findElement(By.xpath(OR.getProperty(prop))).getAttribute("value");
		Assert.assertTrue(actualResult.contains(expectedMessage));
		System.out.println("The first name is Equal to " + expectedMessage);

	}

	public boolean isElementPresent(String objectName) {
		int count = driver.findElements(By.xpath(objectName)).size();
		if (count == 0)
			return false;
		else
			return true;
	}

	public void explicitWait(String locator) {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator)));

	}

	public void closeBrowser() {
		driver.quit();
	}

	public static WebConnector getInstance() {
		if (w == null) {
			w = new WebConnector();
			return w;
		} else {
			return w;
		}
	}

}
