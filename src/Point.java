
public class Point {

	private Integer x;
	private Integer y;
	
	public Point(int x,int y)
	{
		this.x = x;
		this.y = y;
	}
	
	public Point()
	{
		x = 0;
		y = 0;
	}
	
	public Point(String x, String y)
	{
		this.x = Integer.parseInt(x);
		this.y = Integer.parseInt(y);
	}
	
	public void setValue(int x,int y)
	{
		this.x = x;
		this.y = y;
	}
	
	public Integer getX()
	{
		return x;
	}
	public Integer getY()
	{
		return y;
	}
	
	public String toString()
	{
		return "Point(" + x.toString() + "," + y.toString() + ")";
	}
	
	public void Sum(Point other)
	{
		this.x = this.x + other.x;
		this.y = this.y + other.y;
		
	}
	
	public Boolean checkPosition(Point other)
	{
		if(this.x == other.x && this.y == other.y)
			return true;
		return false;
	}
	
}
