package com.wolfff.wolfmessenger.Fragments;

import android.app.ListFragment;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.wolfff.wolfmessenger.R;


public class SettingsFragment extends ListFragment{
    private SettingsFragmentListener listener;

    String data[] = new String[] { "Settings one", "Settings two", "Settings three", "Settings four" };

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_list_item_1, data);
        setListAdapter(adapter);
    }

    public interface SettingsFragmentListener {
         void onSettingSelected(long id);
    }
}
