package com.example.vegetableplantingtutorial;

public class PlantingStep {

    private String name;
    private String process;
    private String imageName;

    public PlantingStep(String name, String process, String imageName) {
        this.name = name;
        this.process = process;
        this.imageName = imageName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProcess() {
        return process;
    }

    public void setProcess(String process) {
        this.process = process;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }
}
