package com.hera.hng.heralios;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.DatabaseUtils.InsertHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Oluwajuwon on 27/09/2019.
 */

public class DatabaseHelper extends SQLiteOpenHelper {
    private static String DATABASE_NAME = "hereliosDB";
    private static final int DATABASE_VERSION = 4;

    //TABLE NAMES
    private static final String TABLE_ELECTRONICS = "electronics";
    private static final String TABLE_CALC_SESSION = "calculation_session";
    private static final String TABLE_SESSION_ENTRIES = "session_entries";

    //COLUMN NAMES
    /*
    public static final String ID_COLUMN = "id";
    public static final String NAME_COLUMN = "name";
    public static final String ELECTRONICS_ID= "electronic_id";
    public static final String POWER_USAGE = "power_usage";
    public static final String TOTAL_POWER = "total_power";
    public static final String BATTERY_SIZE = "battery_size";
    public static final String INVERTER_POWER = "inverter_power";
    public static final String DATE = "date";
    public static final String SOLAR_PANEL_SIZE = "solar_panel_size";
    public static final String TIME = "time";
    public static final String SESSION_ID = "session_id";*/

    public static final String ID_COLUMN = "id";
    public static final String NAME_COLUMN = "name";
    public static final String ELECTRONICS_ID= "electronic_id";
    public static final String ELECTRONICS_POWER_USAGE = "power_usage";
    public static final String TOTAL_POWER = "total_power";
    public static final String BATTERY_SIZE = "battery_size";
    public static final String INVERTER_POWER = "inverter_power";
    public static final String DATE = "date";
    public static final String SOLAR_PANEL_SIZE = "solar_panel_size";
    public static final String TIME = "time";
    public static final String SESSION_ID = "session_id";

    /*CREATE TABLE Electronics ( id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT);*/
    private static final String CREATE_TABLE_ELECTRONICS = "CREATE TABLE "
            + TABLE_ELECTRONICS + "(" + ID_COLUMN
            + " INTEGER PRIMARY KEY AUTOINCREMENT," + NAME_COLUMN + " TEXT," + ELECTRONICS_POWER_USAGE + " TEXT );";

    /*CREATE TABLE Calculation_Session ( id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT);*/
    private static final String CREATE_TABLE_CALC_SESSION = "CREATE TABLE "
            + TABLE_CALC_SESSION + "(" + ID_COLUMN
            + " INTEGER PRIMARY KEY AUTOINCREMENT," + DATE + " TEXT," + TOTAL_POWER + " TEXT," + BATTERY_SIZE
            + " TEXT," + SOLAR_PANEL_SIZE + " TEXT,"+ INVERTER_POWER +" TEXT );";

    /*CREATE TABLE Session_Entries ( id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT);*/


    private static final String CREATE_TABLE_SESSION_ENTRIES = "CREATE TABLE "
            + TABLE_SESSION_ENTRIES + "(" + ID_COLUMN
            + " INTEGER PRIMARY KEY AUTOINCREMENT," + ELECTRONICS_ID + " TEXT,"
            + ELECTRONICS_POWER_USAGE + " TEXT," + TIME + " TEXT," + SESSION_ID +" TEXT );";


    /*
    private static final String CREATE_TABLE_SESSION_ENTRIES = "CREATE TABLE "
            + TABLE_SESSION_ENTRIES + "(" + ID_COLUMN
            + " INTEGER PRIMARY KEY AUTOINCREMENT," + ELECTRONICS_ID + " TEXT,"
            + TIME + " TEXT," + SESSION_ID +" TEXT );";*/

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

       // Log.d("table", CREATE_TABLE_STUDENTS);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_ELECTRONICS);
        db.execSQL(CREATE_TABLE_CALC_SESSION);
        db.execSQL(CREATE_TABLE_SESSION_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        /*
        db.execSQL("DROP TABLE IF EXISTS '" + TABLE_USER + "'");
        db.execSQL("DROP TABLE IF EXISTS '" + TABLE_USER_HOBBY + "'");
        db.execSQL("DROP TABLE IF EXISTS '" + TABLE_USER_CITY + "'");
        onCreate(db);*/
    }

