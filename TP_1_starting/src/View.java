
public class View {
	private UserInputProcessor userInputProcessor;
	public static final int MINIMUMSECTION = 1;
	public static final int MAINSCREENSELECTIONS = 3;
	public static final int IDSCANNINGSELECTIONS = 3;
	
	public void bindUserInputProcessor(UserInputProcessor processor){
		this.userInputProcessor = processor;
	}
	
	public void showMainScreen()
	{
		try{
			StdOut.println("Press '1' Check In");
			StdOut.println("Press '2' Check Out");
			StdOut.println("Press '3' Show Patron's Record");
			
			dispatchMainScreenProcessor();
		}catch(Exception e){
			System.err.println(e.getMessage());
		}
	}
	
	private void dispatchMainScreenProcessor()
	{
		try{
			int userSelection = captureUserSelection(MAINSCREENSELECTIONS);
			switch (userSelection){
			case 1:
				userInputProcessor.selectCheckIn();
				break;
			case 2:
				userInputProcessor.selectCheckOut();
				break;
			case 3: 
				userInputProcessor.selectViewPatronRecord();
				break;
			default: 
				break;
			}
		
		}catch(Exception e){
			System.err.println(e.getMessage());
		}
	}
	
	public void showIdScanningScreen()
	{
		try{
			StdOut.println("Scanning Id...");
			StdOut.println("(Press '1' Simulate a successful scanning)");
			StdOut.println("(Press '2' Simulate a failed scanning");
			StdOut.println("(Press '3' Enter ID Number");
			
			dispatchIdScanningProcessor();
		}catch(Exception e){
			System.err.println(e.getMessage());
		}
	}
	
	private void dispatchIdScanningProcessor()
	{
		try{
			int userSelection = captureUserSelection(IDSCANNINGSELECTIONS);
			Scanner scanner = new Scanner();
			switch (userSelection){
			case 1:
				scanner.generateSuccessfulIdDemo();
				userInputProcessor.scanIdCard(scanner);
				break;
			case 2:
				userInputProcessor.scanIdCard(scanner);
				break;
			case 3: 
				this.showIdTypingScreen();
				break;
			default: 
				break;
			}
		
		}catch(Exception e){
			System.err.println(e.getMessage());
		}
	}
	
	public void showIdTypingScreen()
	{
		
		
	}
	
	private void dispatchIdTypingScreenProcessor()
	{
		
	}
	
	public void showCopyScanningScreen()
	{
		
	}
	
	private void dispatchCopyScanningScreenProcessor()
	{
		
	}
	
	public void showCopyTypingScreen()
	{
		
	}
	
	private void dispatchCopyTypingScreenProcessor()
	{
		
	}
	
	public void showRecordScreen()
	{
		
	}
	
	private void dispatchRecordScreenProcessor()
	{
		
	}
	
	public void showCheckOutComplete()
	{
		
	}
	
	private int captureUserSelection(int optionAmount)
	{
		try{
			int userInput = StdIn.readInt();
			while(userInput < MINIMUMSECTION || userInput > optionAmount)
			{
				StdOut.println("Invalid option, try again");
				userInput = StdIn.readInt();
			}
			return userInput;
			
		}catch(Exception e){
			System.err.println(e.getMessage());
		}
		return optionAmount;
	}
}
