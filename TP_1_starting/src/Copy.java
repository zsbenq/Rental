
public class Copy
{
	private String copyID = null;
	private String condition = null;
	private Patron previousHolder = null;
	private Patron currentHolder = null;
	private String title = null;
	private String author = null;
	private int dueDays = 0;
	private boolean isInStock = true;
	
	public String getCopyID() {
		return copyID;
	}
	public void setCopyID(String copyID) {
		this.copyID = copyID;
	}
	public String getCondition() {
		return condition;
	}
	public void setCondition(String condition) {
		this.condition = condition;
	}

	public Patron getPreviousHolder() {
		return previousHolder;
	}
	public void setPreviousHolder(Patron previousHolder) {
		this.previousHolder = previousHolder;
	}
	public Patron getCurrentHolder() {
		return currentHolder;
	}
	public void setCurrentHolder(Patron currentHolder) {
		this.currentHolder = currentHolder;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public int getDueDays() {
		return dueDays;
	}
	public void setDueDays(int dueDays) {
		this.dueDays = dueDays;
	}
	public boolean isInStock() {
		return isInStock;
	}
	public void setInStock(boolean isInStock) {
		this.isInStock = isInStock;
	}

	
}
