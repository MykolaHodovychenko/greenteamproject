package com.ranpeak.ProjectX.activity.registration;

import android.app.AlertDialog;
import android.app.FragmentManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import com.ranpeak.ProjectX.R;
import com.ranpeak.ProjectX.constant.Constants;


public class RegistrationActivity2 extends AppCompatActivity{

    private final static int REGISTRATION_ACTIVITY2 = R.layout.activity_registration2;

    private EditText register_name;
    private TextView register_age;
    private RadioGroup register_gender;
    private RadioButton register_gender_male;
    private RadioButton register_gender_female;
    private RadioButton radioButton;
    private String gender;

    private TextView register_country;
    private static String email;

    public void setCountry(String country) {
        this.register_country.setText(country);
        this.register_country.setTextColor(Color.parseColor("#D81B60"));
    }

    private Button nextButton;


    private TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

            nextButton.setEnabled(!register_name.getText().toString().trim().isEmpty()
                    && isAgeValid()
                    && stringContainsItemFromList(register_country.getText().toString(),
                    Constants.Values.COUNTRIES)
                    && checkGender()
            );
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(REGISTRATION_ACTIVITY2);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        email = getIntent().getStringExtra("email");

        // Находим и передаем локальным переменным обьекты activity_registration
        register_name = findViewById(R.id.register_name);
        register_name.addTextChangedListener(textWatcher);
        register_age = findViewById(R.id.register_age);
        register_age.addTextChangedListener(textWatcher);
        register_age.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ageClickerDialog();
            }
        });
        register_gender = findViewById(R.id.register_gender_group);
        register_gender_male = findViewById(R.id.register_gender_male);
        register_gender_female = findViewById(R.id.register_gender_female);
//        register_gender.get.addTextChangedListener(textWatcher);


        // start fragmentActivity to choose country
        final FragmentManager fm = getFragmentManager();
        final CountryListActivity  countryListActivity = new CountryListActivity();
        register_country = findViewById(R.id.register_country);
        register_country.addTextChangedListener(textWatcher);
        register_country.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                countryListActivity.show(fm, "Country lists");
            }
        });

        nextButton = findViewById(R.id.registration_button_2);
        nextButton.setEnabled(false);
        nextButton.addTextChangedListener(textWatcher);
    }


    public void clickRegistration_2(View view){
        Intent intent = new Intent(getApplicationContext(), RegistrationActivity3.class);
        intent.putExtra("email", email);
        intent.putExtra("name", register_name.getText().toString().trim());
        intent.putExtra("age", register_age.getText().toString().trim());
        intent.putExtra("gender", gender);
        intent.putExtra("country", register_country.getText().toString().trim());
        startActivity(intent);

        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }


    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }


    private static boolean stringContainsItemFromList(String inputStr, String[] items) {
        for(int i =0; i < items.length; i++)
        {
            if(inputStr.contains(items[i]))
            {
                return true;
            }
        }
        return false;
    }


    public void checkRadioButton(View v){
        int radioId = register_gender.getCheckedRadioButtonId();
        radioButton = findViewById(radioId);
        gender = radioButton.getText().toString();
    }

    private boolean checkGender(){
        if(register_gender_male.isChecked()){
            gender = register_gender_male.getText().toString();
            return true;
        } else if(register_gender_female.isChecked()){
            gender = register_gender_female.getText().toString();
            return true;
        }
        return false;
    }

    private void ageClickerDialog(){
        NumberPicker numberPicker = new NumberPicker(this);
        numberPicker.setMinValue(1);
        numberPicker.setMaxValue(100);
        NumberPicker.OnValueChangeListener valueChangeListener = new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                register_age.setText(""+newVal);
                register_age.setTextColor(Color.parseColor("#D81B60"));
            }
        };
        numberPicker.setOnValueChangedListener(valueChangeListener);
        AlertDialog.Builder builder = new AlertDialog.Builder(this)
                .setView(numberPicker);
        builder.setTitle("Age");
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.show();
    }

    private boolean isAgeValid(){
        boolean answer = false;
        for( int i = 1; i<=100; i++){
            if(register_age.getText().toString().equals(Integer.toString(i))){
                answer = true;
            }
        }
        return answer;
    }
}