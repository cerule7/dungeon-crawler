import java.util.Scanner;

public class Runner {

	private static Tile[][] myMap;
	private static Dungeon myDungeon;
	private static Player thePlayer; 
	private static boolean run; 
	private static String myName;
	
	public static void main(String args[])
	{
		System.out.println(" ██▄     ▄      ▄     ▄▀  ▄███▄   ████▄    ▄       ▄█▄    █▄▄▄▄ ██     ▄ ▄   █     ▄███▄   █▄▄▄▄ \n" + 
							"█  █     █      █  ▄▀    █▀   ▀  █   █     █      █▀ ▀▄  █  ▄▀ █ █   █   █  █     █▀   ▀  █  ▄▀ \n" + 
							"█   █ █   █ ██   █ █ ▀▄  ██▄▄    █   █ ██   █     █   ▀  █▀▀▌  █▄▄█ █ ▄   █ █     ██▄▄    █▀▀▌ \n"  +
							"█  █  █   █ █ █  █ █   █ █▄   ▄▀ ▀████ █ █  █     █▄  ▄▀ █  █  █  █ █  █  █ ███▄  █▄   ▄▀ █  █ \n" +
							"███▀  █▄ ▄█ █  █ █  ███  ▀███▀         █  █ █     ▀███▀    █      █  █ █ █      ▀ ▀███▀     █ \n" +
							"▀▀▀ 	 █  	 ██                     █   ██             ▀      █    ▀ ▀                  ▀    \n" +
			                "                                                				                                 \n"); 
		System.out.println("\t \t \t \t ''#'' = Wall \t ''='' = Path"); 
		System.out.println("\t \t \t \t ''S'' = Shop \t ''D'' = Door");
		System.out.println("\t \t \t \t ''E'' = Enemy \t ''P'' = Player");
		System.out.print("\n \t \t \t \t   What's your name? ");
		Scanner sc = new Scanner(System.in);
		myName = sc.next();
		game();
	}
	
	
	public static void game()
	{
		String action = "-1"; 
		Scanner sc = new Scanner(System.in);
		thePlayer = new Player(0, 0, myName, 1);
		myDungeon = new Dungeon(1);
		myMap = myDungeon.getMap();
		run = true;
		while(thePlayer.getHP() > 0 && run)
		{
		myDungeon.Print();
		action = "-1"; 
		while(action.equals("-1"))
		{
			System.out.println(); 
			System.out.print("\t \t \t MOVE (U/R/L/D), STATS (S), POTION (P), BOMB (B) OR INVENTORY (I)? ");
			action = sc.next().substring(0, 1);
			if(action.toUpperCase().equals("S"))
			{
				System.out.println("\n \t \t \t \t \t " + myName + "'S STATS " + "\n \t \t \t \t \t LVL: " + thePlayer.getLevel() + "\n" + ("\t \t \t \t \t HP: " + thePlayer.getHP()) + "\n" + ("\t \t \t \t \t GOLD: " + thePlayer.getGold()) + "\n");
			}
			else if(action.toUpperCase().equals("I"))
			{
				thePlayer.printInv(); 
			}
			else if(action.toUpperCase().equals("P"))
			{
				thePlayer.usePotion(); 
			}
			else if(action.toUpperCase().equals("B"))
			{
				thePlayer.useBomb(); 
			}
			else {
				if(action.toUpperCase().equals("U") && thePlayer.getX() - 1 > -1 && myMap[thePlayer.getX() - 1][thePlayer.getY()].isAc())
				{
					if(myMap[thePlayer.getX() - 1][thePlayer.getY()] instanceof Door)
					{
						if(Door.ask())
						{
							myDungeon = new Dungeon(thePlayer.getLevel() + 1);
							thePlayer.setLevel(thePlayer.getLevel() + 1);
							thePlayer.setHP(thePlayer.getLevel() * 10);
							thePlayer.setKey(false);
							myMap = myDungeon.getMap();
						}
					} else if(myMap[thePlayer.getX() - 1][thePlayer.getY()] instanceof Enemy) {
						Fight f = new Fight(myDungeon.getEnemy(thePlayer.getX() - 1, thePlayer.getY()));
						if(f.fight() == 1)
						{
							victory();
							myDungeon.setTile(thePlayer.getX(), thePlayer.getY());
							thePlayer.setX(thePlayer.getX() - 1);
							myDungeon.placePlayer(thePlayer.getX(), thePlayer.getY());
						} else if(f.fight() == 2){
							gameOver();
							break;
						} else {
							break;
						}
					} else if(myMap[thePlayer.getX() - 1][thePlayer.getY()] instanceof Shop) {
						if(myDungeon.getShop(thePlayer.getX() - 1, thePlayer.getY()).getPrice() == -1)
						{
							myDungeon.setShop(new Shop(thePlayer.getX() - 1, thePlayer.getY(), "Shop", -1), thePlayer.getX() - 1, thePlayer.getY());
							myDungeon.getShop(thePlayer.getX() - 1, thePlayer.getY()).Speak();
						}  else 
						{
							myDungeon.getShop(thePlayer.getX() - 1, thePlayer.getY()).Speak();
						}
						
					}
					else {
					myDungeon.setTile(thePlayer.getX(), thePlayer.getY());
					thePlayer.setX(thePlayer.getX() - 1);
					myDungeon.placePlayer(thePlayer.getX(), thePlayer.getY());
					}
				} else if(action.toUpperCase().equals("R") && thePlayer.getY() + 1 < myMap[0].length && myMap[thePlayer.getX()][thePlayer.getY() + 1].isAc())
				{
					if(myMap[thePlayer.getX()][thePlayer.getY() + 1] instanceof Door)
					{
						if(Door.ask())
						{
							myDungeon = new Dungeon(thePlayer.getLevel() + 1);
							thePlayer.setLevel(thePlayer.getLevel() + 1);
							thePlayer.setHP(thePlayer.getLevel() * 10); 
							thePlayer.setKey(false);
							myMap = myDungeon.getMap();
						}
					}
					else if(myMap[thePlayer.getX()][thePlayer.getY() + 1] instanceof Enemy) {
						Fight f = new Fight(myDungeon.getEnemy(thePlayer.getX(), thePlayer.getY() + 1));
						if(f.fight() == 1)
						{
							victory();
							myDungeon.setTile(thePlayer.getX(), thePlayer.getY());
							thePlayer.setY(thePlayer.getY() + 1);
							myDungeon.placePlayer(thePlayer.getX(), thePlayer.getY());
						} else if(f.fight() == 2){
							gameOver();
							break;
						} else {
							break;
						}
					} else if(myMap[thePlayer.getX()][thePlayer.getY() + 1] instanceof Shop) {
						if(myDungeon.getShop(thePlayer.getX(), thePlayer.getY() + 1).getPrice() == -1)
						{
							myDungeon.setShop(new Shop(thePlayer.getX(), thePlayer.getY() + 1, "Shop", -1), thePlayer.getX(), thePlayer.getY() + 1);
							myDungeon.getShop(thePlayer.getX(), thePlayer.getY() + 1).Speak();
						}  else 
						{
							myDungeon.getShop(thePlayer.getX(), thePlayer.getY() + 1).Speak();
						}
					}
					else {
					myDungeon.setTile(thePlayer.getX(), thePlayer.getY());
					thePlayer.setY(thePlayer.getY() + 1);
					myDungeon.placePlayer(thePlayer.getX(), thePlayer.getY());
					}
				} else if(action.toUpperCase().equals("D") && thePlayer.getX() + 1 < myMap.length && myMap[thePlayer.getX() + 1][thePlayer.getY()].isAc())
				{
					if(myMap[thePlayer.getX() + 1][thePlayer.getY()] instanceof Door)
					{
						if(Door.ask())
						{
							myDungeon = new Dungeon(thePlayer.getLevel() + 1);
							thePlayer.setLevel(thePlayer.getLevel() + 1);
							thePlayer.setHP(thePlayer.getLevel() * 10); 
							thePlayer.setKey(false);
							myMap = myDungeon.getMap();
						}
					} else if(myMap[thePlayer.getX() + 1][thePlayer.getY()] instanceof Enemy) {
						Fight f = new Fight(myDungeon.getEnemy(thePlayer.getX() + 1, thePlayer.getY()));
						if(f.fight() == 1)
						{
							victory();
							myDungeon.setTile(thePlayer.getX(), thePlayer.getY());
							thePlayer.setX(thePlayer.getX() + 1);
							myDungeon.placePlayer(thePlayer.getX(), thePlayer.getY());
						} else if(f.fight() == 2){
							gameOver();
							break;
						} else {
							break;
						}
					} else if(myMap[thePlayer.getX() + 1][thePlayer.getY()] instanceof Shop) {
						if(myDungeon.getShop(thePlayer.getX() + 1, thePlayer.getY()).getPrice() == -1)
						{
							myDungeon.setShop(new Shop(thePlayer.getX() + 1, thePlayer.getY(), "Shop", -1), thePlayer.getX() + 1, thePlayer.getY());
							myDungeon.getShop(thePlayer.getX() + 1, thePlayer.getY()).Speak();
						}  else 
						{
							myDungeon.getShop(thePlayer.getX() + 1, thePlayer.getY()).Speak();
						}
					} else {
					myDungeon.setTile(thePlayer.getX(), thePlayer.getY());
					thePlayer.setX(thePlayer.getX() + 1);
					myDungeon.placePlayer(thePlayer.getX(), thePlayer.getY());
					}
				} else if(action.toUpperCase().equals("L") && thePlayer.getY() - 1 > -1 && myMap[thePlayer.getX()][thePlayer.getY() - 1].isAc())
				{
					if(myMap[thePlayer.getX()][thePlayer.getY() - 1] instanceof Door)
					{
						if(Door.ask())
						{
							myDungeon = new Dungeon(thePlayer.getLevel() + 1);
							thePlayer.setLevel(thePlayer.getLevel() + 1);
							thePlayer.setHP(thePlayer.getLevel() * 10); 
							thePlayer.setKey(false);
							myMap = myDungeon.getMap();	
						}
					} else if(myMap[thePlayer.getX()][thePlayer.getY() - 1] instanceof Enemy) {
						Fight f = new Fight(myDungeon.getEnemy(thePlayer.getX(), thePlayer.getY() - 1));
						if(f.fight() == 1)
						{
							victory();
							myDungeon.setTile(thePlayer.getX(), thePlayer.getY());
							thePlayer.setY(thePlayer.getY() - 1); 
							myDungeon.placePlayer(thePlayer.getX(), thePlayer.getY());
						} else if(f.fight() == 2){
							gameOver();
							break;
						} else {
							break;
						}
					}  else if(myMap[thePlayer.getX()][thePlayer.getY() - 1] instanceof Shop) {
						if(myDungeon.getShop(thePlayer.getX(), thePlayer.getY() - 1).getPrice() == -1)
						{
							myDungeon.setShop(new Shop(thePlayer.getX(), thePlayer.getY() - 1, "Shop", -1), thePlayer.getX(), thePlayer.getY() - 1);
							myDungeon.getShop(thePlayer.getX(), thePlayer.getY() - 1).Speak();
						}  else 
						{
							myDungeon.getShop(thePlayer.getX(), thePlayer.getY() - 1).Speak();
						}
					} else {
					myDungeon.setTile(thePlayer.getX(), thePlayer.getY());
					thePlayer.setY(thePlayer.getY() - 1); 
					myDungeon.placePlayer(thePlayer.getX(), thePlayer.getY());
					}
				} else {
					System.out.println("\t \t \t \t Please enter a valid move.");
					action = "-1";
				}
			}
		}
		}
	}
	
