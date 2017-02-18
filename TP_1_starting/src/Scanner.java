
public class Scanner {
	private String scannedResult = null;
	
	public String getScannedResult()
	{
		String tempResult = scannedResult;
		scannedResult = null;
		return tempResult;
	}
	
	public void generateSuccessfulIdDemo()
	{
		scannedResult = "23456";
	}
	
	public void generateSuccessfulCopyDemo()
	{
		scannedResult = "CP1001102";
	}
}
