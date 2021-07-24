package tests;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import steps.SearchSteps;
import utils.DriverFactory;
import utils.PropertyReader;

public abstract class BaseTest {


    private static WebDriver driver;
    SearchSteps steps;

    public static WebDriver getDriver() {
        return driver;
    }

    @BeforeClass
    public void setUp() {
//        вместо driver = DriverFactory.getDriver(Browser.CHROME);
        driver = DriverFactory.getDriver(PropertyReader.getBrowser());//используем пропертиридер в тестах
//        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);//настроили неявное ожидание: если єл-нт не будет найден, то через 3 сек тест упадет
//        вместо driver.get("https://www.google.com/");
        driver.get(PropertyReader.getUrl());
        driver.manage().window().maximize();
        driver.switchTo().defaultContent();
        steps = new SearchSteps();//инициализвция переменной steps
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }

    @AfterMethod
    public void goBack() {
        driver.navigate().back();

    }

    @DataProvider(name = "dP1")
    public Object[][] dataProviderMethod() {
        return new Object[][]{{"selenium webdriver"}/*, {"selenium javascript"}*/};
    }
}
