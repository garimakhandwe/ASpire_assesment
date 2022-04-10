package test;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.time.LocalTime;
import java.util.List;


public class Aspire {
    public WebDriver driver;
    public WebDriverWait wait;
    public String ProductName;
    public String MFOrder;
    ReadObject obj = new ReadObject();
	@Test
    public void Login() throws FileNotFoundException {
        System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+"/src/utilities/chromedriver.exe");
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--start-maximized");
        driver =new ChromeDriver(chromeOptions);

    //launch app
    driver.get(obj.getProperty("url"));
    
    // wait for input elements
    wait = new WebDriverWait(driver, 30);
    
	wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(obj.getProperty("txtEmail"))));
	//enter uername,password
	driver.findElement(By.xpath(obj.getProperty("txtEmail"))).sendKeys(obj.getProperty("email"));
	driver.findElement(By.xpath(obj.getProperty("txtPassword"))).sendKeys(obj.getProperty("password"));
    //click login
	driver.findElement(By.xpath(obj.getProperty("btnLogin"))).click();
	
	// wait for link elements
	wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(obj.getProperty("lnkManufacturing"))));
    }
    
	@Test
    public void createInventory() throws FileNotFoundException, InterruptedException {
    //click inventory-->Products-->Products-->Create--Save--UpdateQuantity
    	driver.findElement(By.xpath(obj.getProperty("lnkInventory"))).click();	
    	wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(obj.getProperty("btnProducts"))));
    	   
    	driver.findElement(By.xpath(obj.getProperty("btnProducts"))).click();
    	wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(obj.getProperty("lnkProducts"))));
   	 
    	driver.findElement(By.xpath(obj.getProperty("lnkProducts"))).click();
    	wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(obj.getProperty("btnCreateRecord"))));
    	driver.findElement(By.xpath(obj.getProperty("btnCreateRecord"))).click();
    	wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(obj.getProperty("txtName"))));
    	//Getting the current time value
        LocalTime time = LocalTime.now();
        ProductName="Product"+time;
        driver.findElement(By.xpath(obj.getProperty("txtName"))).clear();
        driver.findElement(By.xpath(obj.getProperty("txtName"))).sendKeys(ProductName);
        try {
        	File newTextFile=new File(System.getProperty("user.dir")+"/src/userdata/products.properties");
        	FileWriter fw= new FileWriter(newTextFile);
        	fw.write(ProductName);
        	fw.close();
        }catch(Exception e) {
        	System.out.println(e);
        }

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(obj.getProperty("btnUpdateQuantity"))));

        driver.findElement(By.xpath(obj.getProperty("btnUpdateQuantity"))).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(obj.getProperty("btnCreate"))));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(obj.getProperty("lnkRemoveFltr"))));
        driver.findElement(By.xpath(obj.getProperty("lnkRemoveFltr"))).click();
        Thread.sleep(2234);
        driver.findElement(By.xpath(obj.getProperty("btnCreate"))).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(obj.getProperty("txtQuantity"))));
        driver.findElement(By.xpath(obj.getProperty("txtQuantity"))).clear();
        driver.findElement(By.xpath(obj.getProperty("txtQuantity"))).sendKeys("11");
        driver.findElement(By.xpath(obj.getProperty("txtQuantity"))).sendKeys(Keys.TAB);
        Thread.sleep(1234);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(obj.getProperty("btnSaveRecord"))));

        driver.findElement(By.xpath(obj.getProperty("btnSaveRecord"))).click();
        Thread.sleep(1234);
        
    }
    
	@Test
    public void createManufactrOrder() throws FileNotFoundException, InterruptedException {
        
        driver.findElement(By.xpath(obj.getProperty("lnkHome"))).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(obj.getProperty("lnkManufacturing"))));
        driver.findElement(By.xpath(obj.getProperty("lnkManufacturing"))).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(obj.getProperty("txtSearchRecords"))));
        driver.findElement(By.xpath(obj.getProperty("btnCreate"))).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(obj.getProperty("txtProductid"))));
        driver.findElement(By.xpath(obj.getProperty("txtProductid"))).sendKeys(ProductName);
        Thread.sleep(2234);
        driver.findElement(By.xpath(obj.getProperty("txtProductid"))).sendKeys(Keys.TAB);
        Thread.sleep(2234);
        driver.findElement(By.xpath(obj.getProperty("btnConfirm"))).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(obj.getProperty("btnDone"))));
        driver.findElement(By.xpath(obj.getProperty("btnDone"))).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(obj.getProperty("btnOk"))));
        driver.findElement(By.xpath(obj.getProperty("btnOk"))).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(obj.getProperty("btnApply"))));
        driver.findElement(By.xpath(obj.getProperty("btnApply"))).click();
        Thread.sleep(2234);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(obj.getProperty("txtMFOrder"))));
        MFOrder= driver.findElement(By.xpath(obj.getProperty("txtMFOrder"))).getText();
        try {
        	File newTextFile=new File(System.getProperty("user.dir")+"/src/userdata/products.properties");
        	FileWriter fw= new FileWriter(newTextFile);
        	fw.write(MFOrder);
        	fw.close();
        }catch(Exception e) {
        	System.out.println(e);
        }
    }
    @Test
    public void validateManufactrOrder() throws FileNotFoundException, InterruptedException {
    	driver.findElement(By.xpath(obj.getProperty("lnkMFHome"))).click();
    	 wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(obj.getProperty("txtSearchRecords"))));
        driver.findElement(By.xpath(obj.getProperty("lnkRemoveFltr"))).click();
        Thread.sleep(2234);
    	 driver.findElement(By.xpath(obj.getProperty("txtSearchRecords"))).sendKeys(MFOrder);
    	 Thread.sleep(2234);
    	 driver.findElement(By.xpath(obj.getProperty("txtSearchRecords"))).sendKeys(Keys.ENTER);
    	 Thread.sleep(2234);
    	//No.of rows 
         List <WebElement> rows = driver.findElements(By.xpath("//table[@class='o_list_table table table-sm table-hover table-striped o_list_table_ungrouped']/tbody/tr/td[1]")); 
         System.out.println("No of rows are : " + rows.size());
         String ActualTitle =driver.findElement(By.xpath("//table[@class='o_list_table table table-sm table-hover table-striped o_list_table_ungrouped']/tbody/tr[1]/td[3]")).getText(); 
         Assert.assertEquals(ActualTitle, MFOrder);
         
    }
    @Test
    public void validateoldManufactrOrder() throws FileNotFoundException, InterruptedException {
        Thread.sleep(2234);
        driver.findElement(By.xpath(obj.getProperty("lnkMFHome"))).click();
        Thread.sleep(2234);
        driver.findElement(By.xpath(obj.getProperty("lnkMFHome"))).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(obj.getProperty("txtSearchRecords"))));
        driver.findElement(By.xpath(obj.getProperty("lnkRemoveFltr"))).click();
        Thread.sleep(2234);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(obj.getProperty("txtSearchRecords"))));
        driver.findElement(By.xpath(obj.getProperty("txtSearchRecords"))).sendKeys("WH/MO/03438");
        Thread.sleep(2234);
        driver.findElement(By.xpath(obj.getProperty("txtSearchRecords"))).sendKeys(Keys.ENTER);
        Thread.sleep(2234);
        //No.of rows
        List <WebElement> rows = driver.findElements(By.xpath("//table[@class='o_list_table table table-sm table-hover table-striped o_list_table_ungrouped']/tbody/tr/td[1]"));
        System.out.println("No of rows are : " + rows.size());
        String ActualTitle =driver.findElement(By.xpath("//table[@class='o_list_table table table-sm table-hover table-striped o_list_table_ungrouped']/tbody/tr[1]/td[3]")).getText();
        Assert.assertEquals(ActualTitle, "WH/MO/03438");
        driver.close();
        driver.quit();

    }
    
}

