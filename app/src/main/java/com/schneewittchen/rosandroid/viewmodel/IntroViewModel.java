package com.schneewittchen.rosandroid.viewmodel;

import android.app.Application;
import android.content.res.TypedArray;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;

import com.schneewittchen.rosandroid.R;
import com.schneewittchen.rosandroid.model.entities.ConfigEntity;
import com.schneewittchen.rosandroid.model.repositories.ConfigRepository;
import com.schneewittchen.rosandroid.model.repositories.ConfigRepositoryImpl;
import com.schneewittchen.rosandroid.ui.helper.ScreenItem;

import java.util.ArrayList;
import java.util.List;

/**
 * TODO: Description
 *
 * @author Nils Rottmann
 * @version 1.0.0
 * @created on 22.06.20
 * @updated on
 * @modified by
 */

public class IntroViewModel extends AndroidViewModel {

    private static final String TAG = IntroViewModel.class.getSimpleName();

    ConfigRepository configRepo;
    private LiveData<ConfigEntity> currentConfig;

    public IntroViewModel(@NonNull Application application) {
        super(application);
        configRepo = ConfigRepositoryImpl.getInstance(getApplication());
    }

    public List<ScreenItem> getScreenItems() {
        List<ScreenItem> mList = new ArrayList<>();
        String[] title_array = getApplication().getResources().getStringArray(R.array.intro_title);
        String[] descr_array = getApplication().getResources().getStringArray(R.array.intro_descr);
        TypedArray img_array = getApplication().getResources().obtainTypedArray(R.array.intro_img);
        for(int i=0; i<title_array.length; i++) {
            mList.add(new ScreenItem(title_array[i], descr_array[i], img_array.getResourceId(i,-1)));
        }
        return mList;
    }
}
