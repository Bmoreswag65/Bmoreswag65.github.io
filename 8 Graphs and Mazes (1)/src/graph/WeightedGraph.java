package graph;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;



/**
 * <P>This class represents a general "directed graph", which could 
 * be used for any purpose.  The graph is viewed as a collection 
 * of vertices, which are sometimes connected by weighted, directed
 * edges.</P> 
 * 
 * <P>This graph will never store duplicate vertices.</P>
 * 
 * <P>The weights will always be non-negative integers.</P>
 * 
 * <P>The WeightedGraph will be capable of performing three algorithms:
 * Depth-First-Search, Breadth-First-Search, and Djikatra's.</P>
 * 
 * <P>The Weighted Graph will maintain a collection of 
 * "GraphAlgorithmObservers", which will be notified during the
 * performance of the graph algorithms to update the observers
 * on how the algorithms are progressing.</P>
 */
public class WeightedGraph<V> {

	/* STUDENTS:  You decide what data structure(s) to use to
	 * implement this class.
	 * 
	 * You may use any data structures you like, and any Java 
	 * collections that we learned about this semester.  Remember 
	 * that you are implementing a weighted, directed graph.
	 */
	
	HashMap<V, HashMap<V, Integer>> verticeMap = new HashMap<V, HashMap<V, Integer>>();
	
	
	
	
	
	
	
	/* Collection of observers.  Be sure to initialize this list
	 * in the constructor.  The method "addObserver" will be
	 * called to populate this collection.  Your graph algorithms 
	 * (DFS, BFS, and Dijkstra) will notify these observers to let 
	 * them know how the algorithms are progressing. 
	 */
	private Collection<GraphAlgorithmObserver<V>> observerList;
	


	/** Initialize the data structures to "empty", including
	 * the collection of GraphAlgorithmObservers (observerList).
	 */
	public WeightedGraph() {
		observerList = new HashSet<GraphAlgorithmObserver<V>>(); 
		verticeMap = new HashMap<V, HashMap<V, Integer>>();
		
	}
		
	/** Add a GraphAlgorithmObserver to the collection maintained
	 * by this graph (observerList).
	 * 
	 * @param observer
	 */
	public void addObserver(GraphAlgorithmObserver<V> observer) {
		observerList.add(observer);
	}

	/** Add a vertex to the graph.  If the vertex is already in the
	 * graph, throw an IllegalArgumentException.
	 * 
	 * @param vertex vertex to be added to the graph
	 * @throws IllegalArgumentException if the vertex is already in
	 * the graph
	 */
	public void addVertex(V vertex) {
		if(verticeMap.containsKey(vertex)){
			throw new IllegalArgumentException();
		}else{
			verticeMap.put(vertex, new HashMap<V, Integer>());
		}
	}
	
	/** Searches for a given vertex.
	 * 
	 * @param vertex the vertex we are looking for
	 * @return true if the vertex is in the graph, false otherwise.
	 */
	public boolean containsVertex(V vertex) {
		if(verticeMap.containsKey(vertex)){
			return true;
		}
		return false;
	}

	/** 
	 * <P>Add an edge from one vertex of the graph to another, with
	 * the weight specified.</P>
	 * 
	 * <P>The two vertices must already be present in the graph.</P>
	 * 
	 * <P>This method throws an IllegalArgumentExeption in three
	 * cases:</P>
	 * <P>1. The "from" vertex is not already in the graph.</P>
	 * <P>2. The "to" vertex is not already in the graph.</P>
	 * <P>3. The weight is less than 0.</P>
	 * 
	 * @param from the vertex the edge leads from
	 * @param to the vertex the edge leads to
	 * @param weight the (non-negative) weight of this edge
	 * @throws IllegalArgumentException when either vertex
	 * is not in the graph, or the weight is negative.
	 */
	public void addEdge(V from, V to, Integer weight) {
		if((!(verticeMap.containsKey(from)) || (!(verticeMap.containsKey(to))) || weight < 0)){
			throw new IllegalArgumentException();
		}else{
			verticeMap.get(from).put(to, weight);
		}
	}

	/** 
	 * <P>Returns weight of the edge connecting one vertex
	 * to another.  Returns null if the edge does not
	 * exist.</P>
	 * 
	 * <P>Throws an IllegalArgumentException if either
	 * of the vertices specified are not in the graph.</P>
	 * 
	 * @param from vertex where edge begins
	 * @param to vertex where edge terminates
	 * @return weight of the edge, or null if there is
	 * no edge connecting these vertices
	 * @throws IllegalArgumentException if either of
	 * the vertices specified are not in the graph.
	 */
	public Integer getWeight(V from, V to) {
		if((!(verticeMap.containsKey(from)) || (!(verticeMap.containsKey(to))))){
			throw new IllegalArgumentException();
		}else{
			if(!(verticeMap.get(from).containsKey(to))){
				return null;
			}
			return verticeMap.get(from).get(to);
		}
	}

