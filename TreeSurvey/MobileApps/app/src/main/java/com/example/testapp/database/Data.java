package com.example.testapp.database;

import java.text.SimpleDateFormat;
import java.time.LocalDate;

public class Data extends Object {
    private int Id;
    private String TreeNameCommon; //text
    private String TreeNameBotanical; //dropbox | text
    private int TreeDiameter;// number
    private String PlantingDate = new SimpleDateFormat("yyyy-mm-dd").toString(); //date
    private String TreeAge;// dropbox
    private String CrownCondition; // dropbox
    private String CrownComments;
    private String TrunkCondition; // dropbox
    private String RootCondition; // dropbox
    private String TypeOfPlantingArea; // dropbox
    private String NearestInf; // dropbox
    private int SizeOfTreeWhenPlanted; // number
    private String ProspectiveTreeSizeCrown; // dropbox
    private String ProspectiveTreeSizeHeight; // dropbox
    private boolean PitPresent; //bool radiobutton
    private int SurfaceTreePitSize; // number m2
    private int VolumeOfTreePit; // number m3
    private String TypeOfTreePit;// text?
    private boolean TreeStake; //bool radiobutton
    //TODO TreeStakeTypes Depends

    private String Location;
    private String SameWithin50M; // dropbox
    private String FurtherComments; // comment box

    public int GetId() {
        return Id;
    }
    public void SetId(int Temp) {
        this.Id = Temp;
    }

    public String GetTreeNameCommon() {
        return TreeNameCommon;
    }
    public void SetTreeNameCommon(String Temp) {
        this.TreeNameCommon = Temp;
    }

    public String GetTreeNameBotanical() {
        return TreeNameBotanical;
    }
    public void SetTreeNameBotanical(String Temp) {
        this.TreeNameBotanical = Temp;
    }

    public int GetTreeDiameter() {
        return TreeDiameter;
    }
    public void SetTreeDiameter(int Temp) {
        this.TreeDiameter = Temp;
    }

    public String GetPlantingDate() {
        return PlantingDate;
    }
    public void SetPlantingDate(String Temp) {
        this.PlantingDate = Temp;
    }

    public String GetTreeAge() {
        return TreeAge;
    }
    public void SetTreeAge(String Temp) {
        this.TreeAge = Temp;
    }

    public String GetCrownCondition() {
        return CrownCondition;
    }
    public void SetCrownCondition(String Temp) {
        this.CrownCondition = Temp;
    }

    public String GetCrownComments() {
        return CrownComments;
    }
    public void SetCrownComments(String Temp) {
        this.CrownComments = Temp;
    }

    public String GetTrunkCondition() {
        return TrunkCondition;
    }
    public void SetTruckCondition(String Temp) {
        this.TrunkCondition = Temp;
    }

    public String GetRootCondition() {
        return RootCondition;
    }
    public void SetRootCondition(String Temp) {
        this.RootCondition = Temp;
    }

    public String GetTypeOfPlantingArea() {
        return TypeOfPlantingArea;
    }
    public void SetTypeOfPlantingArea(String Temp) {
        this.TypeOfPlantingArea = Temp;
    }

    public String GetNearestInf() {
        return NearestInf;
    }
    public void SetNearestInf(String Temp) {
        this.NearestInf = Temp;
    }

    public int GetSizeOfTreeWhenPlanted() {
        return SizeOfTreeWhenPlanted;
    }
    public void SetSizeOfTreeWhenPlanted(int Temp) {
        this.SizeOfTreeWhenPlanted = Temp;
    }

    public String GetProspectiveTreeSizeCrown() {
        return ProspectiveTreeSizeCrown;
    }
    public void SetProspectiveTreeSizeCrown(String Temp) {
        this.ProspectiveTreeSizeCrown = Temp;
    }

    public String GetProspectiveTreeSizeHeight() {
        return ProspectiveTreeSizeHeight;
    }
    public void SetProspectiveTreeSizeHeight(String Temp) {
        this.ProspectiveTreeSizeHeight = Temp;
    }

    public Boolean GetPitPresent() {
        return PitPresent;
    }
    public void SetPitPresent(Boolean Temp) {
        this.PitPresent = Temp;
    }

    public int GetSurfaceTreePitSize() {
        return SurfaceTreePitSize;
    }
    public void SetSurfaceTreePitSize(int Temp) {
        this.SurfaceTreePitSize = Temp;
    }

    public int GetVolumeOfTreePit() {
        return VolumeOfTreePit;
    }
    public void SetVolumeOfTreePit(int Temp) {
        this.VolumeOfTreePit = Temp;
    }

    public String GetTypeOfTreePit() {
        return TypeOfTreePit;
    }
    public void SetTypeOfTreePit(String Temp) {
        this.TypeOfTreePit = Temp;
    }

    public Boolean GetTreeStake() {
        return TreeStake;
    }
    public void SetTreeStake(Boolean Temp) {
        this.TreeStake = Temp;
    }

    /*public String GetTreeStakeTypes() {
        return TreeStakeTypes;
    }
    public void SetTreeStakeTypes(String Temp) {
        this.TreeStakeTypes = Temp;
    }*/

    public String GetLocation() {
        return Location;
    }
    public void SetLocation(String Temp) {
        this.Location = Temp;
    }

    public String GetSameWithin50M() {
        return SameWithin50M;
    }
    public void SetSameWithin50M(String Temp) {
        this.SameWithin50M = Temp;
    }

    public String GetFurtherComments() {
        return FurtherComments;
    }
    public void SetFurtherComments(String Temp) {
        this.FurtherComments = Temp;
    }

}