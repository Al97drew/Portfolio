package com.example.testapp;

public class ToDisplay {
    private int ID;
    private String TreeName;
    private String TreeAge;
    private String PlantingDate;

    public ToDisplay(int ID, String TreeName, String TreeAge, String PlantingDate) {
        this.ID = ID;
        this.TreeName = TreeName;
        this.TreeAge = TreeAge;
        this.PlantingDate = PlantingDate;
    }


    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getTreeName() {
        return TreeName;
    }

    public void setTreeName(String treeName) {
        TreeName = treeName;
    }

    public String getTreeAge() {
        return TreeAge;
    }

    public void setTreeAge(String treeAge) {
        TreeAge = treeAge;
    }

    public String getPlantingDate() {
        return PlantingDate;
    }

    public void setPlantingDate(String plantingDate) {
        PlantingDate = plantingDate;
    }
}
