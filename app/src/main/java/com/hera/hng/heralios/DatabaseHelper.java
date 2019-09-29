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
    private static final int DATABASE_VERSION = 1;

    //TABLE NAMES
    private static final String TABLE_ELECTRONICS = "electronics";
    private static final String TABLE_CALC_SESSION = "calculation_session";
    private static final String TABLE_SESSION_ENTRIES = "session_entries";

    //COLUMN NAMES
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
    public static final String SESSION_ID = "session_id";


    /*CREATE TABLE Electronics ( id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT);*/
    private static final String CREATE_TABLE_ELECTRONICS = "CREATE TABLE "
            + TABLE_ELECTRONICS + "(" + ID_COLUMN
            + " INTEGER PRIMARY KEY AUTOINCREMENT," + NAME_COLUMN + " TEXT );";

    /*CREATE TABLE Calculation_Session ( id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT);*/
    private static final String CREATE_TABLE_CALC_SESSION = "CREATE TABLE "
            + TABLE_CALC_SESSION + "(" + ID_COLUMN
            + " INTEGER PRIMARY KEY AUTOINCREMENT," + DATE + " TEXT," + TOTAL_POWER + " TEXT," + BATTERY_SIZE
            + " TEXT," + SOLAR_PANEL_SIZE + " TEXT,"+ INVERTER_POWER +" TEXT );";

    /*CREATE TABLE Session_Entries ( id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT);*/
    private static final String CREATE_TABLE_SESSION_ENTRIES = "CREATE TABLE "
            + TABLE_SESSION_ENTRIES + "(" + ID_COLUMN
            + " INTEGER PRIMARY KEY AUTOINCREMENT," + ELECTRONICS_ID + " TEXT,"
            + POWER_USAGE + " TEXT," + TIME + " TEXT," + SESSION_ID +" TEXT );";


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

            List<String> electronics = new ArrayList<>();
            electronics.add("Televison");
            electronics.add("Refigerator");
            electronics.add("AirConditioner");
            electronics.add("Oven");
            electronics.add("Washing Machine");
            electronics.add("Piano");
            electronics.add("Water Pump");
            electronics.add("Electric Cooker");
            electronics.add("Fan");


            for (int i = 0; i < electronics.size(); i++) {
                ContentValues addElectronic = new ContentValues();
                addElectronic.put(NAME_COLUMN, electronics.get(i));
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
                addEntry.put(POWER_USAGE, entry.getPowerUsage());
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
                entry.setPowerUsage(cursor.getString(cursor.getColumnIndex(POWER_USAGE)));
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
            addEntry.put(POWER_USAGE, entry.getPowerUsage());
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
