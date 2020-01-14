package com.gimbernat.UntitledSocialGame.scenes.boot.ui.send;

import android.content.Intent;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.gimbernat.UntitledSocialGame.DataSources.SessionDataSource;
import com.gimbernat.UntitledSocialGame.scenes.boot.BootActivity;
import com.gimbernat.UntitledSocialGame.scenes.boot.MainActivity;

public class SendViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public SendViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is send fragment");

        SessionDataSource.shared.signOut();

    }

    public LiveData<String> getText() {
        return mText;
    }
}