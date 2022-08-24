package Model;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

@SuppressWarnings("serial")
public class invoiceHeaderTableModel extends AbstractTableModel{

	private List<InvoiceHeader> list;
	
	
	public invoiceHeaderTableModel(){}
	
	public invoiceHeaderTableModel(List<InvoiceHeader> list) {
		super();
		this.list = list;
	}
	
    public List<InvoiceHeader> getList() {
    	if(list==null) this.list =  new ArrayList<InvoiceHeader>();
        return list;
    }

	@Override
	public int getRowCount() {
		return list.size();
	}

	@Override
	public int getColumnCount() {
		return 4;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		InvoiceHeader currRow = list.get(rowIndex);
		if(columnIndex==0) return currRow.getInvoiceNum();
		else if(columnIndex==1) return currRow.getInvoiceNum();
		else if(columnIndex==2) return currRow.getInvoiceDate();
		else if(columnIndex==3) return currRow.getCustomerName();
		else return null;
	}

}
