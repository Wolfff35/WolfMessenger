package com.wolfff.wolfmessenger.Tools.Json;

import com.wolfff.wolfmessenger.Obj.WMessage;

import org.json.JSONException;
import org.json.JSONObject;

import static com.wolfff.wolfmessenger.Tools.Protocol.ProtocolMessenger.TYPE_MESSAGE_LOGIN_USER;
import static com.wolfff.wolfmessenger.Tools.Protocol.ProtocolMessenger.TYPE_MESSAGE_REGISTER_USER;
import static com.wolfff.wolfmessenger.Tools.Protocol.ProtocolMessenger.TYPE_MESSAGE_SEND_TEXT;

/**
 * Created by wolff on 19.10.2016.
 */

public class JsonManager {

    public JSONObject makeRegistrationMessage(String login, String pass, String nick) {
        JSONObject mess = new JSONObject();
        try {
            mess.put("type", TYPE_MESSAGE_REGISTER_USER);
            mess.put("login", login);
            mess.put("password", pass);
            mess.put("name", nick);
        } catch (JSONException e) {
        }

        return mess;
    }
    public JSONObject makeLoginMessage(String login, String pass,String idDevice) {
        JSONObject mess = new JSONObject();
        try {
            mess.put("type", TYPE_MESSAGE_LOGIN_USER);
            mess.put("login", login);
            mess.put("password", pass);
            mess.put("idDevice", idDevice);
        } catch (JSONException e) {
        }

        return mess;
    }
    public JSONObject makeTextMessage(WMessage message) {
        JSONObject mess = new JSONObject();
        try {
            mess.put("type", TYPE_MESSAGE_SEND_TEXT);
            mess.put("login", message.getPhoneNumber());
            mess.put("contact", message.getContactPhoneNumber());
            mess.put("text", message.getMessage_body());
            mess.put("time", message.getTime());
        } catch (JSONException e) {
        }
        return mess;
    }


}