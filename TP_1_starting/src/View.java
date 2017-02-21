import java.util.ArrayList;
import java.util.Iterator;

public class View {
	private UserInputProcessor userInputProcessor;
	private static final int MINIMUMSECTION = 1;
	private static final int MINUSLIMIT = -20;
	private static final int MAXIMUMINPUTLENGTH = 20;
	private static final int MAINSCREENSELECTIONS = 3;
	private static final int IDSCANNINGSELECTIONS = 3;
	private static final int COPYSCANNINGSELECTIONS = 3;
	private static final int CHECKOUTSELECTIONS = 2;
	private static final String SPLITLINE = "******************";
	
	public void bindUserInputProcessor(UserInputProcessor processor){
		this.userInputProcessor = processor;
	}
	
	public void showMainScreen()
	{
		try{
			StdOut.println(SPLITLINE+"Welcome"+SPLITLINE);
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
			int userSelection = captureUserSelection(MAINSCREENSELECTIONS, false);
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
			StdOut.println("(Press '2' Simulate a failed scanning)");
			StdOut.println("(Press '3' Enter ID Number)");
			
			dispatchIdScanningProcessor();
		}catch(Exception e){
			System.err.println(e.getMessage());
		}
	}
	
	private void dispatchIdScanningProcessor()
	{
		try{
			int userSelection = captureUserSelection(IDSCANNINGSELECTIONS, false);
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
				userInputProcessor.typeIdCardNumber(inputId);
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
			StdOut.println("(Press '2' Simulate a failed scanning)");
			StdOut.println("(Press '3' Enter Copy id)");
			
			dispatchCopyScanningScreenProcessor();
		}catch(Exception e){
			System.err.println(e.getMessage());
		}
	}
	
	private void dispatchCopyScanningScreenProcessor()
	{
		try{
			int userSelection = captureUserSelection(COPYSCANNINGSELECTIONS, false);
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
			StdOut.println(SPLITLINE);
			StdOut.println("name: " + patronName);
			StdOut.println("id: " + patronId);
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
					StdOut.println("<"+tempRecord.getCopyTitle()+">   "+tempRecord.getCopyId()+" due "+tempRecord.getDueDate());
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
					StdOut.println(tempItem.getItemNumber()+".   <"+tempItem.getItemTitle()+">   "+tempItem.getItemAuthor());
				}
			}
			else
			{
				StdOut.println("Error: [showCheckOutItems] parameter is null");
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
			StdOut.println("Input '-1' to remove item 1., '-2' to remove item 2, etc.");
			
			dispatchCheckOutOptionProcessor();
		}catch(Exception e){
			System.err.println(e.getMessage());
		}
	}
	
	private void dispatchCheckOutOptionProcessor()
	{
		try{
			int userSelection = captureUserSelection(CHECKOUTSELECTIONS, true);
			switch (userSelection){
			case 1:
				showCopyScanningScreen();
				break;
			case 2:
				userInputProcessor.completeCheckOut();
				break;
			default: 
				{
					if(userSelection < 0)
					{
						int removeNumber = userSelection * -1;
						userInputProcessor.removeCopyFromCheckOut(removeNumber);
					}
					break;
				}
				
			}
		
		}catch(Exception e){
			System.err.println(e.getMessage());
		}
	}
	
	public void showScanIdFailed()
	{
		try{
			StdOut.println("scanning fail");
			showIdTypingScreen();
		}catch(Exception e){
			System.err.println(e.getMessage());
		}
	}
	
	public void showScanCopyFailed()
	{
		try{
			StdOut.println("scanning fail");
			showCopyTypingScreen();
		}catch(Exception e){
			System.err.println(e.getMessage());
		}
	}
	
	public void showCardIdUnregistered()
	{
		try{
			StdOut.println("id number unregistered, please contact IT department");
		}catch(Exception e){
			System.err.println(e.getMessage());
		}
	}
	
	public void showCopyIdUnregistered()
	{
		try{
			StdOut.println("Copy id is unregistered, please contact the manager");
		}catch(Exception e){
			System.err.println(e.getMessage());
		}
	}
	
	public void showCopyDueDate(ArrayList<CopyDue> due)
	{
		try{
			StdOut.println("Check Out Complete");
			if(due != null)
			{
				Iterator<CopyDue> dueIter = due.iterator();
				while(dueIter.hasNext())
				{
					CopyDue tempDue = dueIter.next();
					StdOut.println("<"+tempDue.getCopyTitle()+"> will due at "+tempDue.getDueDate());
				}
			}
			else
			{
				StdOut.println("Error: [showCopyDueDate] parameter is null");
			}
		}catch(Exception e){
			System.err.println(e.getMessage());
		}
	}
	
	private int captureUserSelection(int optionAmount, boolean minusAllowed)
	{
		try{
			int userSelection = Integer.parseInt(StdIn.readString());
			int restriction = MINIMUMSECTION;
			if(minusAllowed == true){
				restriction = MINUSLIMIT;
			}
			while(userSelection < restriction || userSelection > optionAmount)
			{
				StdOut.println("Invalid option, try again");
				userSelection = Integer.parseInt(StdIn.readString());
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
			while(userInput.length() > MAXIMUMINPUTLENGTH || userInput.length() == 0)
			{
				StdOut.println("Input is invalid");
				userInput = StdIn.readString();
			}
			return userInput;
		}catch(Exception e){
			System.err.println(e.getMessage());
		}
		return null;
		
	}

	public void showCopyUnavailable(String copyID, String title) {
		try{
			StdOut.println(copyID+" "+title+" is rented out");
		}catch(Exception e){
			System.err.println(e.getMessage());
		}
	}

	public void showCheckInCompleted(String copyID, String title) {
		try{
			StdOut.println(copyID+" <"+title+"> is checked in");
		}catch(Exception e){
			System.err.println(e.getMessage());
		}
		
	}

	public void showCopyIsInStock() {
		try{
			StdOut.println("the Copy is in stock");
		}catch(Exception e){
			System.err.println(e.getMessage());
		}
	}
	
}

class CopyDue
{
	private String copyTitle;
	private String dueDate;
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