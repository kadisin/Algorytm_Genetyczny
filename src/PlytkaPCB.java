import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class PlytkaPCB {

	private Integer wysokosc;
	private Integer szerokosc;
	private ArrayList<Osobnik> listOsobnik;

	
	
	
	public PlytkaPCB(int wys,int szer)
	{
		wysokosc = wys;
		szerokosc = szer;
		listOsobnik = new ArrayList<Osobnik>();
	}
	
	public PlytkaPCB()
	{
		wysokosc = 0;
		szerokosc = 0;
		listOsobnik = new ArrayList<Osobnik>();
	}

	
	public void vPrintSettings()
	{
		System.out.println("Plytka PCB");
		System.out.println("Wysokosc: " + wysokosc);
		System.out.println("Szerokosc: " + szerokosc);
		for (Osobnik osobnik : listOsobnik)
			System.out.println(osobnik.toString());
	}
	
	public Boolean wczytajDane(String fileName)
	{
		
		FileReader fr = null;
		String linia = "";
		
		try 
		{
			fr = new FileReader(fileName);
		} catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
		
		BufferedReader bfr = new BufferedReader(fr);
		int indeks = 0;
		
		try
		{
			while((linia = bfr.readLine()) != null)
			{
				//plytka rozmiar
				if(indeks == 0)
				{
					String[] rozmiary = linia.split(";");
					this.wysokosc = Integer.parseInt(rozmiary[0]);
					this.szerokosc = Integer.parseInt(rozmiary[1]);
				}
				// punkty poczatkowe/koncowe
				else
				{
					String[] wspolrzedne_punktow = linia.split(";");
					Point pocz = new Point(wspolrzedne_punktow[0],wspolrzedne_punktow[1]);
					Point konc = new Point(wspolrzedne_punktow[2],wspolrzedne_punktow[3]);
					Osobnik osobnik = new Osobnik(pocz,konc);
					listOsobnik.add(osobnik);
				}
				indeks++;
			}
		} catch (IOException e)
		{
			e.printStackTrace();
		}
		
		
		
		
		try
		{
			fr.close();
		} catch (IOException e)
		{
			e.printStackTrace();
		}
		
		
		return true;
	}
	
	public void addOsobnik(Osobnik osobnik)
	{
		listOsobnik.add(osobnik);
	}
	
	public void vPrint()
	{
		int id = 1;
		int[][] tablica_point = new int[wysokosc][szerokosc];
		for(Osobnik osobnik : listOsobnik)
		{
			
			for(Point p : osobnik.getConnectionPoints())
			{
				tablica_point[osobnik.getPoczatek().getX()][osobnik.getPoczatek().getY()] = id;
				tablica_point[osobnik.getKoniec().getX()][osobnik.getKoniec().getY()] = id;
				tablica_point[p.getX()][p.getY()] = id;
			}
			id += 1;
		}
		
		
		
		for (int ii = 0 ; ii < szerokosc ; ii ++)
		{
			if( ii == 0)
				System.out.print("  " + ii + " ");
			else 
				System.out.print(" " + ii + " ");
		}
		System.out.println("");
		for (int ii = 0 ; ii < wysokosc ; ii ++)
		{
			System.out.print(ii);
			for (int kk = 0 ; kk < szerokosc ; kk ++)
			{
				
				if(tablica_point[ii][kk] == 1)
					System.out.print(" x ");
				else if (tablica_point[ii][kk] == 2)
					System.out.print(" o ");
				else	
					System.out.print(" . ");
			}
			System.out.println("\n");
		}
	}
	
	
	public Boolean checkLimitations()
	{
		for(Osobnik osobnik : listOsobnik)
		{
			for(Osobnik osobnik_ : listOsobnik)
			{
				if(osobnik != osobnik_)
				{
					for(Point p_1 : osobnik.getConnectionPoints())
					{
						for(Point p_2 : osobnik_.getConnectionPoints())
						{
							if(p_1.checkPosition(p_2))
								return false;
							if(p_1.checkPosition(osobnik_.getKoniec()) || p_1.checkPosition(osobnik_.getPoczatek()))
								return false;
						}
						
					}
					
				}
			}
		}
		
		// sprawdzenie czy nie wychodze poza plytke 
		for (Osobnik osobnik : listOsobnik)
		{
			for(Point point : osobnik.getConnectionPoints())
			{
				if(point.getX() >= szerokosc || point.getY() >= wysokosc)
					return false;
			}
		}
		
	
		
		return true;
	}
	
	public PlytkaPCB metodaLosowa()
	{
		//System.out.println("Inicjacja populacji...");
		Boolean isCorrect = false;
		while(!isCorrect)
		{
			
			for(Osobnik osobnik : listOsobnik)
			{
				osobnik.createConnection();
				//System.out.println(osobnik.toString());
			}
			isCorrect = this.checkLimitations();
			// 
			// Dopuszczenie do populacji obiektow z przecieciami!
			//
			isCorrect = true;
			// 
			// Dopuszczenie do populacji obiektow z przecieciami!
			//
			
			
			// zapobiegniecie cofania sie pointow!
			for (Osobnik osobnik : listOsobnik)
			{
				if(osobnik.checkConnection() == false)
					isCorrect = false;
			}
			
			if(!isCorrect)
			{
				for(Osobnik osobnik : listOsobnik)
				{
					osobnik.clearConnectionPoints();
				}
			}
			
			
		}
		//System.out.println("Pomyslnie utworzono populacje inicjujaca!");
		//this.vPrint();
		//System.out.println("Funkcja oceny - laczna dlugosc sciezek: " + this.funkcjaOcenyDlugoscSciezki());
		//System.out.println("Funkcja oceny - ilosc przeciec: " + this.funkcjaOcenyIloscPrzeciec());
		return this;
	}
	
	public int funkcjaOcenyDlugoscSciezki()
	{
		int wynik = 0;
		for (Osobnik osobnik : listOsobnik)
		{
			for (Point point : osobnik.getConnectionPoints())
			{
				wynik += 1;
			}
			
			
		}
		return wynik;	
	}
	
	public int funkcjaOcenyIloscPrzeciec()
	{
		int wynik = 0;
		
		for (Osobnik osobnik : listOsobnik)
			for (Osobnik osobnik_ : listOsobnik)
				if(osobnik != osobnik_)
					for(Point point : osobnik.getConnectionPoints())
						for(Point point_ : osobnik_.getConnectionPoints())
							if(point != point_)
								if(point.checkPosition(point_))
									wynik += 1;
								
		return wynik;
		
	}
	
	
	
}
