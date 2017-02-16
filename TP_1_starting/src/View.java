
public class View {
	private static View viewInstance = new View();
	public static final int DISPLAY_SUCCESS = 1;
	public static final int DISPLAY_FAIL = 2;
	private View(){}
	
	public static View getInstance(){
		if(null == viewInstance){
			viewInstance = new View();
		}
		
		return viewInstance;
	}
	
	public int showMainScreen()
	{
		return 1;
	}
	
	public int showIdScanningScreen()
	{
		return 1;
	}
	
	public int showIdTypingScreen()
	{
		return 1;
		
	}
	
	public int showCopyScanningScreen()
	{
		return 1;
	}
}
