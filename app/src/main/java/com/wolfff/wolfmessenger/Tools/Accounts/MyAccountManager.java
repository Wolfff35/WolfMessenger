package com.wolfff.wolfmessenger.Tools.Accounts;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.annotation.TargetApi;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Build;
import android.util.Log;

import com.wolfff.wolfmessenger.MainServise;

import java.io.IOException;

/**
 * Created by wolff on 21.09.2016.
 */
public class MyAccountManager {
    final String account_type = "ua.wolfff";

    public Account createMessangerAccount(Context context, String userName, String pass, String nickName) {
        AccountManager am = (AccountManager) context.getSystemService(context.ACCOUNT_SERVICE);
        final Account account = new Account(userName, account_type);
        am.addAccountExplicitly(account, pass, null);
        am.setUserData(account, "NickName", nickName);
        return account;
    }

    public boolean isAccountExist(Context context) {
        Account[] acc = getMessagerAccounts(context);
        Log.e("isAccountExist","count of accounts - "+acc.length);
        if (acc.length != 0) {
            return true;
        } else {

            return false;
        }
    }

    public Account[] getMessagerAccounts(Context context) {
        AccountManager am = (AccountManager) context.getSystemService(context.ACCOUNT_SERVICE);
        Account[] accounts = am.getAccountsByType(account_type);
        Log.e("ACCOUNTS ", "======================================================================== ");
        for (Account acc : accounts) {
            Log.e("ACCOUNTS ", "" + acc.name + ";  " + acc.toString() + "" + am.getPassword(acc));
        }
        return accounts;
    }

    public String getAccountNickName(Context context, Account account) {
        AccountManager am = (AccountManager) context.getSystemService(context.ACCOUNT_SERVICE);
        return am.getUserData(account, "NickName");
    }

    public String getAccountPassword(Context context, Account account) {
        AccountManager am = (AccountManager) context.getSystemService(context.ACCOUNT_SERVICE);
        return am.getPassword(account);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP_MR1)

    public boolean deleteAccount(Context context, String nameAccount) {
        AccountManager am = (AccountManager) context.getSystemService(context.ACCOUNT_SERVICE);
        Account[] accounts = getMessagerAccounts(context);
        boolean isRem=false;
        for (Account acc : accounts) {
            if ((acc.name.equals(nameAccount)) && (acc.type.equals(account_type))) {
                //isRem = am.removeAccountExplicitly(acc);
                //am.removeAccount(acc,null,null);

                isRem=true;
            }
        }
        return isRem;
    }
}