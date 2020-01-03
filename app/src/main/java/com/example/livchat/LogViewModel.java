package com.example.livchat;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.livchat.Model.LoginUser;

public class LogViewModel extends ViewModel {
    public MutableLiveData<String> email = new MutableLiveData<>();
    public MutableLiveData<String> password = new MutableLiveData<>();
    private MutableLiveData<LoginUser> userMutableLiveData;

    public MutableLiveData<LoginUser> getUser(){
        if(userMutableLiveData == null){
            userMutableLiveData = new MutableLiveData<>();
        }
        return userMutableLiveData;
    }

    public void onClick(){
        if(email.getValue() != null && password.getValue() != null) {
            LoginUser loginUser = new LoginUser(email.getValue(), password.getValue());
            userMutableLiveData.setValue(loginUser);
        }
    }
}
