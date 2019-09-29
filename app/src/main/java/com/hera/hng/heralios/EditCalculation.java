package com.hera.hng.heralios;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class EditCalculation extends AppCompatActivity {
    ListView listView;
    static List<Entry> entries = new ArrayList<>();
    List<Entry> temp = new ArrayList<>();
    Button done;
     ListEditAdapter adapter;
    ListEditAdapter adapterb;
    DatabaseHelper helper;
    TextView sessionNumber;
    String session_no;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_calculation);
        entries.clear();
        Intent intent = getIntent();
        session_no = intent.getStringExtra("session_no");
        helper = new DatabaseHelper(this);

        entries.addAll(helper.getEntries(session_no));

        done = findViewById(R.id.btn_done);

        sessionNumber = findViewById(R.id.textViewa);
        sessionNumber.setText("S"+session_no);
/*
        Electronic electronic = new Electronic();
        electronic.setName("Television");

        Entry nEntry = new Entry();
        nEntry.setElectronic(electronic);
        nEntry.setPowerUsage("200");
        nEntry.setTime("5");        entry.add(nEntry);*/

        adapter=new ListEditAdapter(this, entries, "EditCalculation");
        listView=(ListView)findViewById(R.id.lv_listViewb);
        listView.setAdapter(adapter);

        listView.setClickable(true);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
                                           int pos, long id) {
                // TODO Auto-generated method stub
                final Entry entry = entries.get(pos);
                AlertDialog.Builder builder1 = new AlertDialog.Builder(getBaseContext());
                builder1.setMessage("Delete this entry?");
                builder1.setCancelable(true);

                builder1.setPositiveButton(
                        "Yes",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                //

                                helper.deleteEntry(entry.getId());
                                entries.remove(entry);

                                listView.requestLayout();
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


        done.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(entries.size()>0){
                    Date c = Calendar.getInstance().getTime();
                    System.out.println("Current time => " + c);

                    SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
                    String formattedDate = df.format(c);

                    Session session = new Session();
                    Calculations calculation = new Calculations();

                    double totalPower = 0;
                    double inverterPower= 0;
                    double batterySize=0;
                    double solarPanelSize = 0;
                    for(Entry e: entries){

                        double cPower = calculation.calculateSolarPowerUsage(Double.parseDouble(e.getPowerUsage()), Double.parseDouble(e.getTime()));
                        totalPower += cPower;
                        //inverterSize += Double.parseDouble(e.getPowerUsage())
                    }
                    inverterPower = calculation.inverterPower(entries);
                    batterySize =  calculation.batterySize(totalPower);
                    //solarPanelSize = calculation.solarPanelSize();

                    session.setId(Integer.parseInt(session_no));
                    session.setBatterySize(""+batterySize);
                    session.setDate(formattedDate);
                    session.setInverterPower(""+inverterPower);
                    session.setSolarPanelSize(totalPower+"/"+"sunlight(hrs)");
                    session.setTotalPowerUsage(""+totalPower);

                    helper.updateSession(session);

                    helper.updateEntryList(entries);

                    Intent intent = new Intent(getBaseContext(), SelectedEntry.class);
                    intent.putExtra("session_no", ""+session_no);
                    startActivity(intent);
                }else{
                    finish();

                }
            }
        });


    }

    @Override
    public void onResume(){
        super.onResume();
        this.adapter.notifyDataSetChanged();
    }

    public void updateList(List<Entry> list){
        adapter=new ListEditAdapter(this, list, "EditCalculation");
        listView.setAdapter(adapter);

        //entries.clear();
        entries=list;
        for(Entry a:entries){
            Log.i("apples", a.getElectronicName());
            Log.i("apples", a.getPowerUsage());
            Log.i("apples", a.getTime());
        }
        adapter.notifyDataSetChanged();
    }



}
