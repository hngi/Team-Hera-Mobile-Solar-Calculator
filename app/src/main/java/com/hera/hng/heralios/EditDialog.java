package com.hera.hng.heralios;

import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Oluwajuwon on 27/09/2019.
 */

public class EditDialog extends DialogFragment {
    private Listener mListener;
    Button ok;
    Spinner spinner;
    List<String> electronics_names=new ArrayList<>();
    DatabaseHelper helper;
    EditText power, time;
    Toolbar toolbar;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState){

        final View view = inflater.inflate(
                R.layout.edit_dialog, container);

        helper = new DatabaseHelper(this.getContext());

        ok = (Button) view.findViewById(R.id.btn_ok);

        power = (EditText) view.findViewById(R.id.ed_pow);
        time = (EditText) view.findViewById(R.id.ed_time);

        spinner = (Spinner) view.findViewById(R.id.sp_elec);

        final List<Electronic> electronicsList = helper.getAllElectronics();

        for(Electronic e: electronicsList){
            electronics_names.add(e.getName());
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this.getContext(), android.R.layout.simple_spinner_item, electronics_names);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        toolbar = (Toolbar) view.findViewById(R.id.my_toolbar);

        String callingClass = getArguments().getString("class");

        if(callingClass.equalsIgnoreCase("editcalculation")){
            toolbar.setBackgroundColor(Color.parseColor("#D3D3D3"));
            int electronic_id = getArguments().getInt("electronic_id");
            String t_power = getArguments().getString("power");
            String t_duration = getArguments().getString("duration");
            final int entryId = getArguments().getInt("entry_id");
            final int entryPosition = getArguments().getInt("entryPosition");

            spinner.setSelection(electronic_id);
            power.setText(t_power);
            time.setText(t_duration);

            ok.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    if(!TextUtils.isEmpty(power.getText().toString()) && !TextUtils.isEmpty(time.getText().toString())){
                        Entry entry = new Entry();

                        String t_power = power.getText().toString();
                        String t_time = time.getText().toString();
                        String selected = spinner.getSelectedItem().toString();


                    for (Electronic elect : electronicsList) {
                                if (elect.getName().equalsIgnoreCase(selected)) {
                                    entry.setElectronic(elect);
                                }
                            }
                        entry.setTime(t_time);
                        entry.setPowerUsage(t_power);
                        entry.setID(entryId);

                        List<Entry> temp = EditCalculation.entries;
                        temp.set(entryPosition, entry);

                        EditCalculation editCalculation = (EditCalculation)getActivity();
                        editCalculation.updateList(temp);


                        //EditCalculation.adapter.replaceData(temp);
                        //EditCalculation.listView.setAdapter(EditCalculation.adapter);
                        //editCalculation.adapter.notifyDataSetChanged();
                        //List<Entry> temp = EditCalculation.entries;
                        //temp.set(entryPosition, entry);


                        //EditCalculation.entries.add(entry);

                        //EditCalculation.entries

                       // editCalculation.updateList();
                       // editCalculation.adapter.updateList(temp);
                        //EditCalculation.entries.addAll(temp);
                        //EditCalculation.entries.set(entryPosition, entry);

                       // EditCalculation.entries.remove(entryPosition);
                        //editCalculation.updateList();

                        //((ArrayAdapter)EditCalculation.listView.getAdapter()).notifyDataSetChanged();


                        dismiss();
                        //getActivity().setContentView(R.layout.activity_new_calculation);

                        //update database

                    }

                }
            });

        }else {
            int electronic_id = getArguments().getInt("electronic_id");
            String t_power = getArguments().getString("power");
            String t_duration = getArguments().getString("duration");
            final int entryId = getArguments().getInt("entry_id");
            final int entryPosition = getArguments().getInt("entryPosition");

            final String edited = getArguments().getString("edit");
            spinner.setSelection(electronic_id);
            power.setText(t_power);
            time.setText(t_duration);

            ok.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {

                    if (!TextUtils.isEmpty(power.getText().toString()) && !TextUtils.isEmpty(time.getText().toString())) {
                        Entry entry = new Entry();

                        String t_power = power.getText().toString();
                        String t_time = time.getText().toString();
                        String selected = spinner.getSelectedItem().toString();


                        for (String name : electronics_names) {
                            for (Electronic elect : electronicsList) {
                                if (elect.getName().equalsIgnoreCase(selected)) {
                                    entry.setElectronic(elect);
                                }
                            }
                        }
                        entry.setTime(t_time);
                        entry.setPowerUsage(t_power);

                        if(edited.equalsIgnoreCase("edit")) {
                            List<Entry> temp = NewCalculation.entry;
                            temp.set(entryPosition, entry);

                            NewCalculation newCalculation = (NewCalculation) getActivity();
                            newCalculation.updateList(temp);
                        }else {
                            NewCalculation.entry.add(entry);
                            NewCalculation newCalculation = (NewCalculation) getActivity();
                            newCalculation.adapter.notifyDataSetChanged();
                        }

                        dismiss();
                        //getActivity().setContentView(R.layout.activity_new_calculation);

                    }
                    else{
                        Toast.makeText(getActivity(), "Please fill in all values",
                                Toast.LENGTH_LONG).show();
                    }

                }
            });

        }
        this.getDialog().setCanceledOnTouchOutside(true);

        return view;
    }

    public void setListener(Listener listener) {
        mListener = listener;
    }

    static interface Listener {
        void returnData();
    }

}
