
public class Actor extends Tile {

	public int myX;
	public int myY; 
	public String myName; 
	public static int myLevel; 
	public int myHP;
	
	public Actor(int x, int y, String name, int level) {
		super(x, y);
		myName = name; 
		setHP(myLevel * 10);
	}

	public void setHP(int h)
	{
		myHP = h;
	}
	
	public int getHP()
	{
		return myHP; 
	}
	
	public int getLevel()
	{
		return myLevel; 
	}
	
	public String getName()
	{
		return myName; 
	}

	Boolean isAc() 
	{
		return false;
	}
	
}
