package com.example.testapp.ui.FormInput;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class FormInputVIewModel extends ViewModel{

    private MutableLiveData<String> mText;

    public FormInputVIewModel(){
        mText = new MutableLiveData<>();
        mText.setValue("");



    }

}
