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

import com.wolfff.wolfmessenger.ListAdapters.MessageListAdapter;
import com.wolfff.wolfmessenger.Obj.WMessage;
import com.wolfff.wolfmessenger.R;
import com.wolfff.wolfmessenger.Tools.LocalData.DataOperations;
import com.wolfff.wolfmessenger.Tools.Sqlite.DBConnector;

import java.util.ArrayList;

public class MessageListFragment extends ListFragment{
    private MessageListFragmentListener listener;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        View header;
        header = createHeader("Список чатов");
        getListView().addHeaderView(header,null,false);

        long activPhoneNumber = this.getArguments().getLong("activNumber");
        Context context = getActivity().getApplicationContext();
        DataOperations dataOperations = new DataOperations();
        DBConnector dbConnector = new DBConnector(context);
        dbConnector.open();
        Cursor cursor = dbConnector.chats_getAll_cursor(activPhoneNumber);
        final ArrayList<WMessage> messages = dataOperations.messages_getAll_ObjectArray(cursor);
        MessageListAdapter messageListAdapter= new MessageListAdapter(context,messages);
        setListAdapter(messageListAdapter);

        getListView().setOnItemClickListener(new android.widget.AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Log.e("MESS_LIST_FRAG","i = "+i+"; messages.size = "+messages.size());
                listener.onMessageListSelected(messages.get(i-1).getContactPhoneNumber());
                //Log.e("onItemClick","================================================================ "+messages.get(i-1).getContactPhoneNumber()+"; "+contacts.get(i).getNickName());

            }
        });

        cursor.close();
        dbConnector.close();
    }
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            listener = (MessageListFragmentListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must implement onSomeEventListener");
        }
    }
    public static MessageListFragment newInstance(long activNumber){
        MessageListFragment fragment = new MessageListFragment();
        Bundle bundle = new Bundle();
        bundle.putLong("activNumber", activNumber);
        fragment.setArguments(bundle);
        return fragment;
    }
    View createHeader(String text) {
        View v = getActivity().getLayoutInflater().inflate(R.layout.header_list, null);
        ((TextView)v.findViewById(R.id.tvHeader)).setText(text);
        return v;
    }


    //==============================================================================================
    public interface MessageListFragmentListener {
        void onMessageListSelected(long phone_number);

    }
}
