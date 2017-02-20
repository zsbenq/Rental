import java.util.ArrayList;
import java.util.Iterator;

public class View {
	private UserInputProcessor userInputProcessor;
	private static final int MINIMUMSECTION = 1;
	private static final int MAXIMUMINPUTLENGTH = 20;
	private static final int MAINSCREENSELECTIONS = 3;
	private static final int IDSCANNINGSELECTIONS = 3;
	private static final int COPYSCANNINGSELECTIONS = 3;
	private static final int CHECKOUTSELECTIONS = 2;
	private static final String SPlITLINE = "******************";
	
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
				StdOut.println("Error occured");
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
				StdOut.println("Error occured");
				break;
			}
		
		}catch(Exception e){
			System.err.println(e.getMessage());
		}
	}
	
	public void showIdTypingScreen()
	{
		try{
			StdOut.println("Enter Id Number:");
			StdOut.println("(\"12345\" : a registered new student)");
			StdOut.println("(\"23456\" : a registered old student)");
			
			dispatchIdTypingScreenProcessor();
		}catch(Exception e){
			System.err.println(e.getMessage());
		}
		
	}
	
	private void dispatchIdTypingScreenProcessor()
	{
		try{
			String inputId = captureUserInput();
			if(inputId != null)
			{
				userInputProcessor.typeIdNumber(inputId);
			}
			else
			{
				StdOut.println("Error occured");
			}
		}catch(Exception e){
			System.err.println(e.getMessage());
		}
	}
	
	public void showCopyScanningScreen()
	{
		try{
			StdOut.println("Scanning Copy...");
			StdOut.println("(Press '1' Simulate a successful scanning)");
			StdOut.println("(Press '2' Simulate a failed scanning");
			StdOut.println("(Press '3' Enter ISBN");
			
			dispatchCopyScanningScreenProcessor();
		}catch(Exception e){
			System.err.println(e.getMessage());
		}
	}
	
	private void dispatchCopyScanningScreenProcessor()
	{
		try{
			int userSelection = captureUserSelection(COPYSCANNINGSELECTIONS);
			Scanner scanner = new Scanner();
			switch (userSelection){
			case 1:
				scanner.generateSuccessfulCopyDemo();
				userInputProcessor.scanCopy(scanner);
				break;
			case 2:
				userInputProcessor.scanCopy(scanner);
				break;
			case 3: 
				this.showCopyTypingScreen();
				break;
			default: 
				StdOut.println("Error occured");
				break;
			}
		
		}catch(Exception e){
			System.err.println(e.getMessage());
		}
	}
	
	public void showCopyTypingScreen()
	{
		try{
			StdOut.println("Enter Copy id:");
			StdOut.println("(Demo: \"CP1001102\" \"CP1001101\" )");
			
			dispatchCopyTypingScreenProcessor();
		}catch(Exception e){
			System.err.println(e.getMessage());
		}
	}
	
	private void dispatchCopyTypingScreenProcessor()
	{
		try{
			String inputCopyid = captureUserInput();
			if(inputCopyid != null)
			{
				userInputProcessor.typeCopyId(inputCopyid);
			}
			else
			{
				StdOut.println("Error occured");
			}
		}catch(Exception e){
			System.err.println(e.getMessage());
		}
	}
	
	public void showPatronInfo(String patronName, String patronId)
	{
		try{
			StdOut.println(SPlITLINE);
			StdOut.println("name: " + patronName);
			StdOut.println("id: " + patronId);
			StdOut.println(SPlITLINE);
		}catch(Exception e){
			System.err.println(e.getMessage());
		}
	}
	
	public void showHistoryRecords(ArrayList<HistoryRecord> historyRecord)
	{
		try{
			if(historyRecord != null)
			{
				StdOut.println("History records:");
				Iterator<HistoryRecord> recordIter = historyRecord.iterator();
				while(recordIter.hasNext())
				{
					HistoryRecord tempRecord = recordIter.next();
					StdOut.println(tempRecord.getCopyTitle()+"   "+tempRecord.getCopyId()+"   "+tempRecord.getDueDate());
				}
			}
			else
			{
				StdOut.println("Error: [showHistoryRecords] parameter is null");
			}
		}catch(Exception e){
			System.err.println(e.getMessage());
		}
	}
	
	public void showCheckOutItems(ArrayList<CheckOutItem> outItems)
	{
		try{
			if(outItems != null)
			{
				StdOut.println("Checking Out:");
				Iterator<CheckOutItem> itemIter = outItems.iterator();
				while(itemIter.hasNext())
				{
					CheckOutItem tempItem = itemIter.next();
					StdOut.println(tempItem.getItemNumber()+".   "+tempItem.getItemTitle()+"   "+tempItem.getItemAuthor());
				}
			}
			else
			{
				StdOut.println("Error: [showHistoryRecords] parameter is null");
			}
		}catch(Exception e){
			System.err.println(e.getMessage());
		}
	}
	
	public void showCheckOutOption()
	{
		try{
			StdOut.println("Press '1' to continue scan");
			StdOut.println("Press '2' to complete check out");
			
			dispatchCheckOutOptionProcessor();
		}catch(Exception e){
			System.err.println(e.getMessage());
		}
	}
	
	private void dispatchCheckOutOptionProcessor()
	{
		try{
			int userSelection = captureUserSelection(CHECKOUTSELECTIONS);
			switch (userSelection){
			case 1:
				showCopyScanningScreen();
				break;
			case 2:
				userInputProcessor.completeCheckOut();
				break;
			default: 
				StdOut.println("Error occured");
				break;
			}
		
		}catch(Exception e){
			System.err.println(e.getMessage());
		}
	}
	
	private int captureUserSelection(int optionAmount)
	{
		try{
			int userSelection = StdIn.readInt();
			while(userSelection < MINIMUMSECTION || userSelection > optionAmount)
			{
				StdOut.println("Invalid option, try again");
				userSelection = StdIn.readInt();
			}
			return userSelection;
			
		}catch(Exception e){
			System.err.println(e.getMessage());
		}
		return 0;
	}
	
	private String captureUserInput()
	{
		try{
			String userInput = StdIn.readString();
			while(userInput.length() > MAXIMUMINPUTLENGTH)
			{
				StdOut.println("Input is too long");
				userInput = StdIn.readString();
			}
			return userInput;
		}catch(Exception e){
			System.err.println(e.getMessage());
		}
		return null;
		
	}
	
}

class HistoryRecord
{
	private String copyTitle;
	private String dueDate;
	private String copyId;
	public String getCopyTitle() {
		return copyTitle;
	}
	public void setCopyTitle(String copyTitle) {
		this.copyTitle = copyTitle;
	}
	public String getDueDate() {
		return dueDate;
	}
	public void setDueDate(String dueDate) {
		this.dueDate = dueDate;
	}
	public String getCopyId() {
		return copyId;
	}
	public void setCopyId(String copyId) {
		this.copyId = copyId;
	}
}

class CheckOutItem
{
	private int itemNumber;
	private String itemTitle;
	private String itemAuthor;
	
	public String getItemTitle() {
		return itemTitle;
	}
	public void setItemTitle(String itemTitle) {
		this.itemTitle = itemTitle;
	}
	public int getItemNumber() {
		return itemNumber;
	}
	public void setItemNumber(int itemNumber) {
		this.itemNumber = itemNumber;
	}
	public String getItemAuthor() {
		return itemAuthor;
	}
	public void setItemAuthor(String itemAuthor) {
		this.itemAuthor = itemAuthor;
	}
}