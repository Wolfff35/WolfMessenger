package com.wolfff.wolfmessenger.Tools;

import android.content.Context;
import android.database.Cursor;
import android.provider.ContactsContract;
import android.telephony.TelephonyManager;
import android.util.Log;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by wolff on 15.09.2016.
 */
public class DeviceManager {
    public HashMap<Long, String> getPhoneContacts(Context context) {
        HashMap<Long, String> stringMap = new HashMap<>();

        Cursor cursor = context.getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                //  new String[] {ContactsContract.CommonDataKinds.Phone._ID, ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME, ContactsContract.CommonDataKinds.Phone.NUMBER},
                new String[]{ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME_ALTERNATIVE, ContactsContract.CommonDataKinds.Phone.NUMBER},
                "" + ContactsContract.CommonDataKinds.Phone.HAS_PHONE_NUMBER + "=1", null, null, null);
        //startManagingCursor(cursor);

        if (cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                Log.e("GET"," - "+cursor.getString(1)+";   "+cursor.getString(0));
                Long num = formatPhoneNumber(cursor.getString(1));
                if(num!=0) {
                    stringMap.put(num, cursor.getString(0));
                }
            }
        }
        cursor.close();
        return stringMap;
    }

    public String[] phoneContactsToArray(Map<String, String> contacts) {
        return (String[]) contacts.keySet().toArray();
    }

    public String getSim1Number(Context context) {
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(context.TELEPHONY_SERVICE);
        return telephonyManager.getLine1Number();
    }

    public String getDeviceId(Context context) {
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(context.TELEPHONY_SERVICE);
        return telephonyManager.getDeviceId();
    }

    public String getSimSerialNumber(Context context) {
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(context.TELEPHONY_SERVICE);
        return telephonyManager.getSimSerialNumber();
    }
    public long formatPhoneNumber(String phoneNumber){
        String p="";
        for (int i=0;i<phoneNumber.length();i++){
            if((phoneNumber.charAt(i)=='0')||(phoneNumber.charAt(i)=='1')
                    ||(phoneNumber.charAt(i)=='2')||(phoneNumber.charAt(i)=='3')
                    ||(phoneNumber.charAt(i)=='4')||(phoneNumber.charAt(i)=='5')
                    ||(phoneNumber.charAt(i)=='6')||(phoneNumber.charAt(i)=='7')
                    ||(phoneNumber.charAt(i)=='8')||(phoneNumber.charAt(i)=='9')){
                p=p+phoneNumber.charAt(i);
            }
        }
        if (p.length()>1){
            return Long.parseLong(p);
        }else {
            return 0;
        }
    }
    //================================================
//    public void writePhoneContactsToMessenger(Context context){
//       HashMap<Long,String> contacts = getPhoneContacts(context);
//        LocalDB db = new LocalDB(context);
    //    Cursor existContact = db.contact_get("9999999999");
//        Cursor existContact = db.contact_getAll();
//        existContact.moveToFirst();
//        while (existContact.isAfterLast() == false) {
//            Log.e("WRITE EXIST1", " count = " + existContact.getCount());
//            Log.e("WRITE EXIST2", " count = " + existContact.getCount() + "; # = " + existContact.getLong(0) + ";  name = " + existContact.getString(1));
//            existContact.moveToNext();
//        }
//        existContact.close();
//        for(Map.Entry entry:contacts.entrySet()){
//            long ll = (long)entry.getKey();
//            Log.e("MAPPPPP",""+entry.getKey()+";    "+entry.getValue());
//            existContact.moveToFirst();
//            Log.e("WRITE EXIST1"," count = "+existContact.getCount());
//            Log.e("WRITE EXIST2"," count = "+existContact.getCount()+"; # = "+existContact.getLong(0)+";  name = "+existContact.getString(1));
           // if (existContact.getCount()!=0) {
//                db.contact_insert(new Contact((long) entry.getKey(), entry.getValue().toString(), ""));
           // }
           // existContact.close();
//        }
//    }
}
