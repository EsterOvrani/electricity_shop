package model;

import java.io.Serializable;

public class Hardwareproduct extends Product {
    private int warrantyperiod;

    public Hardwareproduct(long id, String name, String description, float pricePerUnit, int warrantyperiod) {
        super(id, name, description, pricePerUnit);
        this.warrantyperiod = warrantyperiod;
    }

    public int getWarrantyperiod() {
        return warrantyperiod;
    }

    public void setWarrantyperiod(int warrantyperiod) {
        this.warrantyperiod = warrantyperiod;
    }

    @Override
    public float getPrice() {
        return warrantyperiod+getPricePerUnit();
    }


}
