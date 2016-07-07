import java.util.Scanner;


public class Bomb extends Item {

	public Bomb(int x, int y) {
		super(x, y);
	}
	
	public void use()
	{
		
		Scanner sc = new Scanner(System.in);
		String action = "-1";
		System.out.print("\t \t \t \t In which direction will you throw the bomb? (U/R/D/L) ");
		action = sc.next().toUpperCase().substring(0,1);
		if(action.equals("U") && Runner.getPlayer().getX() - 1 > -1 && !(Runner.getDungeon().getTile(Runner.getPlayer().getX() - 1, Runner.getPlayer().getY()).isAc())) Runner.getDungeon().setTile(Runner.getPlayer().getX() - 1, Runner.getPlayer().getY());
		else if(action.equals("R") && Runner.getPlayer().getY() + 1 < Runner.getDungeon().getMap()[0].length && !(Runner.getDungeon().getTile(Runner.getPlayer().getX(), Runner.getPlayer().getY() + 1).isAc())) Runner.getDungeon().setTile(Runner.getPlayer().getX(), Runner.getPlayer().getY() + 1);
		else if(action.equals("D") && Runner.getPlayer().getX() + 1 < Runner.getDungeon().getMap().length && !(Runner.getDungeon().getTile(Runner.getPlayer().getX() + 1, Runner.getPlayer().getY()).isAc())) Runner.getDungeon().setTile(Runner.getPlayer().getX() + 1, Runner.getPlayer().getY());
		else if(action.equals("L") && Runner.getPlayer().getY() - 1 > -1 && !(Runner.getDungeon().getTile(Runner.getPlayer().getX(), Runner.getPlayer().getY() - 1).isAc())) Runner.getDungeon().setTile(Runner.getPlayer().getX(), Runner.getPlayer().getY() - 1);
		else
		{
			System.out.println("\t \t \t \t You can't throw a bomb in that direction.");
			Runner.getPlayer().addItem(new Bomb(1, 1));
			return; 
		}
		System.out.println("\t \t \t \t \t BOOM!");
	}

	public String toString()
	{
		return "B"; 
	}
}