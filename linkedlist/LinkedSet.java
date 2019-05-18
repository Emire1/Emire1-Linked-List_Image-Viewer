package linkedlist;
/**
 * A quick demo of how to implement a set using a singly-linked list
 * @author trao
 *
 */
public class LinkedSet<T> {
	//---------------------------------------------------------------
	//The body of the list is held in a singly-linked list
	private SLList<T> body;


	//----------------------------------------------------------------
	// Constructor creates a new singly-linked list for the body
	public LinkedSet()
	{
		body = new SLList<T>();
	}

	//----------------------------------------------------------------
	// member method simply delegates the membership testing to SLList
	public boolean member(T elm)
	{
		return body.member(elm);
	}

	//----------------------------------------------------------------
	// Inserts a new element in to the set, if it not already there
	public void insert(T str)
	{
		// Write your code here:
		if(!body.member(str)) {
			body.insertAt(str, 0);
		}
		// if str is not a member of this, insert str into this

	}

	//----------------------------------------------------------------
	// Simple printing method, delegates the job to SLList
	public void printSet()
	{
		System.out.println (body.toString());
	}



	//----------------------------------------------------------------
	// builds a set by inserting all elements of an array
	public void buildSet(T[] elements)
	{
		// Write your code here
		//Insert all elements one by one
		for(int i = 0; i < elements.length; i++) {
			insert(elements[i]);
		}

	}
	//--------------------------------------------------------------
	// Returns the union of this set and the other set without
	// modifying this set or the other set
	public LinkedSet<T> union(LinkedSet<T> otherSet)
	{

		LinkedSet<T> result = new LinkedSet<T>();
		SLList<T> resultBody = result.body;
		SLList<T> otherBody = otherSet.body;

		for(int i = 0; i < body.size(); i++) {
			resultBody.insertAt(body.elementAt(i), 0);
		}
		// First insert all the elements if this set
		// Hint:
		// Use SLList methods on body, otherBody and resultBody
		// Write code here 

		
		for (int i = 0; i < otherBody.size(); i++) {
			if(!resultBody.member(otherBody.elementAt(i)))
				resultBody.insertAt(otherBody.elementAt(i), 0);
		}
		// Now insert all the elements of the other set that 
		// are not in this set
		// Write code here


		return result;
	}

	//--------------------------------------------------------------

	public LinkedSet<T> intersection(LinkedSet<T> otherSet)
	{
		LinkedSet<T> result = new LinkedSet<T>();
		SLList<T> resultBody = result.body;
		SLList<T> otherBody = otherSet.body;

		for(int i = 0; i < body.size(); i ++) {
			if(body.member(otherBody.elementAt(i))) {
				resultBody.insertAt(body.elementAt(i), 0);
				}
		}
			
			// For each element of this set, if it is also a 
			// member of otherSet, then insert it into result
			// Hint:
			// Use SLList methods on body, otherBody and resultBody
			// Write code here

			return result;
	}

	//--------------------------------------------------------------
	// Returns the difference of this set and the other set 
	// i.e. thisSet – otherSet  (All the elements that are in
	// in this set, but not in the other set
	// without modifying this set or the other set
	public LinkedSet<T> difference(LinkedSet<T> otherSet)
	{
		LinkedSet<T> result = new LinkedSet<T>();
		SLList<T> resultBody = result.body;
		SLList<T> otherBody = otherSet.body;
		
		
		for(int i = 0; i < body.size(); i++) {
			if (!body.member(otherBody.elementAt(i)))
				resultBody.insertAt(body.elementAt(i), 0);
		}
		

		// For each element of this set, if it NOT a 
		// member of otherSet, then insert it into result
		// Hint:
		// Use SLList methods on body, otherBody and resultBody
		// Write code here

		return result;
	}


}
