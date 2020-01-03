package com.example.livchat.UI;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.livchat.LogViewModel;
import com.example.livchat.Model.LoginUser;
import com.example.livchat.R;
import com.example.livchat.databinding.FragmentLoginBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Objects;

import static com.example.livchat.databinding.FragmentLoginBinding.bind;

public class FragmentLogin extends Fragment{

    Context ctx;

    private FirebaseAuth mAuth;

    private LogViewModel loginViewModel;
    private FragmentLoginBinding binding;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View vFragment = inflater.inflate(R.layout.fragment_login, null);
        ctx = getContext();

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        loginViewModel = ViewModelProviders.of(this).get(LogViewModel.class);

        binding = FragmentLoginBinding.bind(vFragment);
        binding.setLifecycleOwner(this);
        binding.setLogViewModel(loginViewModel);

        loginViewModel.getUser().observe(this, new Observer<LoginUser>() {
            @Override
            public void onChanged(LoginUser loginUser) {
                if( (!loginUser.isEmailValid()) || (loginUser.getStrEmailAddress().isEmpty()) ){
                    binding.etLogin.setError("Email");
                    binding.etLogin.requestFocus();
                } else if( (!loginUser.isPasswordLength()) || (loginUser.getStrPassword().isEmpty()) ) {
                    binding.etPassword.setError("Password");
                    binding.etPassword.requestFocus();
                } else {
                    String email = loginUser.getStrEmailAddress();
                    String password = loginUser.getStrPassword();
//                    SignIn(email, password);
                    loginUser.SignIn(getActivity());
                }
            }
        });

        return vFragment;
    }

    @Override
    public void onResume() {
        super.onResume();

        loginViewModel.getUser().observe(this, new Observer<LoginUser>() {
            @Override
            public void onChanged(LoginUser loginUser) {
                binding.etLogin.setText("");
                binding.etPassword.setText("");
            }
        });
    }

    private void SignIn(String email, String password){
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(Objects.requireNonNull(getActivity()), new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            FirebaseUser user = mAuth.getCurrentUser();
                            Toast.makeText(ctx, "Authentication cool.", Toast.LENGTH_SHORT).show();
                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(ctx, "Authentication failed.", Toast.LENGTH_SHORT).show();
                        }

                        // ...
                    }
                });
    }
}
