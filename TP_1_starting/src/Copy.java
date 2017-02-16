
public class Copy
{
	private String copyID;
	private Patron outTo;

	// following generated in Eclipse Source menu

	public Patron getOutTo()
	{
		return outTo;
	}

	public void setOutTo(Patron outTo)
	{
		this.outTo = outTo;
	}

	public String getCopyID()
	{
		return copyID;
	}

	public Copy(String cid)
	{
		this.copyID = cid;
	}

	public String toString()
	{
		return "Copy w/id= " + this.copyID;
	}

	@Override
	public boolean equals(Object o)
	{
		if (!(o instanceof Copy))
			return false;

		return ((Copy) o).getCopyID().equals(this.copyID); // yuck.
	}

	public static void main(String[] args)
	{
		Copy c1 = new Copy("0047");
		Patron p1 = new Patron("James", "007");

		System.out.println(c1);
		System.out.println(p1);
	}
}
