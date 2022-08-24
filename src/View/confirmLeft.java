package View;

import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.text.JTextComponent;

@SuppressWarnings("serial")
public class confirmLeft extends JDialog {
    private JTextField leftCusName;
    private JTextField leftDate;
    private JLabel custNameLbl;
    private JLabel invDateLbl;
    private JButton okBtn;
    private JButton cancelBtn;

    public confirmLeft(mainPage frame) {
        custNameLbl = new JLabel("Customer Name:");
        leftCusName = new JTextField(20);
        invDateLbl = new JLabel("Invoice Date:");
        leftDate = new JTextField(20);
        okBtn = new JButton("OK");
        okBtn.setActionCommand("createInvoiceOK");
        okBtn.addActionListener((ActionListener) frame.getListener());
        cancelBtn = new JButton("Cancel");
        cancelBtn.setActionCommand("createInvoiceCancel");
        cancelBtn.addActionListener((ActionListener) frame.getListener());
        
        getContentPane().setLayout(new GridLayout(4, 2));
        getContentPane().add(invDateLbl);
        getContentPane().add(leftDate);
        getContentPane().add(custNameLbl);
        getContentPane().add(leftCusName);
        getContentPane().add(okBtn);
        getContentPane().add(cancelBtn);
        pack();
        
    }

	public JTextField getLeftCusName() {
		return leftCusName;
	}

	public JTextField getLeftDate() {
		return leftDate;
	}
   
}