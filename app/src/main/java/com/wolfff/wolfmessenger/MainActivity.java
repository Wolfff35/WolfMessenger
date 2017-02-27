package com.wolfff.wolfmessenger;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.IBinder;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.wolfff.wolfmessenger.Fragments.AccountsFragment;
import com.wolfff.wolfmessenger.Fragments.ChatFragment;
import com.wolfff.wolfmessenger.Fragments.ContactListFragment;
import com.wolfff.wolfmessenger.Fragments.LogInFragment;
import com.wolfff.wolfmessenger.Fragments.MessageListFragment;
import com.wolfff.wolfmessenger.Fragments.SettingsFragment;
import com.wolfff.wolfmessenger.Fragments.SysInfoFragment;
import com.wolfff.wolfmessenger.MainServise.MainBinder;
import com.wolfff.wolfmessenger.Obj.WMessage;
import com.wolfff.wolfmessenger.Tools.Accounts.MyAccountManager;
import com.wolfff.wolfmessenger.Tools.Json.JsonManager;
import com.wolfff.wolfmessenger.Tools.Protocol.ConnectionMessenger;
import com.wolfff.wolfmessenger.Tools.Protocol.ProtocolMessenger;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class MainActivity extends AppCompatActivity
        implements LogInFragment.onButtonRegisterPressEventListener_logInFragment,NavigationView.OnNavigationItemSelectedListener,
                    ContactListFragment.ContactListFragmentListener,
                    SettingsFragment.SettingsFragmentListener,MessageListFragment.MessageListFragmentListener,ChatFragment.onButtonChatSendPressEventListener_ChatFragment{

    ContactListFragment contactListFragment;
    MessageListFragment messageListFragment;
    ChatFragment chatFragment;
    SettingsFragment settingsFragment;
    SysInfoFragment sysInfoFragment;
    LogInFragment logInFragment;
    AccountsFragment accountsFragment;
    private String currentFragment;
    Toolbar toolbar;

    long mainActivPhoneNumber;
    long mainContactPhoneNumber;

    Intent intent;
    MainServise mainService;
    boolean bound = false;
    MyAccountManager myAccountManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        myAccountManager = new MyAccountManager();
        mainActivPhoneNumber =380673231646L;

        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
 //==================================================================================================
        contactListFragment=new ContactListFragment();
        messageListFragment = MessageListFragment.newInstance(mainActivPhoneNumber);
        settingsFragment = new SettingsFragment();
        sysInfoFragment = new SysInfoFragment();
        logInFragment = new LogInFragment();
        accountsFragment = new AccountsFragment();
//==================================================================================================
        // fragments
        //if (myAccountManager.isAccountExist(getApplicationContext())) {
            displayFragment(contactListFragment);
            currentFragment="CONTACTLIST";
            //fab.setVisibility(View.VISIBLE);
            toolbar.setVisibility(View.VISIBLE);

          //  Account account = myAccountManager.getMessagerAccounts(getApplicationContext())[0];
            //mainActivPhoneNumber = account.name.toString();
        //  textView_phoneNumber_Logo.setText(account.name.toString());
          //  textView_UserName_Logo.setText(myAccountManager.getAccountNickName(getApplicationContext(),account).toString());
            intent = new Intent(this, MainServise.class);
            startService(intent);
            bindService(intent, serviceConnection, BIND_AUTO_CREATE);
            bound=true;
            Log.e("MA_ON_CREATE"," account exist");
        //} else { //нет аккаунтов. регистрируемся
        //    Log.e("MA_ON_CREATE"," account NOT exist");
        //    displayFragment(logInFragment);
        //    fab.setVisibility(View.INVISIBLE);
        //    toolbar.setVisibility(View.INVISIBLE);
        //}
//=================================================================================================
//        myAccountManager.createMessangerAccount(getApplicationContext(),"1234567890","123","Wolf0");
//        myAccountManager.createMessangerAccount(getApplicationContext(),"2222222222","123","Wolf1");
//        myAccountManager.createMessangerAccount(getApplicationContext(),"3333333333","123","Wolf2");

/*       DBConnector dbConnector = new DBConnector(getApplicationContext());
        dbConnector.open();

        dbConnector.contact_insert(new WContact(123456789,"Wolfff 21",true,"",1,0));
        dbConnector.contact_insert(new WContact(222222222,"Wolfff 22",true,"",1,0));
        dbConnector.contact_insert(new WContact(333333333,"Wolfff 23",true,"",1,0));
        dbConnector.contact_insert(new WContact(444444444,"Wolfff 24",true,"",1,0));
        dbConnector.contact_insert(new WContact(555555555,"Wolfff 25",true,"",1,0));

        for(int i=1;i<5000;i++) {
            dbConnector.message_insert(new WMessage(1, 380673231646L, 222222222L, "message "+i+"  - "+(5000-i), "", 1000000+i, "TEXT", 0, 0, 0));
            Log.e("ADD","i = "+i);
        }
        dbConnector.message_insert(new WMessage(1, 380673231646L,333333333L, "message 2 hello my dear friend.I live in London. its raining today", "", 1000010,"TEXT", 0, 0, 0));
        //dbConnector.message_insert(new WMessage(1, 380673231646L,222222222L, "message 3 ", "", 1000020,"TEXT", 0, 0, 0));
        //dbConnector.message_insert(new WMessage(1, 380673231646L,222222222L, "message 4 ", "", 1000030,"TEXT", 0, 0, 0));
        dbConnector.message_insert(new WMessage(1, 380673231646L,444444444L, "message 5 ", "", 1000040,"TEXT", 0, 0, 0));
        dbConnector.message_insert(new WMessage(1, 380673231646L,444444444L, "message 6 ", "", 1000050,"TEXT", 0, 0, 0));
        dbConnector.message_insert(new WMessage(1, 380673231646L,333333333L, "message 7 ", "", 1000060,"TEXT", 0, 0, 0));

 //       dbConnector.close();
*/
//        JsonManager j = new JsonManager();
//        JSONObject dd = j.makeRegistrationMessage("1234567890","qwerty","WOLF");
//        Log.e("JSON",""+dd.toString());
    }

    @Override
    protected void onStart() {
        super.onStart();
    }
        @Override
    protected void onDestroy() {
        super.onDestroy();
        if (!bound) return;
        unbindService(serviceConnection);
        bound = false;
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.nav_contacts: {
                displayFragment(contactListFragment);
                currentFragment="CONTACTLIST";
                break;
            }
            case R.id.nav_settings:{
                displayFragment(settingsFragment);
                currentFragment="SETTINGS";
                break;
            }
            case R.id.nav_accounts:{
                displayFragment(accountsFragment);
                currentFragment="ACCOUNTS";
                break;
            }
            case  R.id.nav_exit:{
                stopService(intent);
                this.finish();
                break;
            }
            case R.id.nav_messages1:{
                displayFragment(messageListFragment);
                currentFragment="MESSAGELIST";
                break;
            }
            case R.id.nav_sysInfo:{
                //fab.setVisibility(View.VISIBLE);
                displayFragment(sysInfoFragment);
                currentFragment="SYSINFO";
                break;
            }
            case R.id.nav_LogIn:{
                displayFragment(logInFragment);
                currentFragment="LOGIN";
                break;
            }
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void displayFragment(Fragment fragment){
        FragmentTransaction fragmentTransaction;
        fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.container_main,fragment);
        fragmentTransaction.commit();

    }

    @Override
    public void onContactSelected(long phone_number) {
        mainContactPhoneNumber = phone_number;
        chatFragment = ChatFragment.newInstance(mainActivPhoneNumber,phone_number);
        displayFragment(chatFragment);
        currentFragment="CHAT";
    }

    @Override
    public void onSettingSelected(long id) {

    }

    @Override
    public void onMessageListSelected(long phone_number) {
        mainContactPhoneNumber = phone_number;
        chatFragment = ChatFragment.newInstance(mainActivPhoneNumber,phone_number);
        displayFragment(chatFragment);
        currentFragment="CHAT";
    }
    //==================================================================================================
    //обработка событий фрагментов  - отправка сообщения
    @Override
    public void buttonChatSendPressEvent_ChatFragment(WMessage message) {
        //WMessage outMessage = new WMessage(0,mainActivPhoneNumber,contactPhoneNumber,message,"",System.currentTimeMillis(),TYPE_MESSAGE_SEND_TEXT,1,0,0);
        SendNewTextMessageTask job = new SendNewTextMessageTask();
        job.execute(message);

    }

    //обработка событий фрагментов  - регистрация
    @Override
    public void buttonRegisterPressEvent_logInFragment(String login, String pass,String nickName) {
        RegisterUserTask registerUserTask = new RegisterUserTask();
        registerUserTask.execute(GlobalConst.MAIN_HOST,Integer.toString(GlobalConst.MAIN_PORT),login,pass,nickName);
    }
