package com.example.testapp;

import android.content.Context;

public class GlobalVar {

    private String CurrentTable;
    private Context GContext;

    private int SelectedId;

    private static final GlobalVar ourInstance = new GlobalVar();
    public static GlobalVar getInstance() {
        return ourInstance;
    }

    private GlobalVar(){}

    public void SetCurrentTable(String Temp){
        this.CurrentTable = Temp;
    }
    public String GetCurrentTable(){
        return CurrentTable;
    }

    public void SetCurrentGContext(Context Temp){this.GContext = Temp;}
    public Context GetCurrnetGContext(){return GContext;}

    public void SetSelecteId(int Temp){this.SelectedId = Temp;}
    public int GetSelected(){return SelectedId;}
}
