

@SuppressWarnings("unused")
public class PolylinesTest {

	public static void main(String[] args) {
		//	TODO test everything
		/*
		 *	-method-		-tested-
		 * 	add				1 - done
		 * 	insertBefore	1 - done
		 * 	remove			1 - done
		 * 	toString		1 - done
		 * 	iterator		1 - done
		 * 	getVertices		1 - done
		 * 	getters/setters	1 - done
		 * 	length			1 - done
		 */
		
		
		Polyline polyline = null;
		polyline = new VPolyline(); //(1)
		//polyline = new NPolyline(); //(2)
		
		
		
		polyline = RandomPolyline.randomNPolyline();
		System.out.println("length: " + polyline.length());
		
		//ADD OR REMOVE /* ON LINE BELOW - 5 vertices - tests add, insertBefore, remove
		/*
		Point vertex1 = RandomPolyline.randomPoint();
		Point vertex2 = RandomPolyline.randomPoint();
		Point vertex3 = RandomPolyline.randomPoint();
		Point vertex4 = RandomPolyline.randomPoint();
		Point vertex5 = RandomPolyline.randomPoint();
		
		//adds 5 vertices
		polyline.add(vertex1);
		polyline.add(vertex3);
		polyline.add(vertex4);
		polyline.add(vertex5);
		polyline.insertBefore(vertex2, vertex3.getName()); //inserts vertex2 before vertex3
		polyline.remove(vertex1.getName());
		System.out.println("Vertex 1's name: " + vertex1.getName() + vertex1.getX() + vertex1.getY());
		System.out.println("Vertex 2's name: " + vertex2.getName() + vertex2.getX() + vertex2.getY());
		System.out.println("Vertex 3's name: " + vertex3.getName() + vertex3.getX() + vertex3.getY());
		System.out.println("Vertex 4's name: " + vertex4.getName() + vertex4.getX() + vertex4.getY());
		System.out.println("Vertex 5's name: " + vertex5.getName() + vertex5.getX() + vertex5.getY());
		
		
		
		//ADD OR REMOVE /* ON LINE BELOW - tests getVertices and iterator()
		/*
		Point[] h = polyline.getVertices();
		int i = 0;
		//VPolylineIterator iter = (VPolylineIterator) polyline.iterator(); //(1)
		NPolylineIterator iter = (NPolylineIterator) polyline.iterator(); //(2)
		Point temp = null;
		while(true){
			if(!iter.hasNext())
				break;
			temp = iter.next();
			System.out.println(h[i++].toString());
		}
		*/

		//ADD OR REMOVE /* ON LINE BELOW - tests iterable
		
		System.out.println("ALL THE VERTICES:");
		for (Point vertex : polyline)
		{
			System.out.println(vertex);
		}
		/*
		*/
		
		
		
	}

}