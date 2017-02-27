package com.wolfff.wolfmessenger.Tools.Sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.wolfff.wolfmessenger.Obj.WContact;
import com.wolfff.wolfmessenger.Obj.WMessage;

import static com.wolfff.wolfmessenger.Tools.Sqlite.DBHelper.KEY_CONTACT_PHONE;
import static com.wolfff.wolfmessenger.Tools.Sqlite.DBHelper.KEY_ID;
import static com.wolfff.wolfmessenger.Tools.Sqlite.DBHelper.KEY_NICK_NAME;
import static com.wolfff.wolfmessenger.Tools.Sqlite.DBHelper.KEY_PHONE_NUMBER;
import static com.wolfff.wolfmessenger.Tools.Sqlite.DBHelper.TABLE_CONTACTS;
import static com.wolfff.wolfmessenger.Tools.Sqlite.DBHelper.TABLE_MESSAGES;

/**
 * Created by wolff on 25.09.2016.
 */

public class DBConnector {
    private SQLiteDatabase database; // Для взаимодействия с базой данных
    private DBHelper databaseHelper;
    public DBConnector(Context context){
        databaseHelper = new DBHelper(context);
    }
    public void open() throws SQLException {
        database = databaseHelper.getWritableDatabase();
    }
    public void close(){
        if (database != null)
            database.close();
    }
//==================================================================================================
    public Cursor chats_getAll_cursor(long activNumber){
        String[] columns = new String[] {KEY_ID,KEY_PHONE_NUMBER,KEY_CONTACT_PHONE,"_message_body","_idServer","_time","_typeMessage","_inOut","_statusOut","_statusIn"};
        String selection = ""+KEY_PHONE_NUMBER+"=?";
        String[] selectionArgs = new String[]{Long.toString(activNumber)};
        String groupBy = KEY_CONTACT_PHONE;
        String having = "max(_time)";
        String orderBy = "_time";
        Cursor curr = database.query(TABLE_MESSAGES, columns,selection, selectionArgs, groupBy, having,orderBy);
        return curr;

    }
    public Cursor getNewIncomingMessages_cursor(){
        String[] columns = new String[] {KEY_ID,KEY_PHONE_NUMBER,KEY_CONTACT_PHONE,"_message_body","_idServer","_time","_typeMessage","_inOut","_statusOut","_statusIn"};
        String selection = "_inOut = ? AND _statusIn = ?";
        String[] selectionArgs = new String[]{"0","0"};
        String groupBy = null;
        String having = null;
        String orderBy = "_time DESC";
        Cursor curr = database.query(TABLE_MESSAGES, columns,selection, selectionArgs, groupBy, having,orderBy);
        return curr;

    }
    //==================================================================================================
    public void message_update(){
        ContentValues chat = new ContentValues();
        String whereClause = "_id = ?";
        String[] whereArgs =new String[]{KEY_ID};
        database.update(TABLE_MESSAGES,chat,whereClause,whereArgs);
    }
    public Cursor messages_getByPhone_cursor(long activNumber,long contactNumber){
        String[] columns = new String[] {KEY_ID,KEY_PHONE_NUMBER,KEY_CONTACT_PHONE,"_message_body","_idServer","_time","_typeMessage","_inOut","_statusOut","_statusIn"};
        String selection = ""+KEY_PHONE_NUMBER+"=? AND "+KEY_CONTACT_PHONE+" = ?";
        String[] selectionArgs = new String[]{Long.toString(activNumber),Long.toString(contactNumber)};
        String groupBy = null;
        String having = null;
        String orderBy = "_time";
        Cursor curr = database.query(TABLE_MESSAGES, columns,selection, selectionArgs, groupBy, having,orderBy);
        return curr;
    }

    public long message_insert(WMessage message){
        ContentValues newMessage = new ContentValues();
        newMessage.put(KEY_PHONE_NUMBER, message.getPhoneNumber());
        newMessage.put(KEY_CONTACT_PHONE, message.getContactPhoneNumber());
        newMessage.put("_message_body", message.getMessage_body());
        newMessage.put("_idServer", message.getIdServer());
        newMessage.put("_time", message.getTime());
        newMessage.put("_typeMessage", message.getTypeMessage());
        newMessage.put("_inOut", message.isInOut());
        newMessage.put("_statusOut", message.getStatusOut());
        newMessage.put("_statusIn", message.getStatusIn());

        open(); // Открытие базы данных
        long rowID = database.insert(TABLE_MESSAGES, null, newMessage);
        close(); // Закрытие базы данных
        return rowID;
    }

    //----------------------------------------------------------------------------------------------
    public long contact_insert(WContact contact){
        ContentValues newContact = new ContentValues();
        newContact.put(KEY_PHONE_NUMBER, contact.getPhone_number());
        newContact.put(KEY_NICK_NAME, contact.getNickName());
        if(contact.isHasMessenger()) {
            newContact.put("_hasMessenger", 1);
        }else {
            newContact.put("_hasMessenger", 0);
        }
        newContact.put("_logoFilePath", contact.getLogoFilePath());
        newContact.put("_logoFileTime", contact.getLogoFileTime());

        open(); // Открытие базы данных
        long rowID = database.insert(TABLE_CONTACTS, null, newContact);
        close(); // Закрытие базы данных
        return rowID;
    }
    public void contact_delete_cursor(long phone_number){
        open();
        database.delete(TABLE_CONTACTS,""+KEY_PHONE_NUMBER+"="+phone_number,null);
        close();
    }

    public Cursor contact_getAll_cursor(){
        String[] columns = new String[] {KEY_PHONE_NUMBER,KEY_NICK_NAME,"_hasMessenger","_logoFilePath","_id","_logoFileTime"};
        String selection = null;
        String[] selectionArgs = null;
        String groupBy = null;
        String having = null;
        String orderBy = KEY_PHONE_NUMBER;
        Cursor curr = database.query(TABLE_CONTACTS, columns,selection, selectionArgs, groupBy, having,orderBy);
        return curr;
    }
    public Cursor contact_getByNumber_cursor(long phone_number){
        String[] columns = new String[] {KEY_PHONE_NUMBER,KEY_NICK_NAME,"_hasMessenger","_logoFilePath","_id","_logoFileTime"};
        String selection = ""+KEY_PHONE_NUMBER+" = ?";
        String[] selectionArgs = new String[]{""+phone_number};
        String groupBy = null;
        String having = null;
        String orderBy = KEY_PHONE_NUMBER;
        return database.query(TABLE_CONTACTS, columns,selection,selectionArgs,groupBy, having, orderBy);
    }

}
