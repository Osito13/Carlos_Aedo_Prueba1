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
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;


import java.util.List;
import java.util.concurrent.TimeUnit;

public class CasosBancoEstado {
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

        driver.get("https://www.bancoestado.cl/");

        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

        driver.manage().window().maximize();
    }

    @AfterEach
    public void posCondiciones(){
        driver.quit();
    }

    @Test
    public void CP001_Comprobar_Acceso_Simulador() throws InterruptedException {

        Actions action = new Actions(driver);
        WebElement consultaRut = driver.findElement(By.id("headermenu-eeb7ca1812-item-2ce075b597-tab"));
        action.moveToElement(consultaRut).build().perform();
        System.out.println("[Click] - Consulto por rut");
        driver.findElement(By.xpath("//a[@href='#consulta-rut']")).click();
        Thread.sleep(3000);
        System.out.println("[Sendkeys] Completar rut");
        driver.findElement(By.xpath("//input[@placeholder='Ingresa tu RUT']")).sendKeys("22222222-2");
        Thread.sleep(1000);
        System.out.println("[Click] - Simular");
        driver.findElement(By.xpath("//button[contains(text(),'Simular')]")).click();
        Assertions.assertEquals("Mes de primer pago",driver.findElement(By.xpath("//div//label[text()='Mes de primer pago']")).getText());
    }

    @Test
    public void CP002_Comprobar_Informacion_Importante() throws InterruptedException {

        Actions action = new Actions(driver);
        WebElement consultaRut = driver.findElement(By.id("headermenu-eeb7ca1812-item-2ce075b597-tab"));
        action.moveToElement(consultaRut).build().perform();
        System.out.println("[Click] - Consulto por rut");
        driver.findElement(By.xpath("//a[@href='#consulta-rut']")).click();
        Thread.sleep(3000);
        System.out.println("[Sendkeys] Completar rut");
        driver.findElement(By.xpath("//input[@placeholder='Ingresa tu RUT']")).sendKeys("22222222-2");
        Thread.sleep(1000);
        System.out.println("[Click] - Simular");
        driver.findElement(By.xpath("//button[contains(text(),'Simular')]")).click();
        if(driver.findElement(By.id("idIngresaMonto")).isDisplayed() == false){
            System.out.println("Se ha detectado una intermitencia en el sitio");
            Assertions.assertNotNull(driver.findElement(By.xpath("//div[contains(text(),'Lo sentimos.')]")), "Se ha detectado una intermitencia en el sitio");
        }else{
            driver.findElement(By.xpath("//div//label[contains(text(),'importante sobre la')]")).click();
            Thread.sleep(1000);

            System.out.println("Valida Información Importante");
            if(driver.findElement(By.xpath("//p[contains(text(),'consulta por otras ofertas en cualquier Sucursal o en el 600 400 7000.')]")).isDisplayed() == true){
                System.out.println("Se ha encontrado elemento de información importante");
            }
            //Assertions.assertNotNull(driver.findElement(By.xpath(")).isDisplayed() == false")), "El ");
        }
    }

    @Test
    public void CP003_ValidaMontoBajo() throws InterruptedException {
        Actions action = new Actions(driver);
        WebElement consultaRut = driver.findElement(By.id("headermenu-eeb7ca1812-item-2ce075b597-tab"));
        action.moveToElement(consultaRut).build().perform();
        System.out.println("[Click] - Consulto por rut");
        driver.findElement(By.xpath("//a[@href='#consulta-rut']")).click();
        Thread.sleep(3000);
        System.out.println("[Sendkeys] Completar rut");
        driver.findElement(By.xpath("//input[@placeholder='Ingresa tu RUT']")).sendKeys("22222222-2");
        Thread.sleep(1000);
        System.out.println("[Click] - Simular");
        driver.findElement(By.xpath("//button[contains(text(),'Simular')]")).click();
        Thread.sleep(5000);


        if(driver.findElement(By.id("idIngresaMonto")).isDisplayed() == true){
            System.out.println("Validación monto bajo");
            // Realizar la búsqueda del elemento con el XPath proporcionado
            driver.findElement(By.id("idIngresaMonto")).sendKeys("500000");
            driver.findElement(By.xpath("//div//select[@formcontrolname='numeroCuotas']")).click();
            WebElement elemento = driver.findElement(By.xpath("//div//span[contains(text(),'El valor debe ser mayor a')]"));
            // Realizar la aserción utilizando JUnit
            Assertions.assertNotNull(elemento, "El elemento no existe en la página");
        }else{
            System.out.println("Se ha detectado una intermitencia en el sitio");
            Assertions.assertNotNull(driver.findElement(By.xpath("//div[contains(text(),'Lo sentimos.')]")), "Se ha detectado una intermitencia en el sitio");

        }


    }

    @Test
    public void CP004_ValidaMontoAlto() throws InterruptedException {
        Actions action = new Actions(driver);
        WebElement consultaRut = driver.findElement(By.id("headermenu-eeb7ca1812-item-2ce075b597-tab"));
        action.moveToElement(consultaRut).build().perform();
        System.out.println("[Click] - Consulto por rut");
        driver.findElement(By.xpath("//a[@href='#consulta-rut']")).click();
        Thread.sleep(3000);
        System.out.println("[Sendkeys] Completar rut");
        driver.findElement(By.xpath("//input[@placeholder='Ingresa tu RUT']")).sendKeys("22222222-2");
        Thread.sleep(1000);
        System.out.println("[Click] - Simular");
        driver.findElement(By.xpath("//button[contains(text(),'Simular')]")).click();
        Thread.sleep(5000);

        if(driver.findElement(By.id("idIngresaMonto")).isDisplayed() == true){
            System.out.println("Validación monto alto");

            driver.findElement(By.id("idIngresaMonto")).sendKeys("60000000");
            // Realizar la búsqueda del elemento con el XPath proporcionado
            WebElement elemento = driver.findElement(By.xpath("//div//span[contains(text(),'El valor debe ser menor a')]"));
            driver.findElement(By.xpath("//div//select[@formcontrolname='numeroCuotas']")).click();
            // Realizar la aserción utilizando JUnit
            Assertions.assertNotNull(elemento, "El elemento no existe en la página");
        }else{
            System.out.println("Se ha detectado una intermitencia en el sitio");
            Assertions.assertNotNull(driver.findElement(By.xpath("//div[contains(text(),'Lo sentimos.')]")), "Se ha detectado una intermitencia en el sitio");

        }

    }

    @Test
    public void CP005_Evaluar_Credito() throws InterruptedException {
        Actions action = new Actions(driver);
        WebElement consultaRut = driver.findElement(By.id("headermenu-eeb7ca1812-item-2ce075b597-tab"));
        action.moveToElement(consultaRut).build().perform();
        System.out.println("[Click] - Consulto por rut");
        driver.findElement(By.xpath("//a[@href='#consulta-rut']")).click();
        Thread.sleep(3000);
        System.out.println("[Sendkeys] Completar rut");
        driver.findElement(By.xpath("//input[@placeholder='Ingresa tu RUT']")).sendKeys("22222222-2");
        Thread.sleep(1000);
        System.out.println("[Click] - Simular");
        driver.findElement(By.xpath("//button[contains(text(),'Simular')]")).click();
        Thread.sleep(5000);
        if(driver.findElement(By.id("idIngresaMonto")).isDisplayed() == false){
            System.out.println("Se ha detectado una intermitencia en el sitio");
            Assertions.assertNotNull(driver.findElement(By.xpath("//div[contains(text(),'Lo sentimos.')]")), "Se ha detectado una intermitencia en el sitio");
        }

        System.out.println("Ingreso monto");
        driver.findElement(By.id("idIngresaMonto")).sendKeys("50000000");
        System.out.println("Click cuotas");
        driver.findElement(By.xpath("//div//select[@formcontrolname='numeroCuotas']")).click();
        System.out.println("Ingreso Numero cuotas");
        driver.findElement(By.xpath("//div//select[@formcontrolname='numeroCuotas']//option[@value= '60']")).click();
        // Forzar el clic usando JavaScript
        System.out.println("Click JS - No seguros");
        WebElement sinSeguroRadioButton = driver.findElement(By.xpath("//div[@role='radiogroup']//div[@for='c07']//input"));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", sinSeguroRadioButton);
        Thread.sleep(2000);

        System.out.println("Click mes de pago");
        driver.findElement(By.xpath("//select[@formcontrolname='mesPago']")).click();
        System.out.println("[Click] - Selecciono mes de pago");
        driver.findElement(By.xpath("//select[@formcontrolname='mesPago']//option[2]")).click();
        Thread.sleep(2000);
        System.out.println("Click Dia pago");
        driver.findElement(By.xpath("//select[@formcontrolname='diaPago']")).click();
        System.out.println("Seleccion dia pago");
        driver.findElement(By.xpath("//select[@formcontrolname='diaPago']//option[2]")).click();

        Thread.sleep(2000);
        System.out.println("Simular");
        driver.findElement(By.xpath("//button[contains(text(),'Continuar')]")).click();
        Thread.sleep(5000);
        Assertions.assertEquals("Valor Impuestos",driver.findElement(By.xpath("//div//label[text()='Valor Impuestos']")).getText());
    }


}
