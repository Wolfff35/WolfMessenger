package com.wolfff.wolfmessenger.Fragments;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.wolfff.wolfmessenger.ListAdapters.ChatAdapter;
import com.wolfff.wolfmessenger.Obj.WMessage;
import com.wolfff.wolfmessenger.R;
import com.wolfff.wolfmessenger.Tools.LocalData.DataOperations;
import com.wolfff.wolfmessenger.Tools.Sqlite.DBConnector;

import java.util.ArrayList;

import static com.wolfff.wolfmessenger.Tools.Protocol.ProtocolMessenger.TYPE_MESSAGE_SEND_TEXT;

public class ChatFragment extends Fragment {
    Button btn_ChatSend;
    EditText edChat_Message;
    ListView lvChat;
     long activPhoneNumber ;
    long contactPhoneNumber;
    ChatAdapter chatAdapter;
    ArrayList<WMessage> messages;
    public interface onButtonChatSendPressEventListener_ChatFragment {
        //void buttonChatSendPressEvent_ChatFragment(long contactNumber,String message);
        void buttonChatSendPressEvent_ChatFragment(WMessage message);
    }
    ChatFragment.onButtonChatSendPressEventListener_ChatFragment buttonChatSendPressEventListener;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


     }
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            buttonChatSendPressEventListener = (ChatFragment.onButtonChatSendPressEventListener_ChatFragment) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must implement onSomeEventListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        buttonChatSendPressEventListener = null;
    }

    //==============================================================================================
    public static ChatFragment newInstance(long activNumber,long contactNumber){
        ChatFragment fragment = new ChatFragment();
        Bundle bundle = new Bundle();
        bundle.putLong("activNumber", activNumber);
        bundle.putLong("contactNumber", contactNumber);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                         Bundle savedInstanceState) {
    View v = inflater.inflate(R.layout.fragment_chat, null);

    btn_ChatSend = (Button) v.findViewById(R.id.btnChat_Send);
    edChat_Message = (EditText) v.findViewById(R.id.edChat_Message);
    lvChat = (ListView) v.findViewById(R.id.lvChat);
    activPhoneNumber = this.getArguments().getLong("activNumber");
    contactPhoneNumber =this.getArguments().getLong("contactNumber");

    Context context = getActivity().getApplicationContext();
    DataOperations dataOperations = new DataOperations();
    DBConnector dbConnector = new DBConnector(context);
    dbConnector.open();
    Cursor cursor = dbConnector.messages_getByPhone_cursor(activPhoneNumber,contactPhoneNumber);
    messages = dataOperations.messages_getAll_ObjectArray(cursor);
    chatAdapter= new ChatAdapter(context,messages);
    lvChat.setAdapter(chatAdapter);
    lvChat.smoothScrollToPosition(lvChat.getCount());
    cursor.close();
    dbConnector.close();

    btn_ChatSend.setOnClickListener(new View.OnClickListener() {
        public void onClick(View v) {
            if((edChat_Message.getText().length()==0)) {
                Toast.makeText(getActivity().getApplicationContext(),"Нечего отправлять!", Toast.LENGTH_LONG);
            }else {
                WMessage outMessage = new WMessage(0,activPhoneNumber,contactPhoneNumber,edChat_Message.getText().toString(),"",System.currentTimeMillis(),TYPE_MESSAGE_SEND_TEXT,1,0,0);
                buttonChatSendPressEventListener.buttonChatSendPressEvent_ChatFragment(outMessage);
                messages.add(outMessage);
                chatAdapter.notifyDataSetChanged();
                lvChat.smoothScrollToPosition(lvChat.getCount());
                edChat_Message.setText("");
            }

        }
    });
        Log.e("VIEWWWWWWWW","==============================================");
    return v;
}


}
