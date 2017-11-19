package ohtu;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.Random;

public class Tester {

    public static void main(String[] args) {
        
        System.setProperty("webdriver.chrome.driver", "chromedriver");
        
        WebDriver driver = new ChromeDriver();
        
        //Kirjautuminen
        
        driver.get("http://localhost:4567");
        
        sleep(2);
        
        WebElement element = driver.findElement(By.linkText("login"));
        element.click();

        sleep(2);

        element = driver.findElement(By.name("username"));
        element.sendKeys("pekka");
        element = driver.findElement(By.name("password"));
        element.sendKeys("akkep");
        element = driver.findElement(By.name("login"));
        
        sleep(2);
        element.submit();

        sleep(3);
        
        //Epäonnist. kirj.
        
        driver.get("http://localhost:4567");
        
        element = driver.findElement((By.linkText("login")));
        element.click();
        
        element = driver.findElement(By.name("username"));
        element.sendKeys("pekka");
        element = driver.findElement(By.name("password"));
        element.sendKeys("pekka");
        element = driver.findElement(By.name("login"));
        
        sleep(2);
        
        element.submit();
        
        sleep(3);
        
        //Uuden tunnuksen luominen
        
        Random rand = new Random();
        int random = rand.nextInt(10000000 - 1 + 1) + 1;
        driver.get("http://localhost:4567");
        
        element = driver.findElement((By.linkText("register new user")));
        element.click();
        element = driver.findElement(By.name("username"));
        element.sendKeys("jarmo"+random);
        element = driver.findElement(By.name("password"));
        element.sendKeys("jorma12");
        element = driver.findElement(By.name("passwordConfirmation"));
        element.sendKeys("jorma12");
        
        sleep(3);
        
        element.submit();
        
        sleep(5);
        
        //Uloskirjautuminen rekisteröitymisen jälkeen
        
        element = driver.findElement(By.linkText("continue to application mainpage"));
        element.click();
        
        sleep(3);
        
        element = driver.findElement(By.linkText("logout"));
        element.click();
        
        sleep(2);
        
        driver.quit();
    }
    
    private static void sleep(int n){
        try{
            Thread.sleep(n*500);
        } catch(Exception e){}
    }
}
