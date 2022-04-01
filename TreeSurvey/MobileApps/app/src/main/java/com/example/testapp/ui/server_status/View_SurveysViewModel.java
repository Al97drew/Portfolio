package com.example.testapp.ui.server_status;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class View_SurveysViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public View_SurveysViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is the Server Status Fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}