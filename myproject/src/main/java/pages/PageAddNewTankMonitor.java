package pages;

import java.util.List;

import org.openqa.selenium.*;

import utils.BasePage;

public class PageAddNewTankMonitor extends BasePage  {

	By menuMyAssets = By.id("flowbite-sidebar-collapse-:rm:");
	By menuMyAssetsHardware = By.xpath("//button[@id='flowbite-sidebar-collapse-:rm:']/following-sibling::ul/li[5]");
	By tabTankMonitor = By
			.xpath("//div[@class='flex items-center h-full']//a[normalize-space(text())='Tank Monitors']");
	// table
	By header = By.xpath(
			"//table[@class='w-full min-w-full table-fixed border-collapse last:[&_th]:sticky last:[&_th]:right-0 last:[&_th]:z-10 last:[&_td]:sticky last:[&_td]:right-0 last:[&_td]:z-5 last:[&_td]:bg-transparent']//thead//tr/th");
	/// th[contains(.,'Manufacturer')]
	By body = By.xpath(
			"//table[@class='w-full min-w-full table-fixed border-collapse last:[&_th]:sticky last:[&_th]:right-0 last:[&_th]:z-10 last:[&_td]:sticky last:[&_td]:right-0 last:[&_td]:z-5 last:[&_td]:bg-transparent']//tbody");
	By btnAddTankMonitor = By.xpath("//div[@class='flex gap-4']//button[normalize-space(text())='Add tank monitor']");
	By btnSelectXtellio = By.xpath(
			"//div[@class='flex justify-between items-center py-[24px] px-[56px] border-b-[2px] border-placeholder last:border-none h-[120px]'][4]//div[@class='flex gap-[32px] items-center'][2]//button[@class='btn-primary btn-lg']");
	// div[normalize-space(text())='Otodata']
	By txtXtellioEmail = By.id("undefined_username");
	By txtXtellioPass = By.id("undefined_password");
	By btnLoginXtellio = By.xpath("//button[@class='btn-primary btn-lg']");
	By btnNext = By.xpath("//div[@class='modal-footer']//button[2]");
	By SearchBox = By.xpath(
			"//div[@class='bg-white flex justify-between items-center table-search-top px-[56px] py-[12px]']//input");
	By SelectedSite = By.xpath("//table[@class='w-full min-w-full table-fixed border-collapse']/tbody/tr[1]/td[1]");

	public By sltSublength(int Sublength) {
		return By
				.xpath("//div[@class='mt-[8px]']//span[@class='text-lg'][normalize-space(text())='" + Sublength + "']");
	}

	By btnActivate = By.xpath("//button[@class='btn-primary btn-lg']");
	By listTankMonitor = By.xpath(
			"//table[@class='w-full min-w-full table-fixed border-collapse last:[&_th]:sticky last:[&_th]:right-0 last:[&_th]:z-10 last:[&_td]:sticky last:[&_td]:right-0 last:[&_td]:z-5 last:[&_td]:bg-transparent']");
	By iconShowHide = By.xpath("span.icon-icon-edit-table");
//	By cbManufacturer = By.xpath("//div[@class='col-name']/a/span[normalize-space(text())='Status']/../../preceding-sibling::div[@class='col-checkbox']//input");
	By btnResetToDefault = By.xpath("//div[@class='modal-footer gap-[32px] !px-[32px] !py-[16px] bg-white']/button[1]");

	public PageAddNewTankMonitor(WebDriver driver) {
		super(driver);
	}

	/* Navigate to Hard ware > Tank monitor */
	public void NavigateTankMonitor() {
		Click(menuMyAssets);
		Click(menuMyAssetsHardware);
		Click(tabTankMonitor);
	};

	public int FindManufacturerIndex() {
	    List<WebElement> headers = WaitForListVisible(header);
	    int manufacturerColIndex = -1;

	    for (int i = 0; i < headers.size(); i++) {
	        String headerText = headers.get(i).getText().trim();
	        if (headerText.equalsIgnoreCase("Manufacturer")) {
	            manufacturerColIndex = i; // index in list (0-based)
	            break;
	        }
	    }

	    if (manufacturerColIndex == -1) {
	        // Not found: show error or recover
	        throw new RuntimeException("❌ Still cannot find 'Manufacturer' column after enabling it!");
	        // Optionally:
	        // Click(iconShowHide);
	        // Click(btnResetToDefault);
	        // WaitForVisible(header);
	    }

	    System.out.println("✅ Index of column Manufacturer is: " + manufacturerColIndex);
	    return manufacturerColIndex;
	}


	public int FindStatusIndex() {
	    List<WebElement> headers = WaitForListVisible(header);
	    int statusIndex = -1;

	    for (int i = 0; i < headers.size(); i++) {
	        String headerText = headers.get(i).getText().trim();
	        if (headerText.equalsIgnoreCase("Status")) {
	            statusIndex = i; // index in list (0-based)
	            break;
	        }
	    }

	    if (statusIndex == -1) {
	        throw new RuntimeException("❌ Still cannot find 'Status' column after enabling it!");
	        // Optionally:
	        // Click(iconShowHide);
	        // Click(btnResetToDefault);
	        // WaitForVisible(header);
	    }

	    System.out.println("✅ Index of column Status is: " + statusIndex);
	    return statusIndex;
	}


	// --- Count Manufacturer as Xtellio with Status = Active ---
	public int CountXtellio() {
	    int manufacturerColIndex = FindManufacturerIndex();
	    int statusColIndex = FindStatusIndex();

	    WebElement listTankmonitor = WaitForVisible(body);
	    List<WebElement> rows = listTankmonitor.findElements(By.tagName("tr"));
	    int count = 0;

	    for (WebElement row : rows) {
	        List<WebElement> cells = row.findElements(By.tagName("td")); // ✅ per row
	        if (cells.size() > Math.max(manufacturerColIndex, statusColIndex)) {
	            String cellText = cells.get(manufacturerColIndex).getText().trim();
	            String cellStatus = cells.get(statusColIndex).getText().trim();
	            String activeXtellio = cellText + "_" + cellStatus;

	            System.out.println(activeXtellio); // Debug log

	            if (activeXtellio.equalsIgnoreCase("Xtellio_Active")) {
	                count++;
	            }
	        }
	    }

	    System.out.println("✅ Existing Active Xtellio count: " + count);
	    return count;
	}
	 
//	/* Activate new Xtellio tank monitor with existing valid payment me

	public void CreateNewXtellio(String XtellioEmail, String XtellioPass, String Site, int Sublength)
			throws InterruptedException {
		Click(btnAddTankMonitor);
		Click(btnSelectXtellio);
		if (isElementVisible(txtXtellioEmail) == true) {
			EnterText(txtXtellioEmail, XtellioEmail);
			EnterText(txtXtellioPass, XtellioPass);
			Click(btnLoginXtellio);
			Click(btnNext);
//			EnterText(SearchBox, Site);	
			Click(SelectedSite);
			Click(sltSublength(Sublength));
			Click(btnActivate);
		} else {
//			EnterText(SearchBox, Site);	
			Click(SelectedSite);
			Click(sltSublength(Sublength));
			Click(btnActivate);
			Thread.sleep(10);
		}
	};
}
