import java.util.ArrayList;
import java.util.Iterator;

public class Model {
	public static final int SUCCESS = 1;
	public static final int FAIL = 0;
	public Patron getPatronbyId(String id)
	{
		ArrayList<Patron> patronsInStorage = Storage.getInstance().getAllPatrons();
		//TODO implementation
		return null;
		
	}
	
	public Copy getCopybyId(String id)
	{
		ArrayList<Copy> copiesInStorage = Storage.getInstance().getAllCopies();
		//TODO implementation
		return null;
		
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
	
	public int calculateDueTime()
	{
		//TODO
		return 0;
		
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
		Copy copyDemoOld = new Copy();
		Copy copyDemoNew = new Copy();
		Patron patronDemoOld = new Patron();
		Patron patronDemoNew = new Patron();
		Record recordDemoforOld = new Record();
		
		copyDemoOld.setCopyID("CP1001102");
		copyDemoOld.setAuthor("Robert");
		copyDemoOld.setCondition("Good");
		copyDemoOld.setStatus("in-stock");
		copyDemoOld.setTitle("Clean Code");
		copyDemoOld.setPrivioutHolder(patronDemoOld);
		copyDemoOld.setCurrentHolder(null);
		
		copyDemoNew.setCopyID("CP1001101");
		copyDemoNew.setAuthor("Larman");
		copyDemoNew.setCondition("New");
		copyDemoNew.setStatus("in-stock");
		copyDemoNew.setTitle("Applying UML & Patterns");
		copyDemoNew.setPrivioutHolder(null);
		copyDemoNew.setCurrentHolder(null);
		
		patronDemoOld.setName("Jimmy");
		patronDemoOld.setPatronID("23456");
		patronDemoOld.addRentalRecord(recordDemoforOld);
		
		patronDemoNew.setName("Tom");
		patronDemoNew.setPatronID("12345");
		
		recordDemoforOld.setCopy(copyDemoOld);
		recordDemoforOld.setDueTime(0);
		
		addCopytoStorage(copyDemoNew);
		addCopytoStorage(copyDemoOld);
		addPatrontoStorage(patronDemoNew);
		addPatrontoStorage(patronDemoOld);
		
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
}
