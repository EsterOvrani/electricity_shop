package storeManagerGUIView;

import controller.Backend_DAO_List;
import model.Customer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.SQLOutput;

public class AddNewCustomerForm extends JFrame {

    Backend_DAO_List bdl = Backend_DAO_List.get();

    private JButton jButtonOK;
    private JLabel  jLabelID;
    private JLabel  jLabelName;
    private JLabel  jLabelAddress;
    private JTextField    jTextFieldID;
    private JTextField   jTextFieldName;
    private JTextField   jTextFieldAddress;

    public AddNewCustomerForm() {
        jButtonOK = new JButton("OK");
        jLabelID = new JLabel(" ID:");
        jLabelName = new JLabel(" Name:");
        jLabelAddress = new JLabel(" Address:");
        jTextFieldID = new JTextField();
        jTextFieldName = new JTextField();
        jTextFieldAddress = new JTextField();

        getContentPane().add(jLabelID);
        getContentPane().add(jTextFieldID);

        getContentPane().add(jLabelName);
        getContentPane().add(jTextFieldName);

        getContentPane().add(jLabelAddress);
        getContentPane().add(jTextFieldAddress);

        getContentPane().add(jButtonOK);

        this.setPreferredSize(new Dimension(400,200));
        getContentPane().setLayout(new GridLayout(0,2,10,10));
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        this.pack();
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
        this.setVisible(true);


        jTextFieldID.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (jTextFieldID.getText().length()>=9)
                {
                    e.consume();
                    System.out.println("MORE THEN 9 DIGITS");
                }
                if(!Character.isDigit(e.getKeyChar()))
                {
                    e.consume();
                    System.out.println("ENTER JUST DIGITS");
                }

            }
        });


        jButtonOK.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    long id = Long.parseLong(jTextFieldID.getText().trim());

                    if (jTextFieldName.getText().isBlank())
                        throw new Exception("חובה להכניס שם");
                    if (bdl.getAllCustomers().containsKey(id))
                        throw new Exception("מספר זהות קיים במערכת");


                    Customer c = new Customer();
                    c.setName(jTextFieldName.getText());
                    c.setId(id);
                    c.setAddress(jTextFieldAddress.getText());
                    bdl.AddCustomer(c);
                    JOptionPane.showMessageDialog(AddNewCustomerForm.this,"הלקוח התווסף בהצלחה");
                    System.out.println(bdl.getAllCustomers());
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(AddNewCustomerForm.this,ex.getMessage());
                }

            }
        });



    }




}
