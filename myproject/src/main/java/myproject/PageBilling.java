package myproject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class PageBilling extends BasePage {
	//Locator
	By menuBilling = By.xpath("//span[@id='flowbite-sidebar-item-:r13:']/..");
	By tabSummary = By.xpath("//div[@class='flex w-full flex-col']//a[normalize-space(text())='Summary']");
	By tabSubscriptions = By.xpath("//div[@class='flex w-full flex-col']//a[normalize-space(text())='Subscriptions']");
	By bodySummary = By.xpath("//table[@class='w-full min-w-full table-fixed border-collapse']/tbody");
	By SubscriptionHeader = By.xpath("//table[@class='w-full min-w-full table-fixed border-collapse last:[&_th]:sticky last:[&_th]:right-0 last:[&_th]:z-10 last:[&_td]:sticky last:[&_td]:right-0 last:[&_td]:z-5 last:[&_td]:bg-transparent']/thead/tr/th");
	By SubscriptionBody = By.xpath("//table[@class='w-full min-w-full table-fixed border-collapse last:[&_th]:sticky last:[&_th]:right-0 last:[&_th]:z-10 last:[&_td]:sticky last:[&_td]:right-0 last:[&_td]:z-5 last:[&_td]:bg-transparent']/tbody");
	public PageBilling(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	
	public void NavigateToBilling() {
		Click(menuBilling);
		};
		
		public void NavigateToSummary() {
			Click(tabSummary);
			};
//	/* Check Billing summary after activating device successfully */
		public int getQuantityByType(String typeName) {

		    Click(tabSummary); // make sure summary tab is active

		    WebElement tbody = WaitForVisible(bodySummary);
		    List<WebElement> rows = tbody.findElements(By.tagName("tr")); // get all rows

		    int quantity = 0;

		    for (WebElement row : rows) {
		        List<WebElement> cells = row.findElements(By.tagName("td")); // get cells for this row
		        if (cells.size() >= 2) {
		            String type = cells.get(0).getText().trim();  // first column = type
//		            System.out.println("Row type: " + type);     // debug print
		            if (type.equalsIgnoreCase(typeName)) {
		                quantity = Integer.parseInt(cells.get(1).getText().trim()); // second column = quantity
		                System.out.println("Quantity of " + typeName + " is: " + quantity);
		                break; // stop once found
		            }
		        }
		    }

		    return quantity;
		}
		protected int typeColIndex = -1;
		public int FindTypeIndex() {
			List<WebElement> headers = WaitForListVisible(SubscriptionHeader);
			for (int i = 0; i < headers.size(); i++) {
				String headerType = headers.get(i).getText().trim();
		        System.out.println("Header " + i + ": " + headerType); // 👈 for debugging
				if (headerType.equals("Type")) {
					typeColIndex = i; // XPath is 1-based index
					break;
				}
			}
			System.out.println("Index of column Type is: " + typeColIndex);
			return typeColIndex;
		}
		public void FilterXtellio (String type) {
			int ColIndex = FindTypeIndex() + 1;	
		By filterType = By.xpath("//table[@class='w-full min-w-full table-fixed border-collapse last:[&_th]:sticky last:[&_th]:right-0 last:[&_th]:z-10 last:[&_td]:sticky last:[&_td]:right-0 last:[&_td]:z-5 last:[&_td]:bg-transparent']//thead/tr/th[" + ColIndex + "]/div/button");
		By searchBox = By.xpath("//div[@class='group items-center border-b-[2px] mx-[16px] group gap-[8px] border-2 border-bg_active rounded-[16px] py-[12px] px-[16px] h-[40px] text-sm flex mb-[16px]']/input");
		By checkbox = By.xpath(" //div[@class='relative select-none items-center rounded-sm text-sm outline-none data-[disabled=true]:pointer-events-none data-[selected=true]:bg-slate-100 data-[selected=true]:text-slate-900 data-[disabled=true]:opacity-50 dark:data-[selected=true]:bg-slate-800 dark:data-[selected=true]:text-slate-50 cursor-pointer p-[16px] flex gap-[16px] text-ellipsis overflow-auto break-words !rounded-none first:border-t-0 border-t-[1px] border-placeholder hover:bg-bg_hover bg-white']/span[1]");
		Click(filterType);
		EnterText(searchBox, type );
		Click(checkbox);
		}
		
		public int CountSubXtellio() {
//		    int typeColIndex = FindTypeIndex();  // 0-based index
		    WebElement tableBody = WaitForVisible(SubscriptionBody);
		    List<WebElement> rows = tableBody.findElements(By.tagName("tr"));

		    int count = 0;
		    for (WebElement row : rows) {
		        // ✅ Find cells *within this row only*
		        List<WebElement> cells = row.findElements(By.tagName("td"));
		        
		        if (cells.size() > typeColIndex) {
		            String cellText = cells.get(typeColIndex).getText().trim();
		            System.out.println("Row: " + cellText);

		            if (cellText.contains("Xtellio")) {
		                count++;
		            }
		        }
		    }
		    System.out.println("✅ Existing Xtellio count: " + count);
		    return count;
		}

		
	/* Check subscription info in Billing page after activating device successfully */
	
//	public void VerifyBillingSubscription (){};
	
//			public void VerifyBillingSummary () {
//				
//			}

}
