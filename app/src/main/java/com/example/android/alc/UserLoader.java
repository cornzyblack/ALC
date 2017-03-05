package com.example.android.alc;

import android.content.AsyncTaskLoader;
import android.content.Context;

import java.util.List;

/**
 * Created by Precious on 05/03/2017.
 */

public class UserLoader extends AsyncTaskLoader<List<User>> {

    private static final String LOG_TAG = UserLoader.class.getName();

    /** Query URL */
    private String mUrl;

    /**
     * Constructs a new {@link UserLoader}.
     *
     * @param context of the activity
     * @param url to load data from
     */
    public UserLoader(Context context, String url) {
        super(context);
        mUrl = url;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    /**
     * This is on a background thread.
     */
    @Override
    public List<User> loadInBackground() {
        if (mUrl == null) {
            return null;
        }

        // Perform the network request, parse the response, and extract a list of Users.
        List<User> users = QueryUtils.fetchUserData(mUrl);
        return users;
    }
}
