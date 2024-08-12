package storeManagerGUIView;

import controller.Backend_DAO_List;
import model.Customer;
import model.Product;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ViewPurchasesForm {
    private JComboBox cmb_customers;
    private JList lst_products;
    public JPanel panel;
    private JLabel lbl_total;
    private JLabel lbl_price;

    Backend_DAO_List b = Backend_DAO_List.get();



    public ViewPurchasesForm() {
        DefaultListModel selectedProductsListModel=new DefaultListModel();
        try {
            DefaultComboBoxModel modelCustomer=new DefaultComboBoxModel();
            modelCustomer.addAll(b.getAllCustomers().values());
            cmb_customers.setModel(modelCustomer);
        }catch (Exception ex){

        }
        cmb_customers.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    selectedProductsListModel.clear();
                    selectedProductsListModel.addAll(b.getCustomersOrders((Customer) cmb_customers.getSelectedItem()));
                }catch (Exception ex){
                    throw new RuntimeException(ex);
                }
                lst_products.setModel(selectedProductsListModel);
                try {
                    Product[] products=new Product[selectedProductsListModel.size()];
                    selectedProductsListModel.copyInto(products);
                    Float total= (float) b.CalcProductsTotalCost(products);
                    lbl_price.setText(total.toString());
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }

            }
        });
    }
}
