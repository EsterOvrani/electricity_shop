package model;

import java.io.Serializable;

import java.time.LocalDate;
import java.util.ArrayList;

public class PurchaseOrder {
    private Customer orderingCustomer;
    private ArrayList<Product> productslist;
    private LocalDate orderDate;

    public PurchaseOrder() {
        this.orderingCustomer = orderingCustomer;
        this.productslist = productslist;
        this.orderDate = LocalDate.now();
    }

    public Customer getOrderingCustomer() {
        return orderingCustomer;
    }

    public void setOrderingCustomer(Customer orderingCustomer) {
        this.orderingCustomer = orderingCustomer;
    }

    public ArrayList<Product> getProductslist() {
        return productslist;
    }

    public void setProductslist(ArrayList<Product> productslist) {
        this.productslist = productslist;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    @Override
    public String toString() {
        return "PurchaseOrder{" +
                "orderingCustomer=" + orderingCustomer +
                ", productslist=" + productslist +
                ", orderDate=" + orderDate +
                '}';
    }
}
