import java.util.*;
import java.lang.*;

public class SLList<E> implements List<E>{
	private Node<E> head; //Reference to the head of the list
	private int size = 0;
	
	
	/**Constructor
	 * @param item - the item we are adding to the head of the list
	 */
	 public SLList(E item) {
		 head = new Node<E>(item);
		 size++;
	 }
	 
	 /** Default constructor*/
	 public SLList() {
		 head = null;
		 size = 0;
	 }
	
	/** Remove all contents from the list, so it is once again
    * empty. 
    */
    public void clear() {
	  head = null;
	  size = 0;
    }
  
	
	
    /** Insert an element at the given location. 
	* allows you to insert after the tail
    * @param item The element to be inserted. 
	*/
    public void insert(int index, E item) {
		if (index < 0 || index > size) {
			throw new IndexOutOfBoundsException(Integer.toString(index));
		}
		if (index == 0){//want a new head of the list
			addFirst(item); //Helper method
		}
		else {
			Node<E> node = getNode(index - 1); // The node before the index
			addAfter(node, item); //Helper method
		}
	}
	
	/** Helper method that makes a new head
	* @param item to insert into the new head
	*/
	
	private void addFirst(E item) {
		head = new Node<E>(item, head);
		size++;
	}
	
	/**Helper to add a node after a given node
	* @param node to add after
	* @param item to add into the node
	*/
	
	private void addAfter(Node<E> node, E item){
		node.setNext(new Node<E>(item, node.getNext()));
		size++;
	}
	
	/** helper method to get a node at a given index
	* @param index - the index it needs to retrieve
	*/
	private Node<E> getNode(int index) {
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException(Integer.toString(index));
		}
		Node<E> node = head;
		for (int i=0; i < index; i++) {
			node = node.getNext();
		}
		return node;
	}
  
    
    /** Append an element at the end of the list.
    *  @param item The element to be appended.
    */
    public void add(E item) {
		if (size == 0) { //Nothing was in the list, so new head
			addFirst(item);
		}
		else {
			Node<E> node = getNode(size - 1);
			addAfter(node, item);
		}
    }
  
  
    /** 
    * Remove the  element at the given location.
	* @param index of node we want to remove
    */
    public void remove(int index) {
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException(Integer.toString(index));
		}
		if (index ==0) { //Removing the head
			removeFirst(); //helper
		}
		else {
			Node<E> node = getNode(index-1);
			removeAfter(node); //helper
		}
    }
	
	/** Helper to remove the head*/
	private void removeFirst() {
		if(head != null) {
			head = head.getNext();
			size--;
		}
	}
	
	/** Helper to after a given node
	* @param node the node to remove after
	*/
	private void removeAfter(Node<E> node) {
		Node<E> temp = node.getNext();
		if (temp != null) {
			node.setNext(temp.getNext());
			size--;
		}
	}
  
  
  
  /** 
  * Get the element in the position to one step left. 
  * @return element in the node to the left of the node at the index, 
  * null if at the head. 
  */  
  public E prev(int index) {
	if (index < 0 || index > size) {
		throw new IndexOutOfBoundsException(Integer.toString(index));
	}
	if (index == 0) {
		return null;
	}
	else {
		return getNode(index-1).getElement();
	}
  }
  
  
	/** Get the element in the position one step right. 
	* @return the element in the node to the right of 
	* the node at the index, null if at the end. 
	*/
	public E next(int index) {
		if (index < 0 || index > size) {
			throw new IndexOutOfBoundsException(Integer.toString(index));
		}
		if(index == size-1) {
			return null;
		}
		else {
			return getNode(index+1).getElement();
		}
	}
  
  
	/** @return The number of elements in the list. */
	public int length() {
		return size;
	}
    
    
   /** Turn the contents of the Nodes to a string in order from head to end.
   * @return The String representation of the 
   * elements in the list from head to end. 
   */
   public String toString() {
	   Node<E> nodeRef = head;
	   String result = "";
	   while (nodeRef != null) {
		   result = result + nodeRef.getElement().toString();
		   
		   if (nodeRef.getNext() != null) {
			   result = result + " ==> ";
		   }
		   nodeRef = nodeRef.getNext();
	   }
	   return result;
   }
  
   /** Reverse the content of the list.
	* if list is A => B => C it becomes C => B => A
	*/
  public void reverse() {
	  Node<E> node = head;
	  if (node == null || node.getNext() == null) { //Empty or one node
		return;
	  }
	  Node<E> prev = node.getNext();
	  Node<E> curr = prev.getNext();
	  prev.setNext(node);
	  node.setNext(null);
	  
	  while (curr != null) {
		  Node<E> next = curr.getNext();
		  curr.setNext(prev);
		  prev = curr;
		  curr = next;
	  }
	  head = prev;
  }
  
   
   /** @return The element at given position. */
   public E getValue(int index) {
	   	if (index < 0 || index > size) {
			throw new IndexOutOfBoundsException(Integer.toString(index));
		}
		Node<E> node = head;
		for (int i=0; i < index; i++) {
			node = node.getNext();
		}
		return node.getElement();
   }
   
   /**
	* insert a list after given index
	* @param list the list to be inserted
	* @param index the index of where the list should go after
	*/
	public void insertList (SLList list, int index) {
		Node<E> head = list.getHead(); // The head node in the list to add
		Node<E> last = list.getLast(); // The last node in the list to add
		Node<E> n = getNode(index); // The node before the index
		Node<E> nodeAfter = n.getNext(); // The node at the index
		if (index < 0 || index > size) {
			throw new IndexOutOfBoundsException(Integer.toString(index));
		}
		if (list.size == 0) {
			return;
		}
		else {
			last.setNext(nodeAfter);
			n.setNext(head);
			size = size + list.length();
		}
		
		
		
	}

	/** @return the head of the list*/
	public Node<E> getHead() {
		return head;
	}
	/** @return the last node in the list*/
	public Node<E> getLast() {
		return getNode(size-1);
	}


}