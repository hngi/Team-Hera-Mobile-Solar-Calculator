package com.hera.hng.heralios;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class NewCalculation extends AppCompatActivity {
    ListView listView;
    static List<Entry> entry=new ArrayList<Entry>();
    FloatingActionButton addNew;
    Button done;
    ListEditAdapter adapter;
    DatabaseHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_calculation);

        helper = new DatabaseHelper(this);

        Electronic electronic = new Electronic();
        electronic.setName("Television");

        /*
        Entry nEntry = new Entry();
        nEntry.setElectronic(electronic);
        nEntry.setPowerUsage("200");
        nEntry.setTime("5");
        entry.add(nEntry);*/

        adapter=new ListEditAdapter(this, entry, "NewCalculation");
        listView=(ListView)findViewById(R.id.lv_listView);
        listView.setAdapter(adapter);

        addNew = (FloatingActionButton) findViewById(R.id.fb_add);
        addNew.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                FragmentManager fragmentManager = getSupportFragmentManager();
                EditDialog dialog = new EditDialog();
                //dialog.setCancelable(false);
                Bundle args = new Bundle();
                args.putString("class", "newcalculation");
                args.putString("edit", "notedit");
                dialog.setArguments(args);
                dialog.show(fragmentManager, "Dialog");
            }
        });

        done = (Button) findViewById(R.id.done);
        done.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(entry.size()>0){
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
                    for(Entry e: entry){

                        double cPower = calculation.calculateSolarPowerUsage(Double.parseDouble(e.getPowerUsage()), Double.parseDouble(e.getTime()));
                        totalPower += cPower;
                        //inverterSize += Double.parseDouble(e.getPowerUsage())
                    }
                    inverterPower = calculation.inverterPower(entry);
                    batterySize =  calculation.batterySize(totalPower);
                    //solarPanelSize = calculation.solarPanelSize();

                    session.setBatterySize(""+batterySize);
                    session.setDate(formattedDate);
                    session.setInverterPower(""+inverterPower);
                    session.setSolarPanelSize(totalPower+"/"+"sunlight(hrs)");
                    session.setTotalPowerUsage(""+totalPower);

                    int session_id = helper.insertSession(session);

                    helper.addEntries(entry, session_id);


                    Intent intent = new Intent(getBaseContext(), SelectedEntry.class);
                    intent.putExtra("session_no", ""+session_id);
                    startActivity(intent);
                    finish();
                }else{
                    Intent intent = new Intent(getBaseContext(), Main.class);
                    startActivity(intent);
                    finish();

                }
            }
        });
        entry.clear();
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onBackPressed() {
        // super.onBackPressed();
        Intent intent= new Intent(this,Main.class);
        startActivity(intent);
        finish();
        return;
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
        entry=list;
        for(Entry a:entry){
            Log.i("apples", a.getElectronicName());
            Log.i("apples", a.getPowerUsage());
            Log.i("apples", a.getTime());
        }
        adapter.notifyDataSetChanged();
    }

}