	/** 
	 * <P>This method will perform a Breadth-First-Search on the graph.
	 * The search will begin at the "start" vertex and conclude once
	 * the "end" vertex has been reached.</P>
	 * 
	 * <P>Before the search begins, this method will go through the
	 * collection of Observers, calling notifyBFSHasBegun on each
	 * one.</P>
	 * 
	 * <P>Just after a particular vertex is visited, this method will
	 * go through the collection of observers calling notifyVisit
	 * on each one (passing in the vertex being visited as the
	 * argument.)</P>
	 * 
	 * <P>After the "end" vertex has been visited, this method will
	 * go through the collection of observers calling 
	 * notifySearchIsOver on each one, after which the method 
	 * should terminate immediately, without processing further 
	 * vertices.</P> 
	 * 
	 * @param start vertex where search begins
	 * @param end the algorithm terminates just after this vertex
	 * is visited
	 */
	public void DoBFS(V start, V end) {
		List<V> visitingSet = new ArrayList<V>(); // creates visitedList
		Queue<V> priorityList= new LinkedList<V>(); // creates Queue for Verticies to enter
		priorityList.add(start);  //Enqueues start vertex

		for (GraphAlgorithmObserver<V> element : observerList) {
			element.notifyBFSHasBegun();
		}
		//Begin!

		while (priorityList.size() != 0) {
			V recentVert = priorityList.element(); //Visit the element
			priorityList.remove(recentVert); //Remove that element
			if (!(visitingSet.contains(recentVert))) {
				for (GraphAlgorithmObserver<V> t : observerList) {
					t.notifyVisit(recentVert);
				}
				visitingSet.add(recentVert); // add current Vertex to visited List
				Map<V, Integer> updatedVertList = verticeMap.get(recentVert);
				for (V recentVertNeighbor : updatedVertList.keySet()) { //get the Successor of X and repeat process
					if (!(visitingSet.contains(recentVertNeighbor))) {
						priorityList.add(recentVertNeighbor);
					}
				}

			}
			if (recentVert.equals(end)) {
				for (GraphAlgorithmObserver<V> t : observerList) {
					t.notifySearchIsOver();
				} // Once at the end the "finish" point stop all operation
				break;
			}
		}

	}

	/** 
	 * <P>This method will perform a Depth-First-Search on the graph.
	 * The search will begin at the "start" vertex and conclude once
	 * the "end" vertex has been reached.</P>
	 * 
	 * <P>Before the search begins, this method will go through the
	 * collection of Observers, calling notifyDFSHasBegun on each
	 * one.</P>
	 * 
	 * <P>Just after a particular vertex is visited, this method will
	 * go through the collection of observers calling notifyVisit
	 * on each one (passing in the vertex being visited as the
	 * argument.)</P>
	 * 
	 * <P>After the "end" vertex has been visited, this method will
	 * go through the collection of observers calling 
	 * notifySearchIsOver on each one, after which the method 
	 * should terminate immediately, without visiting further 
	 * vertices.</P> 
	 * 
	 * @param start vertex where search begins
	 * @param end the algorithm terminates just after this vertex
	 * is visited
	 */
	ArrayList<V> visitedSet; //Establishment of Visited List

	public void DoDFS(V start, V end) {

		for(GraphAlgorithmObserver<V> element: observerList){
			element.notifyDFSHasBegun();

		}

		visitedSet = new ArrayList<V>();

		DoDFSHelperMethod(start, end); //Using recursion to complete traversal



	}



	private boolean DoDFSHelperMethod(V v, V end) {

		for (GraphAlgorithmObserver<V> t : observerList) {// Notices the current Node and Visits it

			t.notifyVisit(v);

		}

		visitedSet.add(v);



		if (v.equals(end)) { //If Node is the end, stop all operations

			for (GraphAlgorithmObserver<V> t : observerList) {

				t.notifySearchIsOver();

			}

			return true;

		}

		Map<V, Integer> list = verticeMap.get(v); //Else... create a List grabbing the Node

		if (list.keySet() != null) {

			for (V y : list.keySet()) {//Complete iteration for as long as element is in the List

				if (visitedSet.contains(y)) {
				}else{

					if (DoDFSHelperMethod(y, end)) { //Recursively call the method again

						return true;

					}



				}

			}

		}

		return false;



	}
		


