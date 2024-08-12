package storeManagerGUIView;

import controller.Backend_DAO_List;
import model.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddNewProductForm {
    private JTextField txt_name;
    private JTextField txt_des;
    private JTextField txt_price;
    private JTextField txt_changes;
    private JButton btn_add;
    private JComboBox comboBox1;
    private JLabel lbl_changes;
    public JPanel panel;
    private JLabel lbl_des;
    private JLabel lbl_name;
    private JLabel priceLabel;

    DefaultComboBoxModel model;

    Backend_DAO_List b = Backend_DAO_List.get();
    public AddNewProductForm() {

        this.model = new DefaultComboBoxModel(ProductType.values());
        comboBox1.setModel(model);
        comboBox1.setSelectedIndex(0);
        lbl_changes.setText("Number of Users:");

        comboBox1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                lbl_changes.setText(isInHardwareMode()?"Warrenty Period:":"Number of Users:");
            }
        });
        btn_add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Product product = null;
                    long id = Long.parseLong(txt_name.getText().trim());

                    if (txt_name.getText().isBlank())
                        throw new Exception("חובה להכניס שם מוצר");


                    if(isInHardwareMode())
                    {
                        product  = new Hardwareproduct(1,txt_name.getText(),txt_des.getText(),Float.parseFloat(txt_price.getText()),Integer.parseInt(txt_changes.getText()));
                    }
                    else {
                        product  = new SoftwareProduct(2,txt_name.getText(),txt_des.getText(),Float.parseFloat(txt_price.getText()),Integer.parseInt(txt_changes.getText()));
                    }

                    b.AddProduct(product);
                    JOptionPane.showMessageDialog(panel,"המוצר התווסף בהצלחה");
                    System.out.println(b.getAllCustomers());
                }
                catch (Exception ex)
                {
                    JOptionPane.showMessageDialog(panel,ex.getMessage());

                }
        }
        });

    }
    private boolean isInHardwareMode(){
            return comboBox1.getSelectedItem().equals(ProductType.HARDWARE);
        }

    public static void main(String[] args) {
        JFrame frame = new JFrame("AddNewProductForm");
        frame.setContentPane(new AddNewProductForm().panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
