package com.example.android.alc;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Precious on 05/03/2017.
 */

public class UsersAdapter extends ArrayAdapter<User> {
    private Context context;
    public UsersAdapter(Activity context, List<User> users) {
        // Here, we initialize the ArrayAdapter's internal storage for the context and the list.
        // the second argument is used when the ArrayAdapter is populating a single TextView.
        // Because this is a custom adapter for two TextViews and an ImageView, the adapter is not
        // going to use this second argument, so it can be any value. Here, we used 0.
        super(context, 0, users);
        this.context = context;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        // Check if there is an existing list item view (called convertView) that we can reuse,
        // otherwise, if convertView is null, then inflate a new list item layout.

        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }


        // Find a user at the given position in the list of Users
        User currentUser = getItem(position);


        // Find the TextView with view ID username
        TextView username = (TextView) listItemView.findViewById(R.id.username);

        // Display the username of the current User in that TextView
        username.setText(currentUser.getUsername());

        //Set the Profile Picture for the User
        ImageView profilePicture = (ImageView) listItemView.findViewById(R.id.profile_picture);
        Picasso.with(context).load(currentUser.getProfilePicture()).into(profilePicture);

        return listItemView;
    }
}
