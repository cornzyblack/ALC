package com.example.android.alc;

import android.text.TextUtils;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class QueryUtils {

    private static final String LOG_TAG = "In QueryUtils";

    private QueryUtils() {

    }

    /**
     * @param stringUrl the request URL in String form
     * @return the Converted URL for the converted String
     */
    private static URL createUrl(String stringUrl) {
        URL url = null;
        try {
            url = new URL(stringUrl);
        } catch (MalformedURLException e) {
            Log.e(LOG_TAG, "Error with creating URL", e);
        }
        return url;
    }

    private static String makeHttpRequest(URL url) throws IOException {
        String jsonResponse = "";
        if (url == null) {
            return jsonResponse;
        }

        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;
        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setReadTimeout(10000);
            urlConnection.setConnectTimeout(15000);
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            if (urlConnection.getResponseCode() == 200) {
                inputStream = urlConnection.getInputStream();
                jsonResponse = readFromStream(inputStream);
            } else
                Log.e(LOG_TAG, "Error response code: " + urlConnection.getResponseCode());
        } catch (IOException e) {
            Log.e(LOG_TAG, "Problem retrieving the Github User JSON results");
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (inputStream != null) {
                inputStream.close();
            }
        }
        return jsonResponse;
    }

    private static String readFromStream(InputStream inputStream) throws IOException {
        StringBuilder output = new StringBuilder();
        if (inputStream != null) {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line = reader.readLine();
            while (line != null) {
                output.append(line);
                line = reader.readLine();
            }
        }
        return output.toString();
    }
    public static List<User> fetchUserData(String requestUrl) {
        // Create URL object
        URL url = createUrl(requestUrl);

        // Perform HTTP request to the URL and receive a JSON response back
        String jsonResponse = null;
        try {
            jsonResponse = makeHttpRequest(url);
        } catch (IOException e) {
            Log.e(LOG_TAG, "Problem making the HTTP request.", e);
        }

        // Extract relevant fields from the JSON response and create a list of {@link User}s
        List<User> users = extractDetailFromJson(jsonResponse);

        // Return the list of {@link User}s
        return users;
    }

    private static List<User> extractDetailFromJson(String userJson) {
        // If the JSON string is empty or null, then return early.
        if (TextUtils.isEmpty(userJson)) {
            return null;
        }

        // Create an empty ArrayList that we can start adding Users
        List<User> users = new ArrayList<>();

        try {
            // Create a JSONObject from the JSON response string
            JSONObject baseJsonResponse = new JSONObject(userJson);

            JSONArray usersArray = baseJsonResponse.getJSONArray("items");
            // For each User in the usersArray, create an {@link User} object
            for (int i = 0; i < usersArray.length(); i++) {

                //Get the Object that contains a User's details
                JSONObject currentUser = usersArray.getJSONObject(i);

                //Get the User's avatar URL
                String avatarURL = currentUser.getString("avatar_url");
                //Get the Username
                String login = currentUser.getString("login");
                //Get the Github URL for the User
                String githubUrl = currentUser.getString("html_url");
                //Create a User Object with the properties
                User user = new User(avatarURL, login, githubUrl);

                //add the User Object the to List of Users
                users.add(user);

            }
        } catch (JSONException e) {
            Log.e("QueryUtils", "Problem parsing the User JSON", e);
        }
        return users;
    }

}
