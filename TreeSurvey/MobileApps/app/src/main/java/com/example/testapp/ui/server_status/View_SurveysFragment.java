package com.example.testapp.ui.server_status;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.testapp.DisplayAdapter;
import com.example.testapp.GlobalVar;
import com.example.testapp.R;
import com.example.testapp.ToDisplay;
import com.example.testapp.database.Data;
import com.example.testapp.database.DbHandler;
import com.example.testapp.databinding.FragmentViewSurveysBinding;

import java.util.ArrayList;

public class View_SurveysFragment extends Fragment {

    private View_SurveysViewModel serverStatusViewModel;
    private FragmentViewSurveysBinding binding;
    private DbHandler dbHandler;

    public String CurrentTable;


    View.OnClickListener listener;


    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        serverStatusViewModel = new ViewModelProvider(this).get(View_SurveysViewModel.class);


        binding = FragmentViewSurveysBinding.inflate(inflater, container, false);
        View root = binding.getRoot();


        //Pain
        GlobalVar saveload = GlobalVar.getInstance();
        CurrentTable = saveload.GetCurrentTable();

        TextView ActiveTable = root.findViewById(R.id.tv_View_CurrentTable);
        RecyclerView SurveyList = root.findViewById(R.id.rv_DisplaySurveys);
        Spinner ViewSurvey = root.findViewById(R.id.dd_vf_ViewSurveys);
        Button btn_ViewSurvey = root.findViewById(R.id.btn_ViewSurvey);
        Button btn_NewSurvey = root.findViewById(R.id.btn_NewSurvey);

        ActiveTable.setText(saveload.GetCurrentTable());

        ArrayAdapter<String> adapter;
        dbHandler = new DbHandler(root.getContext());

        ArrayList<Data> Temp = dbHandler.readAllSurveys(CurrentTable);
        ArrayList<String> x = new ArrayList<>();
        ArrayList<Integer> IDs = new ArrayList<>();
        ArrayList<ToDisplay> list = new ArrayList<>();

        if(Temp != null) {
            for (Data d : Temp) {
                StringBuilder SurveyListElement = new StringBuilder();
                SurveyListElement.append("ID: " + d.GetId());
                SurveyListElement.append(" Tree Name Common: " + d.GetTreeNameCommon());
                IDs.add(d.GetId());
                list.add(new ToDisplay(d.GetId(), d.GetTreeNameCommon(), d.GetTreeAge(), d.GetPlantingDate()));

                x.add(SurveyListElement.toString());
            }
        }else {
            //list.add(new SurveyForDisplay(-1, "null", "null"));
            x.add("null");
        }

        //SurveyList.
///////
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(root.getContext());
        SurveyList.setLayoutManager(mLayoutManager);

        RecyclerView.Adapter adapter2 = new DisplayAdapter(list);
        SurveyList.setAdapter(adapter2);
///////

        adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, x);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ViewSurvey.setAdapter(adapter);


        btn_ViewSurvey.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                int temp = ViewSurvey.getSelectedItemPosition();
                saveload.SetSelecteId(temp+1);

                Navigation.findNavController(v).navigate(R.id.action_nav_view_surveys_to_nav_edit_data);
            }
        });

        btn_NewSurvey.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Navigation.findNavController(v).navigate(R.id.action_nav_view_surveys_to_nav_input_form);
            }
        });
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
