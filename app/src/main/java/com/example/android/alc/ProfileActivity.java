package com.example.android.alc;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class ProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        Intent mainIntent = getIntent();
        Bundle extras = mainIntent.getExtras();

        final String usernameValue = extras.getString("username");
        final String githubUrlValue = extras.getString("githubUrl");
        final String profilePictureUrlValue = extras.getString("userProfilePictureUrl");

        final TextView username = (TextView) findViewById(R.id.profile_username);
        username.setText(usernameValue);

        final TextView githubUrl = (TextView) findViewById(R.id.github_url);
        githubUrl.setText(githubUrlValue);


        final ImageView userProfilePicture = (ImageView) findViewById(R.id.user_profile_picture);
        Picasso.with(this).load(profilePictureUrlValue).into(userProfilePicture);

        FloatingActionButton shareButton = (FloatingActionButton) findViewById(R.id.share);
        shareButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent shareIntent = new Intent(Intent.ACTION_SEND);

                shareIntent.setType("text/plain");
                shareIntent.putExtra(android.content.Intent.EXTRA_TEXT, "Check out this awesome developer " + usernameValue + ", " + githubUrlValue);
                startActivity(shareIntent);
            }
        });

        githubUrl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Parse the Github URL to URI so that it can be read by the Browser
                Uri websiteUri = Uri.parse(githubUrl.getText().toString());

                // Create a new intent to view the Github URI
                Intent websiteIntent = new Intent(Intent.ACTION_VIEW, websiteUri);

                //Start the Activity on the appropriate Browser
                startActivity(websiteIntent);
            }
        });
    }
}
