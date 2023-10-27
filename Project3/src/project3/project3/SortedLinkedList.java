package project3;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * This is an implementation of a sorted doubly-linked list.
 * All elements in the list are maintained in ascending/increasing order
 * based on the natural order of the elements.
 * This list does not allow <code>null</code> elements.
 *
 * @author Joanna Klukowska
 * @author
 *
 * @param <E> the type of elements held in this list
 */
public class SortedLinkedList<E extends Comparable<E>>
    implements Iterable<E> {

    private Node head;
    private Node tail;
    private int size;

    /**
     * Constructs a new empty sorted linked list.
     */
    public SortedLinkedList() {
        head = null;
        tail = null;
        size = 0;
    }

    /**
     * Adds the specified element to the list in ascending order.
     *
     * @param element the element to add
     * @return <code>true</code> if the element was added successfully,
     * <code>false</code> otherwise (if <code>element==null</code>)
     */
    public boolean add(E element) {
    	if (element==null) {
    		return false;
    	}
    	
    	if (this.size()==0) {
    		head = new Node(element);
    		this.size+=1;
    		return true;
    	}
    	if (this.size()==1) {
    		tail = new Node(element);
    		head.next = tail;
    		tail.prev= head;
    		this.size+=1;
    		return true;
    	}
    	Node el = new Node(element);
		Node current = head;
    	for (int x=0; x<this.size(); x++) {
    		if (el.compareTo(current) < 0 ) {
    			if (current.prev != null) {
        			el.prev = current.prev;
        			current.prev = el;
        			el.prev.next = el;
    			}
    			else {
    				head.prev = el;
    				head = el;
    			}
    			el.next = current;
    			this.size+=1;
    			return true;
    		}
    	current = current.next;
    	}
    	if (current==null) {
    		
    		el.prev = tail;
			tail.next = el;
			tail = el;
			this.size+=1;
			return true;
    	}
		
        return false;
    }

    /**
     * Removes all elements from the list.
     */
    public void clear() {
        if (this.size()==0) {
        	System.out.println("No Elements in list");
        }
        else {
        	Node current = head;
        	for (int x = 0; x < this.size(); x++) {
        		
        		Node temp = current.next;
        		current.data = null;
        		current.prev = null;
        		current.next = null;
        		current = temp;
        	}
        	head = null;
        	tail = null;
        }
        this.size=0;
    }

    /**
     * Returns <code>true</code> if the list contains the specified element,
     * <code>false</code> otherwise.
     *
     * @param o the element to search for
     * @return <code>true</code> if the element is in the list,
     * <code>false</code> otherwise
     */
    
    
  
    public boolean contains(Object o) {
        Node current = head;
        for (int x = 0; x<this.size(); x++) {
        	if (current.data.equals(o)){
        		return true;
        	}
        	current = current.next;
        }
        return false;
    }

    /**
     * Returns the element at the specified index in the list.
     *
     * @param index the index of the element to return
     * @return the element at the specified index
     * @throw IndexOutOfBoundsException  if the index is out of
     * range <code>(index < 0 || index >= size())</code>
     */
    public E get(int index) throws IndexOutOfBoundsException {
    	if (head == null){
    		throw new IndexOutOfBoundsException();
    	}
    	Node current = head;
        	for (int x = 0; x<index; x++) {
        		if (current.next==null)
        			throw new IndexOutOfBoundsException();
        		current = current.next;
        	}
        	return current.data;
    }

    /**
     * Returns the index of the first occurrence of the specified element in the list,
     * or -1 if the element is not in the list.
     *
     * @param o the element to search for
     * @return the index of the first occurrence of the element,
     * or -1 if the element is not in the list
     */
    public int indexOf(Object o) {
        Node current = head;
        for (int x = 0; x < this.size() ; x++) {
        	if (current.data.equals(o))
        		return x;
        	current = current.next;
        }
        return -1;
    }

    /**
     * Returns the index of the first occurrence of the specified element in the list,
     * starting at the specified <code>index</code>, i.e., in the range of indexes
     * <code>index <= i < size()</code>, or -1 if the element is not in the list
     * in the range of indexes <code>index <= i < size()</code>.
     *
     * @param o the element to search for
     * @param index the index to start searching from
     * @return the index of the first occurrence of the element, starting at the specified index,
     * or -1 if the element is not found
     */
    public int nextIndexOf(Object o, int index) {
        Node current = head;
        
        for (int x = 0; x<index; x++)
        	current = current.next;
        
        for (int x=index; x < this.size() ; x++) {
        	if (current.data.equals(o))
        		return x;
        	current = current.next;
        }
        return -1;
    }

    /**
     * Removes the first occurence of the specified element from the list.
     *
     * @param o the element to remove
     * @return <code>true</code> if the element was removed successfully,
     * <code>false</code> otherwise
     */
    public boolean remove(Object o) {
    	Node current = head;
    	if (this.size()==0) {
    		return false;
    	}
    	if (this.size()==1) {
    		head = null;
    		this.size-=1;
    	}
        for (int x = 0; x<this.size(); x++) {
        	if (o.equals(current.data)) {
        		if (current == tail) {
        			tail.prev.next = null;
        			current.data = null;
        			current.prev = null;
        			tail = current.prev;
        			this.size-=1;
        			return true;
        		}
        		else if (current == head) {
        			head = current.next;
        			current.next.prev = null;
        			current.data = null;
        			current.next = null;
        			current.prev = null;
        			this.size-=1;
        			return true;
        		}
        		current.prev.next = current.next;
        		current.next.prev = current.prev;
        		
        		current.prev = null;
        		current.next = null;
        		current.data = null;
        		this.size-=1;
        		return true;
        	}
        	current = current.next;
        }
        return false;
    }

    /**
     * Returns the size of the list.
     *
     * @return the size of the list
     */
    public int size() {
        return size;
    }

    /**
     * Returns an iterator over the elements in the list.
     *
     * @return an iterator over the elements in the list
     */
    public Iterator<E> iterator() {
        return new ListIterator();
    }

    /**
     * Compares the specified object with this list for equality.
     *
     * @param o the object to compare with
     * @return <code>true</code> if the specified object is equal to this list,
     * <code>false</code> otherwise
     */
    public boolean equals(Object o) {
    	if(!( o instanceof SortedLinkedList)){
    		return false;

    	}
    	if (((SortedLinkedList) o).size()==0){
    		if (this.size() ==0) {
    			return true;
    		}
    		return false;
    	}
    	if (((SortedLinkedList) o).size()==this.size()) {
    		for (int x=0; x<this.size(); x++) {
    			if (!(this.get(x).equals(((SortedLinkedList) o).get(x))))
    				return false;
    		}
    		return true;
    	}
    	return false;
    }

    /**
     * Returns a string representation of the list.
     *  The string representation consists of a list of the lists's elements in
     *  ascending order, enclosed in square brackets ("[]").
     *  Adjacent elements are separated by the characters ", " (comma and space).
     *
     * @return a string representation of the list
     */
    public String toString() {
    	Node current = this.head;
    	String s = "[";
        for (int x=0; x<this.size; x++) {
        	s += current.data.toString();
        	if (x!=this.size-1)
        		s+= ", ";
        	current = current.next;
        }
        s+= "]";
        return s;
    }

    /* Inner class to represent nodes of this list.*/
    private class Node implements Comparable<Node> {
        E data;
        Node next;
        Node prev;
        Node(E data) {
            if (data == null ) throw new NullPointerException ("does not allow null");
            this.data = data;
        }
        Node (E data, Node next, Node prev) {
            this(data);
            this.next = next;
            this.prev = prev;
        }
        public int compareTo( Node n ) {
            return this.data.compareTo(n.data);
        }
    }

    /* A basic forward iterator for this list. */
    private class ListIterator implements Iterator<E> {

        Node nextToReturn = head;
        @Override
        public boolean hasNext() {
            return nextToReturn != null;
        }

        @Override
        public E next() throws NoSuchElementException {
            if (nextToReturn == null )
                throw new NoSuchElementException("the end of the list reached");
            E tmp = nextToReturn.data;
            nextToReturn = nextToReturn.next;
            return tmp;
        }

    }
}

