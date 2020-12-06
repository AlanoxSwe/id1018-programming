import java.util.Random;

public class ValjPolylinje {

	public static final Random rand = new Random ();
	public static final int ANTAL_POLYLINJER = 10;
	
	public static void main (String[] args)	
	{		
		// skapa ett antal slumpmässiga polylinjer
		Polylinje[] polylinjer = new Polylinje[ANTAL_POLYLINJER]; //10 st
		Polylinje kortasteGula = new Polylinje(); //Ska lagra den kortaste gula polylinjen

		for (int i = 0; i < ANTAL_POLYLINJER; i++)
		{
			polylinjer[i] = slumpPolylinje();
			System.out.println(polylinjer[i].toString()); //Skriver ut polylinjerna 10 st
		}

		//Hittar den kortaste gula polylinjen
		Polylinje kortast = kortasteGula.kortastGulSamling(polylinjer); //skapar en till och kopierar från tidigare polylinjer en gul
		double kortastLangd = 0;
		if(kortast != null) //Om det finns en gul polylinje utförs detta
		{
			kortastLangd = kortast.langd();
			System.out.println("\nKortaste gula linjen är: \n" + kortast + "\nMed längden: " + kortastLangd + "\n");
		}
		else //Körs om det inte finns någon gul linje
		{ 
			System.out.println("\nDet finns ingen gul linje");
		}		
	}

	// slumpPunkt returnerar en punkt med ett slumpmässigt namn, som är en stor bokstav i
	// det engelska alfabetet, och slumpmässiga koordinater.
	public static Punkt slumpPunkt ()
	{
		String n = "" + (char) (65 + rand.nextInt (26)); //Namnger med bokstav
		int x = rand.nextInt (11); //Väljer koordinater mellan 0 och 11
		int y = rand.nextInt (11);
		return new Punkt (n, x, y);
	}

	// slumpPolylinje returnerar en slumpmässig polylinje, vars färg är antingen blå, eller röd eller gul. 
	//Namn på polylinjens hörn är stora bokstäver i det engelska alfabetet. 
	//Två hörn kan inte ha samma namn.
	public static Polylinje slumpPolylinje ()
	{
		Polylinje polylinje = new Polylinje (); // skapa en tom polylinje, och lägg till hörn till den
		int antalHorn = 2 + rand.nextInt (7); //Genererar antal hörn mellan 2 och 7
		int antalValdaHorn = 0;
		boolean[] valdaNamn = new boolean[26];

		// ett och samma namn kan inte förekomma flera gånger
		Punkt valdPunkt = null;
		while (antalValdaHorn < antalHorn) //sålänge antalvaldahörn är lägre än det slumpade antal hörnen
		{
			valdPunkt = slumpPunkt(); //Skickar in punkter via punktgeneratorn
			int i = (char)(valdPunkt.getNamn().charAt(0)-65);
			if (valdaNamn[i])
			{
				continue; //om det namnet redan finns "hoppa över"
			}
			else //om det inte finns lägg in den punkten
			{
				polylinje.laggTill(valdPunkt);
				valdaNamn[i] = true;
				antalValdaHorn++;				
			}			
		}

		// sätt färg
		String[] farg = {"gul", "blå" , "grön"};
		polylinje.setFarg(farg[rand.nextInt(3)]); //Slumpar mellan dessa färger
		return polylinje;
	}

}