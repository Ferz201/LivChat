package com.example.livchat.UI;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.livchat.R;
import com.example.livchat.Watchers.etWatcherPassword;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Objects;

public class FragmentRegistration extends Fragment implements View.OnClickListener {

    Context ctx;
    EditText etPassword, etConfirmPassword, etEmail;

    private FirebaseAuth mAuth;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View vFragment = inflater.inflate(R.layout.fragment_registration, null);
        ctx = getContext();

        etEmail = vFragment.findViewById(R.id.etLogin);

        etPassword = vFragment.findViewById(R.id.etPassword);
        etWatcherPassword watcherPassword = new etWatcherPassword(etPassword, getResources().getString(R.string.short_password));
        etPassword.addTextChangedListener(watcherPassword);

        etConfirmPassword = vFragment.findViewById(R.id.etConfirmPassword);
        etWatcherPassword watcherPasswordConfirm = new etWatcherPassword(etConfirmPassword, etPassword, getResources().getString(R.string.passwords_dont_match));
        etConfirmPassword.addTextChangedListener(watcherPasswordConfirm);

        Button btnRegistration = vFragment.findViewById(R.id.btnRegistration);
        btnRegistration.setOnClickListener(this);


        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        return vFragment;
    }


//      Срабатывает при нажатии на кнопку Регистрации
    private Boolean validRegistration(){
        boolean validReg = true;
        String email = String.valueOf(etEmail.getText());
        email = email.replace(" ", "");

        String password = String.valueOf(etPassword.getText());
        String confirmPassword = String.valueOf(etConfirmPassword.getText());

        if (email.equals("")) {
            etEmail.setError(getResources().getString(R.string.enter_email));
            validReg = false;
        }

        if (password.length() < 8) {
            etPassword.setError(getResources().getString(R.string.short_password));
            validReg = false;
        }

        if (!confirmPassword.equals(password)) {
            etConfirmPassword.setError(getResources().getString(R.string.passwords_dont_match));
            validReg = false;
        }

        return validReg;
    }

    private void Registration(){
        String email = String.valueOf(etEmail.getText());
        String password = String.valueOf(etPassword.getText());

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(Objects.requireNonNull(getActivity()), new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            FirebaseUser user = mAuth.getCurrentUser();
                            Toast.makeText(ctx, "Authentication good.", Toast.LENGTH_SHORT).show();
//                            updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(ctx, "Authentication failed.", Toast.LENGTH_SHORT).show();
//                            updateUI(null);
                        }

                        // ...
                    }
                });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnRegistration:
                if(validRegistration()){
                    Registration();
                }
        }
    }
}
