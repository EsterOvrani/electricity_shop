package model;

import java.io.Serializable;

abstract public class Product {
    private long Id;
    private String name;
    private String description;
    private float pricePerUnit;

    public Product(long id, String name, String description, float pricePerUnit) {
        Id = id;
        this.name = name;
        this.description = description;
        this.pricePerUnit = pricePerUnit;
    }

    public Product() {
    }

    public long getId() {
        return Id;
    }

    public void setId(long id) {
        Id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getPricePerUnit() {
        return pricePerUnit;
    }

    public void setPricePerUnit(float pricePerUnit) {
        this.pricePerUnit = pricePerUnit;
    }

    @Override
    public String toString() {
        return "Product{" +
                "Id=" + Id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", pricePerUnit=" + pricePerUnit +
                '}';
    }

    public abstract float getPrice();

}
