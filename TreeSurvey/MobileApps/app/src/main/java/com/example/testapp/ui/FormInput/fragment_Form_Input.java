package com.example.testapp.ui.FormInput;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.graphics.Region;
import android.os.Bundle;
import android.provider.MediaStore;
import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.example.testapp.GlobalVar;
import com.example.testapp.MainActivity;
import com.example.testapp.R;
import com.example.testapp.database.Data;
import com.example.testapp.database.DbHandler;
import com.example.testapp.databinding.FragmentFormInputBinding;

import java.lang.reflect.Array;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;

public class fragment_Form_Input extends Fragment {

    private FormInputVIewModel testVIewModel;
    private FragmentFormInputBinding binding;

    private Activity root;
    private DbHandler dbHandler;
    public String CurrentTable;


    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Resources res = getResources();
        testVIewModel = new ViewModelProvider(this).get(FormInputVIewModel.class);

        GlobalVar saveload = GlobalVar.getInstance();
        CurrentTable = saveload.GetCurrentTable();


        binding = FragmentFormInputBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        dbHandler = new DbHandler(root.getContext());

        TextView Tree_name_common = root.findViewById(R.id.tb_Tree_Name_Common);
        Spinner Tree_name_Bot = root.findViewById(R.id.dd_Name_Of_Tree_bot);
        TextView Tree_diameter = root.findViewById(R.id.tbn_Tree_diameter);
        TextView Tree_Planting_Date = root.findViewById(R.id.tbdate_Planting_Date);
        Spinner Tree_Age = root.findViewById(R.id.dd_Tree_age);
        Spinner Crown_Condition = root.findViewById(R.id.dd_Crown_Condition);
        TextView Crown_Comments = root.findViewById(R.id.mlt_CrownComments);
        Spinner Trunk_Condition = root.findViewById(R.id.dd_Trunk_Condition);
        Spinner Root_Condition = root.findViewById(R.id.dd_Root_Condition);
        Spinner Type_of_Planting_Area = root.findViewById(R.id.dd_Type_of_Planting_Area);
        Spinner Nearest_Infrastructure = root.findViewById(R.id.dd_Nearest_Infrastructure);
        TextView Size_of_Tree_When_Planted = root.findViewById(R.id.tb_Size_of_Tree);
        Spinner Prospective_Tree_Size_Crown = root.findViewById(R.id.dd_Prospective_Tree_Size_Crown);
        Spinner Prospective_Tree_Size_Height = root.findViewById(R.id.dd_Prospective_Tree_Size_Height);
        RadioGroup Tree_Pit = root.findViewById(R.id.rg_Tree_Pit);

        TextView Surface_Tree_Pit_Size = root.findViewById(R.id.tb_Surface_Tree_Pit_Size);
        TextView Volume_of_Tree_Pit = root.findViewById(R.id.tb_Volume_of_Tree_Pit);
        TextView Type_of_Tree_Pit = root.findViewById(R.id.tb_Type_of_Tree_Pit);

        RadioGroup Tree_Staked = root.findViewById(R.id.rg_Tree_Staked);

        TextView Tree_Location = root.findViewById(R.id.tb_Location);
        Spinner How_Many_Trees = root.findViewById(R.id.dd_How_Many_Trees);
        TextView FurtherComments = root.findViewById(R.id.mlt_Comments);
        Button btn_Submit = root.findViewById(R.id.btn_Form_Submit);

        Button btn_newBotanicalTree = root.findViewById(R.id.btn_AddBotanicalName);

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

        adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, res.getStringArray(R.array.RandomName));
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        How_Many_Trees.setAdapter(adapter);


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
                    case R.id.rb_Tree_Pit_Present_True:
                        Surface_Tree_Pit_Size.setEnabled(true);
                        Volume_of_Tree_Pit.setEnabled(true);
                        Type_of_Tree_Pit.setEnabled(true);
                        break;
                    case R.id.rb_Tree_Pit_Present_False:
                        Surface_Tree_Pit_Size.setEnabled(false);
                        Volume_of_Tree_Pit.setEnabled(false);
                        Type_of_Tree_Pit.setEnabled(false);
                        break;


                }
            }
        });

        //Sends the survey to database
        btn_Submit.setOnClickListener(new View.OnClickListener() {
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
                            case R.id.rb_Tree_Pit_Present_True:
                                x.SetPitPresent(true);
                                x.SetSurfaceTreePitSize(Integer.parseInt(Surface_Tree_Pit_Size.getText().toString()));
                                x.SetVolumeOfTreePit(Integer.parseInt(Volume_of_Tree_Pit.getText().toString()));
                                x.SetTypeOfTreePit(Type_of_Tree_Pit.getText().toString());
                                break;
                            case R.id.rb_Tree_Pit_Present_False:
                                x.SetPitPresent(false);
                                x.SetSurfaceTreePitSize(0);
                                x.SetVolumeOfTreePit(0);
                                x.SetTypeOfTreePit("NA");
                                break;
                        }

                        switch (Tree_Staked.getCheckedRadioButtonId()) {
                            case R.id.rb_Tree_Staked_True:
                                x.SetTreeStake(true);
                                break;
                            case R.id.rb_Tree_Staked_False:
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

                        dbHandler.addNewSurvey(x, CurrentTable);

                        Navigation.findNavController(v).navigateUp();//TODO Test this
                    } else {
                        Toast.makeText(root.getContext(), "You missed a box, go check", Toast.LENGTH_SHORT).show();
                    }


                }catch (Exception e){
                    Toast.makeText(root.getContext(), "Something went Wrong!", Toast.LENGTH_SHORT).show();
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



    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}