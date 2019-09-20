package com.mario_antolovic.mario_twitterclone;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.parse.ParseInstallation;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // we can check if user installed the application on back4app website
        ParseInstallation.getCurrentInstallation().saveInBackground();

    }
}
