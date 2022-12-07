package models;

import java.util.List;

public class Meter extends BaseModel {
    private List<Consumption> consumptionList;
    private double chargesPerUnit;

    public List<Consumption> getConsumptionList() {
        return consumptionList;
    }

    public void setConsumptionList(List<Consumption> consumptionList) {
        this.consumptionList = consumptionList;
    }

    public double getChargesPerUnit() {
        return chargesPerUnit;
    }

    public void setChargesPerUnit(double chargesPerUnit) {
        this.chargesPerUnit = chargesPerUnit;
    }
}
