package com.hera.hng.heralios;

import android.database.DataSetObserver;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Oluwajuwon on 27/09/2019.
 */

public class ListEditAdapter extends BaseAdapter {
    AppCompatActivity context;
    Entry nEntry;
    List<Entry> entry;
    String power_usage;
    String duration_text;
    String activityCalling;
    // private final String[] electronic;
    //private final String[] power;
    //private final Integer[] time;

    public ListEditAdapter(AppCompatActivity context, List<Entry> entry, String activityCalling) {
        // TODO Auto-generated constructor stub

        this.context=context;
        this.entry=entry;
        this.activityCalling=activityCalling;
    }
    @Override
    public boolean areAllItemsEnabled() {
        return false;
    }
    @Override
    public boolean isEnabled(int position) {
        return true;
    }
    @Override
    public void registerDataSetObserver(DataSetObserver observer) {
    }
    @Override
    public void unregisterDataSetObserver(DataSetObserver observer) {
    }
    @Override
    public int getCount() {
        return entry.size();
    }
    @Override
    public Object getItem(int position) {
        return position;
    }
    @Override
    public long getItemId(int position) {
        return position;
    }
    @Override
    public boolean hasStableIds() {
        return false;
    }

    public void replaceData(List<Entry> list) {
        entry.clear();
        entry =  list;
        notifyDataSetChanged();
    }

    public View getView(final int position, View view, ViewGroup parent) {

        if(view==null){
            final LayoutInflater inflater=context.getLayoutInflater();
            view=inflater.inflate(R.layout.list_edit_elec, null);

           // if(activityCalling.equalsIgnoreCase("newcalculation")) {
                 TextView electronicText = (TextView) view.findViewById(R.id.tv_electronic);
                 TextView powerText = (TextView) view.findViewById(R.id.tv_power);
                 TextView durationText = (TextView) view.findViewById(R.id.tv_time);
                ImageButton editButton = (ImageButton) view.findViewById(R.id.img_edit);

                nEntry = entry.get(position);

                String electronic_name=nEntry.getElectronicName();
                power_usage = nEntry.getPowerUsage();
                duration_text = nEntry.getTime();

                electronicText.setText(electronic_name);
                powerText.setText(power_usage);
                durationText.setText(duration_text);



                if(activityCalling.equalsIgnoreCase("newcalculation")){
                editButton.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        FragmentManager fragmentManager = context.getSupportFragmentManager();
                        EditDialog dialog = new EditDialog();
                        Bundle args = new Bundle();
                        args.putString("class", "newcalculation");
                        int spinnerPos = Integer.parseInt(nEntry.getElectronicId()) - 1;
                        args.putString("edit", "edit");
                        args.putInt("electronic_id", spinnerPos);
                        args.putString("power", power_usage);
                        args.putString("duration", duration_text);
                        args.putInt("entry_id", nEntry.getId());
                        args.putInt("entryPosition",position);
                        dialog.setArguments(args);
                        //dialog.setCancelable(false);
                        dialog.show(fragmentManager, "Dialog");
                    }
                });
             }else if(activityCalling.equalsIgnoreCase("editcalculation")){
                    editButton.setOnClickListener(new View.OnClickListener() {

                        @Override
                        public void onClick(View v) {
                            FragmentManager fragmentManager = context.getSupportFragmentManager();
                            EditDialog dialog = new EditDialog();
                            Bundle args = new Bundle();
                            args.putString("class", "editcalculation");
                            int spinnerPos = Integer.parseInt(nEntry.getElectronicId()) - 1;
                            args.putInt("electronic_id", spinnerPos);
                            args.putString("power", power_usage);
                            args.putString("duration", duration_text);
                            args.putInt("entry_id", nEntry.getId());
                            args.putInt("entryPosition",position);
                            dialog.setArguments(args);
                            Log.i("apples", "position:"+position);
                            //dialog.setCancelable(false);
                            dialog.show(fragmentManager, "Dialog");
                        }
                    });

                }
        }

        return view;
    }



    @Override
    public int getItemViewType(int position) {
        return position;
    }
    @Override
    public int getViewTypeCount() {
        return 1;
    }
    @Override
    public boolean isEmpty() {
        return false;
    }

    /*

    public void showCustomDialog() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inf = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inf.inflate(R.layout.edit_dialog, null);
        builder.setView(view);
        final EditText et = (EditText) view.findViewById(R.id.id_student_email_edittext);
        final EditText nameEditText = (EditText) view.findViewById(R.id.id_student_name_edittext);
        final EditText et = (EditText) view.findViewById(R.id.id_student_email_edittext);
        final TextView radioGroup = (TextView) view.findViewById(R.id.student_gender_group);
        final TextView radiomale = (TextView) view.findViewById(R.id.gender_male);
        final TextView radioFemale = (TextView) view.findViewById(R.id.gender_female);

        builder.setPositiveButton("Ok", new OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                //Click listner
            }
        });
        builder.setNegativeButton("cancel", new OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                //click listner.
            }
        });
        builder.show();
    }*/

}

