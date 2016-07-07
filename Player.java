import java.util.ArrayList; 
public class Player extends Actor {


	private static int myX;
	private static int myY; 
	public int myLevel; 
	private static int myHP;
	private static int myGold; 
	private static ArrayList<Item> myInv; 
	private static boolean haveKey;
	
	public static boolean seeKey()
	{
		return haveKey; 
	}
	
	public void setKey(Boolean b)
	{
		haveKey = b;
	}
	
	public Player(int x, int y, String name, int level)
	{
		super(x, y, name, level);
		myLevel = 1;
		myHP = 10;
		myGold = 0;
		myInv = new ArrayList<Item>(); 
		haveKey = false;
		myInv.add(new Bomb(1, 1)); 
	}
	
	public void addItem(Item i)
	{
		myInv.add(i);
	}
	
	public int potionCount()
	{
		int c = 0;
		for(Item i: myInv)
		{
			if(i instanceof Potion) c++;
		}
		return c;
	}
	
	public int bombCount()
	{
		int c = 0;
		for(Item i: myInv)
		{
			if(i instanceof Bomb) c++;
		}
		return c;
	}
	
	public void usePotion()
	{
		if(potionCount() < 1) System.out.println("\n \t \t \t \t \t You have no potions. \n");
		else 
		{
			for(int c = 0; c < myInv.size(); c++)
			{
				if(myInv.get(c).toString().substring(0,1).equals("I"))
				{
				myInv.get(c).use(); 
				myInv.remove(c);
				return;
				}
			}
		}
	}
	
	public void useBomb()
	{
		if(bombCount() < 1) System.out.println("\t \t \t \t \t You have no bombs. \n");
		else 
		{
			for(int c = 0; c < myInv.size(); c++)
			{
				if(myInv.get(c).toString().equals("B"))
				{
				myInv.get(c).use(); 
				myInv.remove(c);
				return;
				}
			}
		}
	}
	
	public void printInv()
	{
		if(potionCount() == 1) 
		{
			System.out.println("\n \t \t \t \t \t You have 1 potion.");
		} else {
			System.out.println("\n \t \t \t \t \t You have " + potionCount() + " potions.");
		}
		if(bombCount() == 1) 
		{
			System.out.println("\t \t \t \t \t You have 1 bomb. \n");
		} else {
			System.out.println("\t \t \t \t \t You have " + bombCount() + " bombs. \n"); 
		}
			
		
	}
	
	public void setHP(int i)
	{
	 myHP = i; 
	}
	
	public int getHP()
	{
		return myHP; 
	}
	
	public int getGold()
	{
		return myGold; 
	}
	public void addGold(Gold g)
	{
		myGold += g.getValue();  
	}
	
	public void changeGold(int i)
	{
		myGold += i;
	}
	
	public void setLevel(int i)
	{
		myLevel = i;
	}
	
	public int getLevel()
	{
		return myLevel; 
	}
	
	
	public String toString()
	{
		return "P"; 
	}

	public void setX(int r) {
	
		myX = r;
	}
	
	public void setY(int c) {
	
		myY = c;
	}
	
	public int getX() {
	
		return myX;
	}
	
	public int getY() {
		
		return myY;
	}

}
