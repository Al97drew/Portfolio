package com.example.testapp.database;



import android.Manifest;
import android.content.ContentValues;
import android.content.Context;

import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import android.os.Environment;


import android.widget.Toast;
import androidx.core.content.ContextCompat;
import com.example.testapp.GlobalVar;


import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;

public class DbHandler extends SQLiteOpenHelper{



    //#region Declaration()
    private static final String DB_NAME = "SQLITEDATABASE";
    // below int is our database version
    private static final int DB_VERSION = 1;
    // below variable is for our table name.
    //private static final String TABLE_NAME = "mysurveys"; //change
    // below variable is for our id column.
    private static final String ID_COL = "Id"; //this can stay
    private static final String TreeNameCommon_COL = "TreeNameCommon"; //text
    private static final String TreeNameBotanical_COL = "TreeNameBotanical"; //dropbox | text
    private static final String TreeDiameter_COL = "TreeDiameter";// number
    private static final String PlantingDate_COL = "PlantingDate"; //date
    private static final String TreeAge_COL = "TreeAge"; // dropbox
    private static final String CrownCondition_COL = "CrownCondition" ; // dropbox
    private static final String CrownComments_COL = "CrownComments";
    private static final String TrunkCondition_COL = "TrunkCondition"; // dropbox
    private static final String RootCondition_COL = "RootCondition"; // dropbox
    private static final String TypeOfPlantingArea_COL = "TypeOfPlantingArea"; // dropbox
    private static final String NearestInf_COL = "NearestInf"; // dropbox
    private static final String SizeOfTreeWhenPlanted_COL = "SizeOfTreeWhenPlanted"; // number
    private static final String ProspectiveTreeSizeCrown_COL = "ProspectiveTreeSizeCrown"; // dropbox
    private static final String ProspectiveTreeSizeHeight_COL = "ProspectiveTreeSizeHeight"; // dropbox
    private static final String PitPresent_COL = "PitPresent"; //bool radiobutton
    private static final String SurfaceTreePitSize_COL = "SurfaceTreePitSize"; // number m2
    private static final String VolumeOfTreePit_COL = "VolumeOfTreePit"; // number m3
    private static final String TypeOfTreePit_COL ="TypeOfTreePit"; // text?
    private static final String TreeStake_COL ="TreeStake"; //bool radiobutton
    private static final String Location_COL = "Location";//Textbox
    private static final String SameWithin50M_COL="SameWithin50M"; // dropbox
    private static final String FurtherComments_COL="FurtherComments";// comment box
    private static final String BotanicalTableName= "BotanicalTreeNames";
    private static final String BotanicalTreeName_Col = "TreeNameB";
    //#endregion


    // creating a constructor for our database handler.
    public DbHandler(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }



