
public class MAIN {
/*
	public static void main(String[] args) {
		
		PlytkaPCB plytka = new PlytkaPCB(10,10);

		
		
		Osobnik osobnik = new Osobnik(new Point(5,5),new Point(1,1));
		osobnik.createConnection();
		System.out.println(osobnik.toString());
		
		Osobnik osobnik_2 = new Osobnik(new Point(6,7),new Point(0,0));
		osobnik_2.createConnection();
		System.out.println(osobnik_2.toString());
		
		plytka.addOsobnik(osobnik);
		plytka.addOsobnik(osobnik_2);
		
		if( plytka.checkLimitations() ) 
			plytka.vPrint();
		
	}
*/
 
	public static void main(String[] args)
	{
		//PlytkaPCB plytka = new PlytkaPCB();
		//plytka.wczytajDane("zad0.txt");
		//plytka.vPrintSettings();
		//for (int ii = 0 ; ii < 1000 ; ii ++)
		//{
		//plytka.createAllConections();
		//}
	
		AlgorytmGenetyczny alg = new AlgorytmGenetyczny("zad0.txt");
		alg.init_population(10);
		alg.wypiszWszystkieDlugosci();
	
		System.out.println("\n\nPrzykladowa plytka o okreslonej dlugosci sciezki!");
		
		PlytkaPCB plytka = new PlytkaPCB();
		plytka.wczytajDane("zad0.txt");
		while(plytka.funkcjaOcenyDlugoscSciezki() < 10)
			plytka.metodaLosowa();
		plytka.vPrintSettings();
		plytka.vPrint();
		System.out.println("Dlugosc sciezki : " + plytka.funkcjaOcenyDlugoscSciezki());
		System.out.println("Ilosc przeciec: " + plytka.funkcjaOcenyIloscPrzeciec());
	}
	
}
