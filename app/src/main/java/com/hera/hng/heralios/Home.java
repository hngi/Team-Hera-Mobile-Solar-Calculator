package com.hera.hng.heralios;

import android.app.SearchManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.hera.hng.heralios.R;

import java.sql.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Class for the Cients tab
 **/

public class Home extends Fragment implements AdapterView.OnItemSelectedListener {
    FloatingActionButton newCalculation;
    HomeListAdapter adapter;
    List<Session> sessions;
    DatabaseHelper helper;
    ListView list;
    TextView message;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        helper = new DatabaseHelper(this.getContext());

        helper.insertElectronics();
        sessions = helper.getAllSessions();

        adapter = new HomeListAdapter(this.getActivity(), sessions);

        list = view.findViewById(R.id.calc_list);
        message = view.findViewById(R.id.tv_message);

        if(sessions.size() ==0){
            message.setVisibility(View.VISIBLE);
        }else {
            message.setVisibility(View.GONE);
        }
        list.setAdapter(adapter);

        list.setSelection(sessions.size()-1);
        newCalculation = (FloatingActionButton) view.findViewById(R.id.fb_addCalculation);
        newCalculation.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), NewCalculation.class);
                getActivity().startActivity(intent);
                getActivity().finish();
            }
        });

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Session session = sessions.get(position);
                Intent intent = new Intent(getActivity(), SelectedEntry.class);
                intent.putExtra("session_no", ""+session.getId());
                startActivity(intent);
                getActivity().finish();
            }
        });

        list.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
                                           int pos, long id) {
                // TODO Auto-generated method stub
                final Session session = sessions.get(pos);
                AlertDialog.Builder builder1 = new AlertDialog.Builder(getContext());
                builder1.setMessage("Delete S"+session.getId());
                builder1.setCancelable(true);

                builder1.setPositiveButton(
                        "Yes",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                //

                                helper.deleteSession(session.getId());
                                sessions.remove(session);

                                list.requestLayout();
                                adapter.notifyDataSetChanged();
                                dialog.cancel();
                            }
                        });

                builder1.setNegativeButton(
                        "No",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

                AlertDialog alert11 = builder1.create();
                alert11.show();

                return true;
            }
        });


        return view;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // On selecting a spinner item

    }
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub
    }


}
