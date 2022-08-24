package View;

import Controller.controll;
import Model.InvoiceHeader;
import Model.invoiceHeaderTableModel;
import Model.invoiceLineTableModel;
import java.util.List;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.GroupLayout.Alignment;
import javax.swing.GroupLayout;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.UIManager;
import javax.swing.WindowConstants;


@SuppressWarnings("serial")
public class mainPage extends JFrame {

public mainPage() {
	// title page
	setTitle("Design Preview");
	
	// menu bar
    menuBar = new JMenuBar();
    menu = new JMenu();
    
    loadMenuItem = new JMenuItem("Load", 'l');
    loadMenuItem.setAccelerator(KeyStroke.getKeyStroke('L',KeyEvent.CTRL_DOWN_MASK));
    loadMenuItem.setActionCommand("LoadFile");
    loadMenuItem.addActionListener(listener);
    menu.add(loadMenuItem);
    
    saveMenuItem = new JMenuItem("Save", 's');
    saveMenuItem.setAccelerator(KeyStroke.getKeyStroke('S',KeyEvent.CTRL_DOWN_MASK));
    saveMenuItem.setActionCommand("SaveFile");
    saveMenuItem.addActionListener(listener);
    menu.add(saveMenuItem);
    
    menu.setText("File");
    menuBar.add(menu);
    setJMenuBar(menuBar);

    // left side table
    scrollLeft = new JScrollPane();
    invoicesTable = new JTable();
    invoicesTable.getSelectionModel().addListSelectionListener(listener);
    createInvBtn = new JButton("Create New Invoice");
    createInvBtn.setActionCommand("CreateNewInvoice");
    createInvBtn.addActionListener(listener);
    deleteInvBtn = new javax.swing.JButton("Delete Invoice");
    deleteInvBtn.setActionCommand("DeleteInvoice");
    deleteInvBtn.addActionListener(listener);
    jLabel1 = new JLabel("Invoice Number");
    jLabel2 = new JLabel("Invoide Date");
    jLabel3 = new JLabel("Customer name");
    jLabel4 = new JLabel("Invoice Total");
    custNameTF = new JTextField();
    invDateTF = new JTextField();
    invNumLbl = new JLabel();
    invTotalLbl = new JLabel();
    scrollLeft.setViewportView(invoicesTable);

    // right side table
    scrollRight = new JScrollPane();
    invLinesTable = new JTable();
    createLineBtn = new JButton("Create New Line");
    createLineBtn.setActionCommand("CreateNewLine");
    createLineBtn.addActionListener(listener);
    deleteLineBtn = new JButton("Delete Line");
    createLineBtn.setActionCommand("DeleteLine");
    deleteLineBtn.addActionListener(listener);
    scrollRight.setViewportView(invLinesTable);
    
    invoicesTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
    
    invLinesTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
    
    setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

    GroupLayout layout = new GroupLayout(getContentPane());
    layout.setHorizontalGroup(
    	layout.createParallelGroup(Alignment.LEADING)
    		.addGroup(layout.createSequentialGroup()
    			.addGroup(layout.createParallelGroup(Alignment.LEADING)
    				.addGroup(layout.createSequentialGroup()
    					.addGroup(layout.createParallelGroup(Alignment.LEADING, false)
    						.addGroup(layout.createSequentialGroup()
    							.addContainerGap()
    							.addComponent(scrollLeft, GroupLayout.PREFERRED_SIZE, 412, GroupLayout.PREFERRED_SIZE)
    							.addGap(22))
    						.addGroup(Alignment.TRAILING, layout.createSequentialGroup()
    							.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
    							.addComponent(createInvBtn)
    							.addGap(41)
    							.addComponent(deleteInvBtn)
    							.addGap(104)))
    					.addGroup(layout.createParallelGroup(Alignment.LEADING)
    						.addGroup(layout.createSequentialGroup()
    							.addGroup(layout.createParallelGroup(Alignment.LEADING, false)
    								.addComponent(jLabel3, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
    								.addComponent(jLabel2, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE)
    								.addComponent(jLabel4, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
    							.addGroup(layout.createParallelGroup(Alignment.LEADING)
    								.addGroup(layout.createSequentialGroup()
    									.addGap(18)
    									.addGroup(layout.createParallelGroup(Alignment.LEADING)
    										.addComponent(invNumLbl)
    										.addComponent(invTotalLbl)))
    								.addGroup(layout.createSequentialGroup()
    									.addPreferredGap(ComponentPlacement.UNRELATED)
    									.addGroup(layout.createParallelGroup(Alignment.LEADING)
    										.addComponent(custNameTF, 361, 361, 361)
    										.addComponent(invDateTF, 361, 361, 361)))))
    						.addComponent(scrollRight, GroupLayout.PREFERRED_SIZE, 452, GroupLayout.PREFERRED_SIZE)
    						.addComponent(jLabel1, GroupLayout.PREFERRED_SIZE, 82, GroupLayout.PREFERRED_SIZE)))
    				.addGroup(layout.createSequentialGroup()
    					.addGap(549)
    					.addComponent(createLineBtn)
    					.addGap(41)
    					.addComponent(deleteLineBtn)))
    			.addGap(40))
    );
    layout.setVerticalGroup(
    	layout.createParallelGroup(Alignment.LEADING)
    		.addGroup(layout.createSequentialGroup()
    			.addContainerGap()
    			.addGroup(layout.createParallelGroup(Alignment.LEADING)
    				.addGroup(layout.createSequentialGroup()
    					.addGroup(layout.createParallelGroup(Alignment.BASELINE)
    						.addComponent(jLabel1)
    						.addComponent(invNumLbl))
    					.addPreferredGap(ComponentPlacement.UNRELATED)
    					.addGroup(layout.createParallelGroup(Alignment.BASELINE)
    						.addComponent(jLabel2)
    						.addComponent(invDateTF, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
    					.addPreferredGap(ComponentPlacement.UNRELATED)
    					.addGroup(layout.createParallelGroup(Alignment.BASELINE)
    						.addComponent(jLabel3)
    						.addComponent(custNameTF, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
    					.addPreferredGap(ComponentPlacement.UNRELATED)
    					.addGroup(layout.createParallelGroup(Alignment.BASELINE)
    						.addComponent(jLabel4)
    						.addComponent(invTotalLbl))
    					.addGap(12)
    					.addComponent(scrollRight, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
    				.addGroup(layout.createSequentialGroup()
    					.addComponent(scrollLeft, GroupLayout.PREFERRED_SIZE, 473, GroupLayout.PREFERRED_SIZE)
    					.addGap(18)
    					.addGroup(layout.createParallelGroup(Alignment.BASELINE)
    						.addComponent(deleteInvBtn)
    						.addComponent(createInvBtn))))
    			.addGap(8)
    			.addGroup(layout.createParallelGroup(Alignment.BASELINE)
    				.addComponent(createLineBtn)
    				.addComponent(deleteLineBtn))
    			.addGap(15))
    );
    getContentPane().setLayout(layout);
    pack();
}


public static void main(String args[]) {

    try {
        for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
            if ("Nimbus".equals(info.getName())) {
                UIManager.setLookAndFeel(info.getClassName());
                break;
            }
        }
    } catch (ClassNotFoundException ex) {
        java.util.logging.Logger.getLogger(mainPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
    } catch (InstantiationException ex) {
        java.util.logging.Logger.getLogger(mainPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
    } catch (IllegalAccessException ex) {
        java.util.logging.Logger.getLogger(mainPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
    } catch (javax.swing.UnsupportedLookAndFeelException ex) {
        java.util.logging.Logger.getLogger(mainPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
    }

    java.awt.EventQueue.invokeLater(new Runnable() {
        public void run() {
            new mainPage().setVisible(true);
        }
    });
}

private JButton createInvBtn;
private JButton createLineBtn;
private JTextField custNameTF;
private JButton deleteInvBtn;
private JButton deleteLineBtn;
private JTextField invDateTF;
private JTable invLinesTable;
private JLabel invNumLbl;
private JLabel invTotalLbl;
private JTable invoicesTable;
private JLabel jLabel1;
private JLabel jLabel2;
private JLabel jLabel3;
private JLabel jLabel4;
private JMenu menu;
private JMenuBar menuBar;
private JScrollPane scrollLeft;
private JScrollPane scrollRight;
private JMenuItem loadMenuItem;
private JMenuItem saveMenuItem;

private List<InvoiceHeader> list = new ArrayList<>();
private invoiceHeaderTableModel ht;
private invoiceLineTableModel lt;
private confirmLeft cl;
private confirmRight cr;
private controll listener = new controll(this);

public controll getListener() {
    return listener;
}

public void setConfirmLeft(confirmLeft cl) {
    this.cl = cl;
}

public void setConfirmRight(confirmRight cr) {
    this.cr = cr;
}

public void setInvoiceHeaderTableModel(invoiceHeaderTableModel ht) {
    this.ht = ht;
}

public void setInvoiceLinesTableModel(invoiceLineTableModel lt) {
    this.lt = lt;
}

public JButton getCreateInvBtn() {
    return this.createInvBtn;
}

public JButton getCreateLineBtn() {
    return this.createLineBtn;
}

public JTextField getCustNameTF() {
    return this.custNameTF;
}

public JButton getDeleteInvBtn() {
    return this.deleteInvBtn;
}

public JButton getDeleteLineBtn() {
    return this.deleteLineBtn;
}

public JTextField getInvDateTF() {
    return this.invDateTF;
}

public JTable getInvLinesTable() {
    return this.invLinesTable;
}

public JLabel getInvNumLbl() {
    return this.invNumLbl;
}

public JLabel getInvTotalLbl() {
    return this.invTotalLbl;
}

public JTable getInvoicesTable() {
    return this.invoicesTable;
}

public JMenuItem getLoadMenuItem() {
    return this.loadMenuItem;
}

public JMenuItem getSaveMenuItem() {
    return this.saveMenuItem;
}

public List<InvoiceHeader> getInvoicesList() {
    return this.list;
}

public invoiceHeaderTableModel getInvoiceHeaderTableModel() {
    return this.ht;
}

public invoiceLineTableModel getInvoiceLinesTableModel() {
    return this.lt;
}

public confirmLeft getConfirmLeft() {
    return this.cl;
}

public confirmRight getConfirmRight() {
    return this.cr;
 }
}