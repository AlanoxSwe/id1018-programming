public class Polylinje1 {
	
	//Instanser
	private Punkt[] horn;
	private String farg = "svart";
	private int bredd = 1;

	/********
	Konstruktorer 
	********/
	
	//Skapar default polylinje utan hörn
	public Polylinje1 ()
	{
		this.horn = new Punkt[0];
	}

	//Skapar en ny vektor som polylinje 
	public Polylinje1 (Punkt[] horn, String farg)
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
		return horn;
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
		for (int i = 0; i < this.horn.length - 1; i++) {
            l += this.horn[i].avstand(this.horn[i + 1]);
		}
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

	/*
	Lägger till ett hörn framför ett angivet hörn
	Letar efter angivet hörn som det nya ska placeras framför och lägger till det nya där
	Flyttar fram/höger resterande hörn efter det nya som de låg innan
	 */
	public void laggTillFramfor (Punkt nyttHorn, String hornNamn) 
	{
		Punkt[] hornLaggTillFramfor = new Punkt[horn.length + 1]; //Skapar ny array med 1 större då ett nytt hörn ska in
		
		int j = 0;
		
		for (int i = 0; i < horn.length; i++) {
			//Letar efter givet hörn som det nya ska placeras framför och lägger in det
			if (this.horn[i].getNamn().equals(hornNamn)) {
				hornLaggTillFramfor[j] = nyttHorn;
				j++;
			}
			hornLaggTillFramfor[i] = this.horn[i];
			j++;
		}
		
		//Flyttar fram resterande från det nya tillgada hörnet
		for (int i = j + 1; i < this.horn.length + 1; i++) //+1 annars försvinner det som låg framför
		{
			hornLaggTillFramfor[i] = this.horn[i - 1];
		}
		this.horn = hornLaggTillFramfor;
		
	}

	//Tar bort ett givet hörn från vektorn
	//Kopierar alla hörn till ny vektorn förutom det hörn som ska tas bort
	public void taBort(String taBortHornNamn) 
	{
		Punkt[] taBortHorn = new Punkt[this.horn.length - 1]; //Skapar ny array med 1 mindre då ett nytt hörn ska bort
		
		int j = 0;
		for (int i = 0; i < this.horn.length; i++)
		{
			if (!this.horn[i].getNamn().equals(taBortHornNamn)) //Hoppar över det angivna och lägger in de andra som ska vara kvar
			{
				taBortHorn[j++] = this.horn[i];
			}
		}
		this.horn = taBortHorn;
	}

}

