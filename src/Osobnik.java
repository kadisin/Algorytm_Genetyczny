import java.util.ArrayList;
import java.util.Random;

public class Osobnik {

	// dwa punkty pocz¹tkowy i koncowy
	private Point poczatek;
	private Point koniec;
	private ArrayList<Point> connectionPoints;

	
	public Osobnik(Point first,Point second)
	{
		this.poczatek = first;
		this.koniec = second;
		connectionPoints = new ArrayList<Point>();
	}
	public ArrayList<Point> getConnectionPoints()
	{
		return connectionPoints;
	}
	public Point getPoczatek()
	{
		return poczatek;
	}
	public Point getKoniec()
	{
		return koniec;
	}
	
	public void addPointToConnection(Point point)
	{
		connectionPoints.add(point);
		
	}
	
	public void clearConnectionPoints()
	{
		connectionPoints.removeAll(connectionPoints);
	}
	
	public String toString()
	{
		String str =  "Poczatek: " + poczatek.toString() +"\n" 
				+ "Koniec: " + koniec.toString() + "\n";
		
		for (Point p : connectionPoints)
		{
			str += "Punkt posredni: " + p.toString() + "\n";
		}
		
		return str;
	}

	public Boolean createConnection()
	{
		
		Boolean czy_polaczone = false;
		Point akt = new Point(poczatek.getX(),poczatek.getY());
		
		while(!czy_polaczone)
		{
		
		
			// losowanie czy isc poziomo czy pionowo
			
			
			Random rand = new Random();
			Point przesuniecie = new Point();
			

			

			int wylosowana_wart = rand.nextInt(3);
				
				//pion
			if(wylosowana_wart == 0)
			{
					
				if(akt.getX() - this.koniec.getX() > 0)
				{
					przesuniecie.setValue(-1, 0);
				}
				else
				{
					przesuniecie.setValue(1, 0);
				}
					
					
			}
				// poziom
			else if (wylosowana_wart == 1)
			{
				if(akt.getY() - this.koniec.getY() > 0)
				{
					przesuniecie.setValue(0, -1);
				}
				else
				{
					przesuniecie.setValue(0, 1);
				}
					
					
			}
			else
			{
				int losowa = rand.nextInt(4);
				if(losowa == 0)
					przesuniecie.setValue(0, 1);
				else if (losowa == 1)
					przesuniecie.setValue(0, -1);
				else if( losowa == 2)
					przesuniecie.setValue(1, 0);
				else
					przesuniecie.setValue(-1, 0);
			}
				
			
			
			// posiadamy juz punkt przesuniecia
			akt.Sum(przesuniecie);
			connectionPoints.add(new Point(akt.getX(),akt.getY()));
			if(akt.checkPosition(koniec))
			{	
				czy_polaczone = true;
				connectionPoints.remove(connectionPoints.size()-1);
			}
			
		}
		

		
		

		return true;
	}
	
	public Boolean checkConnection()
	{
		for (Point point : connectionPoints)
		{
			for (Point point_ : connectionPoints)
			{
				if(point != point_)
				{
					if(point.checkPosition(point_) || point.checkPosition(poczatek) || point.checkPosition(koniec))
					{
						return false;
						
					}
					
				}
			}
		}
		return true;
		
	}
	
	
}
