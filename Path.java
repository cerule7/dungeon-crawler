
public  class Path extends Item {

	public Path(int r, int c) {
		super(r, c); 
	}
	
	public void action()
	{
		return; 
	}
	
	public String toString()
	{
		return "=";
	}

	public Boolean isAc()
	{
		return true;
	}
	
}
