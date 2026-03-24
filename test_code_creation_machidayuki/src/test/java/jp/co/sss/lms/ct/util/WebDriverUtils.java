package jp.co.sss.lms.ct.util;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.io.Files;

/**
 * Webドライバーユーティリティ
 * @author holy
 */
public class WebDriverUtils {

	/** Webドライバ */
	public static WebDriver webDriver;

	/**
	 * インスタンス取得
	 * @return Webドライバ
	 */
	public static void createDriver() {
		System.setProperty("webdriver.chrome.driver", "lib/chromedriver.exe");
		webDriver = new ChromeDriver();
	}
	
	/**
	 * インスタンス終了
	 */
	public static void closeDriver() {
		webDriver.quit();
	}

	/**
	 * 画面遷移
	 * @param url
	 */
	public static void goTo(String url) {
		webDriver.get(url);
		pageLoadTimeout(5);
	}
	
	/**
	 * ページロードタイムアウト設定
	 * @param second
	 */
	public static void pageLoadTimeout(int second) {
		webDriver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(second));
	}
	
	/**
	 * 要素の可視性タイムアウト設定
	 * @param locater
	 * @param second
	 */
	public static void visibilityTimeout(By locater, int second) {
		WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(second));
		wait.until(ExpectedConditions.visibilityOfElementLocated(locater));
	}
	
	/**
	 * 指定ピクセル分だけスクロール
	 * @param pixel
	 */
	public static void scrollBy(String pixel) {
		((JavascriptExecutor) webDriver).executeScript("window.scrollBy(0," + pixel + ");");
	}

	
	/**
	 * 指定位置までスクロール
	 * @param pixel
	 */
	public static void scrollTo(String pixel) {
		((JavascriptExecutor) webDriver).executeScript("window.scrollTo(0," + pixel + ");");
	}

	/**
	 * エビデンス取得
	 * @param instance
	 */
	public static void getEvidence(Object instance) {
		File tempFile = ((TakesScreenshot) webDriver).getScreenshotAs(OutputType.FILE);
		try {
			String className = instance.getClass().getEnclosingClass().getSimpleName();
			String methodName = instance.getClass().getEnclosingMethod().getName();
			Files.move(tempFile, new File("evidence\\" + className + "_" + methodName + ".png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * エビデンス取得（サフィックスあり）
	 * @param instance
	 * @param suffix
	 */
	public static void getEvidence(Object instance, String suffix) {
		File tempFile = ((TakesScreenshot) webDriver).getScreenshotAs(OutputType.FILE);
		try {
			String className = instance.getClass().getEnclosingClass().getSimpleName();
			String methodName = instance.getClass().getEnclosingMethod().getName();
			Files.move(tempFile, new File("evidence\\" + className + "_" + methodName + "_" + suffix + ".png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * タイトルを取得
	 * @return タイトル
	 * @author 町田優希-Case01
	 */
	public static String getTitle() {
		return webDriver.getTitle();
	}
	
	/**
	 * ログインIDを入力
	 * @param inputLoginId
	 * @author 町田優希-Case02
	 */
	public static void setLoginId(String inputLoginId) {
		WebElement loginId = webDriver.findElement(By.id("loginId"));
		loginId.clear();
		loginId.sendKeys(inputLoginId);
	}
	
	/**
	 * パスワードを入力
	 * @param inputPassword
	 * @author 町田優希-Case02
	 */
	public static void setPassword(String inputPassword) {
		WebElement Password = webDriver.findElement(By.id("password"));
		Password.clear();
		Password.sendKeys(inputPassword);
	}
	
	/**
	 * メッセージを文字列で取得
	 * @param serchMessage
	 * @return メッセージ
	 * @author 町田優希-Case02
	 */
	public static String getMessage(String serchMessage) {
		return webDriver.findElement(By.cssSelector(serchMessage)).getText();
	}
	
	/**
	 * 引数のボタンをクリック
	 * @param buttonId
	 * @author 町田優希-Case02
	 */
	public static void clickButton(String buttonId) {
		webDriver.findElement(By.cssSelector(buttonId)).click();
	}
	
	/**
	 * 引数のリンクをクリック
	 * @param liText
	 * @author 町田優希-Case04
	 */
	public static void clickLink(String linkValue) {
		webDriver.findElement(By.linkText(linkValue)).click();
	}
	
	/**
	 * タブを切り替える
	 * @author 町田優希-Case04
	 */
	public static void changeWindow() {
		Object[] windowHandles=webDriver.getWindowHandles().toArray();
		webDriver.switchTo().window((String) windowHandles[1]);
	}

}
