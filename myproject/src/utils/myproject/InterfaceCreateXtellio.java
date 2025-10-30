package myproject;

public interface InterfaceCreateXtellio {
	
	
	void NavigateTankMonitor();
//	int FindStatusIndex();	
//	int FindManufacturerIndex();
//	int CountXtellio ();
	void CreateNewXtellio( String XtellioEmail, String XtellioPass,  String Site, int Sublength) throws InterruptedException;
}
