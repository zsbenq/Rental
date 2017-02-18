import java.util.ArrayList;
import java.util.Iterator;

public class Model {
	public static final int SUCCESS = 1;
	public static final int FAIL = 0;
	public Patron getPatronbyId(String id)
	{
		ArrayList<Patron> patronsInStorage = Storage.getInstance().getData();
		//TODO implementation
		return null;
		
	}
	
	public Copy getCopybyId(String id)
	{
		ArrayList<Patron> patronsInStorage = Storage.getInstance().getData();
		//TODO implementation
		return null;
		
	}
	
	public int updatePatron(Patron uptodatePatron)
	{
		try{
			Storage.getInstance().updateData(uptodatePatron);
			return SUCCESS;
		}catch(Exception e){
			return FAIL;
		}
	}
	
	public int calculateDueTime()
	{
		return 0;
		
	}
	
}

class Storage{
	private static Storage instance = new Storage();
	private ArrayList<Patron> storedData; 
	
	private Storage()
	{
		storedData = new ArrayList<Patron>();
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
		
		updateData(patronDemoNew);
		updateData(patronDemoOld);
		
	}
	
	public void updateData(Patron latestData)
	{
		try{
			Iterator<Patron> dataIterator = storedData.iterator();
			while(dataIterator.hasNext() == true)
			{
				Patron tempPatron = dataIterator.next();
				if(tempPatron.getPatronID().matches(latestData.getPatronID()))
				{
					dataIterator.remove();
					break;
				}
			}
			storedData.add(latestData);
		}catch(Exception e){
			System.err.println(e.getMessage());
		}
	}
	
	public ArrayList<Patron> getData()
	{
		return storedData;
	}
}
