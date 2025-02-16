import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Main {
	
	public static void search(ArrayHashTable<String,Customer> market,String key) {
		
		Customer customer=market.getValue(key);
		
		if(customer==null) {
			
			System.out.println("Customer not found");
		}
		else {
			
			System.out.println(customer.getTransaction().getLength()+" transactions found for "+customer.getName());
			System.out.println();
			
			for(int i=1;i<=customer.getTransaction().getLength();i++) {
				
				System.out.println(customer.getTransaction().getEntry(i).getDate()+","+customer.getTransaction().getEntry(i).getProduct());
			}
		}
		
	}


	public static void main(String[] args) throws IOException {
		
		ArrayHashTable<String,Customer> market=new ArrayHashTable<String,Customer>();
		
	 market.setToDoubleHash();
		market.setToPAF();
		
		File f=new File("supermarket_dataset_50K.csv");
		
		FileReader fr=new FileReader(f);
		
		BufferedReader br=new BufferedReader(fr);
		
		
		String line=br.readLine();
		line=br.readLine();
		
	    long time=System.nanoTime();
	  
		
		while(line!=null) {
			
		   String[] splitedLine=line.split(",");
		   
		  
		  Customer customer=new Customer(splitedLine[0],splitedLine[1],new Transaction(splitedLine[2],splitedLine[3]));
		  
		  Customer oldCustomer= market.put(splitedLine[0],customer);
		  
		  if(oldCustomer!=null) {
			  
			 SortedLList<Transaction> transactions=oldCustomer.getTransaction();
			 
			 
		   transactions.add(new Transaction(splitedLine[2],splitedLine[3]));

		   
		   customer.setTransaction(transactions);
		  }
		  
		  
		   
		   
		   
		   line=br.readLine();
		}
		
		
		
		System.out.println(market.getNumberOfCollisions());
		System.out.println("Indexing time is " + (System.nanoTime()-time));
		
		 br.close();
		
		
		File f2=new File("customer_1K.txt");
			
		FileReader fr2=new FileReader(f2);
			
		BufferedReader br2=new BufferedReader(fr2);
			
		String line2=br2.readLine();
		
		
		long time2=System.nanoTime();
		
		long min=Integer.MAX_VALUE;
		
		long max=Integer.MIN_VALUE;
		
		long time3;
		
		while(line2!=null) {
			
			time3=System.nanoTime();
			
			
			market.getValue(line2);
			
			if(System.nanoTime()-time3<min) {
				
				min=System.nanoTime()-time3;
			}
			
			if(System.nanoTime()-time3>max) {
				
				max=System.nanoTime()-time3;
			}
			
			
			
			line2=br2.readLine();
			
			
			
			
		}
		
		br2.close();
		System.out.println("The average search time is " +   (System.nanoTime()-time2)/1000);
		
		System.out.println("min search time is "+min);
		System.out.println("max search time is "+max);
		

	}

}
