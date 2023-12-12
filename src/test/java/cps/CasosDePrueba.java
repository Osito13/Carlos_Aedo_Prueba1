package cps;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class CasosDePrueba {
    //Atributos
    WebDriver driver;
    JavascriptExecutor js;
    String rutaProyecto = System.getProperty("user.dir"); //no es de la prueba

    String rutaDriver = rutaProyecto + "\\src\\test\\resources\\drivers\\chromedriver.exe";

    @BeforeEach
    public void preCondiciones(){
        System.setProperty("webdriver.chrome.driver", rutaDriver);
        driver = new ChromeDriver();

        js = (JavascriptExecutor) driver;

        driver.get("https://open.spotify.com/");

        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

        driver.manage().window().maximize();
    }

    @AfterEach
    public void posCondiciones(){
        driver.quit();
    }

    @Test
    public void CP001_creacion_cta() throws InterruptedException {
        By localizadorRegistrarte = By.xpath("//button[contains(text(),'Reg')]");

        WebElement btnRegistrarte = driver.findElement(localizadorRegistrarte);

        btnRegistrarte.click();

        driver.findElement(By.id("username")).sendKeys("emailTest123456@algo.com");

        Thread.sleep(1000);
        driver.findElement(By.xpath("//button[@data-testid='submit']")).click();

        driver.findElement(By.id("new-password")).sendKeys("Frre3232");

        Thread.sleep(1000);
        driver.findElement(By.xpath("//button[@data-testid='submit']")).click();

        Thread.sleep(1000);
        driver.findElement(By.id("displayName")).sendKeys("Curso BCI Selenium");

        WebDriverWait wait = new WebDriverWait(driver,10);


        WebElement txtDay = wait.until(ExpectedConditions.
                presenceOfElementLocated(By.xpath("//input[@id='day']")));

        txtDay.sendKeys("28");

        Select drpCountry = new Select(wait.until(ExpectedConditions.
                presenceOfElementLocated(By.xpath("//select[@id='month']"))));

        drpCountry.selectByValue("10");

        WebElement txtYeah = wait.until(ExpectedConditions.
                presenceOfElementLocated(By.xpath("//input[@id='year']")));

        txtYeah.sendKeys("1991");

        Thread.sleep(3000);


        js.executeScript("window.scrollBy(0,100)");

        List<WebElement> generos = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//label[contains(@for,'gender')]")));

        generos.get(0).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//button[@data-testid='submit']")).click();

        Thread.sleep(3000);

        List<WebElement> checks = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//label[contains(@for,'checkbox')]")));

        if(checks.size()==2){
            checks.get(0).click();
            Thread.sleep(1000);
        }

        driver.findElement(By.xpath("//button[@data-testid='submit']")).click();

        Thread.sleep(4000);

        String username = driver.findElement(By.xpath("//button[@data-testid='user-widget-link']")).getAttribute("aria-label");

        Assertions.assertEquals("Curso BCI Selenium",username);
    }

    @Test
    public void CP002_iniciar_sesion() throws InterruptedException {

        By localizadorIniciarSesion = By.xpath("//button[@data-testid='login-button']");

        WebElement btnIniciarSesion = driver.findElement(localizadorIniciarSesion);

        btnIniciarSesion.click();

        driver.findElement(By.id("login-username")).sendKeys("emailTest123456@algo.com");

        Thread.sleep(1000);

        driver.findElement(By.id("login-password")).sendKeys("Frre3232");
        Thread.sleep(1000);
        driver.findElement(By.xpath("//button[@data-testid='login-button']")).click();
        Thread.sleep(2000);
        String username = driver.findElement(By.xpath("//button[@data-testid='user-widget-link']")).getAttribute("aria-label");

        Assertions.assertEquals("Curso BCI Selenium",username);
    }

    @Test
    public void CP003(){

    }

    @Test
    public void CP004(){

    }

    @Test
    public void CP005(){

    }

}
