
public class Scanner {
	private String scannedResult = null;
	
	public String getScannedResult()
	{
		String tempResult = scannedResult;
		scannedResult = null;
		return tempResult;
	}
	
	public void generateSuccessfulIdDemoJimmy()
	{
		scannedResult = "23456";
	}
	
	public void generateSuccessfulIdDemoTom()
	{
		scannedResult = "12345";
	}
	
	public void generateSuccessfulCopyDemo1()
	{
		scannedResult = "CP1001102";
	}
	
	public void generateSuccessfulCopyDemo2()
	{
		scannedResult = "CP1001101";
	}
}
