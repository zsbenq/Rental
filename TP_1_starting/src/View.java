
public class View {
	private UserInputProcessor userInputProssor;
	public static final int DISPLAY_SUCCESS = 1;
	public static final int DISPLAY_FAIL = 2;
	
	public void bindUserInputProcessor(UserInputProcessor prossor){
		this.userInputProssor = prossor;
	}
	
	public int showMainScreen()
	{
		return 1;
	}
	
	private void captureMainScreenInput()
	{
		
	}
	
	public int showIdScanningScreen()
	{
		return 1;
	}
	
	private void captureIdScanningScreenInput()
	{
		
	}
	
	public int showIdTypingScreen()
	{
		return 1;
		
	}
	
	private void captureIdTypingScreenInput()
	{
		
	}
	
	public int showCopyScanningScreen()
	{
		return 1;
	}
	
	private void captureCopyScanningScreenInput()
	{
		
	}
	
	public int showCopyTypingScreen()
	{
		return 1;
	}
	
	private void captureCopyTypingScreenInput()
	{
		
	}
	
	public int showRecordScreen()
	{
		return 1;
	}
	
	private void captureRecordScreenInput()
	{
		
	}
	
	public int showCheckOutComplete()
	{
		return 1;
	}
	
}
