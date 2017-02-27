package com.wolfff.wolfmessenger.Fragments;


import android.app.Activity;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.wolfff.wolfmessenger.R;

/**
 * Created by wolff on 21.09.2016.
 */
public class LogInFragment extends Fragment {
    Button btn_Register;
    EditText editText_phoneNumber;
    EditText editText_password;
    EditText editText_nickName;

    public interface onButtonRegisterPressEventListener_logInFragment {
        void buttonRegisterPressEvent_logInFragment(String login, String pass,String nickName);
    }
    onButtonRegisterPressEventListener_logInFragment buttonRegisterPressEventListener;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            buttonRegisterPressEventListener = (onButtonRegisterPressEventListener_logInFragment) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must implement onSomeEventListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        buttonRegisterPressEventListener = null;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }
    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //return inflater.inflate(R.layout.fragment_login, null);
        View v = inflater.inflate(R.layout.fragment_login, null);

        btn_Register = (Button) v.findViewById(R.id.btn_Register);
        editText_phoneNumber = (EditText) v.findViewById(R.id.editText_phoneNumber);
        editText_password = (EditText) v.findViewById(R.id.editText_password);
        editText_nickName = (EditText) v.findViewById(R.id.editText_nickName);
        btn_Register.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Log.e("LOGIN_F", "Button click in LOGIN FRAGMENT");
                if((editText_phoneNumber.getText().length()==0)||(editText_password.getText().length()==0)) {
                    Toast.makeText(getActivity().getApplicationContext(),"Не заполнены регистрационные данные!",Toast.LENGTH_LONG);
                }else {
                    buttonRegisterPressEventListener.buttonRegisterPressEvent_logInFragment(editText_phoneNumber.getText().toString(),editText_password.getText().toString(),editText_nickName.getText().toString());
               }

            }
        });

        return v;
    }
}
