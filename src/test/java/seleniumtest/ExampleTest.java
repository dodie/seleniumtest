package seleniumtest;

import java.net.URL;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class ExampleTest {
	
	
	@Test
	public void print_env_variables_and_load_google_a_few_times() throws Exception {
		
		System.out.println("The env:");
		
		Map<String, String> env = System.getenv();
        for (String envName : env.keySet()) {
            System.out.format("%s=%s%n",
                              envName,
                              env.get(envName));
        }
		
		System.out.println("----");
		System.out.println("");
		
		for (int i = 0; i < 50; i++) {
			System.out.println("Loading page...");
			WebDriver driver = initWebDriver();
			driver.get("https://www.google.com");
			
			WebElement webElement = driver.findElement(By.tagName("input"));
			System.out.println("Page loaded. Iteration: " + i + ", id of found element: " + webElement.getAttribute("id"));
			
			driver.close();
		}
	}
	
//	@Test
	public void localTest() throws Exception {
		URL location = ExampleTest.class.getProtectionDomain().getCodeSource().getLocation();
		String baseLocation = "file://" + location.getFile() + "test.html";
		System.out.println(baseLocation);
		
		WebDriver driver = initWebDriver();
		
		long start = System.currentTimeMillis();
		driver.get(baseLocation);
		System.out.println("Page loaded in: " + (System.currentTimeMillis() - start));
		
		if (driver instanceof ChromeDriver) {
			System.out.println("Waiting for 2 more seconds.");
			Thread.sleep(2000L);
		}
		
		start = System.currentTimeMillis();
		
		for (int i = 0; i < 20; i++) {
			List<WebElement> items = driver.findElements(By.tagName("h1"));
			System.out.println("Reading dom:" + items.size());
			System.out.println("elapsed: " + (System.currentTimeMillis() - start));
		}
		
		System.out.println("Dom read avg. time: " + (((System.currentTimeMillis() - start)) / 20));
		
		driver.close();
	}
	
//	@Test
	public void remoteJsTest() throws Exception {
		remotePerf("http://dromaeo.com/?dromaeo|sunspider|v8");
	}
	
//	@Test
	public void remoteDomTest() throws Exception {
		remotePerf("http://dromaeo.com/?dom|jslib|cssquery");
	}
	
	public void remotePerf(String baseLocation) throws Exception {
		System.out.println("Perftesting on " + baseLocation);
		
		WebDriver driver = initWebDriver();
		
		long start = System.currentTimeMillis();
		driver.get(baseLocation);
		System.out.println("Page loaded in: " + (System.currentTimeMillis() - start));
		
		System.out.println("Waiting for 2 more seconds.");
		Thread.sleep(2000L);
		
		driver.findElement(By.id("pause")).click();
		
		System.out.println("Waiting for 10 minutes to let some tests run.");
		Thread.sleep(1000L * 60L * 10);
		
		stopTests(driver, 50);
		
		List<WebElement> tests = driver.findElements(By.className("test"));
		
		System.out.println("TESTS:");
		System.out.println("------");
		for (WebElement test : tests) {
			System.out.println(test.getText());
		}
		System.out.println("------");
		
		driver.close();
	}
	
	private void stopTests(WebDriver driver, int tries) {
		if (tries < 0) {
			System.out.println("Failed to stop tests.");
			return;
		}
		try {
			driver.findElement(By.id("pause")).click();
		} catch (Exception e) {
			System.out.println("Failed to stop, retry: " + tries);
			stopTests(driver, tries - 1);
		}
	}

	private static WebDriver initWebDriver()
	{
//		FirefoxProfile firefoxProfile = new FirefoxProfile();
//		firefoxProfile.setPreference("layers.acceleration.disabled", true);
//
//		DesiredCapabilities desiredCapabilities = DesiredCapabilities.firefox();
//		desiredCapabilities.setCapability(FirefoxDriver.PROFILE, firefoxProfile);

//		return new FirefoxDriver(desiredCapabilities);
		
		
		System.setProperty("webdriver.chrome.driver", "chromedriver");
		
		return new ChromeDriver();

//		return new ChromeDriver();
//		return new FirefoxDriver();
//		
//		FirefoxProfile profile = new FirefoxProfile();
//		profile.setPreference("intl.accept_languages", formatLocale(Locale.ENGLISH));
//		
//		DesiredCapabilities desiredCapabilities = DesiredCapabilities.firefox();
//		LoggingPreferences logPrefs = new LoggingPreferences();
//        logPrefs.enable(LogType.BROWSER, Level.SEVERE);
//        desiredCapabilities.setCapability(CapabilityType.LOGGING_PREFS, logPrefs);
//		
//		FirefoxDriver webDriver = new FirefoxDriver(new FirefoxBinary(), profile, desiredCapabilities);
//		
//		Wait.setImplicitWait(webDriver);
//		webDriver.manage().window().setSize(new Dimension(1920, 1080));
//		return webDriver;
	}
	
//	private static String formatLocale(Locale locale) {
//		return locale.getCountry().length() == 0
//			? locale.getLanguage()
//			: locale.getLanguage() + "-" + locale.getCountry().toLowerCase();
//	}

}
