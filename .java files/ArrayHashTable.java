public class ArrayHashTable<K,V> implements HashTableInterface<K,V> {
	
	private static final double LOAD_FACTOR=0.8;
	private int numberOfEntries;
	private boolean isResized=false;
	private int tableSize=13;
	private  Entry<K,V>[] hashTable;
    private String function;
    private String collisionHandling;
    private long numberOfCollisions;
    
    public long getNumberOfCollisions() {
    	
    	return numberOfCollisions;
    }
	
	
	public ArrayHashTable() {
		
		
		hashTable=new Entry[tableSize];
		numberOfEntries=0;
		function="SSF";
		collisionHandling="linearProbe";
		numberOfCollisions=0;
		
	}
	
	public void setToSSF() {
		
		function="SSF";
		
	}

	public void setToPAF() {
		
		function="PAF";
		
	}
	
	public void setToLinearProbe() {
		
		
		collisionHandling="linearProbe";
	}
	
	public  void setToDoubleHash() {
		
		collisionHandling="doubleHash";
	}
	
	
	
    public V remove(K key) {
		
		int index=0;
		
		V result;
		
		switch(function) {
		
		case "SSF": index=SSF(key);
		    break;
		    
		case "PAF": index=PAF(key);
		     break;
		     
		    default:
		     
		
		}
		
		switch(collisionHandling) {
		
		case "linearProbe": index=linearProbe(index,key);
		     break;
		
		case "doubleHash": index=doubleHash(index,key);
		     break;
		     
		     default:
		 
		  
		}
		
		if(hashTable[index]==null || !hashTable[index].isOccupied()) {
			   
			   System.out.println("Customer not found!");
			   result=null;
		}
		
		else {
			
			result=hashTable[index].getValue();
			hashTable[index].setToRemoved();
		}
		
		return result;
		
		
	}
	
	public V put(K key,V value) {
		
	int index=0;
	
	V result;
	
		
	switch(function) {
	
	case "SSF": index=SSF(key);
	    break;
	    
	case "PAF": index=PAF(key);
	     break;
	     
	    default:
	     
	
	}
	
	switch(collisionHandling) {
	
	case "linearProbe": index=linearProbe(index,key);
	     break;
	
	case "doubleHash": index=doubleHash(index,key);
	     break;
	     
	     default:
	 
	  
	}
		
	
		
		
		
		
		if(hashTable[index]==null || !hashTable[index].isOccupied()) {
			
			hashTable[index]=new Entry<>(key,value);
			result=null;
			
			
			if(!isResized)
			numberOfEntries++;
			
			
		}
		else {
			
			result=hashTable[index].getValue();
			hashTable[index].setValue(value);;
			
		}
		
        if(isCapacityExceeded() && !isResized) {
        	
        	resize(hashTable.length);
        	
        	
        	
        }
		
		
		
		return result;
		
		
		
	}
	
	public V getValue(K key) {
		
		int index=0;
		
		V result;
		
		switch(function) {
		
		case "SSF": index=SSF(key);
		    break;
		    
		case "PAF": index=PAF(key);
		     break;
		     
		    default:
		     
		
		}
		
		switch(collisionHandling) {
		
		case "linearProbe": index=linearProbe(index,key);
		     break;
		
		case "doubleHash": index=doubleHash(index,key);
		     break;
		     
		     default:
		 
		  
		}
			
	   if(hashTable[index]==null || !hashTable[index].isOccupied()) {
		   
		   System.out.println("Customer not found!");
		   result=null;
	   }
	   else {
		   
		  result=hashTable[index].getValue();
		   
		   
	   }
	   
	   return result;
		
	}
	
	public int SSF(K key) {
		
		int index=0;
		
		String id=key.toString().toUpperCase().replace("-","");
		

		
		for(int i=0;i<id.length();i++) {
			
			if(isNumeric(id.charAt(i))) {
				
				index+= Character.getNumericValue(id.charAt(i));
				
				
			}
			
			else {
				
				int a=id.charAt(i);
				
				a-=64;
				
				index+=a;
			

			}
			
			
		}
			
		
		
		return index%hashTable.length;
		
		
		
	}
	
	public int PAF(K key) {
		
		int z=37;
		
        int index=0;
        
        String id=key.toString().toUpperCase().replace("-","");
		
		
		
		for(int i=0;i<id.length();i++) {
			
			if(isNumeric(id.charAt(i))) {
				
				index+= Character.getNumericValue(id.charAt(i));
				
				
			}
			
			else {
				
				
				int a=id.charAt(i);
				
				a-=64;
				
				index+=a*Math.pow(z,id.length()-i-1);
			
	
			}
			

		}
		
		return  Math.abs(index%hashTable.length);
	}
	
	public int linearProbe(int index,K key) {
		
		if(hashTable[index]==null || !hashTable[index].isOccupied() || key.equals(hashTable[index].getKey())) {
			
			return index;
		}
		else {
			
			
			
			while(hashTable[index]!=null && !key.equals(hashTable[index].getKey())) {
				
				index=(index+1)%hashTable.length;
				numberOfCollisions++;
			}
			
			return index;
			
		}
	}
	
	
	public int doubleHash(int index,K key) {
		
		if(hashTable[index]==null || !hashTable[index].isOccupied() ||  key.equals(hashTable[index].getKey())) {
			
			return index;
		}
		else {
			
			
			int q=7;
			
			int hk=index%hashTable.length;
			
			int dk=q-(index%q);
			
			int j=1;
			
			while(hashTable[index]!=null && !key.equals(hashTable[index].getKey())) {
				
				index=(hk+j*dk)%hashTable.length;
				j++;
				numberOfCollisions++;
			}
			
			return index;
			
		}
		
	}
	
	private boolean isCapacityExceeded() {
		
		double ratio=numberOfEntries/((double)hashTable.length);
		
		return ratio>LOAD_FACTOR;
	}
	
	private int getNextPrime(int integer)
	{
		// if even, add 1 to make odd
		if (integer % 2 == 0)
		{
			integer++;
		} // end if

		// test odd integers
		while (!isPrime(integer))
		{
			integer = integer + 2;
		} // end while

		return integer;
	} // end getNextPrime

	// Returns true if the given intege is prime.
	private boolean isPrime(int integer)
	{
		boolean result;
		boolean done = false;

		// 1 and even numbers are not prime
		if ( (integer == 1) || (integer % 2 == 0) )
		{
			result = false; 
		}

		// 2 and 3 are prime
		else if ( (integer == 2) || (integer == 3) )
		{
			result = true;
		}

		else // integer is odd and >= 5
		{
			assert (integer % 2 != 0) && (integer >= 5);

			// a prime is odd and not divisible by every odd integer up to its square root
			result = true; // assume prime
			for (int divisor = 3; !done && (divisor * divisor <= integer); divisor = divisor + 2)
			{
				if (integer % divisor == 0)
				{
					result = false; // divisible; not prime
					done = true;
				} // end if
			} // end for
		} // end if

		return result;
	} // end isPrime
 
	
	public void resize(int capacity) {
		
		isResized=true;
		
		Entry<K,V>[] oldTable=hashTable;
    	
    	Entry<K,V>[] temp=new Entry[getNextPrime(2*capacity)];
    	
    	hashTable=temp;
    	
    	
    	for(int i=0;i<oldTable.length;i++) {
    		
    		
    	    if(oldTable[i]!=null) {
    	     
    	    	put(oldTable[i].getKey(),oldTable[i].getValue());
    	     
    	    }
    		
    	}
    	
    	isResized=false;
	}
	
	private boolean isNumeric(char a) {
		
		char[] digits= {'0','1','2','3','4','5','6','7','8','9'};
		
		boolean flag=false;
		
		for(int i=0;i<digits.length;i++) {
			
			
			if(a==digits[i]) {
				
				flag=true;
				break;
			}
		}
		
		return flag;
		
	}
	
	@SuppressWarnings("hiding")
	public class Entry<K,V> {
		
		private K key;
		private V value;
		
		private enum States { Current,Removed};
		private States state;
		
		Entry(K key,V value){
			
			this.key=key;
			this.value=value;
			state=States.Current;
		}
		public K getKey() {
			return key;
		}

		public void setKey(K key) {
			this.key = key;
		}

		public V getValue() {
			return value;
		}

		public void setValue(V value) {
			this.value = value;
		}


		public boolean isOccupied(){
			   
			   return state==States.Current;// end isEmpty
		   }
			
	    public States getState() {
			return state;
		}

		public void setState(States state) {
			this.state = state;
		}

		public void setToRemoved() {
			 
			 state=States.Removed;
		}

	}

}
