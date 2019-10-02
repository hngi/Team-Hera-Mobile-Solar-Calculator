package com.hera.hng.heralios;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Oluwajuwon on 02/10/2019.
 */

public class ElectronicsSpinnerAdapter extends BaseAdapter implements SpinnerAdapter {
    List<Electronic> electronicList;
    Context context;

    public ElectronicsSpinnerAdapter(Context context, List<Electronic> electronicList) {
        this.electronicList = electronicList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return electronicList.size();
    }

    @Override
    public Object getItem(int position) {
        return electronicList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view =  View.inflate(context, R.layout.spinner_layout, null);
        TextView textView = (TextView) view.findViewById(R.id.tv_electronic);
        textView.setText(electronicList.get(position).getName());
        return textView;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {

        View view;
        view =  View.inflate(context, R.layout.spinner_dropdown_layout, null);
        final TextView textView = (TextView) view.findViewById(R.id.tv_electronics);
        textView.setText(electronicList.get(position).getName());

        //textView.setTextColor(Color.parseColor(colors[position]));
        //textView.setBackgroundColor(Color.parseColor(colorsback[position]));


        return view;
    }
}
