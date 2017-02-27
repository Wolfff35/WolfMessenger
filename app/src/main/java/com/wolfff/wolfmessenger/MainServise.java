package com.wolfff.wolfmessenger;

import android.accounts.Account;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import com.wolfff.wolfmessenger.Obj.WMessage;
import com.wolfff.wolfmessenger.Tools.Accounts.MyAccountManager;
import com.wolfff.wolfmessenger.Tools.Json.JsonManager;
import com.wolfff.wolfmessenger.Tools.Protocol.ConnectionMessenger;
import com.wolfff.wolfmessenger.Tools.Protocol.ProtocolMessenger;
import com.wolfff.wolfmessenger.Tools.Sqlite.DBConnector;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Created by wolff on 26.09.2016.
 */

public class MainServise extends Service {
    private final IBinder mBinder = new MainBinder();
    Socket mainSocket = null;
    public PrintWriter mainPrintWriter;
    public BufferedReader mainBufferedReader;
    boolean mainIsAuthorized; //признак того что залогинился

    public Account mainAccount = null;
    ProtocolMessenger protocolMessenger;
    NotificationManager notificationManager;

    @Override
    public void onCreate() {
        super.onCreate();
        protocolMessenger = new ProtocolMessenger();
        notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        sendNotification(getApplicationContext(),"Пробуем подключиться к серверу...","ПРОБУЕМ ПОДКЛЮЧИТЬСЯ К СЕРВЕРУ...");
        Log.e("SERVICE", "== OnCreate");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e("SERVICE", "STOP SERVICE");
        mainSocket = null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.e("SERVICE", "MyService onStartCommand");
        //new AuthorizeUserOnServer_Task().execute();
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.e("SERVICE", "MyService onBind");
        return mBinder;
    }

    @Override
    public void onRebind(Intent intent) {
        super.onRebind(intent);
        Log.e("SERVICE", "MyService onRebind");
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.e("SERVICE", "MyService onUnbind");
        return super.onUnbind(intent);
    }

    //==================================================================================================
    class MainBinder extends Binder {
        MainServise getService() {
            return MainServise.this;
        }
    }


    //=================================================================================================
    public void logOffUser() {
        mainIsAuthorized = false;
        mainBufferedReader = null;
        mainPrintWriter = null;
        mainAccount = null;
        try {
            mainSocket.close();
        } catch (IOException e) {
            mainSocket = null;
        }
        this.stopSelf();
    }

    //=================================================================================================
//Источник: http://startandroid.ru/ru/uroki/vse-uroki-spiskom/164-urok-99-service-uvedomlenija-notifications.html
    void sendNotification(Context context, String textInStatusBar, String contentTitle) {
        // 1-я часть
        Notification notif = new Notification();
        notif.icon = R.mipmap.ic_launcher;
        // 3-я часть
        Intent intent = new Intent(this, MainActivity.class);
        //intent.putExtra(MainActivity.FILE_NAME, "somefile");
        PendingIntent pIntent = PendingIntent.getActivity(this, 0, intent, 0);

        // 2-я часть
        //notif.setLatestEventInfo(getApplicationContext(), "Notification's title", "Notification's text", pIntent);
        Notification.Builder builder = new Notification.Builder(context)
                .setContentIntent(pIntent)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle(contentTitle)
                .setContentText(textInStatusBar);
        notif = builder.build();
        // ставим флаг, чтобы уведомление пропало после нажатия
        notif.flags |= Notification.FLAG_AUTO_CANCEL;

        // отправляем
        notificationManager.notify(1, notif);
    }
    //==================================================================================================
    public boolean authorizeOnServer(){
        ConnectionMessenger connectionMessenger = new ConnectionMessenger();
        Context context = getApplicationContext();
        MyAccountManager myAccountManager = new MyAccountManager();
        boolean isAuth = false;

        mainAccount = myAccountManager.getMessagerAccounts(context)[0];
        String login    = mainAccount.name;
        String pass     = myAccountManager.getAccountPassword(context,mainAccount);

        while (mainSocket==null) {
            mainSocket = connectionMessenger.getConnectionSocket(GlobalConst.MAIN_HOST, GlobalConst.MAIN_PORT);
        }
        mainPrintWriter = connectionMessenger.getSocketOutputStream(mainSocket);
        mainBufferedReader = connectionMessenger.getSocketInputStream(mainSocket);
            ProtocolMessenger protocolMessenger= new ProtocolMessenger();
            JsonManager jsonManager = new JsonManager();
            JSONObject message = jsonManager.makeLoginMessage(login,pass,"");
            protocolMessenger.sendMessageToServer(mainPrintWriter,message);
            while (isAuth==false) {
                String inTypeMess="";
                String inLogin = "";
                String inAnswer="";
                JSONObject inData = protocolMessenger.getStringFromServer(mainBufferedReader);
                try {
                    inTypeMess = inData.getString("type");
                    inLogin = inData.getString("login");
                    inAnswer = inData.getString("answer");
                } catch (JSONException e) {
                }
                if ((inTypeMess.equalsIgnoreCase(ProtocolMessenger.TYPE_MESSAGE_LOGIN_USER)) && (inLogin.equalsIgnoreCase(login))) {
                    switch (inAnswer) {
                        case "0": {
                            isAuth = true;
                            break;
                        }
                        case "1": {
                            isAuth = false;
                            break;
                        }
                    }
                    break;
                }
            }
        return isAuth;


    }
    public void startListenServer(){
        while (true){
            JSONObject inStr = protocolMessenger.getStringFromServer(mainBufferedReader);
            protocolMessenger.handleStringFromServer(inStr);
        }
    }

    public class AuthorizeUserOnServer_Task extends AsyncTask<Void, Void,Void> {


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... strings) {
                if (authorizeOnServer()){
                   startListenServer();
                }
            return  null;
        }


    }
//==================================================================================================
 public void sendTextToServer(WMessage message){
     DBConnector dbConnector = new DBConnector(getApplicationContext());
     dbConnector.open();
     dbConnector.message_insert(message);
     dbConnector.close();
     //СФОРМИРОВАТЬ ДЕЙСОН
     JsonManager jsonManager = new JsonManager();
     JSONObject jMessage = jsonManager.makeTextMessage(message);
     //ОТПРАВИТЬ НА СЕРВЕР
     ProtocolMessenger protocolMessenger = new ProtocolMessenger();
    /// protocolMessenger.sendMessageToServer(mainPrintWriter,jMessage);
     Log.e("SEND_TASK",""+message);

 }
}
