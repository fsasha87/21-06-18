package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import java.io.File;

public class DriverFactory {
    private final static String DRIVER_PATH = "D:/IT/";
    private static WebDriver driver;

    public static WebDriver getDriver(Browser browser) {//статический метод возвращает созданый объект вебдрайвера, параметром задаем переменную
        File file;
        switch (browser) {
            case CHROME:
                file = new File(DRIVER_PATH + "Chromedriver.exe");
                System.setProperty("webdriver.chrome.driver", file.getAbsolutePath());
                driver = new ChromeDriver();
                break;
            case IE:
                file = new File(DRIVER_PATH + "IEDriverServer.exe");
                System.setProperty("webdriver.ie.driver", file.getAbsolutePath());
                driver = new InternetExplorerDriver();
                break;
        }
        return driver;//метод возвращает драйвер
    }
}
