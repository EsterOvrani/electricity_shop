package storeManagerGUIView;

import controller.Backend_DAO_List;
import model.Customer;
import model.Product;
import model.PurchaseOrder;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PlaceOrderForm {
    private JList lst_orderDetails;
    private JButton btn_addToOrder;
    private JComboBox cmbox_cus;
    private JComboBox cmbox_prod;
    private JButton btn_submitOrder;
    private JButton btn_removeSelectedProducts;
    private JButton btn_calculateTotal;
    private JLabel lbl_Customer;
    private JLabel lbl_Product;
    private JLabel lbl_sum;
    public JPanel panel;

    Backend_DAO_List b = Backend_DAO_List.get();

    DefaultListModel SelectedProductsListModel = new DefaultListModel();
    DefaultComboBoxModel CustomerModel;
    DefaultComboBoxModel ProductModel;


    public PlaceOrderForm() {

        try {
            this.CustomerModel = new DefaultComboBoxModel(b.getAllCustomers().values().toArray());
            this.ProductModel = new DefaultComboBoxModel(b.getAllProducts().toArray());
            cmbox_cus.setModel(CustomerModel);
            cmbox_prod.setModel(ProductModel);
            cmbox_cus.setSelectedIndex(0);
            cmbox_prod.setSelectedIndex(0);


        } catch (Exception ex) {
            Logger.getLogger(PlaceOrderForm.class.getName()).log(Level.SEVERE, null, ex);
        }

        cmbox_cus.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });


        cmbox_prod.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        btn_addToOrder.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SelectedProductsListModel.addElement((Product) cmbox_prod.getSelectedItem());
                lst_orderDetails.setModel(SelectedProductsListModel);

            }
        });

        btn_removeSelectedProducts.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (Object objProduct : lst_orderDetails.getSelectedValuesList()) {
                    SelectedProductsListModel.removeElement(objProduct);
                }

            }
        });
        btn_submitOrder.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    PurchaseOrder newOrder = new PurchaseOrder();
                    newOrder.setProductslist(new ArrayList(Arrays.asList(SelectedProductsListModel.toArray())));
                    newOrder.setOrderingCustomer((Customer) cmbox_cus.getSelectedItem());
                    newOrder.setOrderDate(LocalDate.now());
                    b.PlaceOrder((newOrder));
                    JOptionPane.showMessageDialog(panel,"ההזמנה נוספה בהצלחה");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Error Placing order", "Error",
                            JOptionPane.ERROR_MESSAGE);
                    Logger.getLogger(PlaceOrderForm.class.getName()).log(Level.SEVERE, null, ex);


                }

            }
        });

        btn_calculateTotal.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Product[] products = new Product[SelectedProductsListModel.size()];
                    SelectedProductsListModel.copyInto(products);
                    Float total = b.CalcProductsTotalCost(products);
                    lbl_sum.setText(total.toString());
                } catch (Exception ex) {
                    Logger.getLogger(PlaceOrderForm.class.getName()).log(Level.SEVERE, null, ex);
                }


            }
        });
    }
}

