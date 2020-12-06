public class Polylinje {
	
	//Instanser
	private Punkt[] horn;
	private String farg = "svart";
	private int bredd = 1;

	/********
	Konstruktorer 
	********/
	
	//Skapar default polylinje utan h�rn
	public Polylinje ()
	{
		this.horn = new Punkt[0];
	}

	//Skapar en ny vektor som polylinje 
	public Polylinje (Punkt[] horn, String farg)
	{
		this.horn = new Punkt[horn.length]; //Antal h�rn
		
		for (int i = 0; i < horn.length; i++) {
			this.horn[i] = new Punkt(horn[i]); //Genererar h�rnen som punkter
		}
		
		this.farg = farg;  //Anger f�rgen
	}

	//G�r om till str�ng 
	public String toString () 
	{
		String s = "{[";
		for (int i = 0; i < horn.length - 1; i++)
		{
			s = s + horn[i] + ", ";			
		}
		s = s + horn[horn.length - 1] + "], " + farg + ", " + bredd + "}"; //g�r igenom alla h�rn som genererats och skriver ut de som str�ng
		return s;
	}

	//H�mtar lista med punkterna f�r polylinjen och kopierar in det i en ny vektor, returnerar referens till ny vektor
	public Punkt[] getHorn () 
	{
		Punkt[] v = new Punkt[horn.length]; //Skapar ny vektor f�r att kopiera in h�rnen
		for (int i = 0; i < horn.length; i++)
		{
			v[i] = horn[i];
		}
		return v;
		//return horn[i]; endast kopierar
	}

	//H�mtar f�rgen
	public String getFarg () 
	{
		return farg;
	}

	//H�mtar bredd
	public int getBredd () 
	{
		return bredd;
	}

	//S�tter ny f�rg
	public void setFarg (String farg) 
	{
		this.farg = farg;
	}

	//S�tter ny bredd
	public void setBredd (int bredd) 
	{
		this.bredd = bredd;
	}

	//Ber�knar l�ngden genom avst�ndsformeln mellan f�rsta p och andra p f�r alla p
	public double langd () 
	{
		double l = 0;
		for (int i = 0; i < this.horn.length - 1; i++)
            l += this.horn[i].avstand(this.horn[i + 1]);
		return l;
	}

	//L�gger till ett h�rn
	public void laggTill (Punkt nyttHorn)
	{
		Punkt[] h = new Punkt[this.horn.length + 1]; //Skapar ny array med 1 st�rre d� ett nytt h�rn ska in

		int i;
		for (i = 0; i < this.horn.length; i++)
			h[i] = this.horn[i];
		h[i] = new Punkt(nyttHorn);
		this.horn = h;
	}

	
	public void laggTillFramfor (Punkt nyttHorn, String hornNamn) 
	{
		Punkt[] h = new Punkt[this.horn.length + 1]; //Skapar ny array med 1 st�rre d� ett nytt h�rn ska in
		int position = 0;
		for (int i = 0; i < this.horn.length; i++)
		{
			h[i] = this.horn[i];
			if (hornNamn.equals(this.horn[i].getNamn())) //Letar efter givet h�rn som det nya ska placeras framf�r och l�gger in det
			{
				position = i;
				h[position] = nyttHorn;
			}
		}
		//Flyttar fram resterande fr�n det nya tillgada h�rnet
		for (int i = position + 1; i < this.horn.length + 1; i++) //+1 annars f�rsvinner det som l�g framf�r
		{
			h[i] = this.horn[i - 1];
		}
		this.horn = h;
	}
	
		
	//Tar bort ett givet h�rn fr�n vektorn
	//Kopierar alla h�rn till ny vektorn f�rutom det h�rn som ska tas bort
	public void taBort(String taBortHornNamn) 
	{
		Punkt[] taBortHorn = new Punkt[this.horn.length - 1]; //Skapar ny array med 1 mindre d� ett nytt h�rn ska bort
		
		int u = 0;
		for (int i = 0; i < this.horn.length; i++)
		{
			if (this.horn[i].getNamn().equals(taBortHornNamn)) //Hoppar �ver det angivna och l�gger in de andra som ska vara kvar
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
	//Den g�r endast en j�mf�relse om f�rgen == "gul"
	public Polylinje kortastGulSamling (Polylinje[] polylinjer)
	{
		Polylinje valdPolylinje = null;
		int j = 0;
		double kortast = 0;
		for(int i = 0; i < polylinjer.length; i++)
		{
			if(polylinjer[i].getFarg().equals("gul")) //B�rjar med index 1 fr�n tidigare skapat polylinje och ser om den �r "gul"
			{
				if(j == 0)
				{
					kortast = polylinjer[i].langd(); //Lagrar totala l�ngden av den gula linjen som hittats i polylinje
					valdPolylinje = polylinjer[i]; //v�ljer den valda gula linjen och kopierara in den
					j++;
				}
				if(polylinjer[i].langd() < kortast) // j�mf�r nu l�ngden med varje "gul" i polylinjer med det som lagrats i kortast
				{
					kortast = polylinjer[i].langd();
					valdPolylinje = polylinjer[i]; // v�ljer den kortaste
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

