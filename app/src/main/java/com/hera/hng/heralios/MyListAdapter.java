package com.hera.hng.heralios;

import android.app.Activity;
import android.database.DataSetObserver;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Oluwajuwon on 26/09/2019.
 */

public class MyListAdapter implements ListAdapter{
    Activity context;
    List<Entry> entry;
   // private final String[] electronic;
    //private final String[] power;
    //private final Integer[] time;

    public MyListAdapter(Activity context, List<Entry> entry) {
        // TODO Auto-generated constructor stub

        this.context=context;
        this.entry=entry;
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

    public View getView(int position, View view, ViewGroup parent) {
        Entry nEntry = entry.get(position);

        if(view==null){
            LayoutInflater inflater=context.getLayoutInflater();
            view=inflater.inflate(R.layout.my_list, null);
            TextView electronicText = (TextView) view.findViewById(R.id.tv_electronic);
            TextView powerText = (TextView) view.findViewById(R.id.tv_power);
            TextView timeText = (TextView) view.findViewById(R.id.tv_time);

            electronicText.setText(nEntry.getElectronicName());
            powerText.setText(nEntry.getPowerUsage());
            timeText.setText(nEntry.getTime());
        }





        return view;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }
    @Override
    public int getViewTypeCount() {
        return entry.size();
    }
    @Override
    public boolean isEmpty() {
        return false;
    }
}
