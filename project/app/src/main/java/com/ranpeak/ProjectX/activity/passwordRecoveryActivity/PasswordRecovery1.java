package com.ranpeak.ProjectX.activity.passwordRecoveryActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.ranpeak.ProjectX.R;
import com.ranpeak.ProjectX.activity.interfaces.Activity;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PasswordRecovery1 extends AppCompatActivity implements Activity {

    private TextView emailTextView;
    private EditText emailEditText;
    private Button nextButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password_recovery1);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        toolbar();
        findViewById();
        onListener();
    }

    @Override
    public void findViewById() {
        emailTextView = findViewById(R.id.password_recovery1_activity_text_view);
        emailEditText = findViewById(R.id.password_recovery1_activity_edit_text);
        nextButton = findViewById(R.id.password_recovery1_button);
    }

    @Override
    public void onListener() {
        nextButton.setOnClickListener(v -> {
            checkEmail();
        });
    }

    private void checkEmail() {
        /** проверка почты на сервере (существует или нет)*/

        emailEditText.setError(null);
        View requestFocus = null;
        boolean cancel;

        if (TextUtils.isEmpty(emailEditText.getText().toString())) {
            emailEditText.setError(getString(R.string.error_field_required));
            cancel = true;
            requestFocus = emailEditText;
        } else if (!isEmailValid(emailEditText.getText().toString())) {
            emailEditText.setError(getString(R.string.error_invalid_email));
            cancel = true;
            requestFocus = emailEditText;
        } else {
            cancel = false;
        }
        if (cancel) {
            if (requestFocus != null) {
                requestFocus.requestFocus();
            }
        } else {
            Intent intent = new Intent(PasswordRecovery1.this, PasswordRecovery2.class);
            intent.putExtra("email", emailEditText.getText().toString());
            startActivity(intent);
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        }

    }

    private void toolbar() {
        Objects.requireNonNull(getSupportActionBar()).setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(getString(R.string.app_name));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // todo: goto back activity from here
                finish();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    private boolean isEmailValid(String email) {
        String expression = "^[\\w.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
}
