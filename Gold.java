
public class Gold extends Item{

	private int value; 
	
	public Gold(int x, int y)
	{
		super(x, y);
		value = (int) ((Math.random() * 100) + 1);
	}
	
	public int getValue()
	{
		return value;
	}
	
}
