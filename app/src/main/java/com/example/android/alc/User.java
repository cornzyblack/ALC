package com.example.android.alc;

/**
 * Created by Precious on 04/03/2017.
 */

/**
 * A custom class that defines a User
 */

public class User {
    private String mProfilePicture;
    private String mUsername;
    private String mGithubProfileUrl;

    /**
     * @param profilePicture   is the URL for the User's Github Profile Picture
     * @param username         is the Github Username for a User
     * @param githubProfileUrl is the Github profile URL
     */
    public User(String profilePicture, String username, String githubProfileUrl) {
        this.mProfilePicture = profilePicture;
        this.mUsername = username;
        this.mGithubProfileUrl = githubProfileUrl;

    }

    /**
     * @return the Profile Picture Url for the User
     */
    public String getProfilePicture() {
        return mProfilePicture;
    }

    /**
     * @return the Username for the Github Profile
     */
    public String getUsername() {
        return mUsername;
    }

    /**
     * @return the Github profile URL
     */
    public String getGithubProfileUrl() {
        return mGithubProfileUrl;
    }

}
