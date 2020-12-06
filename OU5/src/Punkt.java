public class Punkt 
{

	private String namn;
	private int x;			//x-koordinat
	private int y;			//y-koordinat

	/********
	Konstruktorer 
	********/

	public Punkt(String namn, int x, int y) {
		this.namn = namn;
		this.x = x;
		this.y = y;
	}
	
	//Kopieringskunstruktor
	public Punkt(Punkt p) {
		this.namn = p.namn;
		this.x = p.x;
		this.y = p.y;
	}
	
	/********
	Metoder 
	********/

	//Skapar och returnerar koordinaterna som teckensträng
	public String toString() {
		String s = "";
		s = "(" + namn + ", " + x + ", " + y + ")";
		return s;
	}

	//Get-Set-metoder
	//(Inspektorer och Mutatorer)
	public String getNamn() {
		return namn;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;		
	}
	
	// testa en kombinator och en komparator
	public double avstand (Punkt p) {
		return Math.sqrt(Math.pow((x - p.x), 2) + Math.pow((y - p.y), 2));
	}

	// testa mutatorer, ändrar objektet

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}
	
}