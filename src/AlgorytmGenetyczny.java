import java.util.ArrayList;

public class AlgorytmGenetyczny {

	
	private ArrayList<PlytkaPCB> population;
	private String fileName;
	
	
	public AlgorytmGenetyczny(String fileName)
	{
		this.fileName = fileName;
		population = new ArrayList<PlytkaPCB>();
	}
	
	public void init_population(int amountOfPopulation)
	{
		for(int ii = 0 ; ii < amountOfPopulation ; ii ++)
		{
			PlytkaPCB pcb = new PlytkaPCB();
			pcb.wczytajDane(fileName);
			//pcb.vPrintSettings();
			pcb.metodaLosowa();
			population.add(pcb);
		}
		
	}
	
	public void wypiszWszystkieDlugosci()
	{
		int nr_osobnika = 1;
		for( PlytkaPCB plytka : population)
		{
			System.out.println("Osobnik nr: " + nr_osobnika);
			System.out.println("Dlugosc sciezki: " + plytka.funkcjaOcenyDlugoscSciezki());
			System.out.println("Ilosc przeciec: " + plytka.funkcjaOcenyIloscPrzeciec() + "\n\n");
			nr_osobnika++;
		}
		//System.out.println("\n\nWyglad przykladowej plytkiPCB");
		//population.get(0).vPrintSettings();
		//population.get(0).vPrint();
		//System.out.println("Dlugosc sciezki: " + population.get(0).funkcjaOcenyDlugoscSciezki());
		
	}
	
	
}
