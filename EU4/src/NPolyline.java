import java.util.Iterator;

public class NPolyline implements Polyline {
    private static class Node {
        public Point vertex;
        public Node nextNode;
        public Node(Point vertex) {
            this.vertex = vertex;
            nextNode = null;
        }
    }
    private Node vertices;
    private String colour = "black";
    private int width = 1; //  pixels

    private int size;

    //CONSTRUCTORS
    public NPolyline() {
        this.vertices = null;
        //empty polyline, size is 0
        size = 0;
    }
    public NPolyline(Point[] vertices) {
        //if vertices.length == 0, size is set to 0
        //if vertices.length > 0, size will be the length of vertices
        size = 0;
        if (vertices.length > 0) {
            Node node = new Node(new Point(vertices[0]));

            //stores the number of points in an instance variable
            size = vertices.length;

            //sets vertices to the first node
            this.vertices = node;

            //starts at pos/index 1
            int pos = 1;
            while (pos < vertices.length) {
                node.nextNode = new Node(new Point(vertices[pos++]));
                node = node.nextNode;
            }
        }
    }
    //END OF CONSTRUCTORS


    //	Example:
    //	{[(A 1 2), (B 2 3), (C 3 4)], black, 1}
    public String toString() {
        //stores the first node in the sequence
        Node firstNode = this.vertices;

        //adds the opening braces and brackets, as well as the first vertex
        String result = "{[";

        if (this.vertices != null) {
            result += this.vertices.vertex.toString();

            while (this.vertices.nextNode != null) {
                result += ", ";
                this.vertices = this.vertices.nextNode;
                result += this.vertices.vertex.toString();

            }
        }
        result += "], " + colour + ", " + width + "}";

        //sets vertices to contain the first node in the sequence again
        this.vertices = firstNode;

        return result;
    }

    //GETTERS
    @Override
    public Point[] getVertices() {

        //if there are no nodes/points in the object, return null
        if (this.vertices == null)
            return null;

        //creates a new array based on the instance variable
        //storing the number of nodes/points
        Point[] h = new Point[size];

        //temporary variable to keep track of current and next node
        Node node = this.vertices;
        int i = 0;

        while (i < size) {
            //adds the point from current node
            //and moves on to the next node
            h[i++] = new Point(node.vertex);
            node = node.nextNode;
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
    //END OF GETTERS

    @Override
    public double length() {
        double length = 0;
        //if the polyline has 0 or 1 elements, the length will be 0
        if (this.vertices == null || this.vertices.nextNode == null);
        //do nothing
        else {
            Node node = this.vertices;
            Point nextPoint = null; //starts at null since it is set at the start of the loop

            while (node.nextNode != null) {
                //preparation for the current iteration
                nextPoint = node.nextNode.vertex;

                //adds the length between the current node's point
                //and then next node's point
                length += node.vertex.distance(nextPoint);

                //prepares the next iteration
                node = node.nextNode;

            }
        }
        return length;
    }

    //SETTERS
    @Override
    public void setColour(String colour) {
        this.colour = colour;
    }
    @Override
    public void setWidth(int width) {
        this.width = width;
    }
    //END OF SETTERS

    @Override
    public void add(Point vertex) {
        //adds 1 to the size of the polyline (number of points/nodes)
        size++;

        //if the polyline is empty
        //sets the first node to a new node based on the passed point
        if (this.vertices == null) {
            this.vertices = new Node(new Point(vertex));
        } else { //start of else

            //references the first node in the sequence
            Node node = this.vertices;

            //won't continue if the current node is null
            //will always continue until the break statement is reached
            while (node != null) {
                //skips through all the nodes until the last one is reached
                //then sets its 'nextNode' to a new node containing a new point,
                //which is a copy of the passed point 'vertex'
                if (node.nextNode == null) {
                    node.nextNode = new Node(new Point(vertex));
                    //stops the loop from continuing after 'vertex' has been added
                    break;
                }
                //the "iteration"
                node = node.nextNode;
            }

        } //end of else
    }
    @Override
    public void insertBefore(Point vertex, String vertexName) {

        //adds 1 to the size of the polyline (number of points/nodes)
        size++;
        //creates a node based on the passed point
        Node newNode = new Node(new Point(vertex));
        //keeps track of whether the point/node has been inserted or not
        boolean added = false;

        //if the polyline is empty,
        //sets the first node to the new node
        if (this.vertices == null)
            this.vertices = newNode;
        //if the first vertex matches the string
        else if (this.vertices.vertex.getName().equals(vertexName)) {
            newNode.nextNode = this.vertices;
            this.vertices = newNode;
        } else { //start of else

            //stores the first node in the sequence
            Node node = this.vertices;

            //while the next node exists
            while (node.nextNode != null) {
                if (node.nextNode.vertex.getName().equals(vertexName)) {

                    newNode.nextNode = node.nextNode;
                    node.nextNode = newNode;

                    added = true;
                    //stops the loop from continuing after 'vertex' has been added
                    break;
                }

                node = node.nextNode;
            }

            //if the point wasn't added after the loop, it is added at the end
            //of the sequence - to stay consistent with VPolyline's implementation
            if (!added)
                node.nextNode = newNode;
        } //end of else

    }
    @Override
    public void remove(String vertexName) {

        Node node = this.vertices;

        if (node == null);
        //do nothing
        else {

            //if the first node matches the passed String,
            //the method is already done
            if (node.vertex.getName().equals(vertexName)) {
                //sets the first node in the object to the second node
                //(effectively removes the first node)
                this.vertices = node.nextNode;
                //decrements the variable storing the
                //number of nodes/points in the object
                size--;
            } //if the first node does not match the passed string
            else {
                while (node.nextNode != null) {
                    //if the next node matches the passed string
                    if (node.nextNode.vertex.getName().equals(vertexName)) {
                        //sets the next node to the node that comes after the previous next node
                        //ex: node -> node2 -> node3
                        //node.nextNode = node.nextNode.nextNode
                        //		(node2)                  (node3)
                        //node2 is effectively removed:
                        //ex: node -> node3
                        node.nextNode = node.nextNode.nextNode;

                        //decrements the variable storing the
                        //number of nodes/points in the object
                        size--;
                        //method is done, stop looping
                        break;
                    }
                    //moves to the next node
                    node = node.nextNode;


                } //end of while
            } //end of else for when first element does not match

        } //else for null check
    }

    //the iterator instantiator
    @Override
    public Iterator < Point > iterator() {
        return new NPolylineIterator();
    }

    //INNER CLASS VPOLYLINEITERATOR
    public class NPolylineIterator implements
    Iterator < Point > {

        //instance variable to iterate over nodes
        private Node currentNode = NPolyline.this.vertices;

        public NPolylineIterator() {
            //if vertices is not empty, start the counter variable
            if (NPolyline.this.vertices != null)
                currentNode = NPolyline.this.vertices;
        }

        @Override
        public boolean hasNext() {
            //if the node exists, return true
            return currentNode != null;
        }

        @Override
        public Point next() throws
        java.util.NoSuchElementException {
            if (!hasNext())
                throw new java.util.NoSuchElementException("end of iteration");

            Node temp = currentNode;
            //if hasNext() is true, go to next node and return its point
            currentNode = currentNode.nextNode;
            return temp.vertex;
        }




    }



}