import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.FileWriter;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class Vehicle_List {

    protected WebDriver driver;
    public static String url = "https://www.sahibinden.com";

    @Before
    public void setUp() {

        try {

            ChromeOptions capabilities = new ChromeOptions();
            System.setProperty("webdriver.chrome.driver", "C:\\Users\\SedatCan\\Selenium_Sahibinden_araclistesi\\ChromeWebDriver\\chromedriver.exe");// Bu alana driver’ımızın "chromedriver.exe" path’ini koymanız gerekiyor
            driver = new ChromeDriver(capabilities);
            driver.manage().window().maximize();
            driver.manage().deleteAllCookies();
            driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
            driver.manage().timeouts().setScriptTimeout(30, TimeUnit.SECONDS);
            driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
            System.out.println("Driver Başlatıldı");

        } catch (Exception ex) {

            ex.getMessage();
            System.out.println("Driver Başlatılamadı");

        }
    }

    @Test
    public void Vehicle_list() {

        try {

            driver.get(url);
            driver.findElement(By.xpath("//*[@id=\"onetrust-accept-btn-handler\"]")).click();
            driver.findElement(By.xpath("//*[@id=\"container\"]/div[3]/div/aside/div[1]/nav/ul[4]/li[2]/ul/li[17]/label[1]")).click();
            List<WebElement> category = driver.findElements(By.xpath("//*[@id=\"container\"]/div[3]/div/aside/div[1]/nav/ul[4]/li[2]/ul/li/a"));
            FileWriter fwriter = new FileWriter("C:\\Users\\sedatcan\\Selenium_Sahibinden_araclistesi\\src\\test\\java\\Araclistesi.txt", false);
            // ????? soru işaretli yere kendi bilgisayarınızın adınız yazmanız gerekiyor

            for (int i = 0; i < category.size(); i++) {

                int li = i + 1;
                driver.findElement(By.xpath("//*[@id=\"container\"]/div[3]/div/aside/div[1]/nav/ul[4]/li[2]/ul/li[" + li + "]/a")).click();
                List<WebElement> brand = driver.findElements(By.xpath("//*[@id=\"container\"]/div/div[1]/div[1]/div[2]/ul/div/div[1]/li/a/h2"));
                fwriter.write("------------------------------------------------------");
                fwriter.write("\n");
                fwriter.write(driver.findElement(By.xpath("//*[@id=\"container\"]/div/div[1]/div[1]/div[1]/h1")).getText().toUpperCase(Locale.ROOT));
                fwriter.write("\n");
                fwriter.write("------------------------------------------------------");
                fwriter.write("\n");

                for (WebElement webElement : brand) {

                    if (webElement.getText().length() == 0) {

                        driver.findElement(By.xpath("//*[@id=\"container\"]/div/div[1]/div[1]/div[2]/ul")).sendKeys(Keys.ARROW_DOWN);

                    }

                    fwriter.write(webElement.getText());
                    fwriter.write("\n");

                }


                driver.get(url);
                driver.findElement(By.xpath("//*[@id=\"container\"]/div[3]/div/aside/div[1]/nav/ul[4]/li[2]/ul/li[17]/label[1]")).click();

            }

            fwriter.write("\n");
            fwriter.write("------------------------------------------------------");
            fwriter.close();
            driver.quit();

        } catch (Exception ex) {

            ex.getMessage();
            ex.printStackTrace();

        }

    }
}
