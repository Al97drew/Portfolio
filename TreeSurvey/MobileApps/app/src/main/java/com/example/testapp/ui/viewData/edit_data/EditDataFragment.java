package com.example.testapp.ui.viewData.edit_data;

import androidx.lifecycle.ViewModelProvider;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.testapp.GlobalVar;
import com.example.testapp.R;
import com.example.testapp.database.Data;
import com.example.testapp.database.DbHandler;
import com.example.testapp.databinding.FragmentEditDataBinding;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;

public class EditDataFragment extends Fragment {

    private FragmentEditDataBinding binding;

    private Activity root;
    private DbHandler dbHandler;
    public String CurrentTable;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Resources res = getResources();

        GlobalVar saveload = GlobalVar.getInstance();
        CurrentTable = saveload.GetCurrentTable();

        binding = FragmentEditDataBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        dbHandler = new DbHandler(root.getContext());

        TextView Tree_name_common = root.findViewById(R.id.ed_tb_Tree_Name_Common);
        Spinner Tree_name_Bot = root.findViewById(R.id.ed_dd_Name_Of_Tree_bot);
        TextView Tree_diameter = root.findViewById(R.id.ed_tb_Tree_diameter);
        TextView Tree_Planting_Date = root.findViewById(R.id.ed_tbdate_Planting_Date);
        Spinner Tree_Age = root.findViewById(R.id.ed_dd_Tree_age);
        Spinner Crown_Condition = root.findViewById(R.id.ed_dd_Crown_Condition);
        TextView Crown_Comments = root.findViewById(R.id.ed_mlt_CrownComments);
        Spinner Trunk_Condition = root.findViewById(R.id.ed_dd_Trunk_Condition);
        Spinner Root_Condition = root.findViewById(R.id.ed_dd_Root_Condition);
        Spinner Type_of_Planting_Area = root.findViewById(R.id.ed_dd_Type_of_Planting_Area);
        Spinner Nearest_Infrastructure = root.findViewById(R.id.ed_dd_Nearest_Infrastructure);
        TextView Size_of_Tree_When_Planted = root.findViewById(R.id.ed_tb_Size_of_Tree);
        Spinner Prospective_Tree_Size_Crown = root.findViewById(R.id.ed_dd_Prospective_Tree_Size_Crown);
        Spinner Prospective_Tree_Size_Height = root.findViewById(R.id.ed_dd_Prospective_Tree_Size_Height);
        RadioGroup Tree_Pit = root.findViewById(R.id.ed_rg_Tree_Pit);

        TextView Surface_Tree_Pit_Size = root.findViewById(R.id.ed_tb_Surface_Tree_Pit_Size);
        TextView Volume_of_Tree_Pit = root.findViewById(R.id.ed_tb_Volume_of_Tree_Pit);
        TextView Type_of_Tree_Pit = root.findViewById(R.id.ed_tb_Type_of_Tree_Pit);

        RadioGroup Tree_Staked = root.findViewById(R.id.ed_rg_Tree_Staked);

        TextView Tree_Location = root.findViewById(R.id.ed_tb_Location);
        Spinner How_Many_Trees = root.findViewById(R.id.ed_dd_How_Many_Trees);
        TextView FurtherComments = root.findViewById(R.id.ed_mlt_Comments);
        Button btn_Update = root.findViewById(R.id.ed_btn_Form_Submit);

        Button btn_newBotanicalTree = root.findViewById(R.id.ed_btn_AddBotanicalName);

        ArrayAdapter<String> adapter;

        LoadSpinners(Tree_name_Bot, dbHandler.ReadAllBotanicalNames());

        adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, res.getStringArray(R.array.Input_Tree_age));
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Tree_Age.setAdapter(adapter);

        adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, res.getStringArray(R.array.Input_Crown_Trunk_Condition));
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Crown_Condition.setAdapter(adapter);
        Trunk_Condition.setAdapter(adapter);

        adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, res.getStringArray(R.array.Input_Root_Condition));
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Root_Condition.setAdapter(adapter);

        adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, res.getStringArray(R.array.Input_Type_of_Planmting_area));
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Type_of_Planting_Area.setAdapter(adapter);

        adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, res.getStringArray(R.array.Input_Nearest_Infrastructure__Prospective_Tree_Size_Crown));
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Nearest_Infrastructure.setAdapter(adapter);
        Prospective_Tree_Size_Crown.setAdapter(adapter);

        adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, res.getStringArray(R.array.Prospective_Tree_Size_Height));
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Prospective_Tree_Size_Height.setAdapter(adapter);
        //Prospective_Tree_Size_Height.setSelection();

        adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, res.getStringArray(R.array.RandomName));
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        How_Many_Trees.setAdapter(adapter);


        PopulateAll(Tree_name_common,Tree_name_Bot, Tree_diameter,Tree_Planting_Date, Tree_Age, Crown_Condition, Crown_Comments, Trunk_Condition, Root_Condition, Type_of_Planting_Area, Nearest_Infrastructure,
                Size_of_Tree_When_Planted, Prospective_Tree_Size_Crown, Prospective_Tree_Size_Height, Tree_Pit, Surface_Tree_Pit_Size, Volume_of_Tree_Pit, Type_of_Tree_Pit, Tree_Staked,Tree_Location,How_Many_Trees, FurtherComments);


        btn_newBotanicalTree.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
                try {
                    showNewBotanicalDialog(root.getContext(), Tree_name_Bot);
                }catch (Exception e){

                }
            }
        });

        Tree_Pit.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            public void onCheckedChanged(RadioGroup rg, int id) {
                switch (id) {
                    case R.id.ed_rb_Tree_Pit_Present_True:
                        Surface_Tree_Pit_Size.setEnabled(true);
                        Volume_of_Tree_Pit.setEnabled(true);
                        Type_of_Tree_Pit.setEnabled(true);
                        break;
                    case R.id.ed_rb_Tree_Pit_Present_False:
                        Surface_Tree_Pit_Size.setEnabled(false);
                        Volume_of_Tree_Pit.setEnabled(false);
                        Type_of_Tree_Pit.setEnabled(false);
                        break;


                }
            }
        });

        //TODO Test update survey
        //Sends the survey to database
        btn_Update.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){

                try {
                    if( !Tree_name_common.getText().toString().equals("") && !Tree_diameter.getText().toString().equals("") && !Tree_Planting_Date.getText().toString().equals("") && !Size_of_Tree_When_Planted.getText().toString().equals("") && !Tree_Location.getText().toString().equals("")){
                        Data x = new Data();
                        x.SetTreeNameCommon(Tree_name_common.getText().toString());
                        x.SetTreeNameBotanical(Tree_name_Bot.getSelectedItem().toString());
                        x.SetTreeDiameter(Integer.parseInt(Tree_diameter.getText().toString()));

                        if (isValidDate(Tree_Planting_Date.getText().toString(), "yyyy-mm-dd", true)) {
                            x.SetPlantingDate(Tree_Planting_Date.getText().toString());
                        } else {
                            Toast.makeText(root.getContext(), "Date is in the Wrong Format it needs to be 'yyyy-mm-dd',", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        x.SetTreeAge(Tree_Age.getSelectedItem().toString());
                        x.SetCrownCondition(Crown_Condition.getSelectedItem().toString());

                        if(!Crown_Comments.getText().toString().equals("")){
                            x.SetCrownComments(Crown_Comments.getText().toString());
                        }else{
                            x.SetCrownComments("Null");
                        }

                        x.SetTruckCondition(Trunk_Condition.getSelectedItem().toString());
                        x.SetRootCondition(Root_Condition.getSelectedItem().toString());
                        x.SetTypeOfPlantingArea(Type_of_Planting_Area.getSelectedItem().toString());
                        x.SetNearestInf(Nearest_Infrastructure.getSelectedItem().toString());
                        x.SetSizeOfTreeWhenPlanted(Integer.parseInt(Size_of_Tree_When_Planted.getText().toString()));
                        x.SetProspectiveTreeSizeCrown(Prospective_Tree_Size_Crown.getSelectedItem().toString());
                        x.SetProspectiveTreeSizeHeight(Prospective_Tree_Size_Height.getSelectedItem().toString());
                        switch (Tree_Pit.getCheckedRadioButtonId()) {
                            case R.id.ed_rb_Tree_Pit_Present_True:
                                x.SetPitPresent(true);
                                x.SetSurfaceTreePitSize(Integer.parseInt(Surface_Tree_Pit_Size.getText().toString()));
                                x.SetVolumeOfTreePit(Integer.parseInt(Volume_of_Tree_Pit.getText().toString()));
                                x.SetTypeOfTreePit(Type_of_Tree_Pit.getText().toString());
                                break;
                            case R.id.ed_rb_Tree_Pit_Present_False:
                                x.SetPitPresent(false);
                                x.SetSurfaceTreePitSize(0);
                                x.SetVolumeOfTreePit(0);
                                x.SetTypeOfTreePit("NA");
                                break;
                        }

                        switch (Tree_Staked.getCheckedRadioButtonId()) {
                            case R.id.ed_rb_Tree_Staked_True:
                                x.SetTreeStake(true);
                                break;
                            case R.id.ed_rb_Tree_Staked_False:
                                x.SetTreeStake(false);
                                break;
                        }
                        x.SetLocation(Tree_Location.getText().toString());
                        x.SetSameWithin50M(How_Many_Trees.getSelectedItem().toString());

                        if(!FurtherComments.getText().toString().equals("")){
                            x.SetFurtherComments(FurtherComments.getText().toString());
                        }else{
                            x.SetFurtherComments("Null");
                        }

                        dbHandler.updateSurvey(x, CurrentTable, saveload.GetSelected());

                        Navigation.findNavController(v).navigateUp();//TODO Test this
                    } else {
                        Toast.makeText(root.getContext(), "You missed a box, go check", Toast.LENGTH_SHORT).show();
                    }

                }catch (Exception e){
                    Toast.makeText(root.getContext(), "Something went Wrong!", Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }
            }
        });

        return root;
    }

    public static boolean isValidDate(String value, String datePattern, boolean strict) {

        if (value == null
                || datePattern == null
                || datePattern.length() <= 0) {

            return false;
        }

        SimpleDateFormat formatter = new SimpleDateFormat(datePattern, Locale.ENGLISH);
        formatter.setLenient(false);

        try {
            formatter.parse(value);
        } catch(ParseException e) {
            return false;
        }

        if (strict && (datePattern.length() != value.length())) {
            return false;
        }

        return true;
    }

    public void LoadSpinners(Spinner s, ArrayList<String> list){

        ArrayAdapter<String> adapter;
        adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, list);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        s.setAdapter(adapter);
    }


    private void showNewBotanicalDialog(Context c, Spinner s) {
        final EditText taskEditText = new EditText(c);
        AlertDialog dialog = new AlertDialog.Builder(c)
                .setTitle("New Botanical Name")
                .setMessage("Enter The New Botanical Name:")
                .setView(taskEditText)
                .setPositiveButton("Submit", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String task = String.valueOf(taskEditText.getText());
                        dbHandler.AddNewBotanicalTree(task);
                        LoadSpinners(s, dbHandler.ReadAllBotanicalNames());
                    }
                })
                .setNegativeButton("Cancel", null)
                .create();
        dialog.show();
    }


    private void PopulateAll(TextView Tree_name_common,Spinner Tree_name_Bot,TextView Tree_diameter, TextView Tree_Planting_Date,
                             Spinner Tree_Age, Spinner Crown_Condition, TextView Crown_Comments, Spinner Trunk_Condition,
                             Spinner Root_Condition, Spinner Type_of_Planting_Area, Spinner Nearest_Infrastructure, TextView Size_of_Tree_When_Planted,
                             Spinner Prospective_Tree_Size_Crown, Spinner Prospective_Tree_Size_Height, RadioGroup Tree_Pit, TextView Surface_Tree_Pit_Size,
                             TextView Volume_of_Tree_Pit, TextView Type_of_Tree_Pit, RadioGroup Tree_Staked, TextView Tree_Location, Spinner How_Many_Trees,
                             TextView FurtherComments){

        GlobalVar load = GlobalVar.getInstance();
        Data x = new Data();
        x = dbHandler.readSurvey(load.GetSelected(), CurrentTable);

        Tree_name_common.setText(x.GetTreeNameCommon());
        Tree_name_Bot.setSelection(((ArrayAdapter) Tree_name_Bot.getAdapter()).getPosition(x.GetTreeNameBotanical()));
        Tree_diameter.setText(String.valueOf(x.GetTreeDiameter()));
        Tree_Planting_Date.setText(x.GetPlantingDate());
        Tree_Age.setSelection(((ArrayAdapter) Tree_Age.getAdapter()).getPosition(x.GetTreeAge()));
        Crown_Condition.setSelection(((ArrayAdapter) Crown_Condition.getAdapter()).getPosition(x.GetCrownCondition()));
        Crown_Comments.setText(x.GetCrownComments());
        Trunk_Condition.setSelection(((ArrayAdapter) Trunk_Condition.getAdapter()).getPosition(x.GetTrunkCondition()));
        Root_Condition.setSelection(((ArrayAdapter) Root_Condition.getAdapter()).getPosition(x.GetRootCondition()));
        Type_of_Planting_Area.setSelection(((ArrayAdapter) Type_of_Planting_Area.getAdapter()).getPosition(x.GetTypeOfPlantingArea()));
        Nearest_Infrastructure.setSelection(((ArrayAdapter) Nearest_Infrastructure.getAdapter()).getPosition(x.GetNearestInf()));
        Size_of_Tree_When_Planted.setText(String.valueOf(x.GetSizeOfTreeWhenPlanted()));
        Prospective_Tree_Size_Crown.setSelection(((ArrayAdapter) Prospective_Tree_Size_Crown.getAdapter()).getPosition(x.GetProspectiveTreeSizeCrown()));
        Prospective_Tree_Size_Height.setSelection(((ArrayAdapter) Prospective_Tree_Size_Height.getAdapter()).getPosition(x.GetProspectiveTreeSizeCrown()));
        Tree_Pit.check(x.GetPitPresent() ? R.id.ed_rb_Tree_Pit_Present_True : R.id.ed_rb_Tree_Pit_Present_False);//what the fuckl is this     ///fix the fuckl
        Surface_Tree_Pit_Size.setText(String.valueOf(x.GetSurfaceTreePitSize()));
        Volume_of_Tree_Pit.setText(String.valueOf(x.GetVolumeOfTreePit()));
        Type_of_Tree_Pit.setText(x.GetTypeOfTreePit());
        Tree_Staked.check(x.GetTreeStake() ? R.id.ed_rb_Tree_Staked_True : R.id.ed_rb_Tree_Staked_False); //and again??   ///yep this too
        Tree_Location.setText(x.GetLocation());
        How_Many_Trees.setSelection(((ArrayAdapter) How_Many_Trees.getAdapter()).getPosition(x.GetSameWithin50M()));
        FurtherComments.setText(x.GetFurtherComments());

    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}