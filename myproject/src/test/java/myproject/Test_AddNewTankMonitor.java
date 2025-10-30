package myproject;
import myproject.PageAddNewTankMonitor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Test_AddNewTankMonitor {
	
	PageAddNewTankMonitor addTankMonitor;
//    addTankMonitor = new PageAddNewTankMonitor(driver);

	
    @Test(priority =1)
    public void Test1() throws InterruptedException {
 		addTankMonitor.NavigateTankMonitor() ;     
 		}
    
    @Test(priority =2)
    public void Test2() throws InterruptedException {
 		System.out.println("Manufacturer index is: "+ addTankMonitor.FindManufacturerIndex());
     }
    
    @Test(priority =3)
    public void Test3() throws InterruptedException {
  		System.out.println("Existing Xtellio is: "+ addTankMonitor.CountXtellio());
      }
    
    @Test(priority =4)
    public void Test4() throws InterruptedException {
    	addTankMonitor.CreateNewXtellio("an@fuelcloud.com", "an123456", "SiteAutomate", 3);
      }
//    
//    @AfterClass
//   public void close() {
//        driver.quit();
//    }
	

}
