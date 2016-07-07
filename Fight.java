import java.util.Scanner;


public class Fight {

	static Enemy Mye;
	static Boolean over;
	static int seHP;
	static int spHP; 
	
	public Fight(Enemy e)
	{
		Mye = e;
		over = false;
		seHP =  Mye.getHP();
		spHP = Runner.getPlayer().getHP();
	}
	
	public boolean getOver()
	{
		return over;
	}
	
	public int fight()
	{
		while(!over)
		{
			if(Mye.getHP() <= 0) return 1;
			if(Runner.getPlayer().getHP() <= 0) return 2;
			System.out.println("\n \t \t \t \t " + Runner.getPlayer().getName() + "'s HP:" + Runner.getPlayer().getHP()); 
			System.out.println("\t \t \t \t " + Mye.getName() + "'s HP:" + Mye.getHP() + "\n"); 
			String action = "-1"; 
				while(action.equals("-1"))
				{
					System.out.print("\t \t \t \t Fight or run? (F/R) ");
					Scanner sc = new Scanner(System.in); 
					action = sc.next().toUpperCase().substring(0,1);
					if(action.equals("R"))
					{
						System.out.println("\t \t \t \t You ran away. \n");
						over = true;
						return 3;
					} else if(action.equals("F"))
					{
						int y = (int) ((Math.random() * 2));
						if(y == 1)
							{
						pAttack(); 
						if(Mye.getHP() <= 0) return 1;
						eAttack(); 
						if(Runner.getPlayer().getHP() <= 0) return 2;
							} else {
								eAttack(); 
								if(Runner.getPlayer().getHP() <= 0) return 2;
								pAttack(); 
								if(Mye.getHP() <= 0) return 1;
							}
					}
				}
		}
		return 3;
	}
	
	private void eAttack()
	{
		int damage = (int) ((Math.random() * (seHP * .5)) + (.3 * spHP));
		if(damage == 0) damage = 1; 
		System.out.println("\n \t \t \t \t " + Mye.getName() + " dealt " + damage + " damage to " + Runner.getPlayer().getName()); 
		Runner.getPlayer().setHP(Runner.getPlayer().getHP() - damage);
	}
	
	private void pAttack()
	{
		int damage = (int) ((Math.random() * (spHP * .5)) + (.5 * seHP));
		System.out.println("\n \t \t \t \t " + Runner.getPlayer().getName() + " dealt " + damage + " damage to " + Mye.getName()); 
		Mye.setHP(Mye.getHP() - damage);
	}
	

	
	
	
	
	
	
	
	
	
	
	
	
}