	/** 
	 * <P>Perform Dijkstra's algorithm, beginning at the "start"
	 * vertex.</P>
	 * 
	 * <P>The algorithm DOES NOT terminate when the "end" vertex
	 * is reached.  It will continue until EVERY vertex in the
	 * graph has been added to the finished set.</P>
	 * 
	 * <P>Before the algorithm begins, this method goes through 
	 * the collection of Observers, calling notifyDijkstraHasBegun 
	 * on each Observer.</P>
	 * 
	 * <P>Each time a vertex is added to the "finished set", this 
	 * method goes through the collection of Observers, calling 
	 * notifyDijkstraVertexFinished on each one (passing the vertex
	 * that was just added to the finished set as the first argument,
	 * and the optimal "cost" of the path leading to that vertex as
	 * the second argument.)</P>
	 * 
	 * <P>After all of the vertices have been added to the finished
	 * set, the algorithm will calculate the "least cost" path
	 * of vertices leading from the starting vertex to the ending
	 * vertex.  Next, it will go through the collection 
	 * of observers, calling notifyDijkstraIsOver on each one, 
	 * passing in as the argument the "lowest cost" sequence of 
	 * vertices that leads from start to end (I.e. the first vertex
	 * in the list will be the "start" vertex, and the last vertex
	 * in the list will be the "end" vertex.)</P>
	 * 
	 * @param start vertex where algorithm will start
	 * @param end special vertex used as the end of the path 
	 * reported to observers via the notifyDijkstraIsOver method.
	 */
	public void DoDijsktra(V start, V end) {
		for(GraphAlgorithmObserver<V> element: observerList){
			element.notifyDijkstraHasBegun();
	}
		Map<V, Integer> costMap = new HashMap<V, Integer>(); //Creating Cost List 
		Map<V, V> predMap = new HashMap<V, V>();             //Creating Predecessor List
		ArrayList<V> visitedSet = new ArrayList<V>();		 //Creating Visited List
		costMap.put(start, 0);								 //Putting the value 0 instead of null in place because there
		//is not distance from the vertex itself
		
		for(V currentVertex: verticeMap.keySet()){
			if(!currentVertex.equals(start)){
				costMap.put(currentVertex,  10000); //Since no other vertex in graph is like that of current vertex, each 
				//vertex value will be set to a huge number
				
			}
		}
		for(V currentVertex: verticeMap.keySet()){
			predMap.put(currentVertex, null); //Putting null in for the value because no Vertex associated with Verticies yet
		}
		while(visitedSet.size() != verticeMap.size()){
			int smallest = 10000;
			V smallVertexUp = null;
		
		for(V currentVertex : costMap.keySet()){
			if(visitedSet.contains(currentVertex)==false){
				if(costMap.get(currentVertex) < smallest){ //Checking if the value of in the spot in the Cost Map is smaller 
					smallest = costMap.get(currentVertex); //If so replacing with a new smallest and setting that Vertex to next
					smallVertexUp = currentVertex;          //in being visited
				}
			}
		}
		visitedSet.add(smallVertexUp);
		for(GraphAlgorithmObserver<V> element: observerList){
			element.notifyDijkstraVertexFinished(smallVertexUp, costMap.get(smallVertexUp)); //Smallest Vertex now visited
	}
		for(V currentVertex: verticeMap.get(smallVertexUp).keySet()){ //Now doing the neighbors of that smallest Vertex
			if(visitedSet.contains(currentVertex) == false){
				if(costMap.get(smallVertexUp) + this.getWeight(smallVertexUp, currentVertex) < costMap.get(currentVertex)){
					//Checking if the weight of the neighbor is smaller than the ones registered with smallest Vertex
				costMap.put(currentVertex, costMap.get(smallVertexUp) + this.getWeight(smallVertexUp, currentVertex) );
					//If so now replacing that value with a new value registered with Neighbor Vertex
				predMap.put(currentVertex, smallVertexUp);
				//Replacing it with smallest path Neighbor Vertex
			}
		}
	
		}
	}
	V makeList = end;
	 ArrayList<V> fastPath = new ArrayList<V>(); //Creating the shortestPath at the end
	 while(makeList != null){
		 fastPath.add(0, makeList);
		 makeList = predMap.get(fastPath.get(0));
	 }
	 for(GraphAlgorithmObserver<V> element: observerList){ // Traversal over
			element.notifyDijkstraIsOver(fastPath);
	}
		
	}	
}
