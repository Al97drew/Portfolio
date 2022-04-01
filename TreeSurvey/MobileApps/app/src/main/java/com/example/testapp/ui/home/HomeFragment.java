package com.example.testapp.ui.home;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.example.testapp.GlobalVar;
import com.example.testapp.MainActivity;
import com.example.testapp.R;
import com.example.testapp.database.DbHandler;
import com.example.testapp.databinding.FragmentHomeBinding;
import com.example.testapp.ui.FormInput.fragment_Form_Input;
import com.example.testapp.ui.server_status.View_SurveysFragment;

import java.util.ArrayList;


public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private FragmentHomeBinding binding;

    private DbHandler dbHandler;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);

        View root = binding.getRoot();
        GlobalVar save = GlobalVar.getInstance();
        save.SetCurrentGContext(root.getContext());
//        final TextView textView = binding.textHome;
//        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//                textView.setText(s);
//            }
//        });

        Spinner SelectTable = root.findViewById(R.id.dd_SelectTable);
        Button btnOpenTable = root.findViewById(R.id.btn_OpenTable);
        EditText NewTableName = root.findViewById(R.id.tb_NewTableName);
        Button btnNewTable = root.findViewById(R.id.btn_CreateNewTable);
        Spinner DeleteTableName = root.findViewById(R.id.dd_DeleteTableName);
        EditText DeleteConfirm = root.findViewById(R.id.tb_Confirm);
        Button btnDeleteConfirm = root.findViewById(R.id.btn_Confirm);
        Spinner ExportTableNames = root.findViewById(R.id.dd_SelectToExport);
        Button btnExport = root.findViewById(R.id.btn_Export);

        ArrayAdapter<String> adapter;

        dbHandler = new DbHandler(root.getContext());

        LoadSpinners(SelectTable, dbHandler.getTableNames());
        LoadSpinners(DeleteTableName, dbHandler.getTableNames());
        LoadSpinners(ExportTableNames, dbHandler.getTableNames());
/*
        adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, dbHandler.getTableNames());
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        SelectTable.setAdapter(adapter);

        adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, dbHandler.getTableNames());
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        DeleteTableName.setAdapter(adapter);
*/


        btnOpenTable.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                save.SetCurrentTable(SelectTable.getSelectedItem().toString());

                Navigation.findNavController(v).navigate(R.id.action_nav_home_to_nav_view_surveys);
            }
        });


        btnNewTable.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                boolean ready = true;

                char[] charArray = NewTableName.getText().toString().toCharArray();
                for(int i=0; i < charArray.length;){
                    if(Character.isLetter(charArray[i])) {/*
                        if(!Character.isLowerCase(charArray[i])){
                            Toast.makeText(root.getContext(), "Must be All Lowercase Letters!", Toast.LENGTH_SHORT).show();
                            ready = false;
                        }*/
                        if(Character.isSpaceChar(charArray[i])){
                            Toast.makeText(root.getContext(), "There Cant be any Spaces! If needed use '_' ", Toast.LENGTH_SHORT).show();
                            ready = false;
                        }
                    }
                    i++;
                }


                if(NewTableName.getText() != null && ready) {
                    dbHandler.createNewTable(NewTableName.getText().toString());
                    NewTableName.setText("");

                    LoadSpinners(SelectTable, dbHandler.getTableNames());
                    LoadSpinners(DeleteTableName, dbHandler.getTableNames());
                    LoadSpinners(ExportTableNames, dbHandler.getTableNames());
                }
            }
        });


        btnDeleteConfirm.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String temp = DeleteTableName.getSelectedItem().toString();
                //String tempConfirm = ;
                if(temp.equals(DeleteConfirm.getText().toString())){
                    dbHandler.DeleteTable((temp));

                    LoadSpinners(SelectTable, dbHandler.getTableNames());
                    LoadSpinners(DeleteTableName, dbHandler.getTableNames());
                    LoadSpinners(ExportTableNames, dbHandler.getTableNames());
                }else{
                    Toast.makeText(root.getContext(), "You Need To Spell it Correctly to Delete", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnExport.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                try {
                    dbHandler.exportData(ExportTableNames.getSelectedItem().toString());
                    Toast.makeText(root.getContext(), "Export Successful", Toast.LENGTH_SHORT).show();
                }catch(Exception e){
                    Toast.makeText(root.getContext(), "Something went wrong", Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }
            }
        });

        return root;
    }

    public void LoadSpinners(Spinner s, ArrayList<String> list){

        ArrayAdapter<String> adapter;
        adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, PointlessRemover(list));
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        s.setAdapter(adapter);
    }

    public ArrayList<String> PointlessRemover(ArrayList<String> a){
        ArrayList<String> n = new ArrayList<>();
        for(String x : a){
            if((!x.equals("sqlite_sequence")) && !x.equals("BotanicalTreeNames")){
                n.add(x);
            }

        }
        return n;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}