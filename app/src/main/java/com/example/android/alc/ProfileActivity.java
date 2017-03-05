package com.example.android.alc;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        Intent mainIntent = getIntent();
        Bundle extras = mainIntent.getExtras();

        String usernameValue = extras.getString("username");
        String githubUrlValue = extras.getString("githubUrl");

        TextView username = (TextView) findViewById(R.id.profile_username);
        username.setText(usernameValue);

        TextView githubUrl = (TextView) findViewById(R.id.github_url);
        githubUrl.setText(githubUrlValue);
    }
}