    public void onCreate(SQLiteDatabase db) {
        /*
        String query = "CREATE TABLE " + "Standard" + " ("
                + ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + TreeNameCommon_COL + " VARCHAR(45),"
                + TreeNameBotanical_COL + " VARCHAR(45),"
                + TreeDiameter_COL + " TINTINT,"
                + PlantingDate_COL + " DATE,"
                + TreeAge_COL + " VARCHAR(45),"
                + CrownCondition_COL + " VARCHAR(45),"
                + CrownComments_COL + " VARCHAR(45),"
                + TrunkCondition_COL + " VARCHAR(45),"
                + RootCondition_COL + " VARCHAR(45),"
                + TypeOfPlantingArea_COL + " VARCHAR(45),"
                + NearestInf_COL + " VARCHAR(45),"
                + SizeOfTreeWhenPlanted_COL + " TINYINT,"
                + ProspectiveTreeSizeCrown_COL + " VARCHAR(45),"
                + ProspectiveTreeSizeHeight_COL + " VARCHAR(45),"
                + PitPresent_COL + " BOOLEAN,"
                + SurfaceTreePitSize_COL + " TINTINT,"
                + VolumeOfTreePit_COL + " TINTINT,"
                + TypeOfTreePit_COL + " VARCHAR(45),"
                + TreeStake_COL + " TINTINT,"
                + TreeStakeTypes_COL + " VARCHAR(45),"
                + Location_COL + " VARCHAR(45),"
                + SameWithin50M_COL + " VARCHAR(45),"
                + FurtherComments_COL + " TEXT)";

        // at last we are calling a exec sql
        // method to execute above sql query
        db.execSQL(query);
        */
    }
    //Make new Table
    public void createNewTable(String TABLE_NAME){
        GlobalVar root = GlobalVar.getInstance();
        SQLiteDatabase db = this.getReadableDatabase();
        boolean AlreadyExists = false;

        Cursor cursorSurveys = db.rawQuery("SELECT name FROM sqlite_master WHERE type='table'  order by name", null);

        if(cursorSurveys.moveToFirst()) {
            while(!cursorSurveys.isAfterLast()){
                if(cursorSurveys.getString(0).equals(TABLE_NAME))
                {
                    AlreadyExists = true;
                }
                cursorSurveys.moveToNext();
            }
        }



        if(!AlreadyExists) {
            db = this.getWritableDatabase();

            String query = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " ("
                    + ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + TreeNameCommon_COL + " VARCHAR(45),"
                    + TreeNameBotanical_COL + " VARCHAR(45),"
                    + TreeDiameter_COL + " TINTINT,"
                    + PlantingDate_COL + " DATE,"
                    + TreeAge_COL + " VARCHAR(45),"
                    + CrownCondition_COL + " VARCHAR(45),"
                    + CrownComments_COL + " VARCHAR(45),"
                    + TrunkCondition_COL + " VARCHAR(45),"
                    + RootCondition_COL + " VARCHAR(45),"
                    + TypeOfPlantingArea_COL + " VARCHAR(45),"
                    + NearestInf_COL + " VARCHAR(45),"
                    + SizeOfTreeWhenPlanted_COL + " TINYINT,"
                    + ProspectiveTreeSizeCrown_COL + " VARCHAR(45),"
                    + ProspectiveTreeSizeHeight_COL + " VARCHAR(45),"
                    + PitPresent_COL + " BOOLEAN,"
                    + SurfaceTreePitSize_COL + " TINTINT,"
                    + VolumeOfTreePit_COL + " TINTINT,"
                    + TypeOfTreePit_COL + " VARCHAR(45),"
                    + TreeStake_COL + " TINTINT,"
                    + Location_COL + " VARCHAR(45),"
                    + SameWithin50M_COL + " VARCHAR(45),"
                    + FurtherComments_COL + " TEXT)";

            // at last we are calling a exec sql
            // method to execute above sql query
            db.execSQL(query);
            Toast.makeText(root.GetCurrnetGContext(), "Table Created", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(root.GetCurrnetGContext(), "Table Already Exists, Call it Something else!", Toast.LENGTH_SHORT).show();
        }
        cursorSurveys.close();
        db.close();
    }

    //Get Table Names TODO FIX pointless names
    public ArrayList<String> getTableNames() {
        ArrayList<String> s = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursorSurveys = db.rawQuery("SELECT name FROM sqlite_master WHERE type='table' AND name!='android_metadata' order by name", null);

        if(cursorSurveys.moveToFirst()) {
            while(!cursorSurveys.isAfterLast()){
                s.add(cursorSurveys.getString(0));
                cursorSurveys.moveToNext();
            }
        }
        cursorSurveys.close();
        db.close();
        return s;
    }

    //Add New Survey
    public void addNewSurvey(Data x, String TABLE_NAME) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(TreeNameCommon_COL, x.GetTreeNameCommon());
        values.put(TreeNameBotanical_COL, x.GetTreeNameBotanical());
        values.put(TreeDiameter_COL, x.GetTreeDiameter());
        values.put(PlantingDate_COL, x.GetPlantingDate()); //
        values.put(TreeAge_COL, x.GetTreeAge());
        values.put(CrownCondition_COL, x.GetCrownCondition());
        values.put(CrownComments_COL, x.GetCrownComments());
        values.put(TrunkCondition_COL, x.GetTrunkCondition());
        values.put(RootCondition_COL, x.GetRootCondition());
        values.put(TypeOfPlantingArea_COL, x.GetTypeOfPlantingArea());
        values.put(NearestInf_COL, x.GetNearestInf());
        values.put(SizeOfTreeWhenPlanted_COL, x.GetSizeOfTreeWhenPlanted());
        values.put(ProspectiveTreeSizeCrown_COL, x.GetProspectiveTreeSizeCrown());
        values.put(ProspectiveTreeSizeHeight_COL, x.GetProspectiveTreeSizeHeight());
        values.put(PitPresent_COL, x.GetPitPresent());
        values.put(SurfaceTreePitSize_COL, x.GetSurfaceTreePitSize());
        values.put(VolumeOfTreePit_COL, x.GetVolumeOfTreePit());
        values.put(TypeOfTreePit_COL, x.GetTypeOfTreePit());
        values.put(TreeStake_COL, x.GetTreeStake());
        values.put(Location_COL, x.GetLocation());
        values.put(SameWithin50M_COL, x.GetSameWithin50M());
        values.put(FurtherComments_COL, x.GetFurtherComments());

        db.insert(TABLE_NAME, null, values);

        db.close();
    }

