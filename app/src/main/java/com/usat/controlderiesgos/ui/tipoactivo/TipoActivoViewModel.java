package com.usat.controlderiesgos.ui.tipoactivo;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class TipoActivoViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public TipoActivoViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is TipoActivo fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}