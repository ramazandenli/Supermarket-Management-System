public  class Customer{
	

	private String id;
	private String name;
	private SortedLList<Transaction> transactions=new SortedLList<Transaction>();
	
	
	
	
	
	public Customer(String id,String name,Transaction tr) {
		
		this.id=id;
		this.name=name;
		this.transactions.add(tr);
		
	
		
		
	}




	public String getId() {
		return id;
	}




	public void setId(String id) {
		this.id = id;
	}




	public String getName() {
		return name;
	}




	public void setName(String name) {
		this.name = name;
	}

	
	public SortedLList<Transaction> getTransaction() {
		return transactions;
	}




	public void setTransaction(SortedLList<Transaction> transaction) {
		this.transactions = transaction;
	}



	
	
  

	
	

}
