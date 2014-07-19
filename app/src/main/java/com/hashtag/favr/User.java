package com.hashtag.favr;

import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Alex on 7/18/2014.
 */
public class User implements Parcelable {

    private String name;
    private String phoneNumber;
    private String profilePicThumbnail;
    private String profilePic;

    private int score;

    public static final Parcelable.Creator<User> CREATOR = new Parcelable.Creator<User>() {
        public User createFromParcel(Parcel source) {
            return new User(source);
        }

        public User[] newArray(int size) {
            return new User[size];
        }
    };

    public User() {}

    public User(Parcel in) {
        String[] info = new String[4];
        in.readStringArray(info);

        name = info[0];
        phoneNumber = info[1];
        profilePicThumbnail = info[2];
        profilePic = info[3];
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNumber(String number) {
        phoneNumber = number;
    }

    public void setProfilePicThumbnail(String pic) {
        profilePicThumbnail = pic;
    }

    public void setProfilePic(String pic) {
        profilePic = pic;
    }

    public String getName() {
        return name;
    }

    public String getNumber() {
        return phoneNumber;
    }

    public String getProfilePicThumbnail() {
        return profilePicThumbnail;
    }

    public String getProfilePic() {
        return profilePic;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel out, int i) {
        out.writeStringArray(new String[] {name, phoneNumber, profilePicThumbnail, profilePic});
    }
}
