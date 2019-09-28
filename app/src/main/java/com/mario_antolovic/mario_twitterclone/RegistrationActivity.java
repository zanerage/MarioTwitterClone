package com.mario_antolovic.mario_twitterclone;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;
import com.shashank.sony.fancytoastlib.FancyToast;

public class RegistrationActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnsignup;
    private EditText edt_username,edt_password,edt_email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        btnsignup = findViewById(R.id.btn_signup);

        edt_username = findViewById(R.id.edt_username);
        edt_password = findViewById(R.id.edt_password);
        edt_password.setOnKeyListener(new View.OnKeyListener() {
            // user can register with key event pressing down and enter on virtual keyboard
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_ENTER && event.getAction()== KeyEvent.ACTION_DOWN) {
                    onClick(btnsignup);
                }
                return false;
            }
        });
        edt_email = findViewById(R.id.edt_email);


        btnsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edt_email.getText().toString().equals("")|| edt_username.getText().toString().equals("")|| edt_password.getText().toString().equals("")) {

                    FancyToast.makeText(RegistrationActivity.this,"Email, Username, Password fields are required!" , FancyToast.LENGTH_SHORT, FancyToast.INFO, true).show();

                } else  {
                    final ParseUser appUser = new ParseUser();
                    appUser.setUsername(edt_username.getText().toString());
                    appUser.setPassword(edt_password.getText().toString());
                    appUser.setEmail(edt_email.getText().toString());






                    appUser.signUpInBackground(new SignUpCallback() {
                        @Override
                        public void done(ParseException e) {
                            if (e == null) {
                                FancyToast.makeText(RegistrationActivity.this,"You've successful registered " + appUser.get("username") , FancyToast.LENGTH_LONG, FancyToast.SUCCESS, true).show();
                                Intent intent = new Intent(RegistrationActivity.this,HomeActivity.class);
                                startActivity(intent);

                            } else
                            {
                                FancyToast.makeText(RegistrationActivity.this, e.getMessage(), FancyToast.LENGTH_LONG, FancyToast.ERROR, true).show();

                            }
                        }
                    });
                }

            }
        });
    }

    @Override
    public void onClick(View v) {

    }
    public void user_tap(View view) {
        try {
            InputMethodManager inputMethodManager = (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),0);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}


