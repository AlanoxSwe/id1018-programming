import java.io.*; // PrintWriter

class PunktTest
{
	public static void main (String[] args)
	{
		PrintWriter out = new PrintWriter (System.out, true);
		
		//Testa en konstruktor och en transformator
		Punkt p1 = new Punkt("A", 3, 4);
		Punkt p2 = new Punkt("B", 5, 6);
		out.println(p1 + " " + p2);
		
		//Testa inspektorer
		String n = p1.getNamn();
		int x = p1.getX();
		int y = p1.getY();
		out.println(n + " " + x + " " + y);
		
		//Testa en kombinator och en komparator
		double d = p1.avstand(p2);
		out.println("Avståndet: "+d);
		boolean b = p1.equals(p2);
		out.println("Är p1 och p2 lika? "+b);
		
		//Testa mutatorer
		p2.setX(1);
		p2.setY(2);
		out.println("Nya koordinater för p2: "+p2);
		
		//Testa en konstruktor till
		Punkt p3 = new Punkt(p1);
		out.println("Kopia av p1: "+p3);
	}
}