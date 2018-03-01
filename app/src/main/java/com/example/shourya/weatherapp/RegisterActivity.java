package com.example.shourya.weatherapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener{

    //The view objects
    private EditText mEditTextName, mEditTextEmail, mEditTextMobile,
            mEditTextPassword;

    private Button mButtonRegister;
    private TextView mButtonLogin;

    //defining AwesomeValidation object
    private AwesomeValidation mAwesomeValidation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //initializing awesomevalidation object
        mAwesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);

        //initializing view objects
        mEditTextName = (EditText) findViewById(R.id.editTextName);
        mEditTextEmail = (EditText) findViewById(R.id.editTextEmail);
        mEditTextPassword = (EditText) findViewById(R.id.editTextPassword);
        mEditTextMobile = (EditText) findViewById(R.id.editTextMobile);

        mButtonRegister = (Button) findViewById(R.id.buttonRegister);
        mButtonLogin = (TextView) findViewById(R.id.buttonLogin);

        //adding validation to edittexts
        mAwesomeValidation.addValidation(this, R.id.editTextName, "^[A-Za-z\\s]{1,}[\\.]{0,1}[A-Za-z\\s]{0,}$", R.string.nameerror);
        mAwesomeValidation.addValidation(this, R.id.editTextEmail, Patterns.EMAIL_ADDRESS, R.string.emailerror);
        mAwesomeValidation.addValidation(this, R.id.editTextPassword, "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{4,}$", R.string.passworderror);
        mAwesomeValidation.addValidation(this, R.id.editTextMobile, "^[2-9]{2}[0-9]{8}$", R.string.mobileerror);



        mButtonRegister.setOnClickListener(this);
    }

    private void submitForm() {
        //first validate the form then move ahead
        //if this becomes true that means validation is successfull
        if (mAwesomeValidation.validate()) {
            //show toast if successful submit
            Toast.makeText(this, "Registration Successfull", Toast.LENGTH_LONG).show();

            //go to login activity if successful registration
            Intent login_intent = new Intent(this, LoginActivity.class);
            startActivity(login_intent);

            //process the data further
        }
    }

    @Override
    public void onClick(View view) {
        if (view == mButtonRegister) {
            submitForm();
        }
        if(view == mButtonLogin) {
            //go to login activity
            Intent login_intent = new Intent(this, LoginActivity.class);
            startActivity(login_intent);
        }
    }
}
