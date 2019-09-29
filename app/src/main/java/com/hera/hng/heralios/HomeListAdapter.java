package com.hera.hng.heralios;

import android.app.Activity;
import android.database.DataSetObserver;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ListAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Funmipink on 27/09/2019.
 */

public class HomeListAdapter extends BaseAdapter {
    Activity context;
    List<Session> sessions;
    // private final String[] electronic;
    //private final String[] power;
    //private final Integer[] time;

    public HomeListAdapter(Activity context, List<Session> sessions){

        this.context=context;
        this.sessions=sessions;
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
        return sessions.size();
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
        Session session = sessions.get(position);

        if(view==null){
            final LayoutInflater inflater=context.getLayoutInflater();
            view=inflater.inflate(R.layout.home_list, null);
            TextView sess_no = (TextView) view.findViewById(R.id.tv_sess);
            TextView total_power = (TextView) view.findViewById(R.id.tv_total_power);
            TextView date = (TextView) view.findViewById(R.id.tv_date);

            sess_no.setText("S"+session.getId());
            total_power.setText(session.getTotalPowerUsage()+"W/H");
            date.setText(session.getDate());

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



}
