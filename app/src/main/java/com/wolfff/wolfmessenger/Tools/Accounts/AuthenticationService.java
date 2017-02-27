package com.wolfff.wolfmessenger.Tools.Accounts;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import java.net.Authenticator;


/**
 * Created by wolff on 21.09.2016.
 */
public class AuthenticationService extends Service {
    private Authenticator mAuth;

    @Override
    public void onCreate() {
        //mAuth = new Authenticator(this);
    }

    @Override
    public IBinder onBind(Intent intent) {
        //return mAuth.getIBinder();
    return null;
    }

/*    public Bundle getAccountRemovalAllowed(
            AccountAuthenticatorResponse response, Account account)
            throws NetworkErrorException {
        final Bundle result = new Bundle();

        result.putBoolean(AccountManager.KEY_BOOLEAN_RESULT, true);

        return result;
    }
    */
}
