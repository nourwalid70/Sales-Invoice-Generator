package Model;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class InvoiceHeader {
	
	private int invoiceNum;
	private Date invoiceDate;
	private String customerName;
	private ArrayList<InvoiceLine> list;
	
    public InvoiceHeader(){
    	this.list = new ArrayList<>();
    }
	
	public InvoiceHeader(int invoiceNum, Date invoiceDate, String customerName) {
		super();
		this.invoiceNum = invoiceNum;
		this.invoiceDate = invoiceDate;
		this.customerName = customerName;
	}


	public int getInvoiceNum() {
		return invoiceNum;
	}


	public void setInvoiceNum(int invoiceNum) {
		this.invoiceNum = invoiceNum;
	}


	public Date getInvoiceDate() {
		return invoiceDate;
	}


	public void setInvoiceDate(Date invoiceDate) {
		this.invoiceDate = invoiceDate;
	}


	public String getCustomerName() {
		return customerName;
	}


	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public ArrayList<InvoiceLine> getList() {
		if(list==null) this.list =  new ArrayList<>();
		return list;
	}


	public void setList(ArrayList<InvoiceLine> list) {
		this.list = list;
	}
	
    public void addLine(InvoiceLine line) {
    	getList().add(line);
    }
	
	public double getListTotal() {
		double res = 0.0;
		for(int i=0; i<list.size(); i++) 
			res += list.get(i).getTotal();
		
		return res;
	}
	
	@Override
	public String toString() {
		String l = null;
		for(int i=0; i<list.size(); i++)
			l += "\n" + list.get(i);
		return "InvoiceHeader [invoiceNum=" + invoiceNum + ", invoiceDate=" + invoiceDate + ", customerName="
				+ customerName + ", list=" + l + "]";
	}


	//data for CSV file
	public String forCSV() {
	    SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd-MM-yyyy");
        String date = DATE_FORMAT.format(getInvoiceDate());
		return getInvoiceNum() +
				  "," + date +
				  "," + getCustomerName();
	}
}
