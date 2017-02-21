import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

public class Controller implements UserInputProcessor {
	private View view;
	private Model model;
	private static int CHECKOUTPROCESS = 1;
	private static int CHECKINPROCESS = 2;
	private static int VIEWRECORDPROCESS = 3;
	private int currentProcess = 0;
	private Patron currentPatron = null;
	private ArrayList<Copy> currentCheckOutList = null;

	@Override
	public void selectCheckIn() {
		try{
			if(view != null)
			{
				currentProcess = CHECKINPROCESS;
				view.showCopyScanningScreen();
			}
		}catch(Exception e){
			System.err.println(e.getMessage());
		}
		
	}

	@Override
	public void selectCheckOut() {
		try{
			if(view != null)
			{
				currentProcess = CHECKOUTPROCESS;
				view.showIdScanningScreen();
			}
		}catch(Exception e){
			System.err.println(e.getMessage());
		}
		
	}

	@Override
	public void selectViewPatronRecord() {
		try{
			if(view != null)
			{
				currentProcess = VIEWRECORDPROCESS;
				view.showIdScanningScreen();
			}
		}catch(Exception e){
			System.err.println(e.getMessage());
		}
	}

	@Override
	public void scanIdCard(Object scannerObj) {
		try{
			if(scannerObj != null)
			{
				Scanner scanner = (Scanner) scannerObj;
				String cardId = scanner.getScannedResult();
				if(cardId != null)
				{
					preparePatronData(cardId);
				}
				else
				{
					view.showScanIdFailed();
				}
			}
			else
			{
				StdOut.println("Error: [scanIdCard] parameter is null");
			}
		}catch(Exception e){
			System.err.println(e.getMessage());
		}
	}

	@Override
	public void typeIdCardNumber(String cardId) {
		try{
			preparePatronData(cardId);
			
		}catch(Exception e){
			System.err.println(e.getMessage());
		}
		
	}
	
	private void setCurrentPatron(Patron patron)
	{
		currentPatron = patron;
	}
	
	private void preparePatronData(String patronId)
	{
		try{
			Patron patronFromModel = model.getPatronbyId(patronId);
			if(patronFromModel != null)
			{
				setCurrentPatron(patronFromModel);
				assignPatronInfo();
				assignPatronHistoryRecord();
				if(currentProcess == CHECKOUTPROCESS)
				{
					view.showCopyScanningScreen();
				}
				else
				{
					cleanSessionDate();
				}
			}
			else
			{
				cleanSessionDate();
				view.showCardIdUnregistered();
			}
		}catch(Exception e){
			System.err.println(e.getMessage());
		}
	}

	private void assignPatronHistoryRecord() {
		try{
			if(hasCurrentPatron())
			{
				ArrayList<Record> patronRecord = currentPatron.getRentalRecord();
				Iterator<Record> recordIterator = patronRecord.iterator();
				ArrayList<HistoryRecord> historyRecList = new ArrayList<HistoryRecord>();
				while(recordIterator.hasNext())
				{
					Record tempRecord = recordIterator.next();
					HistoryRecord tempHistoryRecord = new HistoryRecord();
					tempHistoryRecord.setCopyId(tempRecord.getCopy().getCopyID());
					tempHistoryRecord.setCopyTitle(tempRecord.getCopy().getTitle());
					String dueTime = dateLongtoString(tempRecord.getDueTime());
					tempHistoryRecord.setDueDate(dueTime);
					historyRecList.add(tempHistoryRecord);
				}
				view.showHistoryRecords(historyRecList);
			}
		}catch(Exception e){
			System.err.println(e.getMessage());
		}
		
	}

	private String dateLongtoString(long dueTime) {
		try{
			Date dateTool = new Date();
			dateTool.setTime(dueTime);
			dateTool.getTime();
			DateFormat a = DateFormat.getDateInstance(DateFormat.SHORT);
			String dueString = a.format(dateTool);
			return dueString;
		}catch(Exception e){
			System.err.println(e.getMessage());
		}
		return null;
		
	}

	private void assignPatronInfo() {
		try{
			if(hasCurrentPatron())
			{
				view.showPatronInfo(currentPatron.getName(), currentPatron.getPatronID());
			}
		}catch(Exception e){
			System.err.println(e.getMessage());
		}
		
	}

	@Override
	public void scanCopy(Object scannerObj) {
		try{
			if(scannerObj != null)
			{
				Scanner scanner = (Scanner) scannerObj;
				String copyId = scanner.getScannedResult();
				if(copyId != null)
				{
					dispatchCopyProcess(copyId);
				}
				else
				{
					view.showScanCopyFailed();
				}
			}
			else
			{
				StdOut.println("Error: [scanCopy] parameter is null");
			}
		}catch(Exception e){
			System.err.println(e.getMessage());
		}
		
	}

	@Override
	public void typeCopyId(String copyId) {

		try{
			dispatchCopyProcess(copyId);
		}catch(Exception e){
			System.err.println(e.getMessage());
		}
		
	}

	private void dispatchCopyProcess(String copyId)
	{
		try{
			if(currentProcess == CHECKINPROCESS)
			{
				checkInCopy(copyId);
			}
			else if(currentProcess == CHECKOUTPROCESS)
			{
				checkOutCopy(copyId);
			}
		}catch(Exception e){
			System.err.println(e.getMessage());
		}
	}
	
