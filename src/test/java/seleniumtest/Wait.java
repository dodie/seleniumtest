//package seleniumtest;
//
//import java.util.concurrent.TimeUnit;
//import java.util.function.Consumer;
//
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.support.ui.WebDriverWait;
//
//import com.google.common.base.Function;
//import com.google.common.base.Predicate;
//
//public class Wait {
//	private static final long EXPLICIT_WAIT_TIMEOUT_IN_SECONDS = 7L;
//	private static final long IMPLICIT_WAIT_TIMEOUT_IN_SECONDS = 2L;
//	private static final long WAIT_CHECK_POLLINTERVAL = 100L;
//	
//	private final WebDriver webDriver;
//	
//	private Wait(WebDriver webDriver) {
//		this.webDriver = webDriver;
//	}
//	
//	public static Wait on(WebDriver webDriver) {
//		return new Wait(webDriver);
//	}
//	
//	public static void setImplicitWait(WebDriver webDriver) {
//		webDriver.manage().timeouts().implicitlyWait(Wait.IMPLICIT_WAIT_TIMEOUT_IN_SECONDS, TimeUnit.SECONDS);
//	}
//
//	public void untilTrue(Predicate<WebDriver> predicate) {
//		WebDriverWait wdw = new WebDriverWait(webDriver, EXPLICIT_WAIT_TIMEOUT_IN_SECONDS, WAIT_CHECK_POLLINTERVAL);
//		wdw.ignoring(Throwable.class).until(predicate);
//	}
//
//	public void untilTrue(Predicate<WebDriver> predicate, long timeInSeconds) {
//		WebDriverWait wdw = new WebDriverWait(webDriver, timeInSeconds, WAIT_CHECK_POLLINTERVAL);
//		wdw.ignoring(Throwable.class).until(predicate);
//	}
//	
//	public <V> V until(Function<WebDriver, V> function) {
//		WebDriverWait wdw = new WebDriverWait(webDriver, EXPLICIT_WAIT_TIMEOUT_IN_SECONDS, WAIT_CHECK_POLLINTERVAL);
//		return wdw.ignoring(Throwable.class).until(function);
//	}
//
//	public <V> V until(Function<WebDriver, V> function, long timeInSeconds) {
//		WebDriverWait wdw = new WebDriverWait(webDriver, timeInSeconds, WAIT_CHECK_POLLINTERVAL);
//		return wdw.ignoring(Throwable.class).until(function);
//	}
//
//	public void untilSuccess(Consumer<WebDriver> consumer) {
//		WebDriverWait wdw = new WebDriverWait(webDriver, EXPLICIT_WAIT_TIMEOUT_IN_SECONDS, WAIT_CHECK_POLLINTERVAL);
//		wdw.ignoring(Throwable.class).until((Function<? super WebDriver, Boolean>) d -> { consumer.accept(d); return true; });
//	}
//}