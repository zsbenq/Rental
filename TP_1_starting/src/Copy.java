
public class Copy
{
	private String copyID = null;
	private String condition = null;
	private String status = null;
	private Patron privioutHolder = null;
	private Patron currentHolder = null;
	private String title = null;
	private String author = null;
	
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
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Patron getPrivioutHolder() {
		return privioutHolder;
	}
	public void setPrivioutHolder(Patron privioutHolder) {
		this.privioutHolder = privioutHolder;
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

	
}