//==================================================================================================
// ПОДКЛЮЧЕНИЕ К СЕРВИСУ
    private ServiceConnection serviceConnection = new ServiceConnection() {

         @Override
        public void onServiceConnected(ComponentName className,
                                       IBinder service) {
            MainBinder binder = (MainBinder) service;
            mainService = binder.getService();
            bound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName arg0) {
            bound = false;
        }
    };


    //==================================================================================================
//==================================================================================================
// РЕГИСТРАЦИЯ
    public class RegisterUserTask extends AsyncTask<String, Void,Boolean> {


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Boolean doInBackground(String... strings) {
            boolean isReg = false;
            Socket mSocket=null;
            ConnectionMessenger connectionMessenger = new ConnectionMessenger();

            String mHost    = strings[0];
            int mPort       = Integer.parseInt(strings[1]);
            String login    = strings[2];
            String pass     = strings[3];
            String nickName = strings[4];

            while (mSocket==null) {
                mSocket = connectionMessenger.getConnectionSocket(mHost, mPort);
            }
            PrintWriter mPrintWriter = connectionMessenger.getSocketOutputStream(mSocket);
            BufferedReader mBuffReader = connectionMessenger.getSocketInputStream(mSocket);
                ProtocolMessenger protocolMessenger= new ProtocolMessenger();
                JsonManager jsonManager = new JsonManager();
                JSONObject message = jsonManager.makeRegistrationMessage(login,pass,nickName);
                protocolMessenger.sendMessageToServer(mPrintWriter,message);
                while (isReg==false) {
                    JSONObject inData = protocolMessenger.getStringFromServer(mBuffReader);
                    String inTypeMessage="";
                    String inLogin="";
                    String inAnswer="";

                    try {
                        inTypeMessage = inData.getString("type");
                        inLogin = inData.getString("login");
                        inAnswer = inData.getString("answer");
                    } catch (JSONException e) {
                    }
                    if ((inTypeMessage.equalsIgnoreCase(ProtocolMessenger.TYPE_MESSAGE_REGISTER_USER)) && (inLogin.equalsIgnoreCase(login))) {
                        switch (inAnswer) {
                            case "0": {
                                MyAccountManager myAccountManager = new MyAccountManager();
                                myAccountManager.createMessangerAccount(getApplicationContext(),login,pass,nickName);
                                isReg = true;
                                break;
                            }
                            case "1": {
                                isReg = false;
                                break;
                            }
                        }
                        break;
                    }
                }
            try {
                mSocket.close();
                mPrintWriter.close();
                mBuffReader.close();
            } catch (IOException e) {
            }
            return isReg;

        }

        @Override
        protected void onPostExecute(Boolean result) {
            super.onPostExecute(result);
            if(result){
                displayFragment(contactListFragment);
                toolbar.setVisibility(View.VISIBLE);
            }
        }
    }
//==================================================================================================
//ОТПРАВКА ТЕКСТА
    public class SendNewTextMessageTask extends AsyncTask<Object, Void,Long> {


    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected Long doInBackground(Object... strings) {
        WMessage message = (WMessage)strings[0];
        mainService.sendTextToServer(message);
        return message.getContactPhoneNumber();
    }

    @Override
    protected void onPostExecute(Long contactPhone) {
        super.onPostExecute(contactPhone);
        //ОБНОВИТЬ СПИСОК СООБЩЕНИЙ В ЧАТЕ
        // сообщение добавляем в лист в самом фрагменте!!!

        //ОБНОВИТЬ СПИСОК ЧАТОВ
        // список чатов обновлять здесь НЕ НУЖНО, т.к. его не видно, сообщение отправляется из чатФрагмента!!!
        }
    }

}

    //==============================================================================================

