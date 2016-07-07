import java.util.Random;

public class Dungeon {

	private static Tile[][] myFloor; 
	private int doorX;
	private int doorY;

	public Dungeon(int level)
	{
		myFloor = new Tile[(int) (Math.random() * 10) + 10][(int) (Math.random() * 10) + 10];
		doorX = (int) (Math.random() * myFloor.length);
		doorY = (int) (Math.random() * myFloor[0].length);
		myFloor[doorX][doorY] = new Door(doorX, doorY); 
		placeWall();
		placePath();
		placeShop();
		placeNewPlayer(); 
		placeEnemies();
		extra();
	}
	
	public Enemy getEnemy(int x, int y)
	{
		return (Enemy) myFloor[x][y];
	}
	
	public void setShop(Shop s, int x, int y)
	{
		myFloor[x][y] = s; 
	}
	
	public Shop getShop(int x, int y)
	{
		return (Shop) myFloor[x][y];
	}
	
	
	private void placeShop() {
		int x = doorX; 
		int y = doorY;
		while(x == doorX && y == doorY)
		{
		x = (int) (Math.random() * myFloor.length);
		y = (int) (Math.random() * myFloor[0].length);
		if(myFloor[x][y] instanceof Path)
			{
			myFloor[x][y] = new Shop(x, y, "Shop", -1);
			//getShop(x,y).setPrices(-1, -1);
			}
		else 
		{
			x = doorX;
			y = doorY;
		}
		}
	}

	public Tile[][] getMap()
	{
		return myFloor;
	}
	
	public int getEnemies()
	{
		int e = 0;
		for(int r = 0; r < myFloor.length; r++)
		{
			for(int c = 0; c < myFloor[0].length; c++)
			{
				if(myFloor[r][c] instanceof Enemy) e++; 
			}
		}
		return e;
		
	}
	
	public Tile getTile(int x, int y)
	{
		return myFloor[x][y];
	}
	
	private void placeNewPlayer() {
		Boolean placed = false; 
		while(!placed)
		{
			for(int r = 0; r < myFloor.length; r++)
			{
				for(int c = 0; c < myFloor[0].length; c++)
				{ 
					if(myFloor[r][c] instanceof Path && ((r + 1 < myFloor.length && myFloor[r + 1][c] instanceof Path) || 
							(c + 1 < myFloor[0].length && myFloor[r][c + 1] instanceof Path) || 
							(r - 1 > -1 && myFloor[r - 1][c] instanceof Path) || (c - 1 > -1 && myFloor[r][c - 1] instanceof Path))) 
					{
						Runner.getPlayer().setX(r);
						Runner.getPlayer().setY(c);
						myFloor[r][c] = Runner.getPlayer(); 
						placed = true; 
						if(r - 1 >= 0 && c - 1 >= 0) setTile(r - 1, c - 1);
						if(r - 1 >= 0 && c + 1 < myFloor[0].length) setTile(r - 1, c + 1);
						if(r + 1 < myFloor.length && c + 1 < myFloor[0].length) setTile(r + 1, c + 1);
						if(r + 1 < myFloor.length && c - 1 >= 0) setTile(r + 1, c - 1);
						return; 
					}
				}
			}
		}
		
	}

	void setTile(int x, int y)
	{
		Path t = new Path(x, y); 
		myFloor[x][y] = t; 
	}
	
	Object getType(int x, int y)
	{
		return myFloor[x][y];
	}
	
	void placePlayer(int x, int y) 
	{
		myFloor[x][y] = Runner.getPlayer();
	}
	
	private void placeEnemies() {
		
		int numEnemies = (int) (Math.random() * 10) + 1;
		
		while(numEnemies > 0)
		{
			int x = (int) (Math.random() * myFloor.length);
			int y = (int) (Math.random() * myFloor[0].length);
			if(myFloor[x][y] instanceof Path)
			{
				myFloor[x][y] = new Enemy(x, y, "Enemy", Runner.getPlayer().getLevel());
				numEnemies--; 
			}
		}
	}

	private void placeWall() {
		for(int r = 0; r < myFloor.length; r++)
		{
			for(int c = 0; c < myFloor[0].length; c++)
			{
				if(r != doorX && c != doorY) myFloor[r][c] = new Wall(r, c);
			}
		}
	}
	
	private void placePath() {
		System.out.println(); 
		System.out.println("\t \t \t \t \t LOADING DUNGEON...");
		System.out.println();
		int numPaths = (int) (Math.random() * 3) + 1; 
		while(numPaths >= 0)
		{
			int newX = doorX;
			int newY = doorY;
			while(newX == doorX || newY == doorY)
			{
				newX = (int) (Math.random() * myFloor.length);
				newY = (int) (Math.random() * myFloor[0].length);
			}
			Random random = new Random();
			while(newX != doorX && newY != doorY)
			{
				int x = random.nextInt(3) - 1;  
				int y = random.nextInt(3) - 1; 
				if(newX + x < myFloor.length && newX + x >= 0) newX += x;
				if(newY + y < myFloor[0].length && newY + y >= 0) newY += y;
				myFloor[newX][newY] = new Path(newX, newY);
			}
			numPaths--;
		}
		
	}

	private void extra()
	{
		int numPaths = ((int) (Math.random() * 10) + 1) * ((int) (Math.random() * 3) + 1); 
		
		for(int i = 0; i < numPaths; i++)
		{
			int r = (int) (Math.random() * myFloor.length);
			int c = (int) (Math.random() * myFloor[0].length);
			if(myFloor[r][c] instanceof Wall) myFloor[r][c] = new Path(r, c); 
		}	
		
		if(doorX - 1 >= 0 && doorY - 1 >= 0) setTile(doorX - 1, doorY - 1);
		if(doorX - 1 >= 0 && doorY + 1 < myFloor[0].length) setTile(doorX - 1, doorY + 1);
		if(doorX + 1 < myFloor.length && doorY + 1 < myFloor[0].length) setTile(doorX + 1, doorY + 1);
		if(doorX + 1 < myFloor.length && doorY - 1 >= 0) setTile(doorX + 1, doorY - 1);
	}
	
	
	
	
	public void Print()
	{
		if(!(myFloor[doorX][doorY] instanceof Door)) myFloor[doorX][doorY] = new Door(doorX, doorY);
		
		
		for(int r = 0; r < myFloor.length; r++)
		{
			for(int c = 0; c < myFloor[0].length; c++)
			{
				if(c == 0) System.out.print("\t \t \t \t \t");
				if(myFloor[r][c] == null) myFloor[r][c] = new Path(r, c);
				System.out.print(myFloor[r][c].toString());
				if(c == myFloor[0].length - 1) System.out.println(); 
			}
		}
	}

	
	
}
