
public interface UserInputProcessor {
	public void selectCheckIn();
	public void selectCheckOut();
	public void selectViewPatronRecord();
	public void scanIdCard(Object scannerObj);
	public void typeIdCardNumber(String idNumber);
	public void scanCopy(Object scannerObj);
	public void typeCopyId(String copyId);
	public void removeCopyFromCheckOut(int copyNumber);
	public void completeCheckOut();
}
