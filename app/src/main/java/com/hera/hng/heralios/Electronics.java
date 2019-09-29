package com.hera.hng.heralios;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class Electronics extends Fragment implements AdapterView.OnItemSelectedListener {
    Button add;
    ListView electronics_list;
    List<String> electronics_names=new ArrayList<>();
    DatabaseHelper helper;
    EditText input;

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_electronics, container, false);

        electronics_list = (ListView) view.findViewById(R.id.listView2);
        input = (EditText) view.findViewById(R.id.editText);

        helper = new DatabaseHelper(this.getContext());
        helper.insertElectronics();

        List<Electronic> Es = helper.getAllElectronics();

        for(Electronic e: Es){
            electronics_names.add(e.getName());
        }

        final ArrayAdapter adapter = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_list_item_1 , electronics_names);

        electronics_list.setAdapter(adapter);

        add = view.findViewById(R.id.add);
        add.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                boolean found = false;
                if(TextUtils.isEmpty(input.getText())){
                    input.setError("Text box cannot be empty");
                }else{
                    String input_value = input.getText().toString();
                    String input_value_lower = input.getText().toString().toLowerCase();
                    for(int i=0; i<electronics_names.size();i++) {
                        String lower_electronic = electronics_names.get(i).toLowerCase();

                        if (input_value_lower.equals(lower_electronic)) {
                           found = true;
                            break;
                        }
                    }
                    if(found == true){
                        input.setError("Electronic already in list");

                    }else{
                        helper.addElectronic(input_value);
                        electronics_names.add(input_value);
                        input.setText("");
                        Toast.makeText(getActivity(), input_value + " added",
                                Toast.LENGTH_LONG).show();
                        adapter.notifyDataSetChanged();
                        electronics_list.smoothScrollToPosition(adapter.getCount() - 1);
                    }
                }
                /*
                // Perform action on click
                Intent activityChangeIntent = new Intent(getActivity(), EditCalculation.class);

                // currentContext.startActivity(activityChangeIntent);

                getActivity().startActivity(activityChangeIntent);*/
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
