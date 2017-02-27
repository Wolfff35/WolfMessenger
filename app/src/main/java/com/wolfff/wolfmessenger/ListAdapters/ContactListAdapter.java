package com.wolfff.wolfmessenger.ListAdapters;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.wolfff.wolfmessenger.Obj.WContact;
import com.wolfff.wolfmessenger.R;

import java.util.ArrayList;

/**
 * Created by wolff on 12.10.2016.
 */

public class ContactListAdapter extends BaseAdapter {
    private Context context;
    private LayoutInflater lInflater;
    private ArrayList<WContact> contacts;


    public ContactListAdapter(Context context, ArrayList<WContact> contacts) {
        this.context = context;
        this.contacts = contacts;
        this.lInflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public int getCount() {
        return contacts.size();
    }

    @Override
    public WContact getItem(int i) {
        return contacts.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View cView, ViewGroup viewGroup) {
        View view = cView;
        TextView tvNickName;
        TextView tvPhoneNumber;
        ImageView ivLogo;

        if(view==null){
            view = lInflater.inflate(R.layout.contact_item_adapter,viewGroup,false);
        }
        tvNickName = (TextView) view.findViewById(R.id.tvNickName);
        tvPhoneNumber = (TextView) view.findViewById(R.id.tvPhoneNumber);
        ivLogo = (ImageView) view.findViewById(R.id.ivLogo);

        WContact contact = getItem(position);
        //if(!contact.isHasMessenger()) {
        //    tvNickName.setTextColor(R.color.colorPrimary);
        //}
        tvNickName.setText(contact.getNickName());
        tvPhoneNumber.setText(String.valueOf(contact.getPhone_number()));
        String str_logo = contact.getLogoFilePath();
        if((contact.getLogoFilePath()==null)){
            ivLogo.setImageResource(R.drawable.ic_wolf_messenger_icon);
            //Log.e("PICTURE 1-","/"+contact.getLogoFilePath()+"/");
        }else if(str_logo.length()==0){
            ivLogo.setImageResource(R.drawable.ic_wolf_messenger_icon);
            //Log.e("PICTURE 2-","/"+str_logo+"/");
        }else {
            ivLogo.setImageDrawable(Drawable.createFromPath(str_logo));
            //Log.e("PICTURE 3-","/"+str_logo+"/");
        }
        return view;
    }
}
