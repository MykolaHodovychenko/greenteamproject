package com.ranpeak.ProjectX.activity.lobby.forAuthorizedUsers.navigationFragment.profileNavFragment.myProfileFragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ranpeak.ProjectX.R;
import com.ranpeak.ProjectX.activity.interfaces.Activity;
import com.ranpeak.ProjectX.settings.SharedPrefManager;

public class MyProfileFragment extends Fragment implements Activity {

    private final int layout = R.layout.fragment_my_profile;
    private View view;
    private TextView phoneNumber;
    private TextView email;
    private TextView country;
    private TextView telegram;
    private TextView instagram;
    private TextView facebook;

    public MyProfileFragment() {

    }


//    public static MyProfileFragment newInstance(int page) {
//        Bundle args = new Bundle();
//        args.putInt(NAME, page);
//        MyProfileFragment myProfileFragment = new MyProfileFragment();
//        myProfileFragment.setArguments(args);
//        return myProfileFragment;
//    }
//
//    @Override
//    public void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        if (getArguments() != null) {
//            page = getArguments().getInt(NAME);
//        }
//    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(layout, container, false);
        findViewById();
        onListener();
        initData();
        return view;
    }

    @Override
    public void findViewById() {
        phoneNumber = view.findViewById(R.id.fragment_my_profile_phone_number);
        email = view.findViewById(R.id.fragment_my_profile_email);
        country = view.findViewById(R.id.fragment_my_profile_country);
        telegram = view.findViewById(R.id.fragment_my_profile_telegram);
        instagram = view.findViewById(R.id.fragment_my_profile_instagram);
        facebook = view.findViewById(R.id.fragment_my_profile_facebook);
    }

    @Override
    public void onListener() {

    }

    private void initData(){
//        phoneNumber.setOpportunities(SharedPrefManager.getInstance(getContext()).getUeserPhoneNumber());
        email.setText(String.valueOf(SharedPrefManager.getInstance(getContext()).getUserEmail()));
        country.setText(String.valueOf(SharedPrefManager.getInstance(getContext()).getUserCountry()));
//        telegram.setOpportunities(String.valueOf(SharedPrefManager.getInstance(getContext()).getUserTelegram()));
//        instagram.setOpportunities(String.valueOf(SharedPrefManager.getInstance(getContext()).getUserInstagram()));
//        facebook.setOpportunities(String.valueOf(SharedPrefManager.getInstance(getContext()).getUserFacebook()));
    }
}