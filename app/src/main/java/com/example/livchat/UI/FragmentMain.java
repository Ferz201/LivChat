package com.example.livchat.UI;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.livchat.R;

public class FragmentMain extends Fragment implements View.OnClickListener {
    private View vFragment;
    private Context ctx;

    private FragmentTransaction fTrans;
    private Fragment fLogin = new FragmentLogin();
    private Fragment fRegistration = new FragmentRegistration();
    private LinearLayout fragment;
    private Animation animHide;
    private Animation animShow;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        vFragment = inflater.inflate(R.layout.fragment_main, null);

        ctx = getContext();
        fragment = getActivity().findViewById(R.id.fragment);
        animHide = AnimationUtils.loadAnimation(ctx, R.anim.anim_fragment_hide);
        animShow = AnimationUtils.loadAnimation(ctx, R.anim.anim_fragment_show);

        Button btnLogin = vFragment.findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(this);
        Button btnRegistration = vFragment.findViewById(R.id.btnRegistration);
        btnRegistration.setOnClickListener(this);

        return vFragment;
    }


    private void animShowHide(final Fragment fNow){
        animHide.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) { }

            @Override
            public void onAnimationEnd(Animation animation) {
                if(getFragmentManager() != null){
                    fTrans = getFragmentManager().beginTransaction();
                    fTrans.replace(R.id.fragment, fNow).addToBackStack(null).commit();
                    fragment.startAnimation(animShow);
                }
            }

            @Override
            public void onAnimationRepeat(Animation animation) { }
        });
        fragment.startAnimation(animHide);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnLogin:
                animShowHide(fLogin);
                break;

            case R.id.btnRegistration:
                animShowHide(fRegistration);
                break;
        }
    }
}
