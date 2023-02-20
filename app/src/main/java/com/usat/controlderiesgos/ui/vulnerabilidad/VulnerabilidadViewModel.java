package com.usat.controlderiesgos.ui.vulnerabilidad;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class VulnerabilidadViewModel extends ViewModel {
    private final MutableLiveData<String> mText;

    public VulnerabilidadViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is gallery fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}