    public void insertElectronics(){
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery("SELECT count(*) FROM "+TABLE_ELECTRONICS, null);
        cursor.moveToFirst();
        if (cursor.getInt(0) == 0) {

            List<Electronic> electronics = new ArrayList<>();
            Electronic electronic_one = new Electronic();
            electronic_one.setName("TV - LCD");
            electronic_one.setPowerUsage("150");

            Electronic electronic_two = new Electronic();
            electronic_two.setName("TV - Plasma");
            electronic_two.setPowerUsage("200");

            Electronic electronic_three = new Electronic();
            electronic_three.setName("Satelite Dish");
            electronic_three.setPowerUsage("25");

            Electronic electronic_four= new Electronic();
            electronic_four.setName("DVD Player");
            electronic_four.setPowerUsage("15");

            Electronic electronic_five = new Electronic();
            electronic_five.setName("Cable Box");
            electronic_five.setPowerUsage("35");

            Electronic electronic_six = new Electronic();
            electronic_six.setName("BluRay Player");
            electronic_six.setPowerUsage("15");

            Electronic electronic_seven = new Electronic();
            electronic_seven.setName("LED Bulb - 40 Watt equiv.");
            electronic_seven.setPowerUsage("10");

            Electronic electronic_eight = new Electronic();
            electronic_eight.setName("LED Bulb - 60 Watt equiv.");
            electronic_eight.setPowerUsage("13");

            Electronic electronic_nine = new Electronic();
            electronic_nine.setName("LED Bulb - 100 Watt equiv.");
            electronic_nine.setPowerUsage("16");

            Electronic electronic_ten= new Electronic();
            electronic_ten.setName("Laptop");
            electronic_ten.setPowerUsage("100");

            Electronic electronic_eleven = new Electronic();
            electronic_eleven.setName("Printer");
            electronic_eleven.setPowerUsage("100");

            Electronic electronic_twelve = new Electronic();
            electronic_twelve.setName("Smart Phone - Recharge");
            electronic_twelve.setPowerUsage("6");

            Electronic electronic_thirteen = new Electronic();
            electronic_thirteen.setName("Tablet - Recharge");
            electronic_thirteen.setPowerUsage("8");

            Electronic electronic_fourteen = new Electronic();
            electronic_fourteen.setName("Desktop Computer(Standard)");
            electronic_fourteen.setPowerUsage("200");

            Electronic electronic_fifteen = new Electronic();
            electronic_fifteen.setName("Desktop Computer(Gaming)");
            electronic_fifteen.setPowerUsage("500");

            Electronic electronic_sixteen = new Electronic();
            electronic_sixteen.setName("Freezer - Upright - 15 cu.ft.");
            electronic_sixteen.setPowerUsage("1240");

            Electronic electronic_seventeen = new Electronic();
            electronic_seventeen.setName("Freezer - Chest - 15 cu.ft.");
            electronic_seventeen.setPowerUsage("1080");

            Electronic electronic_eighteen = new Electronic();
            electronic_eighteen.setName("Kettle - Electric");
            electronic_eighteen.setPowerUsage("1200");

            Electronic electronic_nineteen = new Electronic();
            electronic_nineteen.setName("Oven - Electric");
            electronic_nineteen.setPowerUsage("1200");

            Electronic electronic_twenty = new Electronic();
            electronic_twenty.setName("Microwave");
            electronic_twenty.setPowerUsage("1000");

            Electronic electronic_twentyone = new Electronic();
            electronic_twentyone.setName("Toaster");
            electronic_twentyone.setPowerUsage("850");

            Electronic electronic_twentytwo = new Electronic();
            electronic_twentytwo.setName("Ceiling Fan");
            electronic_twentytwo.setPowerUsage("120");

            Electronic electronic_twentythree = new Electronic();
            electronic_twentythree.setName("Central AirConditioner(10000 BTU NA)");
            electronic_twentythree.setPowerUsage("900");

            Electronic electronic_twentyfour = new Electronic();
            electronic_twentyfour.setName("Clothes Dryer - Electric");
            electronic_twentyfour.setPowerUsage("3000");

            Electronic electronic_twentyfive = new Electronic();
            electronic_twentyfive.setName("Washing Machine");
            electronic_twentyfive.setPowerUsage("800");

            Electronic electronic_twentysix = new Electronic();
            electronic_twentysix.setName("Iron");
            electronic_twentysix.setPowerUsage("1200");

            Electronic electronic_twentyseven = new Electronic();
            electronic_twentyseven.setName("Hair Dryer");
            electronic_twentyseven.setPowerUsage("1500");

            Electronic electronic_twentyeight = new Electronic();
            electronic_twentyeight.setName("Vacuum");
            electronic_twentyeight.setPowerUsage("1000");

            Electronic electronic_twentynine = new Electronic();
            electronic_twentynine.setName("Sewing Machine");
            electronic_twentynine.setPowerUsage("100");

            Electronic electronic_thirty = new Electronic();
            electronic_thirty.setName("Well Pump - 1/3 1HP");
            electronic_thirty.setPowerUsage("750");

            electronics.add(electronic_one);
            electronics.add(electronic_two);
            electronics.add(electronic_three);
            electronics.add(electronic_four);
            electronics.add(electronic_five);
            electronics.add(electronic_six);
            electronics.add(electronic_seven);
            electronics.add(electronic_eight);
            electronics.add(electronic_nine);
            electronics.add(electronic_ten);
            electronics.add(electronic_eleven);
            electronics.add(electronic_twelve);
            electronics.add(electronic_thirteen);
            electronics.add(electronic_fourteen);
            electronics.add(electronic_fifteen);
            electronics.add(electronic_sixteen);
            electronics.add(electronic_seventeen);
            electronics.add(electronic_eighteen);
            electronics.add(electronic_nineteen);
            electronics.add(electronic_twenty);
            electronics.add(electronic_twentyone);
            electronics.add(electronic_twentytwo);
            electronics.add(electronic_twentythree);
            electronics.add(electronic_twentyfour);
            electronics.add(electronic_twentyfive);
            electronics.add(electronic_twentysix);
            electronics.add(electronic_twentyseven);
            electronics.add(electronic_twentyeight);
            electronics.add(electronic_twentynine);
            electronics.add(electronic_thirty);


            for (int i = 0; i < electronics.size(); i++) {
                ContentValues addElectronic = new ContentValues();
                addElectronic.put(NAME_COLUMN, electronics.get(i).getName());
                addElectronic.put(ELECTRONICS_POWER_USAGE, electronics.get(i).getPowerUsage());
                db.insert(TABLE_ELECTRONICS, null, addElectronic);
            }
        }

    }

