package com.wolfff.wolfmessenger.Fragments;

import android.accounts.Account;
import android.app.ListFragment;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Gravity;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.wolfff.wolfmessenger.R;
import com.wolfff.wolfmessenger.Tools.Accounts.MyAccountManager;

import java.util.ArrayList;

/**
 * Created by wolff on 11.10.2016.
 */

public class AccountsFragment extends ListFragment {
MyAccountManager myAccountManager;
    Account[] accounts;
    ArrayAdapter<String> adapter;
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Context context = getActivity().getApplicationContext();
        myAccountManager = new MyAccountManager();
        ArrayList<String> data = new ArrayList<>();
        if(myAccountManager.isAccountExist(context)){
            accounts = myAccountManager.getMessagerAccounts(context);
            for (int i = 0; i < accounts.length; i++) {
                data.add(accounts[i].name);
            }
        }else {
            data.add("NO ACCOUNTS");
         //   data.add("NO ACCOUNTS 1");
         //   data.add("NO ACCOUNTS 2");
         //   data.add("NO ACCOUNTS 3");

            //Log.e("ACC","NO ACCOUNTS");
        }
        adapter = new ArrayAdapter<>(getActivity(),android.R.layout.simple_list_item_1, data);
        setListAdapter(adapter);
        registerForContextMenu(getListView());
    }
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenu.ContextMenuInfo menuInfo)
    {
        super.onCreateContextMenu(menu, v, menuInfo);

        MenuInflater inflater = getActivity().getMenuInflater();
        inflater.inflate(R.menu.context_menu, menu);
    }
    @Override
    public boolean onContextItemSelected(MenuItem item)
    {
        CharSequence message;
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo)item.getMenuInfo();
        Log.e("onContextItemSelected",""+info.id+"; "+info.position+"; "+info.toString());
        switch (item.getItemId())
        {
            case R.id.context_open:
                message = "Открываем";
                break;
            case R.id.context_delete:
                message = "Удаляем";
                //Log.e("RRRR",""+new  AuthenticationService().getAccountRemovalAllowed());
                //boolean isDel = myAccountManager.deleteAccount(getActivity().getApplicationContext(),accounts[(int) info.id].name);
                //Log.e("DELETE_ACC",""+isDel);
                //adapter.notifyDataSetChanged();
                break;

            default:
                return super.onContextItemSelected(item);
        }
        Toast toast = Toast.makeText(getActivity().getApplicationContext(), message, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
        return true;
    }
    /*   @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenu.ContextMenuInfo menuInfo)
    {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.add(Menu.NONE, 1, Menu.NONE, "Просмотр");
        menu.add(Menu.NONE, 2, Menu.NONE, "Удалить");
    }
    @Override
    public boolean onContextItemSelected(MenuItem item)
    {
        CharSequence message;
        switch (item.getItemId())
        {
            case 1:
                message = "Выбран пункт Просмотр";
                break;
            case 2:
                message = "Выбран пункт Удалить";
                break;
            default:
                return super.onContextItemSelected(item);
        }
        Toast toast = Toast.makeText(getActivity().getApplicationContext(), message, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
        return true;
    }
    */
}
