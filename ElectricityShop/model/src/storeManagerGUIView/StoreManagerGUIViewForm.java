package storeManagerGUIView;

import controller.Backend_DAO_List;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
public class StoreManagerGUIViewForm {
    private JPanel panel;
    private JButton btn_AddCos;
    private JButton btn_Products;
    private JButton btn_MakeOrder;
    private JButton btn_ShowOrders;

    public StoreManagerGUIViewForm() {
        btn_AddCos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                AddNewCustomerForm customerForm = new AddNewCustomerForm();
            }
        });

        btn_Products.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                    JFrame frame = new JFrame("ManageCatalogForm");
                    frame.setContentPane(new ManageCatalogForm().panel);
                    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    frame.pack();
                Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
                frame.setLocation(dim.width/2-frame.getSize().width/2, dim.height/2-frame.getSize().height/2);
                    frame.setVisible(true);


            }
        });

        btn_MakeOrder.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame("PlaceOrderForm");
                frame.setContentPane(new PlaceOrderForm().panel);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.pack();
                Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
                frame.setLocation(dim.width/2-frame.getSize().width/2, dim.height/2-frame.getSize().height/2);
                frame.setVisible(true);
            }
        });
        btn_ShowOrders.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                    JFrame frame = new JFrame("ViewPurchasesForm");
                    frame.setContentPane(new ViewPurchasesForm().panel);
                    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    frame.pack();
                Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
                frame.setLocation(dim.width/2-frame.getSize().width/2, dim.height/2-frame.getSize().height/2);
                    frame.setVisible(true);

            }
        });
    }






    public static void main(String[] args) {
        JFrame frame = new JFrame("StoreManagerGUIViewForm");
        frame.setSize(400,400);
        frame.setContentPane(new StoreManagerGUIViewForm().panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setLocation(dim.width/2-frame.getSize().width/2, dim.height/2-frame.getSize().height/2);
        frame.setVisible(true);
        frame.addWindowListener(new WindowAdapter() {

            @Override
            public void windowOpened(WindowEvent e) {
                Backend_DAO_List.get().loadDataFromFile();
            }

            @Override
            public void windowClosing(WindowEvent e) {
                // Save data when the window is closing
                Backend_DAO_List.get().saveDataToFile();
            }
        });


    }
}
