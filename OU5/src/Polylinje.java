public class Polylinje {
	
	//Instanser
	private Punkt[] horn;
	private String farg = "svart";
	private int bredd = 1;

	/********
	Konstruktorer 
	********/
	
	//Skapar default polylinje utan hörn
	public Polylinje ()
	{
		this.horn = new Punkt[0];
	}

	//Skapar en ny vektor som polylinje 
	public Polylinje (Punkt[] horn, String farg)
	{
		this.horn = new Punkt[horn.length]; //Antal hörn
		
		for (int i = 0; i < horn.length; i++) {
			this.horn[i] = new Punkt(horn[i]); //Genererar hörnen som punkter
		}
		
		this.farg = farg;  //Anger färgen
	}

	//Gör om till sträng 
	public String toString () 
	{
		String s = "{[";
		for (int i = 0; i < horn.length - 1; i++)
		{
			s = s + horn[i] + ", ";			
		}
		s = s + horn[horn.length - 1] + "], " + farg + ", " + bredd + "}"; //går igenom alla hörn som genererats och skriver ut de som sträng
		return s;
	}

	//Hämtar lista med punkterna för polylinjen och kopierar in det i en ny vektor, returnerar referens till ny vektor
	public Punkt[] getHorn () 
	{
		Punkt[] v = new Punkt[horn.length]; //Skapar ny vektor för att kopiera in hörnen
		for (int i = 0; i < horn.length; i++)
		{
			v[i] = horn[i];
		}
		return v;
		//return horn[i]; endast kopierar
	}

	//Hämtar färgen
	public String getFarg () 
	{
		return farg;
	}

	//Hämtar bredd
	public int getBredd () 
	{
		return bredd;
	}

	//Sätter ny färg
	public void setFarg (String farg) 
	{
		this.farg = farg;
	}

	//Sätter ny bredd
	public void setBredd (int bredd) 
	{
		this.bredd = bredd;
	}

	//Beräknar längden genom avståndsformeln mellan första p och andra p för alla p
	public double langd () 
	{
		double l = 0;
		for (int i = 0; i < this.horn.length - 1; i++)
            l += this.horn[i].avstand(this.horn[i + 1]);
		return l;
	}

	//Lägger till ett hörn
	public void laggTill (Punkt nyttHorn)
	{
		Punkt[] h = new Punkt[this.horn.length + 1]; //Skapar ny array med 1 större då ett nytt hörn ska in

		int i;
		for (i = 0; i < this.horn.length; i++)
			h[i] = this.horn[i];
		h[i] = new Punkt(nyttHorn);
		this.horn = h;
	}

	
	public void laggTillFramfor (Punkt nyttHorn, String hornNamn) 
	{
		Punkt[] h = new Punkt[this.horn.length + 1]; //Skapar ny array med 1 större då ett nytt hörn ska in
		int position = 0;
		for (int i = 0; i < this.horn.length; i++)
		{
			h[i] = this.horn[i];
			if (hornNamn.equals(this.horn[i].getNamn())) //Letar efter givet hörn som det nya ska placeras framför och lägger in det
			{
				position = i;
				h[position] = nyttHorn;
			}
		}
		//Flyttar fram resterande från det nya tillgada hörnet
		for (int i = position + 1; i < this.horn.length + 1; i++) //+1 annars försvinner det som låg framför
		{
			h[i] = this.horn[i - 1];
		}
		this.horn = h;
	}
	
		
	//Tar bort ett givet hörn från vektorn
	//Kopierar alla hörn till ny vektorn förutom det hörn som ska tas bort
	public void taBort(String taBortHornNamn) 
	{
		Punkt[] taBortHorn = new Punkt[this.horn.length - 1]; //Skapar ny array med 1 mindre då ett nytt hörn ska bort
		
		int u = 0;
		for (int i = 0; i < this.horn.length; i++)
		{
			if (this.horn[i].getNamn().equals(taBortHornNamn)) //Hoppar över det angivna och lägger in de andra som ska vara kvar
			{
				u = i;
				break;
			}
		}
		for (int j = 0; j < u; j++) {
			taBortHorn[j] = this.horn[j];
        }
        for (int j = u; j < taBortHorn.length; j++) {
        	taBortHorn[j] = this.horn[j + 1];
        }
		this.horn = taBortHorn;
	}

	//Kollar efter den kortaste gula linjen
	//Den gör endast en jämförelse om färgen == "gul"
	public Polylinje kortastGulSamling (Polylinje[] polylinjer)
	{
		Polylinje valdPolylinje = null;
		int j = 0;
		double kortast = 0;
		for(int i = 0; i < polylinjer.length; i++)
		{
			if(polylinjer[i].getFarg().equals("gul")) //Börjar med index 1 från tidigare skapat polylinje och ser om den är "gul"
			{
				if(j == 0)
				{
					kortast = polylinjer[i].langd(); //Lagrar totala längden av den gula linjen som hittats i polylinje
					valdPolylinje = polylinjer[i]; //väljer den valda gula linjen och kopierara in den
					j++;
				}
				if(polylinjer[i].langd() < kortast) // jämför nu längden med varje "gul" i polylinjer med det som lagrats i kortast
				{
					kortast = polylinjer[i].langd();
					valdPolylinje = polylinjer[i]; // väljer den kortaste
				}
			}
		}
		return valdPolylinje; //returnerar den kortaste gula polylinjen
	}
	
	//Iterator
	public class PolylinjeIterator
	{
		private int aktuell = -1;
		public PolylinjeIterator ()
		{
			if (Polylinje.this.horn.length > 0)
				aktuell = 0;
		}
		public boolean finnsHorn ()
		{
			return aktuell != -1;
		}
		public Punkt horn () throws java.util.NoSuchElementException
		{
			if (!this.finnsHorn ())
				throw new java.util.NoSuchElementException (
						"slut av iterationen");
			Punkt horn = Polylinje.this.horn[aktuell];
			return horn;
		}
		public void gaFram ()
		{
			if (aktuell >= 0 && aktuell < Polylinje.this.horn.length - 1)
				aktuell++;
			else
				aktuell = -1;
		}
	}

}

