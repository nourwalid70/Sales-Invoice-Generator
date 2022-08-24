package Model;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

@SuppressWarnings("serial")
public class invoiceLineTableModel extends AbstractTableModel{

	private List<InvoiceLine> list;
	
	public invoiceLineTableModel(){}
	
	public invoiceLineTableModel(ArrayList<InvoiceLine> list) {
		super();
		this.list = list;
	}
	
    public List<InvoiceLine> getList() {
    	if(list==null) this.list =  new ArrayList<InvoiceLine>();
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
		InvoiceLine currRow = list.get(rowIndex);
		if(columnIndex==0) return currRow.getRowNum();
		else if(columnIndex==1) return currRow.getItemName();
		else if(columnIndex==2) return currRow.getItemPrice();
		else if(columnIndex==3) return currRow.getCount();
		else if(columnIndex==4) return currRow.getTotal();
		else return null;
	}

}
