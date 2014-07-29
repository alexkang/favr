package com.hashtag.favr;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Alex on 7/18/2014.
 */
public class ContactsListAdapter extends ArrayAdapter<User> {

    Context mContext;

    public ContactsListAdapter(Context context, ArrayList<User> users) {
        super(context, R.layout.contacts_list_row, users);

        mContext = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        User user = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.contacts_list_row, parent, false);
        }

        ImageView picView = (ImageView) convertView.findViewById(R.id.contact_pic);
        TextView nameView = (TextView) convertView.findViewById(R.id.contact_name);

        String profilePicThumbString = user.getProfilePicThumbnail();

        if (profilePicThumbString != null) {
            Uri picThumbUri = Uri.parse(profilePicThumbString);
            try {
                picView.setImageBitmap(MediaStore.Images.Media.getBitmap(mContext.getContentResolver(), picThumbUri));
            } catch (Exception e) {}
        } else {
            picView.setImageDrawable(mContext.getResources().getDrawable(R.drawable.default_user));
        }

        nameView.setText(user.getName());

        return convertView;
    }

}
