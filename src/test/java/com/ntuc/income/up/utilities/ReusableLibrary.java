package com.ntuc.income.up.utilities;

import com.cucumber.listener.Reporter;
import cucumber.api.Scenario;
import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.NoSuchElementException;
import java.util.function.Function;

import static java.lang.Boolean.FALSE;
import static java.lang.String.format;
import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static java.util.concurrent.TimeUnit.SECONDS;

public class ReusableLibrary {

	private WebDriver 	_driver;

	String path = System.getProperty("user.dir") + "\\src\\test\\java\\GlobalConfig.xml";
	public int iMaxTimeout = 60;

	public ReusableLibrary(WebDriver driver) {
		this._driver = driver;
	}

	/**
	 * @description: Click on an element
	 * @author: Arunava
	 */
	public void click(WebElement wb) {
		try {
			wb.click();
		} catch (Exception e) {
			Assert.fail("Cannot click on the element");
		}
	}

	/**
	 * @description: Get Text
	 * @author: Arunava
	 */
	public void sendKeys(WebElement wb, String text) {
		try {
			wb.clear();
			wb.sendKeys(text);
		} catch (Exception e) {
			Assert.fail("Cannot enter text:: " + text);
		}
	}

	/**
	 * @description: Mouse Hover
	 * @author: Arunava
	 */
	public void HoverMouse(WebElement wb) {
		try {
			Actions action = new Actions(_driver);
			action.moveToElement(wb).build().perform();
		} catch (Exception e) {
			Assert.fail("Cannot hover on the element");
		}
	}

	/**
	 * @description: Explicit Wait
	 * @author: Arunava
	 */
	public boolean isUIObjectReady(WebElement wb) {
		boolean bReturn = false;
		// this.driver=driver;
		try {
			WebDriverWait wait = new WebDriverWait(_driver, iMaxTimeout);
			WebElement field = wait.until(ExpectedConditions.visibilityOf(wb));
			bReturn = true;
		} catch (NoSuchElementException ne) {
			Assert.fail("Element not found");
		} catch (Exception e) {
			bReturn = false;
			Assert.fail("Element not found");
		}
		return bReturn;
	}

	/**
	 * @description: Check whether page is loaded
	 * @author: Arunava
	 */
	public boolean pageSync() {
		boolean bActionStatus = false;
		int icount = 50;
		try {

			for (int i = 0; i < icount; i++) {
				boolean iresult = webpageState();
				Thread.sleep(1000);
				if (iresult) {
					bActionStatus = true;
					break;
				}
			}
		} catch (Exception e) {
			bActionStatus = false;
		}
		return bActionStatus;

	}

	public boolean webpageState() {
		boolean bActionStatus = false;
		try {
			String state = ((JavascriptExecutor) _driver).executeScript("return document.readyState").toString();

			if (state.equals("complete")) {
				bActionStatus = true;
			} else {
				bActionStatus = false;
			}
		} catch (Exception e) {
			bActionStatus = false;
		}
		return bActionStatus;
	}

	/**
	 * @description: Verify whether page contains HTTP error
	 * @author: Arunava
	 */
	public boolean validateHttpPageError(String geturl) {
		boolean toReturn = true;
		// ########## 400 ################
		int responsecode = 0;
		try {

			this.pageSync();
			URL url = new URL(geturl);
			HttpURLConnection urlConn = (HttpURLConnection) url.openConnection();
			urlConn.connect();
			responsecode = urlConn.getResponseCode();
			if (HttpURLConnection.HTTP_OK == responsecode) {
				// report.updateTestLog("NO HTTP ERROR in the page for:: " +
				// geturl, "PASS");
				toReturn = true;

			} else {

				toReturn = false;
				Assert.fail("HTTP error:: " + responsecode);
			}

		} catch (Exception e) {
			toReturn = false;
			Assert.fail("HTTP error:: " + responsecode);
			// report.updateTestLog("400 OR 500 Error", "There is no 400/500
			// category Error displayed in the page",Status.PASS);
		}

		return toReturn;
	}
	/**
	 * @description: Enter text character by character in textbox
	 * @author: Arunava
	 */
	public void sendKeysByChar(WebElement wb, String text) {
		try {
			for (int i = 0; i < text.length(); i++) {
				char c = text.charAt(i);
				String s = new StringBuilder().append(c).toString();
				wb.sendKeys(s);
				Thread.sleep(1000);
			}
		} catch (Exception e) {
			Assert.fail(e.getMessage());
		}
	}

	public String generaterandomNumber() {
		Date dNow = new Date();
		SimpleDateFormat ft = new SimpleDateFormat("yyMMddhhmmssMs");
		String datetime = ft.format(dNow);
		return datetime;
	}

	/**
	 * @description: Capture Screenshot
	 * @author: Arunava
	 */
	public void captureScreenshot(Scenario scenario) {
		String screenshotName = scenario.getName().replaceAll(" ", "_");
		try {
			// This takes a screenshot from the driver at save it to the specified location
			TakesScreenshot scrShot = ((TakesScreenshot) _driver);
			File SrcFile = scrShot.getScreenshotAs(OutputType.FILE);
			// Building up the destination path for the screenshot to save
			// Also make sure to create a folder 'screenshots' with in the cucumber-report
			// folder
			File destinationPath = new File(
					System.getProperty("user.dir") + "/target/cucumber-reports/screenshots/" + screenshotName + ".png");

			// Copy taken screenshot from source location to destination location
			FileUtils.copyFile(SrcFile, destinationPath);

			// This attach the specified screenshot to the test
			Reporter.addScreenCaptureFromPath(destinationPath.toString());
		} catch (IOException e) {
		}
	}

	public void WaitForElementToLoad(WebDriver driver,String Xpath) {
		WebDriverWait wait = new WebDriverWait(driver,25);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(Xpath)));
	}

	public void WaitForElementToLoad(WebDriver driver, WebElement element) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, 25);
			wait.until(ExpectedConditions.visibilityOf(element));
		}catch (Exception e) {
			CommonUtils.LOGGER.error(format("Element %s is not loaded", element), e);
		}
	}

	public Boolean isElemVisible(WebElement element) {
		try {
			return element == null ? FALSE : defaultWait().until(expectedConditions(element.isDisplayed()));
		} catch (Exception e) {
			CommonUtils.LOGGER.error(format("Element %s is not visible", element), e);
			return FALSE;
		}
	}

	private Wait<WebDriver> defaultWait() {
		return fluentWait(getTimeout());
	}

	private Wait<WebDriver> fluentWait(long timeout) {
		return new FluentWait<WebDriver>(_driver).withTimeout(Duration.ofSeconds(100))
				.pollingEvery(Duration.ofMillis(timeout)).ignoring(NoSuchElementException.class);
	}

	private int getTimeout() {
		return Integer.parseInt("30");
	}

	private Function<WebDriver, Boolean> expectedConditions(Boolean condition) {
		return driver -> condition;
	}
}
