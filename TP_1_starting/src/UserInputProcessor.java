
public interface UserInputProcessor {
	public void selectCheckIn();
	public void selectCheckOut();
	public void selectViewPatronRecord();
	public void scanIdCard(Object scanner);
	public void typeIdNumber(String idNumber);
	public void scanCopy(Object scanner);
	public void typeCopyISBN(String copyISBN);
	public void removeCopyFromCheckOut(int copyNumber);
	public void completeCheckOut();
}
