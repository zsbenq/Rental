
public class Record {
	private Copy copy = null;
	private long dueTime = 0;
	private boolean isReturned = false;
	
	public Copy getCopy() {
		return copy;
	}
	public void setCopy(Copy copy) {
		this.copy = copy;
	}
	public long getDueTime() {
		return dueTime;
	}
	public void setDueTime(long dueTime) {
		this.dueTime = dueTime;
	}
	
	public boolean isReturned() {
		return isReturned;
	}
	public void setReturned(boolean isReturned) {
		this.isReturned = isReturned;
	}
	
}
