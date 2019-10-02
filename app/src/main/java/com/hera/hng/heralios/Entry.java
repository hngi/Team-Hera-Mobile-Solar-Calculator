package com.hera.hng.heralios;

/**
 * Created by Oluwajuwon on 26/09/2019.
 */

public class Entry {
    private int id;
    private Electronic electronic;
    private String time;
    private String power_usage;
    private String session_id;

    public void setID(int id){
        this.id = id;
    }

    public int getId(){
        return id;
    }

    public void setElectronic(Electronic electronic){
        this.electronic = electronic;
    }

    public Electronic getElectronic(){
        return electronic;
    }

    public String getElectronicId(){
        String id = ""+electronic.getId();
        return id;
    }

    public String getElectronicName(){
        String name = electronic.getName();
        return name;
    }

    public void setEntryPowerUsage(String power_usage){
        this.power_usage = power_usage;
    }

    public String getEntryPowerUsage(){
        return power_usage;
    }

    public String getPowerUsage(){
        //this.power_usage = electronic.getPowerUsage();
        return power_usage;
    }

    public void setTime(String time){
        this.time = time;
    }

    public String getTime(){
        return time;
    }

    public void setSessionID(String session_id){
        this.session_id = session_id;
    }

    public String getSession_id(){
        return session_id;
    }
}