    //Reads All Surveys
    public ArrayList<Data> readAllSurveys(String TABLE_NAME){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursorSurveys = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);

        ArrayList<Data> surveyModalArrayList = new ArrayList<>();

        if(cursorSurveys.moveToFirst()){
            while(!cursorSurveys.isAfterLast()) {
                Data x = new Data();
                x.SetId(cursorSurveys.getInt(0));
                x.SetTreeNameCommon(cursorSurveys.getString(1));
                x.SetTreeNameBotanical(cursorSurveys.getString(2));
                x.SetTreeDiameter(cursorSurveys.getInt(3));
                x.SetPlantingDate(cursorSurveys.getString(4));
                x.SetTreeAge(cursorSurveys.getString(5));
                x.SetCrownCondition(cursorSurveys.getString(6));
                x.SetCrownComments(cursorSurveys.getString(7));
                x.SetTruckCondition(cursorSurveys.getString(8));
                x.SetRootCondition(cursorSurveys.getString(9));
                x.SetTypeOfPlantingArea(cursorSurveys.getString(10));
                x.SetNearestInf(cursorSurveys.getString(11));
                x.SetSizeOfTreeWhenPlanted(cursorSurveys.getInt(12));
                x.SetProspectiveTreeSizeCrown(cursorSurveys.getString(13));
                x.SetProspectiveTreeSizeHeight(cursorSurveys.getString(14));
                x.SetPitPresent((cursorSurveys.getInt(15) == 1 ? true : false));
                x.SetSurfaceTreePitSize(cursorSurveys.getInt(16));
                x.SetVolumeOfTreePit(cursorSurveys.getInt(17));
                x.SetTypeOfTreePit(cursorSurveys.getString(18));
                x.SetTreeStake((cursorSurveys.getInt(19) == 1 ? true : false));
                x.SetLocation(cursorSurveys.getString(20));
                x.SetSameWithin50M(cursorSurveys.getString(21));
                x.SetFurtherComments(cursorSurveys.getString(22));

                surveyModalArrayList.add(x);

                cursorSurveys.moveToNext();
            }
        }
        cursorSurveys.close();
        db.close();
        return surveyModalArrayList;
    }

    //Reads one Survey
    public Data readSurvey(int ID, String TABLE_NAME){
        SQLiteDatabase db = this.getReadableDatabase();
        String query="SELECT * FROM " + TABLE_NAME +" WHERE Id = ?";
        String Temp = Integer.toString(ID);
        String[] selectionArgs = {Temp};
        Cursor cursorSurveys = db.rawQuery(query,selectionArgs);

        Data x = new Data();
        if(cursorSurveys.moveToFirst()) {
            x.SetId(cursorSurveys.getInt(0));
            x.SetTreeNameCommon(cursorSurveys.getString(1));
            x.SetTreeNameBotanical(cursorSurveys.getString(2));
            x.SetTreeDiameter(cursorSurveys.getInt(3));
            x.SetPlantingDate(cursorSurveys.getString(4));
            x.SetTreeAge(cursorSurveys.getString(5));
            x.SetCrownCondition(cursorSurveys.getString(6));
            x.SetCrownComments(cursorSurveys.getString(7));
            x.SetTruckCondition(cursorSurveys.getString(8));
            x.SetRootCondition(cursorSurveys.getString(9));
            x.SetTypeOfPlantingArea(cursorSurveys.getString(10));
            x.SetNearestInf(cursorSurveys.getString(11));
            x.SetSizeOfTreeWhenPlanted(cursorSurveys.getInt(12));
            x.SetProspectiveTreeSizeCrown(cursorSurveys.getString(13));
            x.SetProspectiveTreeSizeHeight(cursorSurveys.getString(14));
            x.SetPitPresent((cursorSurveys.getInt(15) == 1 ? true : false));
            x.SetSurfaceTreePitSize(cursorSurveys.getInt(16));
            x.SetVolumeOfTreePit(cursorSurveys.getInt(17));
            x.SetTypeOfTreePit(cursorSurveys.getString(18));
            x.SetTreeStake((cursorSurveys.getInt(19) == 1 ? true : false));
            x.SetLocation(cursorSurveys.getString(20));
            x.SetSameWithin50M(cursorSurveys.getString(21));
            x.SetFurtherComments(cursorSurveys.getString(22));
        }
        cursorSurveys.close();
        db.close();
        return x;
    }

    //Edits one survey
    public void updateSurvey(Data x,String TABLE_NAME, int id){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        //ContentValues values = GetValues(x);

        values.put(TreeNameCommon_COL, x.GetTreeNameCommon());
        values.put(TreeNameBotanical_COL, x.GetTreeNameBotanical());
        values.put(TreeDiameter_COL, x.GetTreeDiameter());
        values.put(PlantingDate_COL, x.GetPlantingDate());
        values.put(TreeAge_COL, x.GetTreeAge());
        values.put(CrownCondition_COL, x.GetCrownCondition());
        values.put(CrownComments_COL, x.GetCrownComments());
        values.put(TrunkCondition_COL, x.GetTrunkCondition());
        values.put(RootCondition_COL, x.GetRootCondition());
        values.put(TypeOfPlantingArea_COL, x.GetTypeOfPlantingArea());
        values.put(NearestInf_COL, x.GetNearestInf());
        values.put(SizeOfTreeWhenPlanted_COL, x.GetSizeOfTreeWhenPlanted());
        values.put(ProspectiveTreeSizeCrown_COL, x.GetProspectiveTreeSizeCrown());
        values.put(ProspectiveTreeSizeHeight_COL, x.GetProspectiveTreeSizeHeight());
        values.put(PitPresent_COL, x.GetPitPresent());
        values.put(SurfaceTreePitSize_COL, x.GetSurfaceTreePitSize());
        values.put(VolumeOfTreePit_COL, x.GetVolumeOfTreePit());
        values.put(TypeOfTreePit_COL, x.GetTypeOfTreePit());
        values.put(TreeStake_COL, x.GetTreeStake());
        values.put(Location_COL, x.GetLocation());
        values.put(SameWithin50M_COL, x.GetSameWithin50M());
        values.put(FurtherComments_COL, x.GetFurtherComments());

        //String[] s = new String[]{x.GetTreeNameCommon(),x.GetTreeNameBotanical(),Integer.toString(x.GetTreeDiameter()),x.GetPlantingDate(),x.GetTreeAge(),x.GetCrownCondition(),x.GetCrownComments(),x.GetTrunkCondition(),x.GetRootCondition(),x.GetTypeOfPlantingArea(),x.GetNearestInf(),Integer.toString(x.GetSizeOfTreeWhenPlanted()),x.GetProspectiveTreeSizeCrown(),x.GetProspectiveTreeSizeHeight(),Boolean.toString(x.GetPitPresent()), Integer.toString(x.GetSurfaceTreePitSize()), Integer.toString(x.GetVolumeOfTreePit()), x.GetTypeOfTreePit(), Boolean.toString(x.GetTreeStake()), x.GetLocation(), x.GetSameWithin50M(), x.GetFurtherComments()};
        db.update(TABLE_NAME, values,"Id = " + id,null );
        db.close();
    }

    //Delete Table
    public void DeleteTable(String TABLE_NAME){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        db.close();
    }


