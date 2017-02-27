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
 * Created by wolff on 13.10.2016.
 */

public class ChatAdapter extends BaseAdapter {
    private Context context;
    private LayoutInflater lInflater;
    private ArrayList<WMessage> messages;


    public ChatAdapter(Context context, ArrayList<WMessage> messages) {
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
        TextView tvChat_Text;

        if(view==null){
            view = lInflater.inflate(R.layout.messagelist_item_adapter,viewGroup,false);
        }
        tvChat_Text = (TextView) view.findViewById(R.id.tvChat_Text);

        WMessage message = getItem(position);
        tvChat_Text.setText(message.getMessage_body());
        return view;
    }
}
