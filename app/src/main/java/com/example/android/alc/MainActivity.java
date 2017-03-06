package com.example.android.alc;

import android.app.LoaderManager;
import android.app.LoaderManager.LoaderCallbacks;
import android.content.Intent;
import android.content.Loader;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Precious on 04/03/2017.
 */

public class MainActivity extends AppCompatActivity implements LoaderCallbacks<List<User>> {

    private final static String GITHUB_API_URL = "https://api.github.com/search/users?q=location:lagos+language:java&per_page=100";
    private UsersAdapter mAdapter;
    private static final int USER_LOADER_ID = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Create a new adapter that takes an empty list of users as input
        mAdapter = new UsersAdapter(this, new ArrayList<User>());

        ListView usersListView = (ListView) findViewById(R.id.listview_users);
        usersListView.setAdapter(mAdapter);

        usersListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                // Find the current User that was clicked on
                User currentUser = mAdapter.getItem(position);


                // Create a new intent that will contain information that be passed to the Profile Activity Class
                Intent profileIntent = new Intent(MainActivity.this, ProfileActivity.class);
                profileIntent.putExtra("username", currentUser.getUsername());
                profileIntent.putExtra("githubUrl", currentUser.getGithubProfileUrl());
                profileIntent.putExtra("userProfilePictureUrl", currentUser.getProfilePicture());
                // Send the intent to launch a new activity
                startActivity(profileIntent);
            }
        });

        // Get a reference to the LoaderManager, in order to interact with loaders.
        LoaderManager loaderManager = getLoaderManager();

        // Initialize the loader. Pass in the int ID constant defined above and pass in null for
        // the bundle. Pass in this activity for the LoaderCallbacks parameter (which is valid
        // because this activity implements the LoaderCallbacks interface).
        loaderManager.initLoader(USER_LOADER_ID, null, this);
    }

    @Override
    public Loader<List<User>> onCreateLoader(int i, Bundle bundle) {
        // Create a new loader for the given URL
        return new UserLoader(this, GITHUB_API_URL);
    }

    @Override
    public void onLoadFinished(Loader<List<User>> loader, List<User> users) {
        // Clear the adapter of previous User data
        mAdapter.clear();

        // If there is a valid list of {@link user}s, then add them to the adapter's
        // data set. This will trigger the ListView to update.
        if (users != null && !users.isEmpty()) {
            mAdapter.addAll(users);
        }
    }

    @Override
    public void onLoaderReset(Loader<List<User>> loader) {
        // Loader reset, so we can clear out our existing data.
        mAdapter.clear();
    }
}
