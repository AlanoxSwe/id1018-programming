public class Point {
	private String name = "";
	private int x, y;
	
	public Point(String name, int x, int y)
	{
		this.name = name;
		this.x = x;
		this.y = y;
		
	}
	public Point(Point p)
	{
		this.name = p.getName();
		this.x = p.getX();
		this.y = p.getY();
	}
	
	public double distance(Point p2)
	{
		int x = p2.getX();
		int y = p2.getY();
		return Math.sqrt(Math.pow(this.x-x, 2) + Math.pow(this.y-y, 2));
	}
	public String toString()
	{
		return "(" + this.name + " " + this.x + " " + this.y + ")";
	}
	public boolean equals(Point p2)
	{
		if(this.x == p2.getX() && this.y == p2.getY() && this.name == p2.getName())
			return true;
		else return false;
	}
	
	
	//GETTERS
	public String getName()
	{
		return this.name;
	}
	public int getX()
	{
		return this.x;
	}
	public int getY()
	{
		return this.y;
	}
	//END OF GETTERS
	
	//SETTERS
	public void setX(int x)
	{
		this.x = x;
	}
	public void setY(int y)
	{
		this.y = y;
	}
	//END OF SETTERS
}