package com.example.vegetableplantingtutorial;

public class Garden {

    private String vegetableId;
    private String harvestDate;
    private String imageName;
    private String plantedDate;

    public Garden(String vegetableId, String harvestDate, String imageName, String plantedDate) {
        this.vegetableId = vegetableId;
        this.harvestDate = harvestDate;
        this.imageName = imageName;
        this.plantedDate = plantedDate;
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

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public String getPlantedDate() {
        return plantedDate;
    }

    public void setPlantedDate(String plantedDate) {
        this.plantedDate = plantedDate;
    }
}
