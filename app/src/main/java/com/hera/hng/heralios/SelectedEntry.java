package com.hera.hng.heralios;

import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class SelectedEntry extends AppCompatActivity {
    ListView listView;
    List<Entry> entry=new ArrayList<Entry>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selected_entry);

        Entry nEntry = new Entry("Television","200","5");
        entry.add(nEntry);

        MyListAdapter adapter=new MyListAdapter(this, entry);
        listView=(ListView)findViewById(R.id.listView);
        listView.setAdapter(adapter);

        /*
        listView = findViewById(R.id.list_view);

        Resources r = this.getBaseContext().getResources();
        int eight = (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                8,
                r.getDisplayMetrics()
        );

        int seven = (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                7,
                r.getDisplayMetrics()
        );

        int fifteen = (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                15,
                r.getDisplayMetrics()
        );



        LinearLayout ll = new LinearLayout(this);
        ll.setOrientation(LinearLayout.HORIZONTAL);
        listView.addView(ll);
        ll.setPadding(fifteen,fifteen,fifteen,fifteen);

        ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) ll.getLayoutParams();
        if (params == null) {
            params = new ViewGroup.MarginLayoutParams(ll.getWidth(), ll.getHeight());
        }
        params.leftMargin = 8; params.rightMargin = 8;
        ll.setLayoutParams(params);

        //Typeface martelSans = Typeface.create("@font/martel_sans_extralight", Typeface.NORMAL);
        //float myTextSize = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 18F, this.getBaseContext().getResources().getDisplayMetrics());

        Typeface martelSans = ResourcesCompat.getFont(this, R.font.martel_sans_extralight);

        TableRow.LayoutParams one = new TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 1f);
        TableRow.LayoutParams three = new TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 1.5f);


        TextView ta = new TextView(this);
        ta.setTextSize(TypedValue.COMPLEX_UNIT_SP,14);
        ta.setLayoutParams(three);
        ta.setTypeface(martelSans);
        ta.setText("Electronic");
        ta.setTextColor(Color.BLACK);
        //ta.setGravity(Gravity.CENTER);
        ta.setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);

        TextView tb = new TextView(this);
        tb.setTextSize(TypedValue.COMPLEX_UNIT_SP,14);
        tb.setLayoutParams(one);
        tb.setTypeface(martelSans);
        tb.setText("200");
        tb.setTextColor(Color.BLACK);
        tb.setGravity(Gravity.CENTER);
        tb.setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);


        TextView tc = new TextView(this);
        tc.setTextSize(TypedValue.COMPLEX_UNIT_SP,14);
        tc.setLayoutParams(one);
        tc.setTypeface(martelSans);
        tc.setText("10");
        tc.setTextColor(Color.BLACK);
        tc.setGravity(Gravity.CENTER);
        tc.setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);


        ll.addView(ta);
        ll.addView(tb);
        ll.addView(tc);*/
    }
}
