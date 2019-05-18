package linkedlist;


/**
 * class DoublyLinkedNode: A doubly-linked node implementation.
 * This represents a node in a doubly linked list. We have a
 * forward pointer and a back pointer 
 * 
 * @author (T.M. Rao)
 *
 */

public class DoublyLinkedNode <T>
{
	//DoublyLinkedNode structure
	//------------------------------------------------------------------
	private DoublyLinkedNode<T> nextNode;

	//------------------------------------------------------------------
	private DoublyLinkedNode<T> prevNode;

	//------------------------------------------------------------------
	protected T info;

	/**
	 * A constructor
	 */
	public DoublyLinkedNode(T inf)
	{
		prevNode = null;
		info = inf;
		nextNode = null;
	}
	/**
	 * Another constructor
	 */
	public DoublyLinkedNode (DoublyLinkedNode<T> prev, T inf, 
			DoublyLinkedNode<T> next)
	{
		prevNode = prev;
		info = inf;
		nextNode = next;
	}
	//Retriever methods---------------------------
	public DoublyLinkedNode <T> getNextNode()
	{
		return nextNode;   
	}
	public DoublyLinkedNode <T> getPrevNode()
	{
		return prevNode;   
	}
	public T getInfo()
	{
		return info;   
	}
	//--------------------------------------------


	//Mutator methods-----------------------------
	public void setNextNode(DoublyLinkedNode<T> node)
	{
		nextNode = node;   
	}
	public void setPrevNode(DoublyLinkedNode<T> node)
	{
		prevNode = node;   
	}
	public void setInfo(T inf)
	{
		info = inf;   
	}
	//--------------------------------------------

	public String toString()
	{
		return info.toString();   
	}
}