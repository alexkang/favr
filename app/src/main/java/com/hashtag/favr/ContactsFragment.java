package com.hashtag.favr;



import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.app.Fragment;
import android.provider.ContactsContract;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 *
 */
public class ContactsFragment extends Fragment {

    ListView mContactsList;

    ContactsListAdapter mAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_contacts, container, false);

        mContactsList = (ListView) v.findViewById(R.id.contact_list);

        return v;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        ArrayList<User> contactsList =
                ContactsController.getInstance(getActivity()).getContacts();

        mAdapter = new ContactsListAdapter(getActivity(), contactsList);
        mContactsList.setAdapter(mAdapter);
        mContactsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                User user = (User) mAdapter.getItem(position);
                Intent i = new Intent(getActivity(), CreateFavrActivity.class);

                i.putExtra("user", user);
                startActivity(i);
            }

        });
    }
}
