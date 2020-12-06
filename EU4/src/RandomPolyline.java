import java.util.Random;

public class RandomPolyline {
	public  static  final  Random     rand = new  Random  ();

	// The  randomPoint  method  returns a new  Point  with a name
	//  randomly  chosen  from  the  single  letters A--Z. Coordinates
	// are  random.
	public  static  Point  randomPoint  ()
	{
		String     n = "" + (char) (65 + rand.nextInt  (26));
		int     x = rand.nextInt  (11);
		int     y = rand.nextInt  (11);
		return  new  Point (n, x, y);
	}
	// The  method  randomVPolyline  returns a random vector-based polyline ,
	// with a colour  either  blue , red , or  yellow. The  names
	// of the  vertices  are  single  letters  from  the  set A--Z.
	// Two  vertices  can  not  have  the  same  name.
	public  static  VPolyline  randomVPolyline  ()
	{
		//  Create  an empty  polyline  and  add  vertices
		VPolyline     polyline = new  VPolyline  ();
		int     nofVertices = 2 + rand.nextInt  (7);
		int     nofSelectedVertices = 0;

		//each element is initialized to the standard value of false
		boolean []     selectedNames = new  boolean [26];
		// Two  vertices  can  not  have  the  same  name
		Point     chosenPoint = null;
		while (nofSelectedVertices  < nofVertices)
		{
			//generates a point with name as a random capital letter
			//as well as random x- and y-coordinates
			chosenPoint = randomPoint();
			//gets the letter representing the vertex/point and
			//converts it to an index 0-26 for selectedNames[]
			int i = (char)(chosenPoint.getName().charAt(0) - 65);

			//if the letter has already been used,
			//begin a new iteration of the while-loop
			if(selectedNames[i])
			{
				continue;
			}else
			{
				//adds the vertex/point to the polyline array/vector
				polyline.add(chosenPoint);
				//makes sure the name will not be used again
				selectedNames[i] = true;
				//one more vertex has been added/selected
				nofSelectedVertices++;
			}
		}
		//  Assign a colour
		int colorNum = rand.nextInt(3);				  //0 - 3 (exclusive)
		//switch on colorNum which is a random int from 0 - 2 (inclusive)
		switch(colorNum)
		{
		case 0:
			polyline.setColour("blue");
			break;
		case 1:
			polyline.setColour("red");
			break;
		case 2:
			polyline.setColour("yellow");
			break;
		}
		
		return polyline;
	}
	
	
	
	//RANDOM NPOLYLINE
	// The  method  randomVPolyline  returns a random node-based polyline ,
		// with a colour  either  blue , red , or  yellow. The  names
		// of the  vertices  are  single  letters  from  the  set A--Z.
		// Two  vertices  can  not  have  the  same  name.
		public  static  NPolyline  randomNPolyline  ()
		{
			//  Create  an empty  polyline  and  add  vertices
			NPolyline     polyline = new  NPolyline  ();
			int     nofVertices = 2 + rand.nextInt  (7);
			int     nofSelectedVertices = 0;

			//each element is initialized to the standard value of false
			boolean []     selectedNames = new  boolean [26];
			// Two  vertices  can  not  have  the  same  name
			Point     chosenPoint = null;
			while (nofSelectedVertices  < nofVertices)
			{
				//generates a point with name as a random capital letter
				//as well as random x- and y-coordinates
				chosenPoint = randomPoint();
				//gets the letter representing the vertex/point and
				//converts it to an index 0-26 for selectedNames[]
				int i = (char)(chosenPoint.getName().charAt(0) - 65);

				//if the letter has already been used,
				//begin a new iteration of the while-loop
				if(selectedNames[i])
				{
					continue;
				}else
				{
					//adds the vertex/point to the polyline array/vector
					polyline.add(chosenPoint);
					//makes sure the name will not be used again
					selectedNames[i] = true;
					//one more vertex has been added/selected
					nofSelectedVertices++;
				}
			}
			//  Assign a colour
			int colorNum = rand.nextInt(3);				  //0 - 3 (exclusive)
			//switch on colorNum which is a random int from 0 - 2 (inclusive)
			switch(colorNum)
			{
			case 0:
				polyline.setColour("blue");
				break;
			case 1:
				polyline.setColour("red");
				break;
			case 2:
				polyline.setColour("yellow");
				break;
			}
			
			return polyline;
		}
	
}