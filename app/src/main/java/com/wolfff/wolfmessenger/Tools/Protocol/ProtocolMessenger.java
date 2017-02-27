package com.wolfff.wolfmessenger.Tools.Protocol;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by wolff on 26.09.2016.
 */

public class ProtocolMessenger {
    public final static String SEPARATOR = ";";
    public final static String TYPE_MESSAGE_REGISTER_USER = "01";
    public final static String TYPE_MESSAGE_DELETE_USER = "02";
    public final static String TYPE_MESSAGE_LOGIN_USER = "03";
    public final static String TYPE_MESSAGE_LOGOFF_USER = "04";
    public final static String TYPE_MESSAGE_UPDATE_USERLIST = "05";

    public final static String TYPE_MESSAGE_SEND_TEXT = "06";
    public final static String TYPE_MESSAGE_RECEIVE_TEXT = "16";

//    public final static String TYPE_MESSAGE_SEND_PHOTO = "07";
//    public final static String TYPE_MESSAGE_SEND_FILE = "08";



    //===================================================================================================

    public void sendMessageToServer(PrintWriter printWriter,JSONObject message) {
        printWriter.println(message.toString());
        printWriter.flush();
        Log.e("SENDTOSERVER","ОТПРАВЛЕНО НА СЕРВЕР");
        }

    public JSONObject getStringFromServer(BufferedReader bufferedReader){
        JSONObject data= null;
        try {
            String dataStr = bufferedReader.readLine();
            data = new JSONObject(dataStr);
            Log.e("getStringFromServer",""+data.toString());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return data;
    }

   public void handleStringFromServer(JSONObject serverData){
       String typeMessage=null;
       try {
           typeMessage = serverData.getString("type");
       } catch (JSONException e) {
       }
       switch (typeMessage){
           case TYPE_MESSAGE_LOGOFF_USER:{

               Log.e("TYPE_MESSAGE_LOGOFF",""+TYPE_MESSAGE_LOGOFF_USER);
                break;
           }
           case TYPE_MESSAGE_DELETE_USER:{

                Log.e("DELETE_USER",""+TYPE_MESSAGE_DELETE_USER);
               break;
           }
           case TYPE_MESSAGE_UPDATE_USERLIST:{

               Log.e("UPDATE_USERLIST",""+TYPE_MESSAGE_UPDATE_USERLIST);
               break;
           }
           case TYPE_MESSAGE_SEND_TEXT:{
                //new fromServer_sendText_Task().execute(sString[1],sString[2]);
               Log.e("TYPE_MESSAGE_SEND_TEXT",""+TYPE_MESSAGE_SEND_TEXT);
               break;
           }
           case TYPE_MESSAGE_RECEIVE_TEXT:{

               Log.e("RECEIVE_TEXT",""+TYPE_MESSAGE_RECEIVE_TEXT);
               break;
           }
       }
   }
}
