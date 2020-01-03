package com.example.livchat.Model;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.util.Patterns;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.livchat.Repositories.FirebaseGetUser;
import com.example.livchat.UI.FragmentLogin;
import com.example.livchat.UI.MainActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.concurrent.Executor;


public class LoginUser extends Application {

    private String strEmailAddress;
    private String strPassword;

    public LoginUser(String EmailAddress, String Password) {
        strEmailAddress = EmailAddress;
        strPassword = Password;
    }

    public String getStrEmailAddress() {
        return strEmailAddress;
    }

    public String getStrPassword() {
        return strPassword;
    }

    public boolean isEmailValid() {
        return Patterns.EMAIL_ADDRESS.matcher(getStrEmailAddress()).matches();
    }


    public boolean isPasswordLength() {
        return getStrPassword().length() >= 8;
    }


    public void SignIn(Activity activity){
        FirebaseGetUser user = new FirebaseGetUser();
        user.SignIn(strEmailAddress, strPassword, activity);
    }
}
