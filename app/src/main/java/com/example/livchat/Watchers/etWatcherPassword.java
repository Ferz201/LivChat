package com.example.livchat.Watchers;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

public class etWatcherPassword implements TextWatcher {
    private EditText et;
    private EditText etConfirm;
    private String error;

    public etWatcherPassword(EditText et, EditText etConfirm, String error){
        this.et = et;
        this.error = error;
        this.etConfirm = etConfirm;
    }

    public etWatcherPassword(EditText et, String error){
        this.et = et;
        this.error = error;
    }


    private void etPassword(){
        if(et.getText().length() == 0){
            et.setError(null);
        } else if(et.getText().length() < 8){
            et.setError(error);
        }
    }

    private void etPasswordConfirm(){
        if(!String.valueOf(et.getText()).equals(String.valueOf(etConfirm.getText()))){
            et.setError(error);
        }
    }


    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        if(etConfirm == null){
            etPassword();
        } else {
            etPasswordConfirm();
        }
    }

    @Override
    public void afterTextChanged(Editable s) {

    }
}
