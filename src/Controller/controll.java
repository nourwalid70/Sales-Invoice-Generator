package Controller;
import Model.InvoiceHeader;
import Model.invoiceHeaderTableModel;
import Model.InvoiceLine;
import Model.invoiceLineTableModel;
import View.mainPage;
import View.confirmLeft;
import View.confirmRight;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;


public class controll implements ActionListener, ListSelectionListener  {
    private mainPage frame;
    private DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
    
    public controll(mainPage frame) {
        this.frame = frame;
    }
    
    
    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
	        case "LoadFile":
	            loadFile();
	            break;
	        case "SaveFile":
	            saveFile();
	            break;
	            
            case "CreateNewInvoice":
                displayNewInvoiceDialog();
                break;
            case "DeleteInvoice":
                deleteInvoice();
                break;
            case "createInvOK":
                createInvOK();
                break;
            case "createInvCancel":
                createInvCancel();
                break;
                
            case "CreateNewLine":
                displayNewLineDialog();
                break;
            case "DeleteLine":
                deleteLine();
                break;
            case "createLineOK":
                createLineOK();
                break; 
            case "createLineCancel":
                createLineCancel();
                break;
        }
    }

    private void loadFile() {
        JOptionPane.showMessageDialog(frame, "Please, select your header file", "Header file", JOptionPane.WARNING_MESSAGE);
        JFileChooser fc = new JFileChooser();
        int result = fc.showOpenDialog(frame);
        if (result == JFileChooser.APPROVE_OPTION) {
            File f = fc.getSelectedFile();
            try {
                FileReader headerFr = new FileReader(f);
                BufferedReader headerBr = new BufferedReader(headerFr);
                String headerLine = null;

                while ((headerLine = headerBr.readLine()) != null) {
                    String[] headerParts = headerLine.split(",");
                    String invNumStr = headerParts[0];
                    String invDateStr = headerParts[1];
                    String custName = headerParts[2];

                    int invNum = Integer.parseInt(invNumStr);
                    Date invDate = df.parse(invDateStr);

                    InvoiceHeader inv = new InvoiceHeader(invNum, invDate, custName);
                    frame.getInvoicesList().add(inv);
                }

                JOptionPane.showMessageDialog(frame, "Please, select your lines file!", "Line file", JOptionPane.WARNING_MESSAGE);
                result = fc.showOpenDialog(frame);
                if (result == JFileChooser.APPROVE_OPTION) {
                    File linesFile = fc.getSelectedFile();
                    BufferedReader linesBr = new BufferedReader(new FileReader(linesFile));
                    String linesLine = null;
                    while ((linesLine = linesBr.readLine()) != null) {
                        String[] lineParts = linesLine.split(",");
                        String invNumStr = lineParts[0];
                        String itemName = lineParts[1];
                        String itemPriceStr = lineParts[2];
                        String itemCountStr = lineParts[3];

                        int invNum = Integer.parseInt(invNumStr);
                        double itemPrice = Double.parseDouble(itemPriceStr);
                        int itemCount = Integer.parseInt(itemCountStr);
                        InvoiceHeader header = findInvoiceByNum(invNum);
                        InvoiceLine invLine = new InvoiceLine(header, itemName, itemPrice, itemCount);
                        header.getList().add(invLine);
                    }
                    frame.setInvoiceHeaderTableModel(new invoiceHeaderTableModel(frame.getInvoicesList()));
                    frame.getInvoicesTable().setModel(frame.getInvoiceHeaderTableModel());
                    frame.getInvoicesTable().validate();
                }
            } catch (ParseException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(frame, "Parsing Error\n" + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            } catch (NumberFormatException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(frame, "Number Format Error\n" + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(frame, "File not found\n" + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            } catch (IOException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(frame, "Read Error\n" + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }finally {
            	displayInvoices();
            }
           
        }
    }

    private void saveFile() {
        String headers = "";
        String lines = "";
        for (InvoiceHeader header : frame.getInvoicesList()) {
            headers += header.forCSV();
            headers += "\n";
            for (InvoiceLine line : header.getList()) {
                lines += line.forCSV();
                lines += "\n";
            }
        }
        JOptionPane.showMessageDialog(frame, "Please, select file to save header data!", "Attension", JOptionPane.WARNING_MESSAGE);
        JFileChooser fc = new JFileChooser();
        int result = fc.showSaveDialog(frame);
        if (result == JFileChooser.APPROVE_OPTION) {
            File headerFile = fc.getSelectedFile();
            FileWriter hFW = null;
            FileWriter lFW = null;
            try {
                hFW = new FileWriter(headerFile);
                hFW.write(headers);
                hFW.flush();
                hFW.close();

                JOptionPane.showMessageDialog(frame, "Please, select file to save lines data!", "Attension", JOptionPane.WARNING_MESSAGE);
                result = fc.showSaveDialog(frame);
                if (result == JFileChooser.APPROVE_OPTION) {
                    File linesFile = fc.getSelectedFile();
                    lFW = new FileWriter(linesFile);
                    lFW.write(lines);
                    lFW.flush();
                    lFW.close();
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(frame, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }finally {
            	try {
					hFW.close();
					lFW.close();
				} catch (IOException e) {}
            }
        }
        JOptionPane.showMessageDialog(frame, "Data saved successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
    }

    private InvoiceHeader findInvoiceByNum(int num) {
        InvoiceHeader header = null;
        for (InvoiceHeader inv : frame.getInvoicesList()) {
            if (inv.getInvoiceNum()== num) {
                header = inv;
                break;
            }
        }
        return header;
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        System.out.println("Invoice Selected!");
        invoicesTableRowSelected();
    }

    private void invoicesTableRowSelected() {
        int selectedRowIndex = frame.getInvoicesTable().getSelectedRow();
        if (selectedRowIndex >= 0) {
            InvoiceHeader row = frame.getInvoiceHeaderTableModel().getList().get(selectedRowIndex);
            frame.getCustNameTF().setText(row.getCustomerName());
            frame.getInvDateTF().setText(df.format(row.getInvoiceDate()));
            frame.getInvNumLbl().setText("" + row.getInvoiceNum());
            frame.getInvTotalLbl().setText("" + row.getListTotal());
            ArrayList<InvoiceLine> lines = row.getList();
            frame.setInvoiceLinesTableModel(new invoiceLineTableModel(lines));
            frame.getInvLinesTable().setModel(frame.getInvoiceLinesTableModel());
            frame.getInvoiceLinesTableModel().fireTableDataChanged();
        }
    }
    
    private void displayNewInvoiceDialog() {
        frame.setConfirmLeft(new confirmLeft(frame));
        frame.getConfirmLeft().setVisible(true);
    }

    private void displayNewLineDialog() {
        frame.setConfirmRight(new confirmRight(frame));
        frame.getConfirmRight().setVisible(true);
    }

    private void createInvCancel() {
        frame.getConfirmLeft().setVisible(false);
        frame.getConfirmLeft().dispose();
        frame.setConfirmLeft(null);
    }

    private void createInvOK() {
        String custName = frame.getConfirmLeft().getLeftCusName().getText();
        String invDateStr = frame.getConfirmLeft().getLeftDate().getText();
        try {
            Date invDate = df.parse(invDateStr);
            int invNum = getNextInvoiceNum();
            InvoiceHeader invoiceHeader = new InvoiceHeader(invNum, invDate, custName);
            frame.getInvoicesList().add(invoiceHeader);
            frame.getInvoiceHeaderTableModel().fireTableDataChanged();
            frame.getConfirmLeft().setVisible(false);
            frame.getConfirmLeft().dispose();
        } catch (ParseException e) {
            JOptionPane.showMessageDialog(frame, "Wrong date format", "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }finally {
        	displayInvoices();
        }   
    }

    private int getNextInvoiceNum() {
        int max = Integer.MIN_VALUE;
        for (InvoiceHeader header : frame.getInvoicesList()) {
        	int temp = header.getInvoiceNum();
            if (temp > max) {
                max = temp;
            }
        }
        return max+1;
    }

    private void createLineCancel() {
        frame.getConfirmRight().setVisible(false);
        frame.getConfirmRight().dispose();
        frame.setConfirmRight(null);
    }

    private void createLineOK() {
	    String itemName = frame.getConfirmRight().getRightItemName().getText();
	    String itemCountStr = frame.getConfirmRight().getRightCount().getText();
	    String itemPriceStr = frame.getConfirmRight().getRightPrice().getText();
	    int itemCount = Integer.parseInt(itemCountStr);
	    double itemPrice = Double.parseDouble(itemPriceStr);
	    int headerIndex = frame.getInvoicesTable().getSelectedRow();
	    if (headerIndex != -1){
	    InvoiceHeader invoice = frame.getInvoiceHeaderTableModel().getList().get(headerIndex);
	    InvoiceLine invoiceLine = new InvoiceLine(invoice, itemName, itemPrice, itemCount);
	    invoice.addLine(invoiceLine);
	    frame.getInvoiceLinesTableModel().fireTableDataChanged();
	    frame.getInvoiceHeaderTableModel().fireTableDataChanged();
	    frame.getInvTotalLbl().setText("" + invoice.getListTotal());
	    displayInvoices();
        }
    }

    private void deleteInvoice() {
        int invIndex = frame.getInvoicesTable().getSelectedRow();
        if (invIndex != -1) {
	        frame.getInvoiceHeaderTableModel().getList().remove(invIndex);
	        frame.getInvoiceHeaderTableModel().fireTableDataChanged();
	        frame.setInvoiceLinesTableModel(new invoiceLineTableModel(new ArrayList<InvoiceLine>()));
	        frame.getInvLinesTable().setModel(frame.getInvoiceLinesTableModel());
	        frame.getInvoiceHeaderTableModel().fireTableDataChanged();
	        frame.getCustNameTF().setText("");
	        frame.getInvDateTF().setText("");
	        frame.getInvNumLbl().setText("");
	        frame.getInvTotalLbl().setText("");
        }
        frame.getInvoicesTable().setVisible(false);
        displayInvoices();  
    }

    private void deleteLine() {
        int lineIndex = frame.getInvLinesTable().getSelectedRow();
        if (lineIndex != -1) {
	        InvoiceLine line = frame.getInvoiceLinesTableModel().getList().get(lineIndex);
	        frame.getInvoiceLinesTableModel().getList().remove(lineIndex);
	        frame.getInvoiceLinesTableModel().fireTableDataChanged();
	        frame.getInvoiceHeaderTableModel().fireTableDataChanged();
	        frame.getInvTotalLbl().setText("" + line.getNum().getListTotal());
	        displayInvoices();
        }
    }

    private void displayInvoices() {
        for (InvoiceHeader header : frame.getInvoicesList()) {
            System.out.println(header);
        }
    }
    
}