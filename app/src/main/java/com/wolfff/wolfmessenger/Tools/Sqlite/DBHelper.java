package com.wolfff.wolfmessenger.Tools.Sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by wolff on 25.09.2016.
 */

public class DBHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "messengerDB.db";
    public static final int DATABASE_VERSION = 2;
    public static final String KEY_ID = "_id";
//    public static final String KEY_NAME = "_name";
    public static final String KEY_PHONE_NUMBER = "_phoneNumber";
    public static final String KEY_NICK_NAME = "_nickName";
    public static final String KEY_CONTACT_PHONE = "_contactPhoneNumber";

    public static final String TABLE_CONTACTS = "contacts";
    public static final String TABLE_MESSAGES = "messages";


    private static final String CREATE_CONTACTS_TABLE = "CREATE TABLE "+TABLE_CONTACTS+" ("+KEY_ID+" INTEGER PRIMARY KEY, "
                                                                                            +KEY_PHONE_NUMBER+" INTEGER, "
                                                                                            +KEY_NICK_NAME+" TEXT, "
                                                                                            +"_hasMessenger INTEGER,"
                                                                                            +"_logoFileTime INTEGER,"
                                                                                            +"_logoFilePath TEXT)";
    // _id
    // _phoneNumber - номер мой
    // _contactPhoneNumber - номер контакта
    // _message_body - текст или ссылка на локальный файл
    // _idServer - серверный ИД, для синхронизации, удаления и т.п.
    // _time - дата-время сообщения
    // _typeMessage - text,file,url
    // _inOut - in - 0,out - 1
    // _satusOut 0 - неотправленное, 1 - отправлено, 2 - доставлено, 3 - прочитано,
    // _statusIn 0 - получено, 1 - прочитано

    private static final String CREATE_MESSAGES_TABLE = "CREATE TABLE "+TABLE_MESSAGES+" ("+KEY_ID+" INTEGER PRIMARY KEY, "
                                                                                            +KEY_PHONE_NUMBER+" INTEGER, "
                                                                                            +KEY_CONTACT_PHONE+" INTEGER,"
                                                                                            +" _message_body TEXT, "
                                                                                            +"_idServer TEXT,"
                                                                                            +"_time INTEGER,"
                                                                                            +"_typeMessage TEXT,"
                                                                                            +"_inOut INTEGER,"
                                                                                            +"_statusOut INTEGER,"
                                                                                            +"_statusIn INTEGER)";


    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_CONTACTS_TABLE);
        db.execSQL(CREATE_MESSAGES_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

    }
}
