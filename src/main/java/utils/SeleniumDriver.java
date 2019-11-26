package utils;



import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;


public class SeleniumDriver {

    private static SeleniumDriver seleniumDriver;
 
    //initialize webdriver
  //Singleton design pattern used: Private static variable of the same class that is the only instance of the class.
    private static WebDriver driver;
    
    //initialize timeouts
    //Singleton design pattern used: Private static variable of the same class that is the only instance of the class.
    private static WebDriverWait waitDriver;
    public final static int TIMEOUT = 30;
    public final static int PAGE_LOAD_TIMEOUT = 50;

    //Singleton design pattern used for driver constructor: Private constructor to restrict instantiation of the class from other classes
    private  SeleniumDriver() {

    	System.setProperty("webdriver.chrome.driver", "C:\\Users\\gsume\\selenium-java-3.141.59\\chromedriver.exe");
    	driver = new ChromeDriver();
        driver.manage().window().maximize();
        waitDriver = new WebDriverWait(driver, TIMEOUT);
        driver.manage().timeouts().implicitlyWait(TIMEOUT, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
        String windowhandle=driver.getWindowHandle();
        System.out.println("Window ->"+windowhandle);
       
    }

    public static void openPage(String url) {
    	System.out.println(url);
    	System.out.println(driver);
        driver.get(url);
    }

    //Public static method that returns the instance of the class, this is the global access point for outer world to get the instance of the singleton class.
    public static WebDriver getDriver() {
        return driver;
    }

    //Lazy initialization method to implement Singleton pattern creates the instance in the global access method. This is not thread-safe. We can use synchronized keyword. 
    public static void setUpDriver() {
       if (seleniumDriver == null)
            seleniumDriver = new SeleniumDriver();
      
    }

    public static void tearDown() {
        if (driver != null) {
            driver.close();
            driver.quit();
        }
        seleniumDriver = null;
    }
    public static void waitForPageToLoad()
    {
    	try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}
