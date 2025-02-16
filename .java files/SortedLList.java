public class SortedLList<V extends Comparable<? super V>> implements ListInterface<V>
{
	private Node firstNode;       // Head reference to first node
	private Node lastNode;        // Tail reference to last node
    private int  numberOfEntries; // Number of entries in list
  

	public SortedLList()
	{
		initializeDataFields();
	} // end default constructor

	public void clear()
	{
		initializeDataFields();
	} // end clear
  
   public void add(V  newEntry)
   {
      Node newNode = new Node(newEntry);
      
      if(firstNode==null) {
    	  
    	  firstNode=newNode;
    	  lastNode=newNode;
    	  numberOfEntries++;
      }
      
     
      else {
    	  
    	  Node temp=firstNode;
    	  
    	  if(temp.getData().compareTo(newNode.getData())==0) {
        	  
        	  add(1,newEntry);
          }
    	  
    	  else {
    	  
    	  int position=1;
    	  
    	  while(temp!=null && temp.getData().compareTo(newNode.getData())==1) {
    		  
    		  temp=temp.getNextNode();
    		  position++;
    		  
    	  }
    	  
    	  add(position,newEntry);
    	  
    	  }
    	  
    	 
    	  
    	  
      }
	
      
      
     
   }  // end add

   public void add(int givenPosition, V  newEntry)
   {
      if ((givenPosition >= 1) && (givenPosition <= numberOfEntries + 1))
      {
         Node newNode = new Node(newEntry);
         if (isEmpty())
         {
            firstNode = newNode;
            lastNode = newNode;
         }
         else if (givenPosition == 1)
         {
            newNode.setNextNode(firstNode);
            firstNode = newNode;
         }
         else if (givenPosition == numberOfEntries + 1)
         {
            lastNode.setNextNode(newNode);
            lastNode = newNode;
         }
         else
         {
            Node nodeBefore = getNodeAt(givenPosition - 1);
            Node nodeAfter = nodeBefore.getNextNode();
            newNode.setNextNode(nodeAfter);
            nodeBefore.setNextNode(newNode);
         } // end if
         numberOfEntries++;
      }
      else
         throw new IndexOutOfBoundsException(
                                             "Illegal position given to add operation.");
   } // end add
   
   public V remove(int givenPosition)
   {
	   V  result = null;                           // Return value
      if ((givenPosition >= 1) && (givenPosition <= numberOfEntries))
      {
         // Assertion: !isEmpty()
         if (givenPosition == 1)                 // Case 1: Remove first entry
         {
            result = firstNode.getData();        // Save entry to be removed
            firstNode = firstNode.getNextNode();
            if (numberOfEntries == 1)
               lastNode = null;                  // Solitary entry was removed
         }
         else                                    // Case 2: Not first entry
         {
            Node nodeBefore = getNodeAt(givenPosition - 1);
            Node nodeToRemove = nodeBefore.getNextNode();
            Node nodeAfter = nodeToRemove.getNextNode();
            nodeBefore.setNextNode(nodeAfter);
            result = nodeToRemove.getData();
            if (givenPosition == numberOfEntries)
               lastNode = nodeBefore;            // Last node was removed
         } // end if
         numberOfEntries--;
      }
      else
         throw new IndexOutOfBoundsException(
                                             "Illegal position given to remove operation.");
      
      return result;                             // Return removed entry
   } // end remove

   public V  replace(int givenPosition, V  newEntry)
   {
      
	   Node temp=firstNode;
	   
	   for(int i=1;i<givenPosition;i++) {
		   
		   temp=temp.getNextNode();
	   }
	   
	   V data=temp.getData();
	   
	   
	   temp.setData(newEntry);
	   
	   return data;
	  
	  
   } // end replace

   public V  getEntry(int givenPosition)
   {
		if ((givenPosition >= 1) && (givenPosition <= numberOfEntries))
		{
			assert !isEmpty();
         return getNodeAt(givenPosition).getData();
     	}
      else
         throw new IndexOutOfBoundsException("Illegal position given to getEntry operation.");
   } // end getEntry

   
   public V[] toArray()
   {
      // The cast is safe because the new array contains null entries
      @SuppressWarnings("unchecked")
      V[] result = (V[])new Object[numberOfEntries];
      
      int index = 0;
      Node currentNode = firstNode;
      while ((index < numberOfEntries) && (currentNode != null))
      {
         result[index] = currentNode.getData();
         currentNode = currentNode.getNextNode();
         index++;
      } // end while
      
      return result;
   } // end toArray
  
   
	public boolean contains(V  anEntry)
	{
		boolean found = false;
		Node currentNode = firstNode;
		
		while (!found && (currentNode != null))
		{
			if (anEntry.equals(currentNode.getData()))
				found = true;
			else
				currentNode = currentNode.getNextNode();
		} // end while
		
		return found;
	} // end contains

   public int getLength()
   {
      return numberOfEntries;
   } // end getLength
   
  
   public boolean isEmpty()
   {
   		boolean result;
   		
      	if (numberOfEntries == 0) // Or getLength() == 0
      	{
      		assert (firstNode == null) && (lastNode == null);
      		result = true;
      	}
      	else
      	{
      		assert (firstNode != null) && (lastNode != null);
      		result = false;
      	} // end if
      	
      	return result;
   } 
   

// Initializes the class's data fields to indicate an empty list.
   private void initializeDataFields()
   {
      firstNode = null;
      lastNode = null;
      numberOfEntries = 0;
   } // end initializeDataFields
   
	// Returns a reference to the node at a given position.
	// Precondition: List is not empty; 1 <= givenPosition <= numberOfEntries.
	private Node getNodeAt(int givenPosition)
	{
		assert (firstNode != null) && (1 <= givenPosition) && (givenPosition <= numberOfEntries);
		Node currentNode = firstNode;
		
      if (givenPosition == numberOfEntries)
         currentNode = lastNode;
      else if (givenPosition >1)
		{
         assert givenPosition < numberOfEntries;
         // Traverse the chain to locate the desired node
         for (int counter = 1; counter < givenPosition; counter++)
            currentNode = currentNode.getNextNode();
		} // end if
      
		assert currentNode != null;
		return currentNode;
	} // end getNodeAt

   private class Node
   {
      private V   data; // Entry in list
      private Node next; // Link to next node
      
      private Node(V dataPortion)
      {
         data = dataPortion;
         next = null;
      } // end constructor
      
      private Node(V dataPortion, Node nextNode)
      {
         data = dataPortion;
         next = nextNode;
      } // end constructor
      
      private V  getData()
      {
         return data;
      } // end getData
      
      private void setData(V  newData)
      {
         data = newData;
      } // end setData
      
      private Node getNextNode()
      {
         return next;
      } // end getNextNode
      
      private void setNextNode(Node nextNode)
      {
         next = nextNode;
      } // end setNextNode
   } // end Node
} // end LList

