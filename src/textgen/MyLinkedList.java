package textgen;

import java.util.AbstractList;

/**
 * A class that implements a doubly linked list
 * 
 * @author UC San Diego Intermediate Programming MOOC team
 *
 * @param <E>
 *            The type of the elements stored in the list
 */
public class MyLinkedList<E> extends AbstractList<E> {
	LLNode<E> head;
	LLNode<E> tail;
	int size;

	/** Create a new empty LinkedList */
	public MyLinkedList() {
		// TODO: Implement this method
		this.head = new LLNode<E>();
		this.tail = new LLNode<E>();
		size = 0;
		this.head.next = (tail);
		this.tail.prev = (head);
	}

	/**
	 * Appends an element to the end of the list
	 * 
	 * @param element
	 *            The element to add
	 */
	public boolean add(E element) throws NullPointerException{
		if (element == null){
			throw new NullPointerException("null elements are not excepted here baby!");
		} 
		LLNode<E> toadd = new LLNode<E>(element); 
		toadd.prev = this.tail.prev;
		toadd.next = this.tail; 
		this.tail.prev.next = toadd; 
		this.tail.prev = toadd; 
		size++; 
		// TODO: Implement this method
		return true;
	}

	/**
	 * Get the element at position index
	 * 
	 * @throws IndexOutOfBoundsException
	 *             if the index is out of bounds.
	 */
	public E get(int index) throws IndexOutOfBoundsException{
		if ((index <0 || index > size-1)) {
			throw new IndexOutOfBoundsException("index out of bounds baby!"); 
		}
		else {
			LLNode<E> current = this.head; 
			for(int i=0; i<this.size(); i++){
				current = current.next; 
				if (index == i){
					return current.data; 
				}
			}
		}
		// TODO: Implement this method.
		return null;
	}

	/**
	 * Add an element to the list at the specified index
	 * 
	 * @param The
	 *            index where the element should be added
	 * @param element
	 *            The element to add
	 */
	public void add(int index, E element) throws IndexOutOfBoundsException, NullPointerException{
		if (element == null){
			throw new NullPointerException("null elements are not excepted here baby!");
		} 
		if ((index < 0 || index > this.size())&& index!=0){
				throw new IndexOutOfBoundsException("Out of bounds baby!");
		}
			LLNode<E> current = this.head; 
			for (int i=0;i  < this.size; i++){
				current =current.next; 
				if (i == index){
					LLNode<E> toadd = new LLNode<E>(element); 
					toadd.prev = current.prev;
					(current.prev).next = toadd; 
					toadd.next = current; 
					current.prev = toadd; 
					size++; 
					break; 
				}
			}
		}
		
		
		// TODO: Implement this method


	/** Return the size of the list */
	public int size() {
		// TODO: Implement this method
		return this.size;
	}

	/**
	 * Remove a node at the specified index and return its data element.
	 * 
	 * @param index
	 *            The index of the element to remove
	 * @return The data element removed
	 * @throws IndexOutOfBoundsException
	 *             If index is outside the bounds of the list
	 * 
	 */
	public E remove(int index) throws IndexOutOfBoundsException {
		if ((index<0 || index >= this.size()) && index!=0){
			throw new IndexOutOfBoundsException("Out of bounds baby!"); 
		}
			LLNode<E> current = this.head; 
			for (int i =0; i< this.size(); i++){
				current = current.next; 
				if( i == index){
					LLNode<E> next = current.next; 
					LLNode<E> prev = current.prev; 
					prev.next = next; 
					next.prev= prev; 
					this.size--; 
					return current.data; 
				}
			}
		
		// TODO: Implement this method
		return null;
	}

	/**
	 * Set an index position in the list to a new element
	 * 
	 * @param index
	 *            The index of the element to change
	 * @param element
	 *            The new element
	 * @return The element that was replaced
	 * @throws IndexOutOfBoundsException
	 *             if the index is out of bounds.
	 */
	public E set(int index, E element)  throws IndexOutOfBoundsException, NullPointerException{
		if (element == null){
			throw new NullPointerException("null elements are not excepted here baby!");
		} 
		if (index <0 || index >= this.size()){
			throw new IndexOutOfBoundsException("Out of bounds baby!"); 
		}
		LLNode<E> current = this.head; 
		for (int i=0; i<this.size; i++){
			current = current.next; 
			if (index == i){
				current.data = element; 
				return element; 
			}
		}
		// TODO: Implement this method
		return null;
	}
	public String toString(){
		StringBuffer res = new StringBuffer("+  "); 
		LLNode<E> current = this.head; 
		for(int i =0; i<this.size(); i++){
			current = current.next; 
			res.append("=> "+current.toString()); 
		}
		return res.toString(); 
	}
}

class LLNode<E> {
	LLNode<E> prev;
	LLNode<E> next;
	E data;

	// TODO: Add any other methods you think are useful here
	// E.g. you might want to add another constructor

	public LLNode(E e) {
		this.data = e;
		this.prev = null;
		this.next = null;
	}
	public String toString(){
		return data.toString(); 
	}

	public LLNode() {
		this.data = null;
		this.prev = null;
		this.next = null;
		// TODO Auto-generated constructor stub
	}

}
