

package tests;

import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import pages.Login_v1;
import pages.PageAddNewTankMonitor;
import pages.PageBilling;

public class Test_Login_v1 {
    WebDriver driver;
    Login_v1 TestLogin;
    PageAddNewTankMonitor addTankMonitor;
    PageBilling Billing;

    @BeforeClass
    public void setup() {

        driver = new ChromeDriver();
        driver.get("https://stage1skyn.fuelcloud.com/login");
        TestLogin = new Login_v1(driver);
        addTankMonitor = new PageAddNewTankMonitor(driver);  
        Billing = new PageBilling(driver);
        driver.manage().window().maximize();
    }
    
    int hwcountBefore;
    int hwcountAfter;
    int subXtellioBefore;
    int subXtellioAfter;
    
    @Test (priority =1)
    public void Test1() throws InterruptedException {
          TestLogin.Login("ngan+stage1.job1@fuelcloud.com", "ngan123");
    }  
    
    @Test (priority =2)
    public void Test2() throws InterruptedException {
    	Billing.NavigateToBilling();
    	Thread.sleep(10);
//    	Billing.FindTypeIndex();   
//    	System.out.println(Billing.FindTypeIndex());
    	
    	Billing.FilterXtellio("Xtellio Tank Monitor");
    	Thread.sleep(10);
    	subXtellioBefore = Billing.CountSubXtellio();
 		System.out.println("Before: " + subXtellioBefore);
    }
    
    
    @Test (priority =3)
    public void Test3() throws InterruptedException {
 		addTankMonitor.NavigateTankMonitor() ;   
 		addTankMonitor.FindManufacturerIndex();
 		hwcountBefore = addTankMonitor.CountXtellio();
 		System.out.println("Before: " + hwcountBefore);
 	
    }
   
    @Test(priority = 4)
    public void Test4() throws InterruptedException {
 		addTankMonitor.CreateNewXtellio("an@fuelcloud.com", "an123456", "SiteAutomate", 3);
 		}
    
    @Test(priority = 5)
    public void Test5() throws InterruptedException {
		
    	driver.navigate().to("https://stage1skyn.fuelcloud.com/device?tab=tank-monitor");
    	Thread.sleep(10);
		addTankMonitor.FindManufacturerIndex();
 		hwcountAfter = addTankMonitor.CountXtellio();
 		System.out.println("After: "+ hwcountAfter);
 		
 		}
   
   @Test(priority = 6)
    public void Test6() throws InterruptedException {
 		
 		Assert.assertTrue(hwcountAfter == hwcountBefore + 1, "Cannot activate new Xtellio");
 		
 		}
   @Test (priority =7)
   public void Test7() throws InterruptedException {
   	Billing.NavigateToBilling();
   	Thread.sleep(10);
//   	Billing.FindTypeIndex();   
//   	System.out.println(Billing.FindTypeIndex());
   	
   	Billing.FilterXtellio("Xtellio Tank Monitor");
   	Thread.sleep(10);
   	subXtellioAfter = Billing.CountSubXtellio();
	System.out.println("Before: " + subXtellioAfter);
   }
   @Test(priority = 8)
   public void Test8() throws InterruptedException {
		
		Assert.assertTrue(subXtellioAfter == subXtellioBefore + 1, "Subscription count of Xtellio isn't correct");
		
		}
   
    @Test (priority =9)
    public void Test9() throws InterruptedException {
    	Billing.NavigateToSummary();
    	int SummaryCount = Billing.getQuantityByType("Xtellio Tank Monitors");
   	Assert.assertTrue( SummaryCount == subXtellioAfter, "Summary tab count is wrong");
   	}

    
    @AfterClass
 public void quitBrowser() {
     driver.quit();
 }

}
