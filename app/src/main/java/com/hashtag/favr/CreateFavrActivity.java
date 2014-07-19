package com.hashtag.favr;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.hashtag.favr.R;

public class CreateFavrActivity extends Activity {

    TextView mBody;

    ImageView mSenderPic;
    ImageView mRecepientPic;
    TextView mSenderName;
    TextView mRecepientName;

    EditText mDelay;
    TextView mSendButton;

    User mUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_favr);

        mUser = getIntent().getParcelableExtra("user");

        mBody = (TextView) findViewById(R.id.message_box);

        mSenderPic = (ImageView) findViewById(R.id.sender_pic);
        mRecepientPic = (ImageView) findViewById(R.id.receipient_pic);
        mSenderName = (TextView) findViewById(R.id.sender_name);
        mRecepientName = (TextView) findViewById(R.id.receipient_name);

        mDelay = (EditText) findViewById(R.id.delay);
        mSendButton = (TextView) findViewById(R.id.send_button);

        mSenderName.setText(ContactsController.getInstance(this).getName());
        mRecepientName.setText(mUser.getName());

        if (mUser.getProfilePicThumbnail() != null) {
            try {
                Uri recepientUri = Uri.parse(mUser.getProfilePicThumbnail());
                mRecepientPic.setImageResource(0);
                mRecepientPic.setImageBitmap(MediaStore.Images.Media.getBitmap(getContentResolver(), recepientUri));
            } catch (Exception e) {
            }
        }

        mSendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String body = mBody.getText().toString();
                int delay = Integer.parseInt(mDelay.getText().toString());

                PhoneController.getInstance(getBaseContext()).sendDelayedSms(mUser.getNumber(), body, delay);

                Toast.makeText(getBaseContext(), "favr created!", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }

}
