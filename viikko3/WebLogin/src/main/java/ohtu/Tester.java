package ohtu;

import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

public class Tester {

    public static void main(String[] args) {
        // WebDriver driver = new ChromeDriver();
        WebDriver driver = new HtmlUnitDriver();

        driver.get("http://localhost:4567");

        sleep(2);

        WebElement element = driver.findElement(By.linkText("login"));
        element.click();

        sleep(2);

        element = driver.findElement(By.name("username"));
        element.sendKeys("pekka");
        element = driver.findElement(By.name("password"));
        element.sendKeys("väärä");
        element = driver.findElement(By.name("login"));

        sleep(2);
        element.submit();

        sleep(3);

        // System.out.println(driver.getPageSource());

        element = driver.findElement(By.linkText("back to home"));
        element.click();
        element = driver.findElement(By.linkText("register new user"));
        element.click();

        element = driver.findElement(By.name("username"));
        Random r = new Random();
        String username = "valtteri" + r.nextInt(10000);
        element.sendKeys(username);
        element = driver.findElement(By.name("password"));
        element.sendKeys("irettlav");
        element = driver.findElement(By.name("passwordConfirmation"));
        element.sendKeys("irettlav");

        element = driver.findElement(By.name("signup"));
        element.click();

        element = driver.findElement(By.linkText("continue to application mainpage"));
        element.click();

        element = driver.findElement(By.linkText("logout"));
        element.click();

        System.out.println(driver.getPageSource());

        driver.quit();
    }

    private static void sleep(int n) {
        try {
            Thread.sleep(n * 1000);
        } catch (Exception e) {
        }
    }
}