	private static void victory()
	{
	if(myDungeon.getEnemies() <= 1 && !(thePlayer.seeKey())) 
	{
		thePlayer.setKey(true);
		System.out.println("\t \t \t \t You won. It dropped a shiny golden key. \n");
	} else {
	int y = (int) ((Math.random() * 10));
		if(y == 1)
			{
			thePlayer.addItem(new Potion(-1, -1));
			System.out.println("\t \t \t \t You won. You got a potion! \n");
			}
		else if(!(thePlayer.seeKey()) && y >= 8)
		{
			thePlayer.setKey(true);
			System.out.println("\t \t \t \t You won. It dropped a shiny golden key. \n");
		}
		else {
			Gold g = new Gold(-1, -1); 
			thePlayer.addGold(g);
			System.out.println("\t \t \t \t You won. It dropped " + g.getValue() + " gold! \n");
			}
		}
	}
	
	private static void gameOver()
	{
		run = false;
		System.out.println("\n \t \t \t \tGAME OVER: YOU DIED" + "\n \t \t \t \t" + "LVL: " + thePlayer.getLevel() + "\n \t \t \t \t" + "GOLD: " + thePlayer.getGold());
		
		Scanner sc = new Scanner(System.in);
		String act = "-1";
		while(act.equals("-1")){
			System.out.print("\t \t \t \tPLAY AGAIN? (Y/N) ");
			act = sc.next();
			if(act.substring(0,1).toUpperCase().equals("Y"))
			{
				game();
			} else {
				return; 
			}
		}
	
	}

	public static Player getPlayer() {
		// TODO Auto-generated method stub
		return thePlayer;
	}
	

	public static Dungeon getDungeon() {
		// TODO Auto-generated method stub
		return myDungeon;
	}
	
	
	
}
