import java.util.Iterator;

/** 
 * The MyHashSet API is similar to the Java Set interface. This
 * collection is backed by a hash table.
 */
public class MyHashSet<T> implements Iterable<T> {

	/** Unless otherwise specified, the table will start as
	 * an array of this length.*/
	/* STUDENTS:  DO NOT MODIFY THIS VALUE */
	public final static int DEFAULT_INITIAL_CAPACITY = 4;
	
	/** When the ratio of size/capacity reaches or exceeds this
	 * value, it is time for the table to be expanded*/
	/* STUDENTS:  DO NOT MODIFY THIS VALUE */
	public final static double LOAD_FACTOR = 0.75;

	
	/* STUDENTS:  LEAVE THIS PUBLIC FOR OUR TESTS */
	public Node<T>[] table;
	private int size;           // number of elements in the table

	
	
	
	/* STUDENTS:  DO NOT ADD ANY OTHER INSTANCE VARIABLES
	 * OR STATIC VARIABLES.  IF YOU DO, YOUR GRADE WILL BE 0.
	 */
	
	
	
	
	
	/* STUDENTS:  LEAVE THIS PUBLIC FOR OUR TESTS */
	public static class Node<T> {
		private T data;
		public Node<T> next;   // STUDENTS:  Leave this public, too!

		private Node(T data) {
			this.data = data;
			next = null;
		}

		@SuppressWarnings("unchecked")
		private static <T> Node<T>[] makeArray(int size) {
			return new Node[size];
		}
	}

	/**
	 * Initializes an empty table of the specified length (capacity).  
	 * Relies on the static makeArray method of the Node class.
	 * @param initialCapacity initial length (capacity) of table
	 */
	public MyHashSet(int initialCapacity) {
			table = Node.makeArray(initialCapacity);
	}

	/**
	 * Initializes an empty table of length equal to 
	 * DEFAULT_INITIAL_CAPACITY
	 */
	public MyHashSet() {
		table = Node.makeArray(DEFAULT_INITIAL_CAPACITY);
	}

	/**
	 * Returns the number of elements stored in the table.
	 * @return number of elements in the table
	 */
	public int getSize(){
		return size;
	}

	/**
	 * Returns the length of the table (the number of buckets).
	 * @return length of the table (capacity)
	 */
	public int getCapacity(){
	
		return table.length;
	}
	
	/** 
	 * Looks for the specified element in the table.
	 * 
	 * @param element to be found
	 * @return true if the element is in the table, false otherwise
	 */
	public boolean contains(T element) {
		int pos = Math.abs((997*element.hashCode() % table.length));
		Node curr = table[pos];
		if(curr == null){
			return false;
		}while(curr != null){
			if(curr.data.equals(element)){
				return true;
			}else{
				curr= curr.next;
			}
		}
		
		return false;
		
	}
			


	/** Adds the specified element to the collection.  If the element
	 * is already in the collection, then this method should do nothing.
	 * 
	 * Important:  After adding this element to the table, consider
	 * the ratio of size/capacity.  If this ratio is greater than or equal
	 * to the LOAD_FACTOR then you must double the size of the table.
	 * This will require re-hashing of all of the data!
	 * 
	 * @param element the element to be added to the collection
	 */
	
	public void add(T element) {
		if(this.contains(element) == true){
			return;
		}
		size++;
	int hash = Math.abs(((element.hashCode()*997) % getCapacity()));
	Node<T> elementAdded = new Node<T>(element);
	
	elementAdded.next = table[hash]; // adding....
	table[hash] =elementAdded;
	

	
	if(Double.valueOf(getSize())/Double.valueOf(getCapacity())>= LOAD_FACTOR){
		int newLength = table.length*2;
		int pos = Math.abs((997*element.hashCode())% newLength);
		
		Node<T>[] newHashTable = Node.makeArray(newLength);
		for(int i =0; i < table.length; i++){
			Node<T> curr = table[i];
			while(curr != null){
			int newHashPos = Math.abs((997*curr.data.hashCode())% newLength);
			Node<T> currentNode = new Node<T>(curr.data);
			currentNode.next = newHashTable[newHashPos];
			newHashTable[newHashPos] = currentNode;
			curr= curr.next;
			
			}
		}
		
		table = newHashTable;
	}

	
}

	/** Removes the specified element from the collection.  If the
	 * element is not present then this method should do nothing.
	 *
	 * @param element the element to be removed
	 */
	public void remove(T element) {
		Node<T> prev = null;
		for(int i = 0; i < table.length; i++){
			Node curr = table[i];
			if(curr != null && curr.data.equals(element)){
				table[i] = curr.next;
				size--;
			}else{
				while(curr != null){
				if(curr.data.equals(element)){
				prev.next = curr.next;
				size--;
				return;
				}
				prev = curr;
				curr = curr.next;
			}
			
		}
	
	}
		
}

	/** Returns an Iterator that can be used to iterate over
	 * all of the elements in the collection.
	 */
	public Iterator<T> iterator() {
		return new MyIt();
		}
	private class MyIt implements Iterator<T>{
	 
		int i = 0;
		Node<T> curr = table[i];
			public boolean hasNext(){
		boolean flag;
				if(curr != null){
				curr = curr.next;
				
				}
				
				while(curr == null && i+1 != table.length){ //Checks table for "bucket" that has something in it
					i++;
					curr = table[i];
				}
				
				if(curr == null){
					return false;
				}
				return true;
			}
			
		
			public T next(){
				return curr.data;
			}
	}
}
		
	
		
	

