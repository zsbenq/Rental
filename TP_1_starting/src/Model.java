import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

public class Model {
	public static final int SUCCESS = 1;
	public static final int FAIL = 0;
	public static final long DAYTIMELONG = 24*60*60*1000;
	public Patron getPatronbyId(String patronId)
	{
		return Storage.getInstance().getPatronbyId(patronId);
		
	}
	
	public Copy getCopybyId(String copyId)
	{
		return Storage.getInstance().getCopybyId(copyId);
		
	}
	
	public int updatePatron(Patron uptodatePatron)
	{
		try{
			Storage.getInstance().addPatrontoStorage(uptodatePatron);
			return SUCCESS;
		}catch(Exception e){
			return FAIL;
		}
	}
	
	public long calculateDueTime(int days)
	{
		Date date = new Date();
		long today = date.getTime();
		long dueTime = today+days*DAYTIMELONG;
		return dueTime;
	}

	public void updateCopyPreviousHolder(String copyId, Patron previousHolder) {
		Copy targetCopy = Storage.getInstance().getCopybyId(copyId);
		if(targetCopy != null)
		{
			targetCopy.setPreviousHolder(previousHolder);
		}
		
	}

	public void updateCopyCurrentHolder(String copyId, Patron currentHolder) {
		Copy targetCopy = Storage.getInstance().getCopybyId(copyId);
		if(targetCopy != null)
		{
			targetCopy.setCurrentHolder(currentHolder);
		}
		
	}

	public void updateCopyInstockStatus(String copyId, boolean isInStock) {
		Copy targetCopy = Storage.getInstance().getCopybyId(copyId);
		if(targetCopy != null)
		{
			targetCopy.setInStock(isInStock);
		}
	}
	
}

class Storage{
	private static Storage instance = new Storage();
	private ArrayList<Patron> storedPatrons;
	private ArrayList<Copy> storedCopies;
	
	private Storage()
	{
		storedPatrons = new ArrayList<Patron>();
		storedCopies = new ArrayList<Copy>();
		initializeDemoData();
	}
	public static Storage getInstance()
	{
		return instance;
	}
	
	private void initializeDemoData()
	{
		Copy copyDemo1 = new Copy();
		Copy copyDemo2 = new Copy();
		Patron patronDemoJimmy = new Patron();
		Patron patronDemoTom = new Patron();
		Record recordforJimmy = new Record();
		
		copyDemo1.setCopyID("CP1001102");
		copyDemo1.setAuthor("Robert");
		copyDemo1.setCondition("Good");
		copyDemo1.setInStock(true);
		copyDemo1.setTitle("Clean Code");
		copyDemo1.setPreviousHolder(patronDemoJimmy);
		copyDemo1.setCurrentHolder(null);
		copyDemo1.setDueDays(30);
		
		copyDemo2.setCopyID("CP1001101");
		copyDemo2.setAuthor("Larman");
		copyDemo2.setCondition("New");
		copyDemo2.setInStock(true);
		copyDemo2.setTitle("Applying UML & Patterns");
		copyDemo2.setPreviousHolder(null);
		copyDemo2.setCurrentHolder(null);
		copyDemo2.setDueDays(90);
		
		patronDemoJimmy.setName("Jimmy");
		patronDemoJimmy.setPatronID("23456");
		patronDemoJimmy.addRentalRecord(recordforJimmy);
		
		patronDemoTom.setName("Tom");
		patronDemoTom.setPatronID("12345");
		
		recordforJimmy.setCopy(copyDemo1);
		recordforJimmy.setDueTime(0);
		recordforJimmy.setReturned(true);
		
		addCopytoStorage(copyDemo2);
		addCopytoStorage(copyDemo1);
		addPatrontoStorage(patronDemoTom);
		addPatrontoStorage(patronDemoJimmy);
		
	}
	
	
	private void addCopytoStorage(Copy newCopy)
	{
		try{
			Iterator<Copy> copyIterator = storedCopies.iterator();
			while(copyIterator.hasNext() == true)
			{
				Copy tempCopy = copyIterator.next();
				if(tempCopy.getCopyID().matches(newCopy.getCopyID()))
				{
					copyIterator.remove();
					break;
				}
			}
			storedCopies.add(newCopy);
		}catch(Exception e){
			System.err.println(e.getMessage());
		}
	}
	
	public ArrayList<Copy> getAllCopies()
	{
		return storedCopies;
	}
	
	public void addPatrontoStorage(Patron newPatron)
	{
		try{
			Iterator<Patron> patronIterator = storedPatrons.iterator();
			while(patronIterator.hasNext() == true)
			{
				Patron tempPatron = patronIterator.next();
				if(tempPatron.getPatronID().matches(newPatron.getPatronID()))
				{
					patronIterator.remove();
					break;
				}
			}
			storedPatrons.add(newPatron);
		}catch(Exception e){
			System.err.println(e.getMessage());
		}
	}
	
	public ArrayList<Patron> getAllPatrons()
	{
		return storedPatrons;
	}
	
	public Copy getCopybyId(String copyId)
	{
		Copy targetCopy = null;
		for(int i=0; i<storedCopies.size(); i++)
		{
			if(storedCopies.get(i).getCopyID().equals(copyId))
			{
				targetCopy = storedCopies.get(i);
			}
		}
		return targetCopy;
	}
	
	public Patron getPatronbyId(String patronId)
	{
		Patron targetPatron = null;
		for(int i=0; i<storedPatrons.size(); i++)
		{
			if(storedPatrons.get(i).getPatronID().equals(patronId))
			{
				targetPatron = storedPatrons.get(i);
			}
		}
		return targetPatron;
	}
}
