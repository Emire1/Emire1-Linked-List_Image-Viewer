package linkedlist;

/**
 * DoublyLinkedList: A doubly-linked implementation. An abstract class.
 * Implements the various insert/delete methods on a list of abstract nodes
 * We assume a doubly-linked node.
 * The implementation keeps pointers to the front and rear of the list and
 * maintains its own length.
 * 
 * @author (T.M. Rao)
 * 
 */
import java.util.*;
public class DoublyLinkedList <T> implements Enumeration<T>, IList<T>
{

    //Instance Variables
    //------------------------------------------------------------
    protected DoublyLinkedNode<T> firstNode;
    
    //------------------------------------------------------------
    protected DoublyLinkedNode<T> lastNode;
    
    //------------------------------------------------------------
    private DoublyLinkedNode<T> currentNode;
    
    //------------------------------------------------------------
    //Constructor
    public DoublyLinkedList()
    {
        firstNode = null;
        currentNode = null;
        lastNode = null;
    }
    
    //------------------------------------------------------------
    /**
     * Checks if the list is empty. 
     */
    public boolean isEmpty()
    {
        return (firstNode == null && lastNode == null);   
    }
    //------------------------------------------------------------
    public DoublyLinkedNode<T> getFirst()
    {
    	return firstNode;
    }
    //------------------------------------------------------------
    public DoublyLinkedNode<T> getLast()
    {
    	return lastNode;
    }
    //------------------------------------------------------------
    public DoublyLinkedNode<T> getCurrent()
    {
    	return currentNode;
    }
    //------------------------------------------------------------
    /**
     * Retriever method: returns length
     */
    public int length()
    {
        //return length;  This is a O(N) operation
        int len = 0;
        DoublyLinkedNode<T> dln = firstNode;
        while (dln != null)
        {
        	len++;
        	dln = dln.getNextNode();
        }
        return len;
    }
    
    //------------------------------------------------------------
    /**
     * Resets the currentNode cursor to the beginning of the
     * list. This should be done before we scan the list for
     * any reason
     */
    public void resetCurrentElement()
    {
        currentNode = firstNode;         
    }
    //------------------------------------------------------------
    public void resetCurrentElementToLast()
    {
        currentNode = lastNode;         
    }
    //------------------------------------------------------------
    /**
     * Creates a brand new copy of this list in completely different 
     * memory area.
     */
    DoublyLinkedList<T> deepCopy()
    {
    	DoublyLinkedList<T> copy = new DoublyLinkedList<T>();
    	DoublyLinkedNode<T> dln = firstNode;
        while (dln != null)
        {
        	copy.insertAtEnd(dln.getInfo());
        	dln = dln.getNextNode();
        }
    	return copy;
    }
    //------------------------------------------------------------
    /**
     * Determines if this list is actually a set (i.e. it has no repeated 
     * elements
     */
    public boolean isSet()
    {
    	DoublyLinkedNode<T> dln1 = firstNode;
    	if (isEmpty()) return true;
    	if (firstNode == lastNode) return true;
    	DoublyLinkedNode<T> lastButOneNode = lastNode.getPrevNode();
    	while (dln1 != lastButOneNode)
        {
    		DoublyLinkedNode<T> dln2 = dln1.getNextNode();
    		while (dln1 != lastNode)
    		{
    			if (dln1.getInfo().equals(dln2.getInfo()))
    				return false;
    			dln2 = dln2.getNextNode();
    		}
        	dln1 = dln1.getNextNode();
        }
    	return false;
    }
    //------------------------------------------------------------
    /**
     * hasMoreElements() and nextElement() methods provide a
     * way of scanning the elements of a list from first node
     * to last node.
     * Typical usage:
     * resetCurrentElement();
     * while (hasMoreElements())
     * {
     *  nextEle = nextElement();
     *  ... process the nextEle ...
     * }
     */
    public boolean hasMoreElements()
    {
        return (currentNode != null);   
    }
    
    //------------------------------------------------------------
    public T nextElement()
    {
        T info = currentNode.getInfo();
        currentNode = currentNode.getNextNode();
        return info;       
    }
    //------------------------------------------------------------
    public T prevElement()
    {
        T info = currentNode.getInfo();
        currentNode = currentNode.getPrevNode();
        return info;       
    }
    //------------------------------------------------------------
    public T getCurrentElement()
    {
        T info = currentNode.getInfo();
        return info;       
    }
    
