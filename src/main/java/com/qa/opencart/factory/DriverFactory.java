package com.qa.opencart.factory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import com.qa.opencart.utils.JavaScriptUtil;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverFactory {
	public WebDriver driver;
	public static String highlight;
	private OptionsManager optionsManager;

	public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<WebDriver>();

	/**
	 * This method initialize driver
	 * 
	 * @param browserName
	 * @return
	 */

	public WebDriver initDriver(Properties prop) {
		String browserName = prop.getProperty("browser");
		highlight = prop.getProperty("highlight");
		optionsManager = new OptionsManager(prop);

		System.out.println("Browser name is :" + browserName);
		if (browserName.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			// driver = new ChromeDriver(optionsManager.getChromeOptions());
			tlDriver.set(new ChromeDriver(optionsManager.getChromeOptions()));
		} else if (browserName.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			// driver = new FirefoxDriver();
			tlDriver.set(new FirefoxDriver(optionsManager.getFirefoxOptions()));
		} else if (browserName.equalsIgnoreCase("edge")) {
			WebDriverManager.edgedriver().setup();
			// driver = new EdgeDriver();
			tlDriver.set(new EdgeDriver());
		} else if (browserName.equalsIgnoreCase("safari")) {
			// driver = new SafariDriver();
			tlDriver.set(new SafariDriver());
		} else {
			System.out.println("Please pass the correct browser : " + browserName);
		}
		getDriver().manage().deleteAllCookies();
		getDriver().manage().window().maximize();

		getDriver().get(prop.getProperty("url"));

		return getDriver();
	}

	public WebDriver getDriver() {
		return tlDriver.get();
	}

	public Properties init_properties() {
		/**
		 * This method initialize the properties based on the given env name
		 */
		Properties prop = null;
		FileInputStream ip = null;

		String env = System.getProperty("env"); // mvn clean install -Denv="QA"
		try {
			if (env == null) {
				System.out.println("Running on environment : PROD Env...");
				ip = new FileInputStream("./src/test/resources/config/config.properties");
			}

			else {
				System.out.println("Running on Environment :" + env);
				switch (env) {
				case "QA":
					ip = new FileInputStream("./src/test/resources/config/QA.config.properties");
					break;
				case "dev":
					ip = new FileInputStream("./src/test/resources/config/dev.config.properties");
					break;
				case "Stage":
					ip = new FileInputStream("./src/test/resources/config/Stage.config.properties");
					break;
				default:
					System.out.println("No environment found...");
					throw new Exception("ENVNOTFOUNDEXCEPTION");
				}
			}
		}

		catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			prop = new Properties();
			prop.load(ip);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return prop;
	}

	/*
	 * Get screenshot
	 */

	public String getScreenshot() {

		File srcFile = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
		String path = System.getProperty("user.dir") + "\\screenshots\\" + System.currentTimeMillis() + ".png";
		File destination = new File(path);
		try {
			FileUtils.copyFile(srcFile, destination);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return path;

	}
}
