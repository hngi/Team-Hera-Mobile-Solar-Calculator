package com.hera.hng.heralios;

/**
 * Created by Oluwajuwon on 26/09/2019.
 */

public class Entry {
    private String electronic_name;
    private String power_usage;
    private String time;

    public Entry(String electonic_name, String power_usage, String time){
        this.electronic_name = electonic_name;
        this.power_usage = power_usage;
        this.time = time;
    }

    public String getElectronicName(){
        return electronic_name;
    }

    public String getPowerUsage(){
        return power_usage;
    }

    public String getTime(){
        return time;
    }
}