    public List<Electronic> getAllElectronics(){
        List<Electronic> electronics = new ArrayList<>();

        String selectQuery = "SELECT  * FROM " + TABLE_ELECTRONICS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);
        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {
                Electronic electronic = new Electronic();
                electronic.setId(c.getInt(c.getColumnIndex(ID_COLUMN)));
                electronic.setName(c.getString(c.getColumnIndex(NAME_COLUMN)));
                electronic.setPowerUsage(c.getString(c.getColumnIndex(ELECTRONICS_POWER_USAGE)));

                electronics.add(electronic);
            } while (c.moveToNext());
        }
        return electronics;
    }

    public void addElectronic(String electronic){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues addElectronic = new ContentValues();
        addElectronic.put(NAME_COLUMN, electronic);

        db.insert(TABLE_ELECTRONICS, null, addElectronic);
    }

    public int insertSession(Session session){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues addSession = new ContentValues();
        addSession.put(DATE, session.getDate());
        addSession.put(SOLAR_PANEL_SIZE, session.getSolarPanelSize());
        addSession.put(TOTAL_POWER, session.getTotalPowerUsage());
        addSession.put(BATTERY_SIZE, session.getBatterySize());
        addSession.put(INVERTER_POWER, session.getInverterPower());

        long id = db.insertWithOnConflict(TABLE_CALC_SESSION, null, addSession, SQLiteDatabase.CONFLICT_IGNORE);
        Long prevId = new Long(id);
        int newId =prevId.intValue();
        return newId;
    }

    public Session getSession(int session_id){
        Session session = new Session();
        SQLiteDatabase db = this.getWritableDatabase();

        String whereclause = " WHERE "+ ID_COLUMN+" = "+session_id;
        Cursor cursor = db.rawQuery("SELECT * FROM "+TABLE_CALC_SESSION+whereclause, null);
        if (cursor.moveToFirst()){
            do {
                // Passing values
                session.setId(session_id);
                session.setDate(cursor.getString(cursor.getColumnIndex(DATE)));
                session.setTotalPowerUsage(cursor.getString(cursor.getColumnIndex(TOTAL_POWER)));
                session.setSolarPanelSize(cursor.getString(cursor.getColumnIndex(SOLAR_PANEL_SIZE)));
                session.setInverterPower(cursor.getString(cursor.getColumnIndex(INVERTER_POWER)));
                session.setBatterySize(cursor.getString(cursor.getColumnIndex(BATTERY_SIZE)));
                //session.setTotalPowerUsage(" ");

            } while(cursor.moveToNext());
        }
        cursor.close();
        db.close();

        return session;
    }

    public void addEntries(List<Entry> entries, int session_id){
        SQLiteDatabase db = this.getWritableDatabase();

        for (int i = 0; i < entries.size(); i++) {
            Entry entry = entries.get(i);
            entry.setSessionID(""+session_id);
            ContentValues addEntry = new ContentValues();
            addEntry.put(ELECTRONICS_ID, entry.getElectronicId());
            addEntry.put(ELECTRONICS_POWER_USAGE, entry.getEntryPowerUsage());
            addEntry.put(TIME, entry.getTime());
            addEntry.put(SESSION_ID, entry.getSession_id());

            db.insert(TABLE_SESSION_ENTRIES, null, addEntry);
        }

    }

    public  List<Entry> getEntries(String session_no){
        List<Entry> entries = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();

        List<Electronic> electronics = getAllElectronics();

        String whereclause = " WHERE "+ SESSION_ID+" = ?";
        Cursor cursor = db.rawQuery("SELECT * FROM "+TABLE_SESSION_ENTRIES+whereclause, new String[]{session_no});
        if (cursor.moveToFirst()){
            do {
                Entry entry = new Entry();
                // Passing values
                entry.setID(cursor.getInt(cursor.getColumnIndex(ID_COLUMN)));
                entry.setSessionID(cursor.getString(cursor.getColumnIndex(SESSION_ID)));
                entry.setEntryPowerUsage(cursor.getString(cursor.getColumnIndex(ELECTRONICS_POWER_USAGE)));
                entry.setTime(cursor.getString(cursor.getColumnIndex(TIME)));
                String electronic_id = cursor.getString(cursor.getColumnIndex(ELECTRONICS_ID));
                for(Electronic elec: electronics){
                    if(elec.getId() == Integer.parseInt(electronic_id)){
                        entry.setElectronic(elec);
                    }
                }
                entries.add(entry);
            } while(cursor.moveToNext());
        }
        cursor.close();
        db.close();

        return entries;
    }

    public List<Session> getAllSessions(){
        List<Session> sessions = new ArrayList<>();

        String selectQuery = "SELECT  * FROM " + TABLE_CALC_SESSION;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);
        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {
                Session session = new Session();
                session.setId(c.getInt(c.getColumnIndex(ID_COLUMN)));
                session.setDate(c.getString(c.getColumnIndex(DATE)));
                session.setTotalPowerUsage(c.getString(c.getColumnIndex(TOTAL_POWER)));

                sessions.add(session);
            } while (c.moveToNext());
        }
        return sessions;
    }

    public void updateSession(Session session){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues addSession = new ContentValues();
        addSession.put(DATE, session.getDate());
        addSession.put(SOLAR_PANEL_SIZE, session.getSolarPanelSize());
        addSession.put(TOTAL_POWER, session.getTotalPowerUsage());
        addSession.put(BATTERY_SIZE, session.getBatterySize());
        addSession.put(INVERTER_POWER, session.getInverterPower());

        db.update(TABLE_CALC_SESSION, addSession, ID_COLUMN + " = ?", new String[]{String.valueOf(session.getId())});
    }

    public void updateEntryList(List<Entry> entries){
        SQLiteDatabase db = this.getWritableDatabase();

        for (int i = 0; i < entries.size(); i++) {
            Entry entry = entries.get(i);
            ContentValues addEntry = new ContentValues();
            addEntry.put(ELECTRONICS_ID, entry.getElectronicId());
            addEntry.put(ELECTRONICS_POWER_USAGE, entry.getEntryPowerUsage());
            addEntry.put(TIME, entry.getTime());
            //addEntry.put(SESSION_ID, entry.getSession_id());

            Log.i("apple", entry.getElectronicName());
            Log.i("apple", entry.getPowerUsage());
            Log.i("apple", entry.getTime());

            db.update(TABLE_SESSION_ENTRIES, addEntry, ID_COLUMN + " = ?", new String[]{String.valueOf(entry.getId())});
        }

    }

    public void deleteSession(int id) {

        // delete row in students table based on id
        SQLiteDatabase db = this.getWritableDatabase();

        //deleting from users table
        db.delete(TABLE_CALC_SESSION, ID_COLUMN + " = ?",new String[]{String.valueOf(id)});

    }

    public void deleteEntry(int id) {

        // delete row in students table based on id
        SQLiteDatabase db = this.getWritableDatabase();

        //deleting from users table
        db.delete(TABLE_SESSION_ENTRIES, ID_COLUMN + " = ?",new String[]{String.valueOf(id)});

    }

}
