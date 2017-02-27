package com.wolfff.wolfmessenger.Tools.LocalData;

import android.database.Cursor;

import com.wolfff.wolfmessenger.Obj.WContact;
import com.wolfff.wolfmessenger.Obj.WMessage;

import java.util.ArrayList;

import static com.wolfff.wolfmessenger.Tools.Sqlite.DBHelper.KEY_ID;
import static com.wolfff.wolfmessenger.Tools.Sqlite.DBHelper.KEY_NICK_NAME;
import static com.wolfff.wolfmessenger.Tools.Sqlite.DBHelper.KEY_PHONE_NUMBER;

/**
 * Created by wolff on 26.09.2016.
 */

public class DataOperations {

    public ArrayList<WContact> contact_getAll_ObjectArray(Cursor cursor){
        ArrayList<WContact> data = new ArrayList<>(cursor.getCount());
        cursor.moveToFirst();
        while (cursor.isAfterLast() == false) {
            long phoneNumber        = cursor.getLong(cursor.getColumnIndex(KEY_PHONE_NUMBER));
            String nickName         = cursor.getString(cursor.getColumnIndex(KEY_NICK_NAME));
            boolean hasMessenger    = (cursor.getInt(cursor.getColumnIndex("_hasMessenger"))==1);
            String logoFilePath     = cursor.getString(cursor.getColumnIndex("_logoFilePath"));
            long id                 = cursor.getLong(cursor.getColumnIndex(KEY_ID));
            long logoFileTime       = cursor.getLong(cursor.getColumnIndex("_logoFileTime"));

             data.add(new WContact(phoneNumber,nickName,hasMessenger,logoFilePath,id,logoFileTime));
            cursor.moveToNext();
        }
        return data;
    }
    public ArrayList<WMessage> messages_getAll_ObjectArray(Cursor cursor){
        ArrayList<WMessage> data = new ArrayList<>(cursor.getCount());
        cursor.moveToFirst();
        while (cursor.isAfterLast() == false) {
            long id = cursor.getLong(cursor.getColumnIndex(KEY_ID));
            long phoneNumber            = cursor.getLong(cursor.getColumnIndex(KEY_PHONE_NUMBER));
            long contactPhoneNumber     = cursor.getLong(cursor.getColumnIndex("_contactPhoneNumber"));
            String message_body         = cursor.getString(cursor.getColumnIndex("_message_body"));
            String idServer               = cursor.getString(cursor.getColumnIndex("_idServer"));
            long time                   = cursor.getLong(cursor.getColumnIndex("_time"));
            String typeMessage          = cursor.getString(cursor.getColumnIndex("_typeMessage"));
            int inOut                   = cursor.getInt(cursor.getColumnIndex("_inOut"));
            int statusIn                = cursor.getInt(cursor.getColumnIndex("_statusIn"));
            int statusOut                = cursor.getInt(cursor.getColumnIndex("_statusOut"));
            data.add(new WMessage(id,phoneNumber,contactPhoneNumber,message_body,idServer,time,
            typeMessage,inOut,statusIn,statusOut));
            cursor.moveToNext();
        }
        return data;
    }

}
