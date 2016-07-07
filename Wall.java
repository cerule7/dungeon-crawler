
public class Wall extends Item {

	public Wall(int r, int c) {
		super(r, c); 
	}
	
	public String toString()
	{
		return "#";
	}

	public Boolean isAc()
	{
		return false;
	}
}
