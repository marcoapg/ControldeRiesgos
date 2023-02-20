package com.usat.controlderiesgos.ui.amenaza;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class AmenazaViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public AmenazaViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is gallery fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }

}