package com.example.vegetableplantingtutorial;

public class Garden {

    private String vegetableId;
    private String harvestDate;
    private String imageName;

    public Garden(String vegetableId, String harvestDate, String imageName) {
        this.vegetableId = vegetableId;
        this.harvestDate = harvestDate;
        this.imageName = imageName;
    }

    public String getVegetableId() {
        return vegetableId;
    }

    public void setVegetableId(String vegetableId) {
        this.vegetableId = vegetableId;
    }

    public String getHarvestDate() {
        return harvestDate;
    }

    public void setHarvestDate(String harvestDate) {
        this.harvestDate = harvestDate;
    }
}
