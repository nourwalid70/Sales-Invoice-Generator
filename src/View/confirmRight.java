package View;

import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class confirmRight extends JDialog{
    private JTextField itemNameField;
    private JTextField itemCountField;
    private JTextField itemPriceField;
    private JLabel itemNameLbl;
    private JLabel itemCountLbl;
    private JLabel itemPriceLbl;
    private JButton okBtn;
    private JButton cancelBtn;
    
    public confirmRight(mainPage frame) {
        itemNameField = new JTextField(20);
        itemNameLbl = new JLabel("Item Name");
        
        itemCountField = new JTextField(20);
        itemCountLbl = new JLabel("Item Count");
        
        itemPriceField = new JTextField(20);
        itemPriceLbl = new JLabel("Item Price");
        
        okBtn = new JButton("OK");
        cancelBtn = new JButton("Cancel");
        
        okBtn.setActionCommand("createLineOK");
        cancelBtn.setActionCommand("createLineCancel");
        
        okBtn.addActionListener(frame.getListener());
        cancelBtn.addActionListener(frame.getListener());
        setLayout(new GridLayout(5, 2));
        
        add(itemNameLbl);
        add(itemNameField);
        add(itemCountLbl);
        add(itemCountField);
        add(itemPriceLbl);
        add(itemPriceField);
        add(okBtn);
        add(cancelBtn);
        pack();
    }
    
    public JTextField getRightItemName() {
        return this.itemNameField;
    }

    public JTextField getRightCount() {
        return this.itemCountField;
    }

    public JTextField getRightPrice() {
        return this.itemPriceField;
    }
}