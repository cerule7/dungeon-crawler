import java.util.Scanner;

public class Door extends Item{
	
	public Door(int x, int y)
	{
		super(x, y);
	}
	
	public static Boolean ask()
	{
		Runner.getPlayer();
		if(Player.seeKey())
		{
			String a = "-1";
			while(a.equals("-1"))
			{
				System.out.print("\t \t \t \t Do you want to proceed? (Y/N) ");
				Scanner s = new Scanner(System.in);
				a = s.next().toUpperCase().substring(0,1);
				if(a.equals("Y")) return true;
				else if(a.equals("N")) return false;
				else a = "-1";
			}
		}
		else {
			System.out.println("\t \t \t \t You need a key to proceed.");
		}
		return false;
	}
	
	
	public String toString()
	{
		return "D";
	}
	
	
}
