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
import android.widget.AdapterView;
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
    Button ok;
    Spinner spinner;
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

        //power.setClickable(false);

        final List<Electronic> electronics = helper.getAllElectronics();

        ElectronicsSpinnerAdapter adapter = new ElectronicsSpinnerAdapter(this.getContext(), electronics);

        spinner.setAdapter(adapter);


        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                Electronic selectedItem = (Electronic)parent.getItemAtPosition(position);
                Log.i("apple", "Selected Spinner: "+selectedItem);
                for(Electronic electronic : electronics){
                    if(selectedItem.equals(electronic)){
                        power.setText(electronic.getPowerUsage());
                    }
                }
            } // to close the onItemSelected
            public void onNothingSelected(AdapterView<?> parent)
            {

            }
        });

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
                        Electronic selected = (Electronic)spinner.getSelectedItem();


                    for (Electronic elect : electronics) {
                                if (elect.equals(selected)) {
                                    entry.setElectronic(elect);
                                }
                            }
                        entry.setTime(t_time);
                        entry.setID(entryId);
                        entry.setEntryPowerUsage(t_power);

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
                        Electronic selected = (Electronic) spinner.getSelectedItem();


                            for (Electronic elect : electronics) {
                                if (elect.equals(selected)) {
                                    elect.getPowerUsage();
                                    entry.setElectronic(elect);
                                }
                            }

                        entry.setTime(t_time);
                        entry.setEntryPowerUsage(t_power);



                        if(edited.equalsIgnoreCase("edit")) {
                            List<Entry> temp = NewCalculation.entry;
                            temp.set(entryPosition, entry);

                            for(Entry a:temp){
                                Log.i("barfs", a.getElectronicName());
                                Log.i("barfs", a.getEntryPowerUsage());
                                Log.i("barfs", a.getTime());
                            }

                            NewCalculation newCalculation = (NewCalculation) getActivity();
                            newCalculation.updateList(temp);
                        }else {
                            NewCalculation newCalculation = (NewCalculation) getActivity();
                            NewCalculation.entry.add(entry);

                            List<Entry> temp = NewCalculation.entry;

                            Log.i("apple", "I am here");
                            newCalculation.addList(temp);
                           // newCalculation.adapter.notifyDataSetChanged();
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

}
