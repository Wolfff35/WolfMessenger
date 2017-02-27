package com.wolfff.wolfmessenger.ListAdapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.wolfff.wolfmessenger.Obj.WMessage;
import com.wolfff.wolfmessenger.R;

import java.util.ArrayList;

/**
 * Created by wolff on 14.10.2016.
 */

public class MessageListAdapter extends BaseAdapter {
    private Context context;
    private LayoutInflater lInflater;
    private ArrayList<WMessage> messages;


    public MessageListAdapter(Context context, ArrayList<WMessage> messages) {
        this.context = context;
        this.messages = messages;
        this.lInflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public int getCount() {
        return messages.size();
    }

    @Override
    public WMessage getItem(int i) {
        return messages.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View cView, ViewGroup viewGroup) {
        View view = cView;
        TextView tvContactChat;
        TextView tvChat_Text;

        if(view==null){
            view = lInflater.inflate(R.layout.messagelist_item_adapter,viewGroup,false);
        }
        tvContactChat = (TextView) view.findViewById(R.id.tvContactChat);
        tvChat_Text = (TextView) view.findViewById(R.id.tvChat_Text);

        WMessage message = getItem(position);
        tvContactChat.setText(Long.toString(message.getContactPhoneNumber()));
        tvChat_Text.setText(String.valueOf(message.getMessage_body()));
        return view;
    }
}
