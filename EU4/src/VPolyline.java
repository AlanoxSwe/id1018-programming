import java.util.Iterator;

public class VPolyline implements Polyline {
	private int width = 1;
	private Point[] vertices;
	private String colour = "black";

	//CONSTRUCTORS
	public  VPolyline  ()
	{
		this.vertices = new  Point [0];
	}
	public  VPolyline (Point []  vertices)
	{
		//creates a new array with copies of the passed array's elements
		this.vertices = new  Point[vertices.length];
		for (int i = 0; i < vertices.length; i++)
			this.vertices[i] = new  Point (vertices[i]);
	}
	//CONSTRUCTORS END

	@Override
	public Point[] getVertices() {
		//creates a new array, loops through all the elements
		//in the old array and adds them to the new one which is then returned
		Point[] h = new Point[vertices.length];
		for (int i = 0; i < vertices.length; i++) {
			h[i] = vertices[i];
		}
		return h;
	}


	@Override
	public String getColour() {
		return this.colour;
	}

	@Override
	public int getWidth() {
		return this.width;
	}

	@Override
	public double length() {
		double length = 0;
		Point[] h = this.vertices;
		for (int i = 1; i < h.length; i++)
		{
			//length between the previous point and the current point
			length += h[i-1].distance(h[i]);
		}
		return length;
	}

	@Override
	public void setColour(String colour) {
		this.colour = colour;
	}

	@Override
	public void setWidth(int width) {
		this.width = width;
	}

	@Override
	public void add(Point vertex) {
		Point[] h = new Point[this.vertices.length+1];
		int i;
		for (i = 0; i < this.vertices.length; i++) {
			h[i] = this.vertices[i];
		}		
		h[i] = new Point(vertex);

		//saves the reference to the new vector/array
		this.vertices = h;
	}

	@Override
	public void insertBefore(Point vertex, String vertexName) {
		//creates a copy of the passed Point
		Point copy = new Point(vertex);
		//creates a new array of Points with space for the new Point
		Point[]		h = new  Point[this.vertices.length + 1];

		boolean added = false;		
		
		int i;
		//loops through the vertices array
		for (i = 0; i < vertices.length; i++)
		{
			if(!added)
			{
				if(this.vertices[i].getName().equals(vertexName))
				{
					added = true;
					h[i] = copy;
				}
				else
					h[i] = vertices[i];

				//when the copy is added, the next element in h should be
				//from the previous index in this.vertices[]
			}else //if(added)
				h[i] = this.vertices[i-1];
		}//end of for, i is incremented one last time
		
		//if the vertex wasn't inserted before any other vertex, it is added at the end
		if(!added)
			h[i] = copy;
		else //if it was inserted, we need to guarantee that
			 //the last point from vertices is added
			h[i] = this.vertices[i-1];
		
		this.vertices = h;
	}

	@Override
	public void remove(String vertexName) {
		//if the array is greater than 0, make a new array with one index less
		//- else just size 0
		//prevents negative size arrays
		Point[]		h = (this.vertices.length > 0) ? new	Point[this.vertices.length - 1]
				: new Point[0];
		boolean removed = false;
		for (int i = 0; i < h.length; i++)
		{
			//assigns the point from the next index to the current index
			//since one element has been removed
			if(removed){
				h[i] = vertices[i+1];
			}
			else{//before removed
				//Checks the point that is to be copied to the new array
				//to see if its name matches the name from the parameter
				if(this.vertices[i].getName().equals(vertexName))
				{
					removed = true;
					h[i] = vertices[i+1];
				}
				else
				{
					h[i] = vertices[i];

				}
			}//end of before removed
		}//end of for
		this.vertices = h;
	}
	
	//	Example:
	//	{[(A 1 2), (B 2 3), (C 3 4)], black, 1}
	public String toString()
	{
		String result = "{[";

		//adds all the elements (in their toString() form with commas in between
		for (int i = 0; i < vertices.length; i++)
		{
			result += vertices[i].toString();
			//prevents adding a comma at the end
			if(i < vertices.length-1)
				result += ", ";
		}
		result += "], " + colour + ", " + width + "}";

		return result;
	}

	//the iterator instantiator
	@Override
	public Iterator<Point> iterator() {
		return new VPolylineIterator();
	}

	//INNER CLASS VPOLYLINEITERATOR
	public class VPolylineIterator implements
	Iterator<Point>{
		private int current = -1;

		public VPolylineIterator()
		{
			//if vertices is not empty, start the counter variable
			if(VPolyline.this.vertices.length > 0)
				current = 0;
		}

		@Override
		public boolean hasNext() {
			return current != -1;
		}

		@Override
		public Point next() throws
							java.util.NoSuchElementException{
			if(!hasNext())
				throw new java.util.NoSuchElementException("end of iteration");

			//if hasNext() is true
			Point vertex = VPolyline.this.vertices[current];
			if (current  >= 0   &&
					current  < VPolyline.this.vertices.length  - 1)
				current ++;
			else
				current =  -1;
			return vertex;
		}




	}

}