    //------------------------------------------------------------
    /**
     * Returns a reference to the n-th node of the list
     */ 
    public DoublyLinkedNode<T> nodeAt(int index)
    {
        if (index < 0 || index >= length())
        {
            //First filter out the illegal values
            System.out.println("Error in elementAt"+
                "index is negative or too large; null returned");
            return null;
        }
        //If the list is empty, failure
        if (isEmpty())
        {
            System.out.println("Error in elementAt"+
                "List is empty; null returned");
            return null;
        }
        //First element
        else if (index == 0)
        {
            return firstNode;   
        }
        //Last element
        else if (index == length()-1)
        {
            return lastNode;   
        }
        else
        //Somewhere in the middle
        {
            DoublyLinkedNode<T> node = firstNode;
            
            //count up to index-1 starting at 0
            for (int count = 0; count < index; count++)
            {
                node = node.getNextNode();
            }
            return node;
        }
    }
    //------------------------------------------------------------
    /**
     * Returns a reference to the n-th element of the list
     */
    public T elementAt(int index)
    {
    	DoublyLinkedNode<T> node = nodeAt(index);
    	return node.getInfo();
    }
    
    //------------------------------------------------------------
    /**
     * Special case: insertion at the beginning 
     * (like push in a stack)
     */
    public void insertAtBeginning(DoublyLinkedNode<T> node)
    {
        node.setNextNode(firstNode);
        node.setPrevNode(null);       
        if (isEmpty())
        {
            lastNode = node;
        }
        else 
        {
            firstNode.setPrevNode(node);
        }    
        firstNode = node;
    }
    
    //------------------------------------------------------------
    //Anothe Version (with just Info)
    public void insertAtBeginning(T info)
    {
        insertAtBeginning(new DoublyLinkedNode<T>(null, info, null));
    }
    
    //------------------------------------------------------------
    /**
     * Special case: insertion at the end 
     * (like insert in a queue)
     */
    public void insertAtEnd(DoublyLinkedNode<T> node)
    {
        node.setPrevNode(lastNode);
        node.setNextNode(null);
        if (isEmpty())
        {
            firstNode = node;
        }
        else 
        {
            lastNode.setNextNode(node);
        }    
        lastNode = node;
    }
    
    //------------------------------------------------------------
    //Another version
    public void insertAtEnd(T info)
    {
        insertAtEnd(new DoublyLinkedNode<T>(null, info, null));
    }
    
    //------------------------------------------------------------
    /**
     * General case: insertion at the n-th location 
     * 
     */
    public void insertAt(DoublyLinkedNode<T> node, int location)
    {
        //Check for illegal values of index
        if ((location < 0)||(location > length()))
        {
            System.out.println("Error in insertAt"+
               " location is too large or too small; No Action taken");
        }
        //Spacial Case: Insert At beginning
        else if (location == 0)
        {
            insertAtBeginning(node);
        }
        //Spacial Case: Insert At end
        else if (location == length())
        {
            insertAtEnd(node);
        }
        else
        {
            //Get a pointer to n-th element
            DoublyLinkedNode<T> thisNode = nodeAt(location);                  
                        
            //Adjust the pointers correctly to effect the insertion
            DoublyLinkedNode<T> prevNode = thisNode.getPrevNode();
            node.setNextNode(thisNode);
            node.setPrevNode(prevNode);
            prevNode.setNextNode(node);
            thisNode.setPrevNode(node);
        }
    }
    
    //------------------------------------------------------------
    //Another Version
    public void insertAt(T info, int location)
    {
        insertAt(new DoublyLinkedNode<T>(null, info, null), location);
    }
    
    //------------------------------------------------------------
    /**
     * Delete the first element of the list
     */
    public void deleteFirst()
    {
        //Can't delete from an empty list
        if (isEmpty())
        {
            System.out.println("Error in AbstractList.deleteFirst() "+
                    "Attempted delete from Empty List ");
        }
        
        //Special case: list has only one element
        else if (length()==1)
        {
            firstNode = null;
            lastNode = null;
        }
        //All othe lists: Only first pointer changes. Last doesn't
        else
        {
            DoublyLinkedNode<T> nextNode = firstNode.getNextNode();
            nextNode.setPrevNode(null);
            firstNode = nextNode;
        }
    }
    
    //------------------------------------------------------------
    /**
     * Delete last element of a list
     */
    public void deleteLast()
    {
        //Can't delete from an empty list
        if (isEmpty())
        {
            System.out.println("Error in AbstractList.deleteLast() "+
                    "Attempted delete from Empty List ");
        }
        //Special case: list has only one element
        else if (length()==1)
        {
            firstNode = null;
            lastNode = null;
            //length = 0;
        }
        //All of the lists: Only last pointer changes. First doesn't
        else
        {
            DoublyLinkedNode<T> prevNode = lastNode.getPrevNode();
            prevNode.setNextNode(null);
            lastNode = prevNode;
        }
    }
    
