import java.util.ArrayList;

public class Model {
	public Patron getPatronbyId(String id)
	{
		return null;
		
	}
	
	public Copy getCopybyId(String id)
	{
		return null;
		
	}
	
	public int updatePatron(Patron uptodatePatron)
	{
		return 0;
		
	}
}

class Storage{
	private static Storage instance = new Storage();
	private ArrayList<Patron> storedData; 
	
	private Storage()
	{
		initializeData();
	}
	public static Storage getInstance()
	{
		return instance;
	}
	
	private void initializeData()
	{
		
	}
	
	public void addData(Patron newData)
	{
		try{
			storedData.add(newData);
		}catch(Exception e){
			System.err.println(e.getMessage());
		}
	}
	
	public void updateData(Patron latestData)
	{
		try{
			while(storedData.iterator().hasNext() == true)
			{
				Patron tempPatron = storedData.iterator().next();
				if(tempPatron.getPatronID().matches(latestData.getPatronID()))
				{
					storedData.iterator().remove();
					break;
				}
			}
			storedData.add(latestData);
		}catch(Exception e){
			System.err.println(e.getMessage());
		}
	}
}
