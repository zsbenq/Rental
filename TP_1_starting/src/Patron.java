import java.util.ArrayList;

public class Patron
{
	private String name = null;
	private String patronID = null;
	private ArrayList<Record> rentalRecord = new ArrayList<Record>();
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPatronID() {
		return patronID;
	}
	public void setPatronID(String patronID) {
		this.patronID = patronID;
	}
	public ArrayList<Record> getRentalRecord() {
		return rentalRecord;
	}
	public void addRentalRecord(Record newRecord) {
		this.rentalRecord.add(newRecord);
	}

	

	

}
