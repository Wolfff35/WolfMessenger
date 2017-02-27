package com.wolfff.wolfmessenger.Fragments;

import android.app.Activity;
import android.app.ListFragment;
import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;

import com.wolfff.wolfmessenger.ListAdapters.ContactListAdapter;
import com.wolfff.wolfmessenger.Obj.WContact;
import com.wolfff.wolfmessenger.R;
import com.wolfff.wolfmessenger.Tools.LocalData.DataOperations;
import com.wolfff.wolfmessenger.Tools.Sqlite.DBConnector;

import java.util.ArrayList;

public class ContactListFragment extends ListFragment {
    private ContactListFragmentListener listener;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        View header;
        header = createHeader("Список контактов");
        getListView().addHeaderView(header,null,false);

        Context context = getActivity().getApplicationContext();
        DataOperations dataOperations = new DataOperations();
        DBConnector dbConnector = new DBConnector(context);
        dbConnector.open();
        Cursor cursor = dbConnector.contact_getAll_cursor();
        final ArrayList<WContact> contacts = dataOperations.contact_getAll_ObjectArray(cursor);
        ContactListAdapter contactListAdapter= new ContactListAdapter(context,contacts);
        setListAdapter(contactListAdapter);

        getListView().setOnItemClickListener(new android.widget.AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Log.e("CONTLIST_LIST_FRAG","i = "+i+"; contact.size = "+contacts.size());
                listener.onContactSelected(contacts.get(i-1).getPhone_number());
                //Log.e("onItemClick","================================================================ "+contacts.get(i-1).getPhone_number()+"; "+contacts.get(i).getNickName());

            }
        });

        cursor.close();
        dbConnector.close();
    }
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            listener = (ContactListFragmentListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must implement onSomeEventListener");
        }
    }
    View createHeader(String text) {
        View v = getActivity().getLayoutInflater().inflate(R.layout.header_list, null);
        ((TextView)v.findViewById(R.id.tvHeader)).setText(text);
        return v;
    }


    //==============================================================================================
    public interface ContactListFragmentListener {
        void onContactSelected(long phone_number);

    }

}
