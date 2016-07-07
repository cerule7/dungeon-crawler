import java.util.Scanner;


public class Shop extends Actor {

	private int price;
	private int price2;
	
	public Shop(int x, int y, String name, int level) {
		super(x, y, name, level);
	}
	
	public int getPrice()
	{
		return price; 
	}
	
	public void setPrices()
	{
		price = (int) ((Math.random() * (Runner.getPlayer().getGold()) + ((Runner.getPlayer().getGold()) * .5)));
		price2 = (int) ((Math.random() * (Runner.getPlayer().getGold()) + ((Runner.getPlayer().getGold()) * .5)));
	}
	
	public String toString()
	{
		return "S";
	}

	public Boolean isAc()
	{
		return true;
	}
	
	public void Speak()
	{
		System.out.println("\t \t \t \t This is a shop. You can buy items here.");
		if(Runner.getPlayer().getGold() <= 1) 
		{
			System.out.println("\t \t \t \t You don't have any gold to purchase items.");
			return; 
		}
		if(price == 0 || price2 == 0) setPrices();
		System.out.println("\t \t \t \t Price per potion:" + price + " gold.");
		System.out.println("\t \t \t \t Price per bomb:" + price2 + " gold.");
		Scanner sc = new Scanner(System.in); 
		String action = "-1";
		while(action.equals("-1"))
		{
		System.out.print("\t \t \t \t Buy a potion, bomb, or exit? (P/B/E) ");
		action = sc.next().substring(0,1).toUpperCase();
			if(action.equals("P"))
			{
				if(Runner.getPlayer().getGold() >= price)
				{
					Runner.getPlayer().addItem(new Potion(-1, -1)); 
					Runner.getPlayer().changeGold(price * -1);
					System.out.println("\t \t \t \t You bought a potion. You now have " + Runner.getPlayer().getGold() + " gold."); 
				} else {
					System.out.println("\t \t \t \t You don't have enough gold to purchase a potion.");
				}
				action = "-1";
			} else if(action.equals("B"))
			{
				if(Runner.getPlayer().getGold() >= price2)
				{
					Runner.getPlayer().addItem(new Bomb(-1, -1)); 
					Runner.getPlayer().changeGold(price2 * -1);
					System.out.println("\t \t \t \t You bought a bomb. You now have " + Runner.getPlayer().getGold() + " gold."); 
				} else {
					System.out.println("\t \t \t \t You don't have enough gold to purchase a bomb.");
				}
				action = "-1";
			} else if(action.equals("E")){
				return;
			} else {
				action = "-1";
			}
		}
	}
}
