package com.hashtag.favr;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.provider.ContactsContract;
import android.provider.MediaStore;

import java.util.ArrayList;

/**
 * Created by Alex on 7/18/2014.
 */
public class ContactsController {

    private static final String[] SELF_PROJECTION = new String[] {
            ContactsContract.CommonDataKinds.Phone._ID,
            ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,
            ContactsContract.Profile.PHOTO_URI
    };

    private static ContactsController instance = null;

    private Cursor mCursor = null;
    private Context mContext;

    public static ContactsController getInstance(Context context) {
        if (instance == null) {
            instance = new ContactsController(context);
        }

        return instance;
    }

    private ContactsController(Context context) {
        mContext = context;
        ContentResolver contentResolver = context.getContentResolver();
        mCursor = contentResolver.query(
                ContactsContract.Profile.CONTENT_URI,
                SELF_PROJECTION,
                null, null, null);
    }

    public ArrayList<User> getContacts() {
        ArrayList<User> contactList = new ArrayList<User>();
        ContentResolver contentResolver = mContext.getContentResolver();
        Cursor cursor = contentResolver.query(
                ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                null, null, null, ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME + " ASC");

        while (cursor.moveToNext()) {
            User user = new User();
            String name = cursor.getString(
                    cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
            String phoneNumber = cursor.getString(
                    cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
            String picThumbString = cursor.getString(
                    cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.PHOTO_THUMBNAIL_URI));
            String picString = cursor.getString(
                    cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.PHOTO_URI));

            user.setName(name);
            user.setNumber(phoneNumber);
            user.setProfilePicThumbnail(picThumbString);
            user.setProfilePic(picString);

            contactList.add(user);
        }

        return contactList;
    }

    public String getName() {
        mCursor.moveToFirst();
        return mCursor.getString(1);
    }

    public String getProfilePic() {
        mCursor.moveToFirst();
        return mCursor.getString(2);
    }

}
