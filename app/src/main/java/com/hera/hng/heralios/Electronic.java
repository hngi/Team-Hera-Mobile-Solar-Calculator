package com.hera.hng.heralios;

/**
 * Created by Funmipink on 27/09/2019.
 */

public class Electronic {
    private int id;
    private String name;
    private String power_usage;

    public void setId(int id){
        this.id = id;
    }

    public int getId(){
        return id;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }

    public void setPowerUsage(String power_usage){
        this.power_usage = power_usage;
    }

    public String getPowerUsage(){
        return power_usage;
    }
}