	private void checkInCopy(String copyId) {
		try{
			Copy checkinCopy = model.getCopybyId(copyId);
			if(checkinCopy != null)
			{
				Patron holder = checkinCopy.getCurrentHolder();
				if(holder != null)
				{
					ArrayList<Record> recordList = holder.getRentalRecord();
					for(int i=0; i<recordList.size(); i++)
					{
						Record tempRecord = recordList.get(i);
						if(tempRecord.getCopy().getCopyID().equals(copyId))
						{
							recordList.get(i).setReturned(true);
							break;
						}
					}
					model.updateCopyInstockStatus(copyId, true);
					model.updateCopyCurrentHolder(copyId, null);
					model.updateCopyPreviousHolder(copyId, holder);
					view.showCheckInCompleted(checkinCopy.getCopyID(), checkinCopy.getTitle());
				}
				else
				{
					view.showCopyIsInStock();
				}
			}
			else
			{
				view.showCopyIdUnregistered();
			}
			cleanSessionDate();
		}catch(Exception e){
			System.err.println(e.getMessage());
		}
		
	}

	private void checkOutCopy(String copyId) {
		try{
			if(hasCurrentPatron())
			{
				Copy checkedCopy = model.getCopybyId(copyId);
				if(checkedCopy != null)
				{
					if(checkedCopy.isInStock())
					{
						addCopytoCurrentCheckOutList(checkedCopy);
						model.updateCopyInstockStatus(checkedCopy.getCopyID(), false);
					}
					else
					{
						view.showCopyUnavailable(checkedCopy.getCopyID(), checkedCopy.getTitle());
					}
					view.showCheckOutItems(makeCheckOutItemList());
					view.showCheckOutOption();
				}
				else
				{
					view.showCopyIdUnregistered();
				}
			}
		}catch(Exception e){
			System.err.println(e.getMessage());
		}
		
	}
	
	private ArrayList<CheckOutItem> makeCheckOutItemList() {
		try{
			if(currentCheckOutList != null)
			{
				ArrayList<CheckOutItem> itemList = new ArrayList<CheckOutItem>();
				for(int i=0; i<currentCheckOutList.size(); i++)
				{
					Copy tempCopy = currentCheckOutList.get(i);
					CheckOutItem tempItem = new CheckOutItem();
					tempItem.setItemAuthor(tempCopy.getAuthor());
					tempItem.setItemTitle(tempCopy.getTitle());
					tempItem.setItemNumber(i+1);
					itemList.add(tempItem);
				}
				return itemList;
			}
		}catch(Exception e){
			System.err.println(e.getMessage());
		}
		return null;
	}

	private void addCopytoCurrentCheckOutList(Copy newCopy)
	{
		try{
			if(currentCheckOutList == null)
			{
				currentCheckOutList = new ArrayList<Copy>();
			}
			currentCheckOutList.add(newCopy);
			
		}catch(Exception e){
			System.err.println(e.getMessage());
		}
	}

	private boolean hasCurrentPatron() {
		if(currentPatron != null)
		{
			return true;
		}
		else
		{
			cleanSessionDate();
			view.showCardIdUnregistered();
			return false;
		}
	}

	@Override
	public void removeCopyFromCheckOut(int copyNumber) {
		try{
			if(currentCheckOutList != null && copyNumber > 0)
			{
				for(int i=0; i<currentCheckOutList.size(); i++)
				{
					if(i == (copyNumber-1))
					{
						currentCheckOutList.remove(i);
					}
				}
			}
			view.showCheckOutItems(makeCheckOutItemList());
			view.showCheckOutOption();
		}catch(Exception e){
			System.err.println(e.getMessage());
		}
		
	}

	@Override
	public void completeCheckOut() {
		try{
			if(hasCurrentPatron())
			{
				ArrayList<CopyDue> copyDueList = new ArrayList<CopyDue>();
				Iterator<Copy> currentListIter = currentCheckOutList.iterator();
				while(currentListIter.hasNext())
				{
					Copy tempCopy = currentListIter.next();
					Record newRecord = new Record();
					newRecord.setCopy(tempCopy);
					long dueDateLong = model.calculateDueTime(tempCopy.getDueDays());
					newRecord.setDueTime(dueDateLong);
					currentPatron.addRentalRecord(newRecord);
					
					CopyDue copyDue = new CopyDue();
					copyDue.setCopyTitle(tempCopy.getTitle());
					copyDue.setDueDate(dateLongtoString(dueDateLong));
					copyDueList.add(copyDue);
					
					model.updateCopyCurrentHolder(tempCopy.getCopyID(), currentPatron);
				}
				
				model.updatePatron(currentPatron);
				view.showCopyDueDate(copyDueList);
				cleanSessionDate();
			}
		}catch(Exception e){
			System.err.println(e.getMessage());
		}
		
	}
	
	private void cleanSessionDate()
	{
		currentCheckOutList = null;
		currentPatron = null;
		currentProcess = 0;
	}
	
	private void initializeApplication()
	{
		try{
			this.view = new View(); 
			this.model = new Model();
			view.bindUserInputProcessor(this);
		}catch(Exception e){
			System.err.println(e.getMessage());
		}
	}
	
	private void start()
	{
		try{
			if(this.view != null)
			{
				while(true)
				{
					this.view.showMainScreen();
				}
			}
		}catch(Exception e){
			System.err.println(e.getMessage());
		}
	}

	public static void main(String[] args)
	{
		Controller controller = new Controller();
		controller.initializeApplication();
		controller.start();
	}
}
