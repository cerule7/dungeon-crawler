
public class Potion extends Item {

	private int HP; 
	
	public Potion(int x, int y)
	{
		super(x, y);
		HP = (int) ((Math.random() * Runner.getPlayer().getLevel() * 10) + 10);
	}
	
	public void use()
	{
		Runner.getPlayer().setHP(Runner.getPlayer().getHP() + HP);
		System.out.println("\t \t \t \t Restored " + HP + "HP.");
	}
	
	public String toString()
	{
		return "It's a magic potion. Restores " + HP + " HP"; 
	}
	
}
