
public abstract class Tile {

	private int myX;
	private int myY;
	private Boolean isAc;
	
	public Tile(int x, int y)
	{
		myX = x;
		myY = y;
	}
	
	public int getX()
	{
		return myX;
	}
	
	public int getY()
	{
		return myY;
	}
	
	abstract Boolean isAc();
}
