public class Transaction implements Comparable<Transaction>{
	
	
	private String date;
	private String product;
	
	
	public Transaction(String date,String product) {
		
		
		this.date=date;
		this.product=product;
	}
    
	

	public String getDate() {
		return date;
	}


	public void setDate(String date) {
		this.date = date;
	}


	public String getProduct() {
		return product;
	}


	public void setProduct(String product) {
		this.product = product;
	}
	
	public int compareTo(Transaction tr) {
		
		String[] splitedOtherDate=tr.getDate().split("-");
    	
    	String[] splitedDate=this.date.split("-");
    	
    	if(Integer.parseInt(splitedDate[0])>Integer.parseInt(splitedOtherDate[0])) {
    		
    		return 1;
    	}
    	else if(Integer.parseInt(splitedDate[0])<Integer.parseInt(splitedOtherDate[0])) {
    		
    		return 0;
    	}
    	else if(Integer.parseInt(splitedDate[1])>Integer.parseInt(splitedOtherDate[1])) {
    		
    		return 1;
    		
    	}
    	else if(Integer.parseInt(splitedDate[1])<Integer.parseInt(splitedOtherDate[1])) {
    		
    		return 0;
    		
    	}
    	
    	else if(Integer.parseInt(splitedDate[2])>Integer.parseInt(splitedOtherDate[2])) {
    		
    		return 1;
    		
    	}
    	else if(Integer.parseInt(splitedDate[2])<Integer.parseInt(splitedOtherDate[2])) {
    		
    		return 0;
    		
    	}
    	else {
    		
    		return 0;
    	}
    	
	}

}
