package controller;

import model.Customer;
import model.Product;
import model.PurchaseOrder;
import model.Hardwareproduct;

import java.io.*;
import java.util.*;

public class Backend_DAO_List implements Backend {
    private Map<Long, Customer> _Customers;
    private Set<Product> _Products;
    private List<PurchaseOrder> _PurchaseOrders;

    private Backend_DAO_List() {
        _Customers = new HashMap<>();
        _Products = new HashSet<>();
        _PurchaseOrders = new ArrayList<>();
        _Products.add(new Hardwareproduct(111, "samsung", "computare", 45, 5));
        _Products.add(new Hardwareproduct(222, "apple", "phone", 100, 10));
        _Customers.put(Long.parseLong("1"), (new Customer(123, "naama", "elad")));
        _Customers.put(Long.parseLong("2"), (new Customer(123, "tamar", "elad")));
    }

    static Backend_DAO_List b;

    public static Backend_DAO_List get() {
        if (b == null)
            b = new Backend_DAO_List();
        return b;
    }

    @Override
    public void AddCustomer(Customer c) throws Exception {
        _Customers.put(c.getId(), c);
    }

    @Override
    public void AddProduct(Product c) throws Exception {
        _Products.add(c);
    }

    @Override
    public HashMap<Long, Customer> getAllCustomers() throws Exception {
        return (HashMap<Long, Customer>) _Customers;
    }

    @Override
    public HashSet<Product> getAllProducts() throws Exception {
        return (HashSet<Product>) _Products;
    }

    @Override
    public void PlaceOrder(PurchaseOrder po) throws Exception {
        _PurchaseOrders.add(po);
    }

    @Override
    public void RemoveProduct(Product c) throws Exception {
        _Products.remove(c);
    }

    @Override
    public ArrayList<Product> getCustomersOrders(Customer c) throws Exception {
        ArrayList<Product> ret = new ArrayList<Product>();
        for (PurchaseOrder po : _PurchaseOrders) {
            if (po.getOrderingCustomer().getId() == c.getId())
                ret.addAll(po.getProductslist());
        }
        return ret;
    }

    @Override
    public Float CalcProductsTotalCost(Product[] products) throws Exception {
        float sum = 0;
        for (Product p : products) {
            sum += p.getPrice();
        }
        return sum;
    }

    public Backend_DAO_List(HashMap<Long, Customer> _Customers, HashSet<Product> _Products, List<PurchaseOrder> _PurchaseOrders) {
        this._Customers = _Customers;
        this._Products = _Products;
        this._PurchaseOrders = _PurchaseOrders;

    }

    public void saveDataToFile() {
        ObjectOutput oos = null;
        try {
            oos = new ObjectOutputStream(new FileOutputStream("data"));
            oos.writeObject(_Customers);
            oos.writeObject(_Products);
            oos.writeObject(_PurchaseOrders);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }finally {
            try {
                oos.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }


    }
    public void loadDataFromFile() {
        ObjectInputStream ois = null;
        try {
            ois = new ObjectInputStream(new FileInputStream("data"));
            _Customers = (HashMap<Long, Customer>) ois.readObject();
            _Products = (HashSet<Product>) ois.readObject();
            _PurchaseOrders = (List<PurchaseOrder>) ois.readObject();

        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }finally {
            try {
                ois.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

    }

}
