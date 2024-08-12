package storeManagerGUIView;

import controller.Backend_DAO_List;
import model.Product;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ManageCatalogForm {
    private JList lst_Product;
    private JButton btn_Delete;
    private JButton btn_AddNew;
    public JPanel panel;
    private DefaultListModel<Product> AllProductsListModel;
     Backend_DAO_List b = Backend_DAO_List.get();

    public ManageCatalogForm() {

        AllProductsListModel = new DefaultListModel<Product>();
        lst_Product.setModel(AllProductsListModel);
        RefreshProductList();

        btn_AddNew.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame("AddNewProductForm");
                frame.setContentPane(new AddNewProductForm().panel);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.pack();
                Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
                frame.setLocation(dim.width / 2 - frame.getSize().width / 2, dim.height / 2 - frame.getSize().height / 2);
                frame.setVisible(true);
            }
        });


        btn_Delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<Product> selectedValuesList = lst_Product.getSelectedValuesList();
                for (Product p : selectedValuesList) {
                    AllProductsListModel.removeElement(p);
                    try {
                        b.RemoveProduct(p);
                    } catch (Exception ex) {
                        throw new RuntimeException(ex);

                    }
                }
            }
        });
    }
    public void RefreshProductList() {
        try {
            AllProductsListModel.clear();
            AllProductsListModel.addAll(b.getAllProducts());


    } catch (Exception ex) {
            JOptionPane.showMessageDialog(panel,ex.getMessage());
    }
  }


    public static void main(String[] args) {
        JFrame frame = new JFrame("ManageCatalogForm");
        frame.setContentPane(new ManageCatalogForm().panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();

        frame.setVisible(true);

    }
}