//    //Create BotanicalNameTale
//    public void CreateBotanicalNameTable(){
//        GlobalVar root = GlobalVar.getInstance();
//        SQLiteDatabase db = this.getWritableDatabase();
//
//        String query = "CREATE TABLE IF NOT EXISTS " + "BotanicalTreeNames" + " ("
//                + ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, "
//                + BotanicalTreeName_Col + " VARCHAR(45))";
//
//        db.execSQL(query);
//
//        Toast.makeText(root.GetCurrnetGContext(), "Table Created", Toast.LENGTH_SHORT).show();
//
//        db.close();
//    }

    public void AddNewBotanicalTree(String x) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(BotanicalTreeName_Col, x);

        db.insert(BotanicalTableName, null, values);

        db.close();
    }

    //Reads All Botanical names
    public ArrayList<String> ReadAllBotanicalNames(){
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<String> s = new ArrayList<>();

        String query = "CREATE TABLE IF NOT EXISTS " + "BotanicalTreeNames" + " ("
                + ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + BotanicalTreeName_Col + " VARCHAR(45))";

        db.execSQL(query);

        //Toast.makeText(root.GetCurrnetGContext(), "Table Created", Toast.LENGTH_SHORT).show();

/*
        boolean AlreadyExists = false;
        boolean CreateNew = true;


        Cursor cursorSurveys = db.rawQuery("SELECT * FROM BotanicalNames WHERE type='table' AND name!='android_metadata'  order by name", null);

        if(cursorSurveys.moveToFirst()) {
            while(!cursorSurveys.isAfterLast()){
                if(cursorSurveys.getString(1) == BotanicalTableName)
                {
                    AlreadyExists = true;
                    CreateNew = false;
                }
                cursorSurveys.moveToNext();
            }
        }

        if(CreateNew){
            CreateBotanicalNameTable();
            AlreadyExists = true;
        }
        */

        //if(AlreadyExists) {

            Cursor cursorSurveys = db.rawQuery("SELECT * FROM " + BotanicalTableName, null);

            if (cursorSurveys.moveToFirst()) {
                while (!cursorSurveys.isAfterLast()) {

                    s.add(cursorSurveys.getString(1));
                    cursorSurveys.moveToNext();
                }
            }
        //}
        if(s.isEmpty()){
            s.add("Null");

        }

        cursorSurveys.close();
        db.close();
        return s;
    }




    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

         //this method is called to check if the table exists already.
         //db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
         //onCreate(db);


        //Toast.makeText(root.getApplicationContext(), "This DataBase exists", Toast.LENGTH_SHORT).show();
    }




    public void exportData(String TableName) {
        GlobalVar save = GlobalVar.getInstance();

        String state = Environment.getExternalStorageState();
        if (!Environment.MEDIA_MOUNTED.equals(state)) {
            Toast.makeText(save.GetCurrnetGContext(), "No External Drive Detected", Toast.LENGTH_SHORT).show();
        } else {
            try {
                //if (ContextCompat.checkSelfPermission(save.GetCurrnetGContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                    //We use the Download directory for saving our .csv file.
                    File sdCard = new File("/storage/9C33-6BBD");
                    //File sdCard = new File(Environment.getExternalStorageDirectory().getPath());
                    //File sdCard = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS); //FIX
                    File exportDir = new File(sdCard + "/Surveys/"); //FIX

                    System.out.println(exportDir);

                    if (!exportDir.exists()) {
                        exportDir.mkdirs();
                    }

                    File file;
                    PrintWriter printWriter;

                    file = new File(exportDir, TableName + ".csv");

                    if (file.exists()) {
                        file.delete();
                    }

                    file.createNewFile();

                    printWriter = new PrintWriter(new FileWriter(file));

                    SQLiteDatabase db = this.getReadableDatabase(); //open the database for reading

                    Cursor curCSV = db.rawQuery("select * from " + TableName, null);
                    //Write the name of the table and the name of the columns (comma separated values) in the .csv file.

                    printWriter.println("ID,TreeNameCommon,TreeNameBotanical,TreeDiameter,PlantingDate,TreeAge,CrownCondition,CrownComments,TrunkCondition,RootCondition,TypeOfPlantingArea,NearestInf,SizeOfTreeWhenPlanted,ProspectiveTreeSizeCrown,ProspectiveTreeSizeHeight,PitPresent,SurfaceTreePitSize,VolumeOfTreePit,TypeOfTreePit,TreeStake,Location,SameWithin50M,FurtherComments");
                    curCSV.moveToFirst();
                    while (!curCSV.isAfterLast()) {
                        int id = curCSV.getInt(0);
                        String TreeNameCommon = curCSV.getString(1);
                        String TreeNameBotanical = curCSV.getString(2);
                        int TreeDiameter = curCSV.getInt(3);
                        String PlantingDate = curCSV.getString(4);
                        String TreeAge = curCSV.getString(5);
                        String CrownCondition = curCSV.getString(6);
                        String CrownComments = curCSV.getString(7);
                        String TrunkCondition = curCSV.getString(8);
                        String RootCondition = curCSV.getString(9);
                        String TypeOfPlantingArea = curCSV.getString(10);
                        String NearestInf = curCSV.getString(11);
                        int SizeOfTreeWhenPlanted = curCSV.getInt(12);
                        String ProspectiveTreeSizeCrown = curCSV.getString(13);
                        String ProspectiveTreeSizeHeight = curCSV.getString(14);
                        Boolean PitPresent = (curCSV.getInt(15) == 1 ? true : false);
                        int SurfaceTreePitSize = curCSV.getInt(16);
                        int VolumeOfTreePit = curCSV.getInt(17);
                        String TypeOfTreePit = curCSV.getString(18);
                        Boolean TreeStake = (curCSV.getInt(19) == 1 ? true : false);
                        String Location = curCSV.getString(20);
                        String SameWithin50M = curCSV.getString(21);
                        String FurtherComments = curCSV.getString(22);
//TODO FIx this mess


                        /**Create the line to write in the .csv file.
                         * We need a String where values are comma separated.
                         * The field date (Long) is formatted in a readable text. The amount field
                         * is converted into String.
                         */
                        String record = id + "," + TreeNameCommon + "," + TreeNameBotanical + "," + TreeDiameter + "," + PlantingDate + "," + TreeAge + "," + CrownCondition + "," + CrownComments + "," + TrunkCondition + "," + RootCondition + "," + TypeOfPlantingArea + "," + NearestInf + "," + SizeOfTreeWhenPlanted + "," + ProspectiveTreeSizeCrown + "," + ProspectiveTreeSizeHeight + "," + PitPresent + "," + SurfaceTreePitSize + "," + VolumeOfTreePit + "," + TypeOfTreePit + "," + TreeStake + "," + Location + "," + SameWithin50M + "," + FurtherComments;
                        printWriter.println(record); //write the record in the .csv file
                        curCSV.moveToNext();

                    }



                    printWriter.close();
                    curCSV.close();
                    db.close();
                //}
                } catch (Exception e) {
                    Toast.makeText(save.GetCurrnetGContext(), "Something went wrong", Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }
                //If there are no errors, return true.
            }
        }

}

