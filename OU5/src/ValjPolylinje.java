import java.util.Random;

public class ValjPolylinje {

	public static final Random rand = new Random ();
	public static final int ANTAL_POLYLINJER = 10;
	
	public static void main (String[] args)	
	{		
		// skapa ett antal slumpm�ssiga polylinjer
		Polylinje[] polylinjer = new Polylinje[ANTAL_POLYLINJER]; //10 st
		Polylinje kortasteGula = new Polylinje(); //Ska lagra den kortaste gula polylinjen

		for (int i = 0; i < ANTAL_POLYLINJER; i++)
		{
			polylinjer[i] = slumpPolylinje();
			System.out.println(polylinjer[i].toString()); //Skriver ut polylinjerna 10 st
		}

		//Hittar den kortaste gula polylinjen
		Polylinje kortast = kortasteGula.kortastGulSamling(polylinjer); //skapar en till och kopierar fr�n tidigare polylinjer en gul
		double kortastLangd = 0;
		if(kortast != null) //Om det finns en gul polylinje utf�rs detta
		{
			kortastLangd = kortast.langd();
			System.out.println("\nKortaste gula linjen �r: \n" + kortast + "\nMed l�ngden: " + kortastLangd + "\n");
		}
		else //K�rs om det inte finns n�gon gul linje
		{ 
			System.out.println("\nDet finns ingen gul linje");
		}		
	}

	// slumpPunkt returnerar en punkt med ett slumpm�ssigt namn, som �r en stor bokstav i
	// det engelska alfabetet, och slumpm�ssiga koordinater.
	public static Punkt slumpPunkt ()
	{
		String n = "" + (char) (65 + rand.nextInt (26)); //Namnger med bokstav
		int x = rand.nextInt (11); //V�ljer koordinater mellan 0 och 11
		int y = rand.nextInt (11);
		return new Punkt (n, x, y);
	}

	// slumpPolylinje returnerar en slumpm�ssig polylinje, vars f�rg �r antingen bl�, eller r�d eller gul. 
	//Namn p� polylinjens h�rn �r stora bokst�ver i det engelska alfabetet. 
	//Tv� h�rn kan inte ha samma namn.
	public static Polylinje slumpPolylinje ()
	{
		Polylinje polylinje = new Polylinje (); // skapa en tom polylinje, och l�gg till h�rn till den
		int antalHorn = 2 + rand.nextInt (7); //Genererar antal h�rn mellan 2 och 7
		int antalValdaHorn = 0;
		boolean[] valdaNamn = new boolean[26];

		// ett och samma namn kan inte f�rekomma flera g�nger
		Punkt valdPunkt = null;
		while (antalValdaHorn < antalHorn) //s�l�nge antalvaldah�rn �r l�gre �n det slumpade antal h�rnen
		{
			valdPunkt = slumpPunkt(); //Skickar in punkter via punktgeneratorn
			int i = (char)(valdPunkt.getNamn().charAt(0)-65);
			if (valdaNamn[i])
			{
				continue; //om det namnet redan finns "hoppa �ver"
			}
			else //om det inte finns l�gg in den punkten
			{
				polylinje.laggTill(valdPunkt);
				valdaNamn[i] = true;
				antalValdaHorn++;				
			}			
		}

		// s�tt f�rg
		String[] farg = {"gul", "bl�" , "gr�n"};
		polylinje.setFarg(farg[rand.nextInt(3)]); //Slumpar mellan dessa f�rger
		return polylinje;
	}

}