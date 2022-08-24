package Model;

public class InvoiceLine {
	
	private InvoiceHeader num; // to get the number of item
	private String itemName;
	private double itemPrice;
	private int count;
	
	public InvoiceLine(){}
	
	public InvoiceLine(InvoiceHeader num, String itemName, double itemPrice, int count) {
		super();
		this.num = num;
		this.itemName = itemName;
		this.itemPrice = itemPrice;
		this.count = count;
	}

	public InvoiceHeader getNum() {
		return this.num;
	}
	
	public int getRowNum() {
		return getNum().getInvoiceNum();
	}
	
	public void setNum(InvoiceHeader num) {
		this.num = num;
	}

	public String getItemName() {
		return this.itemName;
	}


	public void setItemName(String itemName) {
		this.itemName = itemName;
	}


	public double getItemPrice() {
		return this.itemPrice;
	}


	public void setItemPrice(double itemPrice) {
		this.itemPrice = itemPrice;
	}


	public int getCount() {
		return this.count;
	}


	public void setCount(int count) {
		this.count = count;
	}

    public double getTotal() {
    	return this.count * this.itemPrice;
    }
	
	@Override
	public String toString() {
		return "InvoiceLine [itemName=" + itemName + ", itemPrice=" + itemPrice + ", count=" + count + "]";
	}
	
	//data for CSV file
	public String forCSV() {
		return getNum().getInvoiceNum() +
				  "," + getItemName() +
				  "," + getItemPrice() +
				  "," + getCount();
	}
	
	

}
