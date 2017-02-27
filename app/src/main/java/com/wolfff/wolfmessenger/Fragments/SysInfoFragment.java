package com.wolfff.wolfmessenger.Fragments;

import android.app.ListFragment;
import android.os.Bundle;
import android.widget.ArrayAdapter;

import com.wolfff.wolfmessenger.Tools.DeviceManager;

/**
 * Created by wolff on 19.09.2016.
 */
public class SysInfoFragment extends ListFragment {
    String data[] = new String[] { "1", "2", "3", "-------------------------" };

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        DeviceManager deviceManager = new DeviceManager();

        data[0] = "SIM 1 number - "+deviceManager.getSim1Number(getActivity().getApplicationContext());
        data[1] = "Device ID - "+deviceManager.getDeviceId(getActivity().getApplicationContext());
        data[2] = "SIM serial № - "+deviceManager.getSimSerialNumber(getActivity().getApplicationContext());
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(),
                android.R.layout.simple_list_item_1, data);
        setListAdapter(adapter);
    }
}