    //------------------------------------------------------------
    /**
     * Delete the n-th element of a list
     */
    public void deleteNth(int location)
    {
        //Check for illegal values of index
        if ((location < 0)||(location >= length()))
        {
            System.out.println("Error in deleteNth"+
               " location is too large or too small; No Action taken");
        }
        //Spacial Case: Delete At beginning
        else if (location == 0)
        {
            deleteFirst();
        }
        //Spacial Case: Delete At end
        else if (location == length()-1)
        {
            deleteLast();
        }
        else
        {
            //Get a pointer to the n-th element
            DoublyLinkedNode<T> thisNode = nodeAt(location);
            
            //Get pointers to its prev and next nodes
            DoublyLinkedNode<T> prevNode = thisNode.getPrevNode();
            DoublyLinkedNode<T> nextNode = thisNode.getNextNode();
            
            //Adjust pointers
            prevNode.setNextNode(nextNode);
            nextNode.setPrevNode(prevNode);
        }
    }
    
    //------------------------------------------------------------
    /**
     * Membership in a list: Is this object present in the list?
     */
    public boolean member(T info)
    {
        DoublyLinkedNode<T> thisNode = firstNode;
        while (thisNode != null)
        {
            T nodeInfo = thisNode.getInfo();
            if (nodeInfo.equals(info)) 
               return true;
            thisNode = thisNode.getNextNode();
        }
        return false;
    }
    
    //------------------------------------------------------------
    /**
     * Delete the first occurrence of this info in a list
     * If this info doesn't occur then nothing is done
     */
    public void deleteFirstOccurrence(T info)
    {
        DoublyLinkedNode<T> thisNode = firstNode;
        int location = -1;
        while (thisNode != null)
        {
            location++;
            Object nodeInfo = thisNode.getInfo();
            if (nodeInfo.equals(info)) 
            {
                if (location == 0)
                   deleteFirst();
                else if (location == length()-1)
                   deleteLast();
                else
                {
                    //Get pointers to its prev and next nodes
                    DoublyLinkedNode<T> prevNode = thisNode.getPrevNode();
                    DoublyLinkedNode<T> nextNode = thisNode.getNextNode();
            
                    //Adjust pointers
                    prevNode.setNextNode(nextNode);
                    nextNode.setPrevNode(prevNode);
                }
                break;
            }
            //Increment the pointer to next node
            thisNode = thisNode.getNextNode();
        }
    }
   
    //------------------------------------------------------------
    /**
     * Delete the first occurrence of this info in a list
     * If this info doesn't occur then nothing is done
     */
    public void deleteAllOccurrences(T info)
    {
        DoublyLinkedNode<T> thisNode = firstNode;
        while (thisNode != null)
        {
            T nodeInfo = thisNode.getInfo();
            if (nodeInfo.equals(info)) 
            {                                         
                //Get pointers to its prev and next nodes
                DoublyLinkedNode<T> prevNode = thisNode.getPrevNode();
                DoublyLinkedNode<T> nextNode = thisNode.getNextNode();
            
                //Adjust pointers
                if (prevNode == null && nextNode == null)
                {
                    //There is only one element in the list
                    deleteFirst();
                    return;
                }
                if (prevNode != null)
                   prevNode.setNextNode(nextNode);
                else
                {
                    firstNode = nextNode;
                    nextNode.setPrevNode(null);
                }
                if (nextNode != null)
                   nextNode.setPrevNode(prevNode);
                else
                {
                    lastNode = prevNode;
                    prevNode.setNextNode(null);
                }    
            }
            thisNode = thisNode.getNextNode();
        }
    }
    
    //------------------------------------------------------------
    /**
     * Convert list contents into a printable String
     */
    public String toString()
    {
        String result = "[ ";
        DoublyLinkedNode<T> nextNode = firstNode;
        while (nextNode != null)
        {
            result = result + nextNode.toString() + " ";
            nextNode = nextNode.getNextNode();
        }
        result = result+" ]";
        return result;
    }
    //------------------------------------------------------------
    /**
     * Reverse the list using only a constant amount of extra memory
     */
    public void reverseInPlace()
    {
        DoublyLinkedNode<T> node = firstNode;
        while (node != null)
        {
            DoublyLinkedNode<T> prev = node.getPrevNode();
            DoublyLinkedNode<T> next = node.getNextNode();
            node.setPrevNode(next);
            node.setNextNode(prev);
            node = next;
        }
        node = firstNode;
        firstNode = lastNode;
        lastNode = node;
    }
    
}
