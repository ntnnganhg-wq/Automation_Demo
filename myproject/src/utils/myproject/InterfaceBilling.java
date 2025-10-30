package myproject;

public interface InterfaceBilling {
	void NavigateToBilling();
	void NavigateToSummary();
    int getQuantityByType();
    int FindTypeIndex();
    int CountSubXtellio();
	void VerifyBillingSummary();
	void FilterXtellio(String Type);
	
//	void VerifyBillingSubscription();
}
