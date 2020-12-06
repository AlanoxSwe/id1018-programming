public class Polylinje1 {
	
	//Instanser
	private Punkt[] horn;
	private String farg = "svart";
	private int bredd = 1;

	/********
	Konstruktorer 
	********/
	
	//Skapar default polylinje utan h�rn
	public Polylinje1 ()
	{
		this.horn = new Punkt[0];
	}

	//Skapar en ny vektor som polylinje 
	public Polylinje1 (Punkt[] horn, String farg)
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
		return horn;
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
		for (int i = 0; i < this.horn.length - 1; i++) {
            l += this.horn[i].avstand(this.horn[i + 1]);
		}
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

	/*
	L�gger till ett h�rn framf�r ett angivet h�rn
	Letar efter angivet h�rn som det nya ska placeras framf�r och l�gger till det nya d�r
	Flyttar fram/h�ger resterande h�rn efter det nya som de l�g innan
	 */
	public void laggTillFramfor (Punkt nyttHorn, String hornNamn) 
	{
		Punkt[] hornLaggTillFramfor = new Punkt[horn.length + 1]; //Skapar ny array med 1 st�rre d� ett nytt h�rn ska in
		
		int j = 0;
		
		for (int i = 0; i < horn.length; i++) {
			//Letar efter givet h�rn som det nya ska placeras framf�r och l�gger in det
			if (this.horn[i].getNamn().equals(hornNamn)) {
				hornLaggTillFramfor[j] = nyttHorn;
				j++;
			}
			hornLaggTillFramfor[i] = this.horn[i];
			j++;
		}
		
		//Flyttar fram resterande fr�n det nya tillgada h�rnet
		for (int i = j + 1; i < this.horn.length + 1; i++) //+1 annars f�rsvinner det som l�g framf�r
		{
			hornLaggTillFramfor[i] = this.horn[i - 1];
		}
		this.horn = hornLaggTillFramfor;
		
	}

	//Tar bort ett givet h�rn fr�n vektorn
	//Kopierar alla h�rn till ny vektorn f�rutom det h�rn som ska tas bort
	public void taBort(String taBortHornNamn) 
	{
		Punkt[] taBortHorn = new Punkt[this.horn.length - 1]; //Skapar ny array med 1 mindre d� ett nytt h�rn ska bort
		
		int j = 0;
		for (int i = 0; i < this.horn.length; i++)
		{
			if (!this.horn[i].getNamn().equals(taBortHornNamn)) //Hoppar �ver det angivna och l�gger in de andra som ska vara kvar
			{
				taBortHorn[j++] = this.horn[i];
			}
		}
		this.horn = taBortHorn;
	}

}

